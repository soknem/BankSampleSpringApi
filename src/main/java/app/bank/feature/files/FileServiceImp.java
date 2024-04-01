package app.bank.feature.files;

import app.bank.feature.files.dto.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Service
public class FileServiceImp implements FileService {
    @Value("${fileStorage.image_location}")
    String fileStorageLocation;

    private String generateUrl(String fileName, HttpServletRequest request){
        return String.format("%s://%s:%d/images/%s",request.getScheme(),request.getServerName(),request.getServerPort(),fileName);
    }

    private String generateDownloadUrl(String fileName, HttpServletRequest request){
        return String.format("%s://%s:%d/api/v1/files/download/%s",request.getScheme(),request.getServerName(),request.getServerPort(),fileName);
    }

    public String uploadFile(MultipartFile file){
        try{
            Path fileStoragePath = Path.of(fileStorageLocation);
            if (!Files.exists(fileStoragePath)) {
                Files.createDirectories(fileStoragePath);
            }
            String fileName = UUID.randomUUID() + "." +
                                      Objects.requireNonNull(file.getOriginalFilename())
                                              .split("\\.")[1];

            Files.copy(file.getInputStream(),
                    fileStoragePath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public FileResponse uploadSingleFile(MultipartFile file,HttpServletRequest request) {
        try{
            String fileName=uploadFile(file);
            String fullImageUrl= generateUrl(fileName,request);
            return FileResponse.builder().downloadUrl(generateDownloadUrl(fileName,request)).type("png").size(file.getSize()).name(fileName).fullUrl(fullImageUrl).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> uploadMultipleFile(MultipartFile[] files) {
        List<String> fileNames=new ArrayList<>();
        for(var file:files){
            fileNames.add(uploadFile(file));
        }
        return fileNames;
    }

    @Override
    public ResponseEntity<Resource> serveFile(String fileName, HttpServletRequest request) {
        try{
            Path imagePath=Path.of(fileStorageLocation).resolve(fileName);
            Resource resourceUrl=new UrlResource(imagePath.toUri());
            if(resourceUrl.exists()){
                return ResponseEntity.ok()
                               .contentType(MediaType.parseMediaType("image/jpeg"))
                               .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resourceUrl.getFilename()+"\"").body(resourceUrl);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void deleteByName(String fileName) {
        try{
            Path deleteDestination=Path.of(fileStorageLocation).resolve(fileName);
            if(Files.exists(deleteDestination)){
                Files.delete(deleteDestination);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
