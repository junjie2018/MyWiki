package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageDocCondition;
import junjie.fun.mywiki.request.doc.CreateOrUpdateDocRequest;
import junjie.fun.mywiki.response.data.DocData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocService {
    Page<DocData> pageDoc(PageRequest<PageDocCondition> request);

    Long createOrUpdateDoc(CreateOrUpdateDocRequest request);

    void deleteDocs(List<Long> docIds);

    void vote(Long docId);

    String getContent(Long docId);
}
