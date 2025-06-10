package com.gec.ocean.service;

import com.gec.ocean.entity.Ebook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.ocean.req.EbookQueryReq;
import com.gec.ocean.req.EbookSaveReq;
import com.gec.ocean.resp.EbookQueryResp;
import com.gec.ocean.resp.PageResp;

/**
 * <p>
 * 电子书 服务类
 * </p>
 *
 * @author zhuzec
 * @since 2025-05-22
 */
public interface IEbookService extends IService<Ebook> {

    PageResp<EbookQueryResp> listByname(EbookQueryReq req);

    public void save(EbookSaveReq req);

    public void delete(Long id);
}
