package vip.lycheer.langchain4j.json;


import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.lycheer.langchain4j.service.Assistant;

import java.util.List;

@RequestMapping("api/json")
@RequiredArgsConstructor
@RestController
public class JsonApi {
    final ChatLanguageModel chatLanguageModel;

    @GetMapping("high")
    public String high(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
//        ResponseFormat responseFormat = ResponseFormat.builder()
//                .type(ResponseFormatType.JSON)
//                .jsonSchema(JsonSchema.builder()
//                        .rootElement(JsonObjectSchema.builder()
//                                .addIntegerProperty("age", "the person's age")
//                                .addIntegerProperty("weight", "the person's weight")
//                                .required("age", "weight")
//                                .build())
//                        .build())
//                .build();
//
//        ChatResponse chat = chatLanguageModel.chat(ChatRequest.builder()
//                .messages(List.of(UserMessage.from(message)))
//                .parameters(ChatRequestParameters.builder()
//                        .responseFormat(responseFormat)
//                        .build())
//                .build());
//        return chat.aiMessage().text();


        PersonService personService = AiServices.create(PersonService.class, chatLanguageModel);
        Person person = personService.extractPerson(message);
        return person.toString();
    }
}
