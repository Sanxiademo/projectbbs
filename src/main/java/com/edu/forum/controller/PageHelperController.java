package com.edu.forum.controller;

import java.util.List;

import com.edu.forum.model.PageBean;
import com.edu.forum.model.Topic;
import com.edu.forum.service.PageService;
import com.edu.forum.util.ForumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageHelperController {

    @Autowired
    public PageService pageService;

    //	@RequestMapping(path="/topics/{category}/{currentPage}",method=RequestMethod.GET)
//	@ResponseBody
    public String pageHelper(@PathVariable String category, @PathVariable int currentPage) {
        PageBean<Topic> pageTopic = pageService.findItemByPage(category, currentPage, 5);
        List<Topic> topics = pageTopic.getItems();
        return ForumUtil.getJSONString(1, topics);
    }
}
