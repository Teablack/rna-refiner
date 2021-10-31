package com.put.rnarefiner.pdb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PdbController {

    @PostMapping(value = "/api/pdb/{uuid}")
    public ResponseEntity<?> uploadTaskFile(@PathVariable String uuid, @RequestPart MultipartFile taskFile) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
