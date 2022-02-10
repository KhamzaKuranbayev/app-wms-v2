package webbrain.incomeexpenseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentCreateDto {

    private String name;
    private String originalName;
    private String contentType;
    private Long size;
    private boolean status;
    private String data;
    private String path;

}
