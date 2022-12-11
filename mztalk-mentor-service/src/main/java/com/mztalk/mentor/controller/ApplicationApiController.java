package com.mztalk.mentor.controller;

import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.service.ApplicationService;
import com.mztalk.mentor.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentors")
public class ApplicationApiController {

    private final ApplicationService applicationService;
    private final ImageService imageService;

    @GetMapping("/application")
    public String saveForm(){
        return null;
    }

    @PostMapping("/application")
    public Long saveApplication(@RequestBody ApplicationDto applicationDto){
//        @ModelAttribute MultipartFile file,
//        HttpServletRequest request
//        ImageDto imageDto = new Image().saveFile(file, request);
//        imageService.saveImage(imageDto);
        System.out.println(applicationDto);
        return applicationService.save(applicationDto);
    }
    
    @PostMapping("/application/file")
    public void haosiad(MultipartFile file){
        System.out.println("테스트");
        System.out.println(file);
    }

    @GetMapping("/application/{id}")
    public ApplicationDto findById(@PathVariable("id")Long id){
        return applicationService.findById(id);
    }

    @GetMapping("/applications")
    public Result findAll(){
        return applicationService.findAll();
    }

    @DeleteMapping("/application/{id}")
    public Long delete(@PathVariable("id") Long id){
        return applicationService.delete(id);
    }

    @PatchMapping("/application/{id}")
    public Long updateApplication(@PathVariable("id") Long id,@RequestBody ApplicationDto applicationDto){
        return applicationService.updateApplication(id,applicationDto);
    }











}
