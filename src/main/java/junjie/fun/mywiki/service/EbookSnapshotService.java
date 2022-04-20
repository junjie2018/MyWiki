package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import junjie.fun.mywiki.common.response.PageData;

import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.entity.*;

@Service
public interface EbookSnapshotService extends IService<EbookSnapshot> {

  /** 创建电子书快照表 */
  Long createEbookSnapshot(CreateEbookSnapshotRequest request);

  /** 删除电子书快照表 */
  void deleteEbookSnapshots(List<Long> ebookSnapshotIds);

  /** 编辑电子书快照表 */
  Long updateEbookSnapshot(UpdateEbookSnapshotRequest request);

  /** 分页查找电子书快照表 */
  PageData<EbookSnapshotData> pageEbookSnapshot(PageEbookSnapshotRequest request);

  /** 根据Id数组查找电子书快照表 */
  List<EbookSnapshotData> queryEbookSnapshots(List<Long> ebookSnapshotIds);
}
