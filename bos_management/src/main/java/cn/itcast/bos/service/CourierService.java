package cn.itcast.bos.service;


import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CourierService {
    void save(Courier courier);

    public Page<Courier> findAll(Pageable pageable);

    void delete(int[] ids);

    Page<Courier> findPageDate(Specification<Courier> specification, Pageable pageable);

    void deleteFei(int[] ids);

    void restore(int[] ids);
}
