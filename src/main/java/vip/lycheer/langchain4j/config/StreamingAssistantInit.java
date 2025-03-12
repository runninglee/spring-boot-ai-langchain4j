package vip.lycheer.langchain4j.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vip.lycheer.langchain4j.service.StreamingAssistant;

@Configuration
@RequiredArgsConstructor
public class StreamingAssistantInit {


    @Bean
    public StreamingAssistant streamingAssistant(EmbeddingStore<TextSegment> embeddingStore, StreamingChatLanguageModel streamingChatLanguageModel) {
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        EmbeddingStoreContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        return AiServices.builder(StreamingAssistant.class).contentRetriever(retriever).chatMemory(MessageWindowChatMemory.withMaxMessages(10))// 保留最近10轮对话
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10)).streamingChatLanguageModel(streamingChatLanguageModel).build();

    }

}
