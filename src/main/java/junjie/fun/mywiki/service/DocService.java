package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import junjie.fun.mywiki.common.response.PageData;

import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.entity.*;

@Service
public interface DocService extends IService<Doc> {

  /** 创建文档 */
  Long createDoc(CreateDocRequest request);

  /** 删除文档 */
  void deleteDocs(List<Long> docIds);

  /** 编辑文档 */
  Long updateDoc(UpdateDocRequest request);

  /** 分页查找文档 */
  PageData<DocData> pageDoc(PageDocRequest request);

  /** 根据Id数组查找文档 */
  List<DocData> queryDocs(List<Long> docIds);
}
