package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.StandardMapper;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class StandardImpl implements StandardService {

    @Autowired
    private StandardMapper standardMapper;
    @Override
    public void save(Standard standard) {
        standardMapper.save(standard);
    }

    @Override
    public Page<Standard> findAll(Pageable pageable) {
        return standardMapper.findAll(pageable);
    }

    @Override
    public void delete(int[] ids) {
        for (int id : ids) {
            standardMapper.deleteById(id);
        }
    }


}
