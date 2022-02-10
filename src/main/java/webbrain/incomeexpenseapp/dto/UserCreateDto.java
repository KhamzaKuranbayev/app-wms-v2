package webbrain.incomeexpenseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Long uniqueNumber;
    private String password;
    private Long werehouseId;
    private Set<Long> roleIds;

}
