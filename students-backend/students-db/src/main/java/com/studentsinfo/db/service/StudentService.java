package com.studentsinfo.db.service;

import com.studentsinfo.db.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    void saveStudentsToDatabase(MultipartFile file) throws IOException;

     List<Student> readStudentsFromExcel (File excelFile) throws IOException;

    Optional<Student> findByUSN(String usn);
}

