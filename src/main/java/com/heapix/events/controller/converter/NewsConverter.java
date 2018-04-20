package com.heapix.events.controller.converter;

import com.heapix.events.controller.dto.CreateNewsDto;
import com.heapix.events.persistence.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author mgergalov
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface NewsConverter {

    News toModel(CreateNewsDto dto);

}
