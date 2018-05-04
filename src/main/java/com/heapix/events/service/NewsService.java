package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.UpdateResponseBo;
import com.heapix.events.persistence.model.News;

import java.util.List;

/**
 * @author mgergalov
 */
public interface NewsService {

    CreateResponseBo createNews(News news, Long userId);
    List<News> getAll();
    News findNews(Long id);
    UpdateResponseBo update(String body, Long newsId);
    void delete(Long id);

}
