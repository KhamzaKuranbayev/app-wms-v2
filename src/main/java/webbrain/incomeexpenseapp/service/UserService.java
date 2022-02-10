package webbrain.incomeexpenseapp.service;

import webbrain.incomeexpenseapp.dto.UserCreateDto;
import webbrain.incomeexpenseapp.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User save(UserCreateDto dto);

    List<User> findAll();

    User findById(Long id);

    User edit(Long id, UserCreateDto dto);

    void delete(Long id);

    User bindRole(Long id, Set<Long> roleIds);

    List<User> findRoleName(Set<String> name);
}
