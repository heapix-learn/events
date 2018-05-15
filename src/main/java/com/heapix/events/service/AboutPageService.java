package com.heapix.events.service;

import com.heapix.events.controller.dto.UpdateAboutPageDto;
import com.heapix.events.persistence.model.AboutPage;

/**
 * @author mgergalov
 */
public interface AboutPageService {

    AboutPage getAboutPage();
    void updateAboutPage(UpdateAboutPageDto page);

}
