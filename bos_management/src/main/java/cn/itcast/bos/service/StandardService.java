package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StandardService {
    void save(Standard standard);
    public Page<Standard> findAll(Pageable pageable);

    void delete(int id);
}
