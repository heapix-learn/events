package com.heapix.events.controller;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.converter.NewsConverter;
import com.heapix.events.controller.dto.CreateNewsDto;
import com.heapix.events.controller.dto.UpdateNewsDto;
import com.heapix.events.persistence.model.News;
import com.heapix.events.service.NewsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mgergalov
 */
@RestController
@RequestMapping("events")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsConverter newsConverter;


    @GetMapping
    public List<News> getAllEvents() {
        return newsService.getAll();
    }

    @GetMapping("/{id}")
    public News getEventInfo(@NotNull @PathVariable String id) throws NotFoundException {
        return newsService.findNews(Long.valueOf(id));
    }

    @PostMapping
    public ResponseEntity addEvent(@RequestBody CreateNewsDto newsDto) {
        //impl
        CreateResponseBo response = newsService.createNews(newsConverter.toModel(newsDto), newsDto.getCreatorName());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEvent(@NotEmpty @RequestBody UpdateNewsDto eventDto, @PathVariable String id) {
        //impl
        return new ResponseEntity(newsService.update(eventDto.getBody(), Long.valueOf(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeEvent(@PathVariable String id) {
        //impl
        newsService.delete(Long.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }



}
