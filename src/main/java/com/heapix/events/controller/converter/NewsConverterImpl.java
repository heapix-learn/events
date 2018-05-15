package com.heapix.events.controller.converter;

import com.heapix.events.controller.dto.CreateNewsDto;
import com.heapix.events.persistence.model.News;
import org.springframework.stereotype.Component;

/**
 * @author mgergalov
 */
@Component
public class NewsConverterImpl implements NewsConverter {
    public NewsConverterImpl() {
    }

    public News toModel(CreateNewsDto dto) {
        if (dto == null) {
            return null;
        } else {
            News news = new News();
            news.setTitle(dto.getTitle());
            news.setBody(dto.getBody());
            news.setRole(dto.getRole());
            return news;
        }
    }
}
