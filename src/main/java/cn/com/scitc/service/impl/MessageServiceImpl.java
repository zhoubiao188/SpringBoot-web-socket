package cn.com.scitc.service.impl;

import cn.com.scitc.entity.Message;
import cn.com.scitc.repository.MessageRepository;
import cn.com.scitc.service.MessageSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class MessageServiceImpl implements MessageSerice {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    @Override
    public Message saveMsg(Message message) {
        message = messageRepository.save(message);
        return message;
    }
}
