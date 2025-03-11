package vip.lycheer.langchain4j.service;

import dev.langchain4j.service.*;

public interface StreamingAssistant {

    @SystemMessage("{{system}}")
    @UserMessage("客户的核心痛点: {{user}}")
    TokenStream stream(@V("user") String user,@V("system") String system);

    //保持会话记忆
    @SystemMessage("{{system}}")
    @UserMessage("客户的核心痛点: {{user}}")
    TokenStream stream(@MemoryId String memoryId, @V("user") String user,@V("system") String system);


}
