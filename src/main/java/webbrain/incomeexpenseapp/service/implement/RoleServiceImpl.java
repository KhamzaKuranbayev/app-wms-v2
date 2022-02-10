package webbrain.incomeexpenseapp.service.implement;

import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.RoleCreateDto;
import webbrain.incomeexpenseapp.entity.Role;
import webbrain.incomeexpenseapp.exception.RoleNotFoundException;
import webbrain.incomeexpenseapp.repository.RoleRepository;
import webbrain.incomeexpenseapp.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(RoleCreateDto dto) {
        Role role = new Role(
                dto.getName()
        );
        Role savedRole = roleRepository.save(role);
        return savedRole;
    }

    @Override
    public List<Role> findAll() {
        List<Role> findAll = roleRepository.findAll();
        return findAll;
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty())
            throw new RoleNotFoundException("Such role id {" + id + "}");
        Role role = optionalRole.get();
        return role;
    }

    @Override
    public Role edit(Long id, RoleCreateDto dto) {

        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty())
            throw new RoleNotFoundException("Such role id {" + id + "}");
        Role role = optionalRole.get();

        if(dto.getName() != null && !dto.getName().equals(role.getName())){
            role.setName(dto.getName());
        }
        return role;
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
