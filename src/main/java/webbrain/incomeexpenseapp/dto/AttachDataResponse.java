package webbrain.incomeexpenseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/8/2022
 * Time: 5:44 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachDataResponse {
    private Long id;
    private String path;
    private String contentType;
    private String name;
    private boolean fromSystem;


}
