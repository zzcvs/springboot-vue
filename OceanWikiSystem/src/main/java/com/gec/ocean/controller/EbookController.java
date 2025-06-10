package com.gec.ocean.controller;

import com.gec.ocean.entity.Ebook;
import com.gec.ocean.req.EbookQueryReq;
import com.gec.ocean.req.EbookSaveReq;
import com.gec.ocean.resp.EbookQueryResp;
import com.gec.ocean.resp.PageResp;
import com.gec.ocean.service.IEbookService;
import com.gec.ocean.utils.CommonResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 电子书 前端控制器
 * </p>
 *
 * @author zhuzec
 * @since 2025-05-22
 * Rest服务 Api
 */
@Api(tags = "电子书管理模块")
@RestController // 这个才能和json数据格式交互
@RequestMapping("/ebook")
@CrossOrigin
public class EbookController {

    @Autowired
    public IEbookService ebookService;

//    @ApiOperation("查询全部电子书信息")
    @GetMapping("/findAll")
    public CommonResp findAll(){
        List<Ebook> list = ebookService.list();
        return new CommonResp(true,"查询所有电子书信息",list);
    }

//    @ApiOperation("根据id查询唯一电子书信息")
    @GetMapping("/findOne/{id}")
    public CommonResp findOne(@PathVariable int id){
        Ebook ebook = ebookService.getById(id);
        return new CommonResp(true,"查询唯一电子书信息（根据唯一id）",ebook);
    }

//    @ApiOperation("分页查询唯一电子书信息")
    @GetMapping("/listByPage")
    public CommonResp listByPage(@ApiParam("电子书查询对象") EbookQueryReq req){
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>(true,"查询成功",null);

        PageResp<EbookQueryResp> pageResp = ebookService.listByname(req);
        resp.setContent(pageResp);

        return resp;

    }

    //@RequestBody 把前端提交的json数据转成java类型
    //@ResponseBody 把java的数据转换成json的数据返回到前端
//    @ApiOperation("新增和修改电子书信息")
    @PostMapping("/save")
    public CommonResp save(@ApiParam("电子书保存对象") @RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>(true,"成功",null);
        ebookService.save(req);
        return resp;
    }


//    @ApiOperation("根据id删除删除电子书信息")
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
