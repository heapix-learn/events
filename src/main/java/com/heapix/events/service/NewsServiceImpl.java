package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.UpdateResponseBo;
import com.heapix.events.controller.converter.NewsConverter;
import com.heapix.events.persistence.model.News;
import com.heapix.events.persistence.repository.NewsRepository;
import com.heapix.events.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author mgergalov
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsConverter newsConverter;

    @Override
    public CreateResponseBo createNews(News news, String user) {
        news.setCreatedDate(new Date());
        //check is user exist
        news.setUserId(userRepository.findByEmail(user).getId());
        News response = newsRepository.save(news);
        return new CreateResponseBo(response.getId());
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findTop10ByOrderByCreatedDateAsc();
    }

    @Override
    public News findNews(Long id) {
        return newsRepository.getOne(id);
    }

    @Override
    public UpdateResponseBo update(String body, Long newsId) {
        News news = newsRepository.getOne(newsId);
        news.setBody(body);
        news.setLastModifiedDate(new Date());
        return new UpdateResponseBo(newsRepository.save(news).getId());
    }

    @Override
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}
