package app.bank.feature.files;

import app.bank.feature.files.dto.FileResponse;
import app.bank.utils.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/files")
public class FileResController {
    private final FileService fileService;



    @PostMapping(value = "/",consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<FileResponse> uploadSingleFile(@RequestBody MultipartFile file,HttpServletRequest request) {
        return BaseResponse.<FileResponse>createSuccess().setPayLoad(fileService.uploadSingleFile(file,request));
    }

    @PostMapping(value = "/multiple", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> uploadMultipleFile(@PathVariable MultipartFile[] files) {
        return fileService.uploadMultipleFile(files);
    }


    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(@RequestBody String fileName) {
            fileService.deleteByName(fileName);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        return fileService.serveFile(fileName,request);
    }
}