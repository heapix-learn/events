package com.heapix.events.controller.converter;

import com.heapix.events.controller.dto.CreateNewsDto;
import com.heapix.events.persistence.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * @author mgergalov
 */
@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsConverter {

    News toModel(CreateNewsDto dto);

}
