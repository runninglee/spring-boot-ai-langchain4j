package vip.lycheer.langchain4j.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.lycheer.langchain4j.service.Assistant;

@Configuration
@RequiredArgsConstructor
public class AssistantInit {
    final ChatLanguageModel chatLanguageModel;

    @Bean
    public Assistant init() {
        return AiServices.builder(Assistant.class).chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10)).chatLanguageModel(chatLanguageModel).build();
    }
}
