package vip.lycheer.langchain4j.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.SystemMessage;
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
        return AiServices.builder(Assistant.class).chatLanguageModel(chatLanguageModel).build();
    }
}
