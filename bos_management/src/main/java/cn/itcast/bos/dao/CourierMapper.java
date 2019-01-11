package cn.itcast.bos.dao;

import cn.itcast.bos.domain.base.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CourierMapper extends JpaRepository<Courier,Integer>,JpaSpecificationExecutor<Courier> {
    @Query(value = "update Courier set deltag ='1' where id = ?1 ")
    @Modifying
    void update(int id);
    @Query(value = "update Courier set deltag =null where id = ?1 ")
    @Modifying
    void reupdate(int id);
}
