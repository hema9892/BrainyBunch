package com.bookcatalouge.intelj.service;

import com.bookcatalouge.intelj.dto.CopyDTO;
import com.bookcatalouge.intelj.entity.Copy;
import com.bookcatalouge.intelj.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyService {
    @Autowired
    private CopyRepository copyRepository;

    public Copy saveCopy(CopyDTO copy){
        Copy copyDetails =  Copy.builder().build();

       return copyRepository.save(copyDetails);
    }

    public List<Copy> findAllCopies(){
       return copyRepository.findAll();
    }

    public Copy findCopiesById(Long id){
       return copyRepository.findById(id).orElseThrow(null);
    }
}
