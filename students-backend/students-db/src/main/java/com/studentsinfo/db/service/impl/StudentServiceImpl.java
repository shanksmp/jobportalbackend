package com.studentsinfo.db.service.impl;

import com.studentsinfo.db.entity.Student;
import com.studentsinfo.db.helper.ExcelHelper;
import com.studentsinfo.db.repository.StudentRepository;
import com.studentsinfo.db.service.StudentService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExcelHelper excelHelper;

    @Override
    public void saveStudentsToDatabase(MultipartFile excelFile) throws IOException {
        List<Student> students = readStudentsFromExcel(excelHelper.convertMultipartFileToFile(excelFile));
        studentRepository.saveAll(students);
    }

    @Override
    public List<Student> readStudentsFromExcel(File excelFile) throws IOException {
        List<Student> students = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            // Skip header
            if (row.getRowNum() == 0) {
                continue;
            }

            // Check if the first cell in the row is empty, indicating the end of the data
            if (row.getCell(0) == null || row.getCell(0).getStringCellValue().isEmpty()) {
                break; // Exit the loop if the first cell is empty
            }

            // Process the data in the current row
            Student student = new Student();
            student.setName(row.getCell(0).getStringCellValue());
            student.setUsn(row.getCell(1).getStringCellValue());
            student.setDate(LocalDate.from(row.getCell(2).getLocalDateTimeCellValue()));
            student.setEmail(row.getCell(3).getStringCellValue());
            student.setPhoneNumber((long) row.getCell(4).getNumericCellValue());
            student.setCgpa(row.getCell(5).getNumericCellValue());

            students.add(student);
        }

        workbook.close();
        System.out.println(students);
        return students;
    }

    @Override
    public Optional<Student> findByUSN(String usn) {
        return studentRepository.findById(usn);
    }


}
