package cn.com.scitc.repository;

import cn.com.scitc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户dao
 */
public interface UserRepository  extends JpaRepository<User,Long> {
    /**
     * 查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);

}
