package com.heapix.events.controller;

import com.heapix.events.controller.bo.ClubInfoBo;
import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.UpdateResponseBo;
import com.heapix.events.controller.dto.ClubDto;
import com.heapix.events.persistence.model.Club;
import com.heapix.events.service.ClubService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("club")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubInfoBo> getAllClubs() {
        //impl
        return clubService.getAll();
    }

    @GetMapping("/{id}")
    public Club getCLub(@NotNull @PathVariable String id) throws NotFoundException {
        return clubService.getClub(Long.valueOf(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Administrator', 'Moderator')")
    public ResponseEntity addClub(@NotEmpty @RequestBody ClubDto clubDto) {
        CreateResponseBo response = clubService.createClub(clubDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrator', 'Moderator')")
    public ResponseEntity updateCLub(@NotEmpty @RequestBody ClubDto clubDto,
                                     @PathVariable String id) {
        UpdateResponseBo response = clubService.updateClub(clubDto, Long.valueOf(id));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrator', 'Moderator')")
    public ResponseEntity removeClub(@PathVariable String id) {
        clubService.remove(Long.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}
