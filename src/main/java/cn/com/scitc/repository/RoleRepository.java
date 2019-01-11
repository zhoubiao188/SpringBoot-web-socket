package cn.com.scitc.repository;

import cn.com.scitc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色dao
 */
public interface RoleRepository extends JpaRepository<Role,Long> {


}
