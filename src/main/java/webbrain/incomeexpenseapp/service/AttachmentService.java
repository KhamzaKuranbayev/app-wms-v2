package webbrain.incomeexpenseapp.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import webbrain.incomeexpenseapp.dto.AttachmentCreateDto;
import webbrain.incomeexpenseapp.entity.Attachment;

import java.net.MalformedURLException;

public interface AttachmentService {
    Attachment uploadFileToDB(MultipartFile multipartFile);
    Attachment uploadFileToRemoteStorage(MultipartFile multipartFile);

    Resource downloadFile(String fileName) throws ClassNotFoundException, MalformedURLException;
}
