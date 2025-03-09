package vip.lycheer.langchain4j.func;

import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.lycheer.langchain4j.service.Assistant;
import vip.lycheer.langchain4j.util.JsonUtil;

import java.util.List;

@RequestMapping("api/func")
@RequiredArgsConstructor
@RestController
public class FuncApi {
    final ChatLanguageModel chatLanguageModel;

    final Assistant assistant;

    @GetMapping("low")
    public String low(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {

        //定义Function Calling
        ToolSpecification specification = ToolSpecification.builder().name("Sum").description("输入两个数，对这两个数求和").parameters(JsonObjectSchema.builder().addIntegerProperty("a", "第一个参数").addIntegerProperty("b", "第二个参数").required("a", "b").build()).build();

        ChatResponse chatResponse = chatLanguageModel.doChat(ChatRequest.builder()
                .messages(List.of(SystemMessage.systemMessage("假如你是特朗普，接下来请以特朗普的语气来对话"), UserMessage.from(message)))
                .parameters(ChatRequestParameters.builder()
                        .toolSpecifications(specification)
                        .build())
                .build());

        //打印执行效果
//        chatResponse.aiMessage().toolExecutionRequests().forEach(toolExecutionRequest -> {
//            System.out.println(toolExecutionRequest.name());
//            System.out.println(toolExecutionRequest.arguments());
//            try {
//                Class<?> aClass = Class.forName("vip.lycheer.langchain4j.func." + toolExecutionRequest.name());
//                Runnable runnable = (Runnable) JsonUtil.toJsonObject(toolExecutionRequest.arguments(), aClass);
//                runnable.run();
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        });
        return chatResponse.aiMessage().text();
    }

    @GetMapping("high")
    public String high(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return assistant.chat(message);
    }
}
