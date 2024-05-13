package com.example.apistock.services.center;

import com.example.apistock.models.dto.CenterDto;

import java.util.List;
import java.util.UUID;

public interface CenterService{
    CenterDto create (CenterDto centerDto) throws Exception;

    CenterDto modified(UUID id, CenterDto centerDto) throws Exception;

    List<CenterDto> getAllCenter() throws  Exception;

    CenterDto getCenterForId(UUID id) throws Exception;
}
