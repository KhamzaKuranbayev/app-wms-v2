package webbrain.incomeexpenseapp.service.implement;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import webbrain.incomeexpenseapp.dto.AttachmentCreateDto;
import webbrain.incomeexpenseapp.entity.Attachment;
import webbrain.incomeexpenseapp.exception.AttachmentNotFoundException;
import webbrain.incomeexpenseapp.properties.StorageProperties;
import webbrain.incomeexpenseapp.repository.AttachmentRepository;
import webbrain.incomeexpenseapp.service.AttachmentService;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final Path fileLocation;

    public AttachmentServiceImpl(StorageProperties storageProperties,
                                 AttachmentRepository attachmentRepository) {
        this.fileLocation = Paths.get(storageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.attachmentRepository = attachmentRepository;
    }

    @SneakyThrows
    @Override
    public Attachment uploadFileToDB(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();

        if (originalFilename.contains(".."))
            throw new AttachmentNotFoundException("Uzr, file nomida xatolik bo'ldi " + originalFilename);

        String uniqueFileName = System.currentTimeMillis() + "_" + originalFilename;

        Attachment attachment = new Attachment(
                uniqueFileName,
                originalFilename,
                multipartFile.getContentType(),
                multipartFile.getSize(),
                true,
                multipartFile.getBytes());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        return savedAttachment;
    }

    @SneakyThrows
    @Override
    public Attachment uploadFileToRemoteStorage(MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();

        if (filename.contains(".."))
            throw new AttachmentNotFoundException("Uzr, file nomida xatolik bo'ldi " + filename);

        // my_icon.jpg
        Path path = this.fileLocation.resolve(filename);

        // FILE SYSTEMAGA NUSXALANDI
        Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Attachment attachment = new Attachment(
                System.currentTimeMillis() + "_" + filename,
                filename,
                multipartFile.getContentType(),
                multipartFile.getSize(),
                false,
                path.toString());
        return attachmentRepository.save(attachment);
    }


    @Override
    public Resource downloadFile(String fileName) throws ClassNotFoundException, MalformedURLException {
        Path path = this.fileLocation.resolve(fileName);
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists()) {
            return resource;
        }
        throw new ClassNotFoundException("File Not Found");

    }
}
