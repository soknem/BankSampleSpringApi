package app.bank.feature.files;

import app.bank.feature.files.dto.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileResponse uploadSingleFile(MultipartFile file, HttpServletRequest request);
    List<String> uploadMultipleFile(MultipartFile[] files);
    ResponseEntity<Resource> serveFile(String fileName, HttpServletRequest request);
    void deleteByName(String fileName);
}
