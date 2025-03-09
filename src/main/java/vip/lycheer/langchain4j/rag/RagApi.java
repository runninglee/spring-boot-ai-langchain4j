package vip.lycheer.langchain4j.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.lycheer.langchain4j.service.Assistant;

import java.util.List;

@RequestMapping("api/rag")
@RequiredArgsConstructor
@RestController
public class RagApi {

    final Assistant assistant;

    final EmbeddingStore<TextSegment> embeddingStore;

    @GetMapping("load")
    public String load() {
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("/Users/huilee/rag");
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
        return "Success";
    }

    @GetMapping("high")
    public String high(@RequestParam(value = "memoryId") String memoryId, @RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return assistant.chat(memoryId, message);
    }
}
