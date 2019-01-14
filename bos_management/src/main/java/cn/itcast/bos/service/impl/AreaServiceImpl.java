package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.AreaMapper;
import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;
    @Override
    public void saveBatch(List<Area> areas) {
//        for (Area area : areas) {
//            areaMapper.save(area);
//        }
        areaMapper.saveAll(areas);
    }

    @Override
    public Page<Area> findAll(Pageable pageable) {
        return areaMapper.findAll(pageable);
    }
}
