package com.studentsinfo.db.repository;

import com.studentsinfo.db.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Long> {
}
