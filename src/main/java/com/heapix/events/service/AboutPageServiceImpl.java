package com.heapix.events.service;

import com.heapix.events.controller.dto.UpdateAboutPageDto;
import com.heapix.events.persistence.model.AboutPage;
import com.heapix.events.persistence.repository.AboutPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mgergalov
 */
@Service
public class AboutPageServiceImpl implements AboutPageService{

    @Autowired
    private AboutPageRepository pageRepository;

    @Override
    public AboutPage getAboutPage() {
        return pageRepository.getOne(1l);
    }

    @Override
    public void updateAboutPage(UpdateAboutPageDto page) {
        AboutPage entity = pageRepository.getOne(1l);
        if(page.getTitle() != null && !page.getBody().isEmpty()) {
            entity.setTitle(page.getTitle());
        }
        if(page.getBody() != null && !page.getBody().isEmpty()){
            entity.setBody(page.getBody());
        }
        pageRepository.save(entity);
    }
}
