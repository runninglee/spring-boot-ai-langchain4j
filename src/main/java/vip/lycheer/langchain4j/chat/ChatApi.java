package vip.lycheer.langchain4j.chat;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.lycheer.langchain4j.service.Assistant;

import java.util.List;

@RequestMapping("api/chat")
@RequiredArgsConstructor
@RestController
public class ChatApi {

    final ChatLanguageModel chatLanguageModel;

    final Assistant assistant;

    @GetMapping("low")
    public String low(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return chatLanguageModel.chat(List.of(SystemMessage.systemMessage("假如你是特朗普，接下来请以特朗普的语气来对话"), UserMessage.from(message))).aiMessage().text();
    }

    @GetMapping("high")
    public String high(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return assistant.chat(message);
    }
}
