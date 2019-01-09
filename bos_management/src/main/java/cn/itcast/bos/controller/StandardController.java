package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.domain.common.ResponseResult;
import cn.itcast.bos.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/standard")
public class StandardController {

    @Autowired
    private StandardService standardService;

    @RequestMapping("/save")
    public ResponseResult save(Standard standard){
        try {
            standardService.save(standard);
            return ResponseResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
    }

    @RequestMapping("/pageQuery")
    public Map pageQuery(int page,int rows){

        Pageable pageable = PageRequest.of(page-1,rows);
        Page<Standard> standardPage = standardService.findAll(pageable);
        long totalElements = standardPage.getTotalElements();
        List<Standard> pageContent = standardPage.getContent();
        Map map = new HashMap();
        map.put("total",totalElements);
        map.put("rows",pageContent);
        return map;
    }
    @RequestMapping("/delete")
    public ResponseResult delete(int id){
        try {
            standardService.delete(id);
            return ResponseResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
    }
}
