package com.heapix.events.controller.converter;

import com.heapix.events.controller.bo.ClubInfoBo;
import com.heapix.events.controller.bo.EventInfoBo;
import com.heapix.events.controller.dto.ClubDto;
import com.heapix.events.persistence.model.Club;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClubConverter {

    Club toModel(ClubInfoBo club);

    Club toModel(ClubDto club);

    EventInfoBo toInfoBo(Club club);

    List<ClubInfoBo> toDtos(List<Club> clubs);
}
