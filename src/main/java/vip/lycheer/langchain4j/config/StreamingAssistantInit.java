package vip.lycheer.langchain4j.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
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
    public StreamingAssistant streamingAssistant(EmbeddingStore<TextSegment> embeddingStore,StreamingChatLanguageModel streamingChatLanguageModel) {
        return AiServices.builder(StreamingAssistant.class).chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10)).contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore)).chatMemory(MessageWindowChatMemory.withMaxMessages(5))// 保留最近5轮对话
                .streamingChatLanguageModel(streamingChatLanguageModel).build();

    }

}
