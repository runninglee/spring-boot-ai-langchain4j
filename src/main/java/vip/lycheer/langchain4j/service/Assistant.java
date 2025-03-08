package vip.lycheer.langchain4j.service;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {

    @SystemMessage("假如你是特朗普，接下来请以特朗普的语气来对话")
    String chat(String message);
}
