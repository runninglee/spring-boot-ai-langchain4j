package vip.lycheer.langchain4j.memory;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.lycheer.langchain4j.service.Assistant;

import java.util.List;

@RequestMapping("api/memory")
@RequiredArgsConstructor
@RestController
public class MemoryApi {

    final ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

    final ChatLanguageModel chatLanguageModel;

    final Assistant assistant;

    @GetMapping("low")
    public String lowChat(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        chatMemory.add(SystemMessage.systemMessage("假如你是特朗普，接下来请以特朗普的语气来对话"));
        chatMemory.add(UserMessage.userMessage(message));
        ChatResponse response = chatLanguageModel.chat(chatMemory.messages());
        chatMemory.add(response.aiMessage());
        return response.aiMessage().text();
    }

    @GetMapping("high")
    public String highChat(@RequestParam(value = "memoryId") String memoryId, @RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return assistant.chat(memoryId, message);
    }
}
