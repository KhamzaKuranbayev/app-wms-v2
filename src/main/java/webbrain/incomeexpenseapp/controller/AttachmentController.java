package webbrain.incomeexpenseapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webbrain.incomeexpenseapp.dto.AttachDataResponse;
import webbrain.incomeexpenseapp.dto.Response;
import webbrain.incomeexpenseapp.entity.Attachment;
import webbrain.incomeexpenseapp.service.AttachmentService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @RequestMapping(value = "/api/v1/attachment/upload_file", method = RequestMethod.POST)
    public Response uploadFile(@RequestParam("file") MultipartFile multipartFile,
                               @RequestParam("status") boolean toSystem) {
        Attachment attachment;
        if (toSystem) {
            attachment = attachmentService.uploadFileToRemoteStorage(multipartFile);
        } else {
            attachment = attachmentService.uploadFileToDB(multipartFile);
        }

        if (attachment == null)
            return new Response(false, "Something went wrong", HttpStatus.BAD_REQUEST);

        AttachDataResponse attachData = new AttachDataResponse(
                attachment.getId(),
                attachment.getPath(),
                attachment.getContentType(),
                attachment.getName(),
                toSystem
        );

        return new Response(true, "File Uploaded", attachData, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/api/v1/attachment/downloadFile/{fileName:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFileName(@PathVariable("fileName") String fileName, HttpServletRequest request) throws IOException, ClassNotFoundException {
        Resource resource = attachmentService.downloadFile(fileName);
        String type = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(type)).
                body(resource);
    }


}
