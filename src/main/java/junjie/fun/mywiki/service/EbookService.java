package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import junjie.fun.mywiki.common.response.PageData;

import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.entity.*;

@Service
public interface EbookService extends IService<Ebook> {

  /** 创建电子书 */
  Long createEbook(CreateEbookRequest request);

  /** 删除电子书 */
  void deleteEbooks(List<Long> ebookIds);

  /** 编辑电子书 */
  Long updateEbook(UpdateEbookRequest request);

  /** 分页查找电子书 */
  PageData<EbookData> pageEbook(PageEbookRequest request);

  /** 根据Id数组查找电子书 */
  List<EbookData> queryEbooks(List<Long> ebookIds);
}
