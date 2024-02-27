package com.studentsinfo.db.helper.impl;

import com.studentsinfo.db.helper.ExcelHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Component
public class ExcelHelperImpl implements ExcelHelper {
    @Override
    public File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        file.transferTo(convertedFile);
        return convertedFile;
    }
}
