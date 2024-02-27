package com.studentsinfo.db.controller;


import com.studentsinfo.db.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@RestController
@AllArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/check")
    public String check(){
        return "Checked";
    }
    @PostMapping("/students/upload")
    public ResponseEntity<String> uploadStudents(@RequestParam("file") MultipartFile file){
        System.out.println("Inside Controller");
        if(file.isEmpty()){
            return ResponseEntity.badRequest().body("Please select a file");
        }
        try{
            studentService.saveStudentsToDatabase(file);
            return ResponseEntity.ok("Data Saved Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading Data" + e.getMessage());
        }

    }


}
