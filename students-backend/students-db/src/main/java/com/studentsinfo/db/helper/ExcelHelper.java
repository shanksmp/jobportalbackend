package com.studentsinfo.db.helper;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ExcelHelper {
    File convertMultipartFileToFile(MultipartFile file) throws IOException;
}
