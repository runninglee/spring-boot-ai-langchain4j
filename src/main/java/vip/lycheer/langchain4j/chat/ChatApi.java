package vip.lycheer.langchain4j.chat;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.lycheer.langchain4j.service.Assistant;

@RequestMapping("api")
@RequiredArgsConstructor
@RestController
public class ChatApi {

    final ChatLanguageModel chatLanguageModel;

    final Assistant assistant;

    @GetMapping("/low/chat")
    public String lowChat(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return chatLanguageModel.chat(UserMessage.from(message)).aiMessage().text();
    }

    @GetMapping("/high/chat")
    public String highChat(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return assistant.chat(message);
    }
}
