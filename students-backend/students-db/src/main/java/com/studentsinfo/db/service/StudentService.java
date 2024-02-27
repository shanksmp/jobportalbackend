package com.studentsinfo.db.service;

import com.studentsinfo.db.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface StudentService {
    void saveStudentsToDatabase(MultipartFile file) throws IOException;

    public List<Student> readStudentsFromExcel (File excelFile) throws IOException;
}

