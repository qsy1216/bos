package cn.itcast.bos.dao;

import cn.itcast.bos.domain.base.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StandardMapper extends JpaRepository<Standard,Integer>,JpaSpecificationExecutor<Standard>{


}
