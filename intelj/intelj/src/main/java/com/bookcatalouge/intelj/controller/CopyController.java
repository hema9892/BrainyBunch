package com.bookcatalouge.intelj.controller;

import com.bookcatalouge.intelj.dto.CopyDTO;
import com.bookcatalouge.intelj.entity.Copy;
import com.bookcatalouge.intelj.repository.CopyRepository;
import com.bookcatalouge.intelj.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/copies")
public class CopyController {
    @Autowired
    private CopyService copyService;

    @PostMapping
    public Copy createCopy(@RequestBody CopyDTO copy){
       return copyService.saveCopy(copy);
    }
    @GetMapping
    public List<Copy> getAllCopies(){
 return copyService.findAllCopies();
    }
    @GetMapping("/{id}")
    public Copy getCopyById(@PathVariable Long id){
       return copyService.findCopiesById(id);
    }
}
