package cn.com.scitc.repository;

import cn.com.scitc.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 消息dao
 */
public interface MessageRepository extends JpaRepository<Message,Long> {
}
