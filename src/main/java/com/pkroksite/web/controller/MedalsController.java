package com.pkroksite.web.controller;

import com.pkroksite.web.domain.MedalsDTO;
import com.pkroksite.web.service.FileStorageService;
import com.pkroksite.web.service.MedalsService;
import com.pkroksite.web.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("medals")
public class MedalsController {
    private MedalsService medalsService;
    private FileStorageService fileStorageService;

    @Autowired
    public MedalsController(MedalsService medalsService, FileStorageService fileStorageService){
        this.medalsService=medalsService;
        this.fileStorageService=fileStorageService;
    }

    @PostMapping
    public ResponseEntity<?> addType(@RequestBody MedalsDTO medal) {
        medalsService.addMedal(medal);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedalsDTO>> getMedals(){
        return ResponseEntity.ok(medalsService.findAllOrderById());
    }

    @PostMapping("{userId}/image")
    public ResponseEntity<?> uploadImage(
            @PathVariable("userId") Long id,
            @RequestParam("file") MultipartFile file
    ) {
        if (FileUtil.isNotEmpty(file)) {
            System.out.println(file.getOriginalFilename());
            fileStorageService.storeFile(file);
            medalsService.addImageToProduct(file.getOriginalFilename(), id);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("image")
    public ResponseEntity<?> getFile(
            @RequestParam("fileName") String fileName,
            HttpServletRequest request) {
        Resource resource;
        if (fileName.equals("null")) {
            resource = fileStorageService.loadFile("empty.png");
        } else {
            resource = fileStorageService.loadFile(fileName);
        }
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline: filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
