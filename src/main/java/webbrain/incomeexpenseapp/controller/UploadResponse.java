package webbrain.incomeexpenseapp.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {

    private String name;
    private String downloadUrl;
    private String type;
    private long size;


}
