package webbrain.incomeexpenseapp.service;

import webbrain.incomeexpenseapp.dto.RoleCreateDto;
import webbrain.incomeexpenseapp.entity.Role;

import java.util.List;

public interface RoleService {
    Role save(RoleCreateDto dto);

    List<Role> findAll();

    Role findById(Long id);

    Role edit(Long id, RoleCreateDto dto);

    void delete(Long id);
}
