package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.CourierMapper;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.CourierService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourierServiceImpl implements CourierService {
    @Autowired
    private CourierMapper courierMapper;
    @Override
    public void save(Courier courier) {
        courierMapper.save(courier);
    }

    @Override
    public Page<Courier> findAll(Pageable pageable) {
        return courierMapper.findAll(pageable);
    }

    @Override
    public void delete(int[] ids) {
        for (int id : ids) {
            courierMapper.deleteById(id);
        }
    }

    @Override
    public Page<Courier> findPageDate(Specification<Courier> specification, Pageable pageable) {
        return courierMapper.findAll(specification,pageable);
    }

    @Override
    public void deleteFei(int[] ids) {
        for (int id : ids) {
            courierMapper.update(id);
        }
    }

    @Override
    public void restore(int[] ids) {
        for (int id : ids) {
            courierMapper.reupdate(id);
        }
    }
}
