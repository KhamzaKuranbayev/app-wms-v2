package webbrain.incomeexpenseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {
    private String name;
    private boolean active;
    private Long parentId;
}
