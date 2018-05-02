package com.heapix.events.service;

import com.heapix.events.controller.bo.ClubInfoBo;
import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.UpdateResponseBo;
import com.heapix.events.controller.converter.ClubConverter;
import com.heapix.events.controller.dto.ClubDto;
import com.heapix.events.persistence.model.Club;
import com.heapix.events.persistence.repository.ClubRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepo;
    private ClubConverter clubConverter;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepo ) {
        this.clubRepo = clubRepo;
     //   this.clubConverter = clubConverter;
    }

    @Override
    public CreateResponseBo createClub(ClubDto club) {
        Club saved = clubRepo.save(clubConverter.toModel(club));
        return new CreateResponseBo(saved.getId());
    }

    @Override
    public Club getClub(Long id) throws NotFoundException {
        return clubRepo.getOne(id);
    }

    @Override
    public List<ClubInfoBo> getAll() {
        return clubConverter.toDtos(clubRepo.findAll());
    }

    @Override
    public UpdateResponseBo updateClub(ClubDto clubDto, Long id) {
        Club club = clubRepo.getOne(id);
        club.setInfo(clubDto.getInfo());
        return new UpdateResponseBo(clubRepo.save(club).getId());
    }

    @Override
    public void remove(Long id) {
        clubRepo.deleteById(id);
    }
}
