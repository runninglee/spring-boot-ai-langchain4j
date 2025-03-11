package vip.lycheer.langchain4j.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface StreamingAssistant {

    @SystemMessage("假如你是特朗普，接下来请以特朗普的语气来对话")
    TokenStream stream(String message);

    //保持会话记忆
    @SystemMessage("假如你是特朗普，接下来请以特朗普的语气来对话")
    TokenStream stream(@MemoryId String memoryId, @UserMessage String message);

}
