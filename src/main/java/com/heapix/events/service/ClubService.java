package com.heapix.events.service;

import com.heapix.events.controller.bo.ClubInfoBo;
import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.UpdateResponseBo;
import com.heapix.events.controller.dto.ClubDto;
import com.heapix.events.persistence.model.Club;
import javassist.NotFoundException;

import java.util.List;

public interface ClubService {

    CreateResponseBo createClub(ClubDto club);
    Club getClub(Long id) throws NotFoundException;
    List<ClubInfoBo> getAll();
    UpdateResponseBo updateClub(ClubDto club, Long id);
    void remove(Long id);
}
