package cn.itcast.bos.controller;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.AreaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;
    @RequestMapping("/batchImport")
    public void batchImport(MultipartFile file) throws Exception{
        List<Area> areas = new ArrayList<>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream());
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum()==0){
                continue;
            }
            // 跳过空行
            if (row.getCell(0)==null|| StringUtils.isBlank(row.getCell(0).getStringCellValue())){
                continue;
            }
            Area area= new Area();
            area.setId(row.getCell(0).getStringCellValue());
            area.setProvince(row.getCell(1).getStringCellValue());
            area.setCity(row.getCell(2).getStringCellValue());
            area.setDistrict(row.getCell(3).getStringCellValue());
            area.setPostcode(row.getCell(4).getStringCellValue());
            areas.add(area);

        }

        areaService.saveBatch(areas);
    }

    @RequestMapping("/pageQuery")
    public Map pageQuery(int page,int rows){
        Pageable pageable = PageRequest.of(page-1,rows);
        Page<Area> areaPage = areaService.findAll(pageable);
        long totalElements = areaPage.getTotalElements();
        List<Area> pageContent = areaPage.getContent();
        Map map = new HashMap();
        map.put("total",totalElements);
        map.put("rows",pageContent);
        return map;
    }
}
