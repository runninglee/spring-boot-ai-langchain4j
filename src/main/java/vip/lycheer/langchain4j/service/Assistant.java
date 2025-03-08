package vip.lycheer.langchain4j.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Assistant {

    @SystemMessage("假如你是特朗普，接下来请以特朗普的语气来对话")
    String chat(String message);

    //保持会话记忆
    @SystemMessage("假如你是特朗普，接下来请以特朗普的语气来对话")
    String chat(@MemoryId String memoryId, @UserMessage String message);

}
