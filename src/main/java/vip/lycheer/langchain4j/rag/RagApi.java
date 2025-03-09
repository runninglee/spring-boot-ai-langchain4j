package vip.lycheer.langchain4j.rag;

import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByLineSplitter;
import dev.langchain4j.data.segment.TextSegment;
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
        //默认模式向量存储
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
        //构建方式EmbeddingMode 接口暂时无法使用
        //EmbeddingStoreIngestor.builder().embeddingStore(embeddingStore).embeddingModel(embeddingModel).documentSplitter(new DocumentByLineSplitter(30, 20)).build().ingest(documents);
        return "Success";
    }

    @GetMapping("high")
    public String high(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return assistant.chat(message);
    }
}
