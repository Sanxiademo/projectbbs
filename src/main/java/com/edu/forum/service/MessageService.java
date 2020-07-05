package com.edu.forum.service;

import java.util.List;

import com.edu.forum.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.forum.dao.MessageDao;


@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public List<Message> getReadMessageById(Long id) {
        List<Message> messages = messageDao.getMessageByToId(id);
        return messages;
    }

    public List<Message> getUnreadMessageById(Long id) {
        List<Message> unReadMessages = messageDao.getUnReadMessageByToId(id);
        return unReadMessages;
    }
}
