package com.edu.forum.controller;

import java.util.Set;

import com.edu.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.forum.dao.MessageDao;
import com.edu.forum.dao.UserDao;
import com.edu.forum.util.HostHolder;
import com.edu.forum.util.JedisAdapter;


@Controller
public class RankController {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JedisAdapter jedisAdapter;

    private String rankKey = "forumRankKey";

    @RequestMapping(path = "/rank", method = RequestMethod.GET)
    public String rankPoint(Model model) {
        User user = hostHolder.getUser();
        Long points = userDao.getPoints(user.getId());
        jedisAdapter.zadd(rankKey, points, user.getUsername());
        Set<String> pointSet = jedisAdapter.zrevrange(rankKey, 0, 9);
        model.addAttribute("user", user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("pointSet", pointSet);
        model.addAttribute("userDao", userDao);
        return "rank";
    }
}
