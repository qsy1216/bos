package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.domain.common.ResponseResult;
import cn.itcast.bos.service.CourierService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courier")
public class CourierController {

    @Autowired
    private CourierService courierService;
    @RequestMapping("/save")
    public ResponseResult save(Courier courier){

        try {
            courierService.save(courier);
            return ResponseResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
    }

    /**
     * 分页条件查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/pageQuery")
    public Map pageQuery(int page, int rows,Courier courier){
        // 封装 pageable 对象
        Pageable pageable = PageRequest.of(page-1,rows);
        // 创建查询条件
        Specification<Courier> specification = new Specification<Courier>() {
            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                // 单表查询
                if (StringUtils.isNotBlank(courier.getCourierNum())){
                    Predicate p1=criteriaBuilder.equal(
                            root.get("courierNum").as(String.class),
                            courier.getCourierNum()
                    );
                    list.add(p1);
                }

                if (StringUtils.isNotBlank(courier.getCompany())){
                    Predicate p2=criteriaBuilder.like(
                            root.get("company").as(String.class),
                           "%"+ courier.getCompany()+"%"
                    );
                    list.add(p2);
                }

                if (StringUtils.isNotBlank(courier.getType())){
                    Predicate p3=criteriaBuilder.equal(
                            root.get("type").as(String.class),
                            courier.getType()
                    );
                    list.add(p3);
                }
                // 多表查询
                Join<Courier, Standard> standard = root.join("standard", JoinType.INNER);
                if (courier.getStandard()!=null&&StringUtils.isNotBlank(courier.getStandard().getName())){
                    Predicate p4=criteriaBuilder.like(standard.get("name").as(String.class),"%"+courier.getStandard().getName()+"%");
                    list.add(p4);
                }

                return criteriaBuilder.and(list.toArray(new Predicate[0]));
            }
        };

//        Page<Courier> courierPage = courierService.findAll(pageable);
        Page<Courier> courierPage =courierService.findPageDate(specification,pageable);
        long totalElements = courierPage.getTotalElements();
        List<Courier> pageContent = courierPage.getContent();
        Map map = new HashMap();
        map.put("total",totalElements);
        map.put("rows",pageContent);
        return map;
    }

    @RequestMapping("/delete")
    public ResponseResult delete(int[] ids){
        try {
            courierService.delete(ids);
            return ResponseResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
    }
    @RequestMapping("/deleteFei")
    public ResponseResult deleteFei(int[] ids){
        try {
            courierService.deleteFei(ids);
            return ResponseResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
    }

    @RequestMapping("/restore")
    public ResponseResult restore(int[] ids){
        try {
            courierService.restore(ids);
            return ResponseResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.FAIL();
        }
    }


}
