package vip.lycheer.langchain4j.config;


import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;

import dev.langchain4j.web.search.searchapi.SearchApiWebSearchEngine;
import lombok.RequiredArgsConstructor;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.lycheer.langchain4j.func.HighSum;
import vip.lycheer.langchain4j.service.Assistant;

@Configuration
@RequiredArgsConstructor
public class AssistantInit {
    final ChatLanguageModel chatLanguageModel;

    //实例化内存向量数据库
//    @Bean
//    public EmbeddingStore<TextSegment> initEmbeddingStore() {
//        return new InMemoryEmbeddingStore<>();
//    }


    @Bean
    public Assistant assistant(EmbeddingStore<TextSegment> embeddingStore, SearchApiWebSearchEngine engine) {

        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        EmbeddingStoreContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        return AiServices.builder(Assistant.class)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10)).
                contentRetriever(retriever)
                //调用 Function Calling,比较简单
                //联网搜索能力
                //.tools(new HighSum(), new WebSearchTool(engine))
                //移除联网检索
                .tools(new HighSum()).chatLanguageModel(chatLanguageModel).build();
    }


}
