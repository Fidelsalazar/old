package com.example.apistock.services.center;

import com.example.apistock.models.dto.CenterDto;
import com.example.apistock.models.entities.Center;
import com.example.apistock.repositories.CenterRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class CenterServiceImpl implements CenterService{

    @Autowired
    private final CenterRepository centerRepository;

    public CenterServiceImpl(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @Override
    public CenterDto create(CenterDto centerDto) throws Exception {
        try{
            if(
                    centerRepository.findByCenter(centerDto.getCenter()) == null
            ){
               Center newCenter = Center.builder()
                       .center(centerDto.getCenter())
                       .sitio(centerDto.getSitio())
                       .build();
               centerRepository.save(newCenter);

               log.info("Create successful for ......." +
                       centerRepository.findByCenter(centerDto.getCenter()).getCenter()+
                       "with ID" + centerRepository.findByCenter(centerDto.getCenter()).getId()
               );

                CenterDto centerDto1 =new CenterDto(centerDto.getCenter(),centerDto.getSitio());
                return centerDto1;
            } else{
                log.error("Create error for ......." + centerDto.getCenter());
            }
        }catch ( Exception e){
            log.error("An error occurred while create center");
            throw new Exception("Center already exists", e);
        }
        return null;
    }

    @Override
    public  CenterDto modified(UUID id, CenterDto centerDto) throws Exception{
        try{
            Optional<Center> existingCenterOptional = centerRepository.findById(id);
            if (
                    existingCenterOptional.isPresent()
            ){
                Center existingCenter = existingCenterOptional.get();

                existingCenter.setCenter(centerDto.getCenter());
                existingCenter.setSitio(centerDto.getSitio());
                centerRepository.save(existingCenter);

                log.info("Modified successful for ......." +
                        existingCenter.getCenter() +
                        " with ID " + existingCenter.getId()
                );

                CenterDto centerDto1 =new CenterDto(centerDto.getCenter(),centerDto.getSitio());
                return centerDto1;
            } else{
                log.error("Modified error for ......." + centerDto.getCenter());
            }
        }catch (Exception e) {
            log.error("An error occurred while modified the center ", e);
            throw new Exception("Error modifying center", e);
        }
        return null;
    }

    @Override
    public List<CenterDto> getAllCenter() throws  Exception{
        try{
            List<CenterDto> centerList = new ArrayList<>();

            centerRepository.findAll().forEach( i ->{
                CenterDto centerDto = CenterDto.builder()
                        .id(i.getId())
                        .center(i.getCenter())
                        .sitio(i.getSitio())
                        .build();
                centerList.add(centerDto);
            });
            return centerList;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception();
        }
    }

    @Override
    public  CenterDto getCenterForId(UUID id) throws Exception{
        try{
            return CenterDto.builder()
                    .id(centerRepository.findById(id).get().getId())
                    .center(centerRepository.findById(id).get().getCenter())
                    .sitio(centerRepository.findById(id).get().getSitio())
                    .build();
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception();
        }
    }

    @Override
    public String delete(UUID id) throws Exception {
      try{
        centerRepository.deleteById(id);
        return null ;
      }catch(Exception e) {
        log.error(e.getMessage());
        throw new Exception();
      }

    }
}
