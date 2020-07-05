package com.edu.forum.async.handler;

import java.util.Arrays;
import java.util.List;

import com.edu.forum.async.EventHandler;
import com.edu.forum.async.EventModel;
import com.edu.forum.async.EventType;
import com.edu.forum.model.Message;
import com.edu.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.forum.dao.AnswerDao;
import com.edu.forum.dao.MessageDao;
import com.edu.forum.dao.UserDao;
import com.edu.forum.util.HostHolder;

/**
 * 评论的处理器
 *
 * @author Je.perdre
 */
@Component
public class CommentHandler implements EventHandler {

    @Autowired
    UserDao userDao;

    @Autowired
    MessageDao messageDao;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    AnswerDao answerDao;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(model.getActorId());
        message.setToId(model.getEntityOwnerId());
        User user = userDao.getUserById((long) model.getActorId());
        message.setContent("用户" + user.getUsername() + "评论你的话题！");
        message.setCreatedDate(model.getCreatedDate());
        message.setIdTopic(model.getEntityId());
        message.setHasRead(0);
        messageDao.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.COMMENT);
    }

}
