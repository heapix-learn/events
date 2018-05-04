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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author mgergalov
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    private NewsConverter newsConverter;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('Administrator', 'Moderator', 'Member')")
    public List<News> getAllNews() {
        return newsService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrator', 'Moderator', 'Member')")
    public News getNews(@NotNull @PathVariable("id") String id) throws NotFoundException {
        return newsService.findNews(Long.valueOf(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Administrator', 'Moderator')")
    public ResponseEntity addNews(@RequestBody CreateNewsDto newsDto) {
        //impl
        CreateResponseBo response = newsService.createNews(newsConverter.toModel(newsDto), newsDto.getCreatorName());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrator', 'Moderator')")
    public ResponseEntity updateNews(@RequestBody UpdateNewsDto eventDto, @PathVariable String id) {
        //impl
        return new ResponseEntity(newsService.update(eventDto.getBody(), Long.valueOf(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrator', 'Moderator')")
    public ResponseEntity removeNews(@PathVariable String id) {
        //impl
        newsService.delete(Long.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }


}
