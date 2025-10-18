package com.example.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {
    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleSaveUpUpload(MultipartFile file, String tagetFolder) {

        if (file.isEmpty()) {
            return "";
        }
        String finalName = "";
        try {
            // 1. Lấy bytes của file
            byte[] bytes = file.getBytes();

            // 2. Lấy đường dẫn thực tế tới thư mục images/avatar trong webapp
            String rootPath = servletContext.getRealPath("/resources/images");
            File dir = new File(rootPath + File.separator + tagetFolder);

            // 3. Nếu thư mục chưa tồn tại thì tạo mới
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 4. Tạo tên file mới (tránh trùng)
            finalName = System.currentTimeMillis() + "-"
                    + file.getOriginalFilename();

            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);

            // 5. Ghi file lên server
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            System.out.println("Upload thành công: " + serverFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Upload lỗi: " + e.getMessage());
        }
        return finalName;
    }
}
