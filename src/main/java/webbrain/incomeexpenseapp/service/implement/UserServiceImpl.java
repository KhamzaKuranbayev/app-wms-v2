package webbrain.incomeexpenseapp.service.implement;

import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.UserCreateDto;
import webbrain.incomeexpenseapp.entity.Role;
import webbrain.incomeexpenseapp.entity.User;
import webbrain.incomeexpenseapp.entity.Werehouse;
import webbrain.incomeexpenseapp.exception.RoleNotFoundException;
import webbrain.incomeexpenseapp.exception.UserNotFoundException;
import webbrain.incomeexpenseapp.exception.WerehouseNotFoundException;
import webbrain.incomeexpenseapp.repository.RoleRepository;
import webbrain.incomeexpenseapp.repository.UserRepository;
import webbrain.incomeexpenseapp.repository.WarehouseRepository;
import webbrain.incomeexpenseapp.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository,
                           WarehouseRepository warehouseRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User save(UserCreateDto dto) {

        Optional<Werehouse> optionalWerehouse = warehouseRepository.findById(dto.getWerehouseId());
        if (optionalWerehouse.isEmpty())
            throw new UserNotFoundException("Such user is{" + dto.getWerehouseId() + "}");
        Werehouse werehouse = optionalWerehouse.get();

        Set<Role> roles = new HashSet<>();
        for (Long roleId : dto.getRoleIds()) {
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            if (optionalRole.isEmpty())
                throw new RoleNotFoundException("Such role id{" + roleId + "}");
            Role role = optionalRole.get();
            roles.add(role);
        }
        User user = new User(
                dto.getFirstname(),
                dto.getLastname(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getUniqueNumber(),
                dto.getPassword(),
                werehouse,
                roles);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> findAll = userRepository.findAll();
        return findAll;
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException("Such user id {" + id + "}");
        User user = optionalUser.get();
        return user;
    }

    @Override
    public User edit(Long id, UserCreateDto dto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException("Such user id {" + id + "}");
        Optional<Werehouse> optionalWerehouse = warehouseRepository.findById(dto.getWerehouseId());
        if (optionalWerehouse.isEmpty())
            throw new WerehouseNotFoundException("Such werehouse id {" + dto.getWerehouseId() + "}");
        Set<Role> roles = new HashSet<>();
        for (Long roleId : dto.getRoleIds()) {
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            if (optionalRole.isEmpty())
                throw new RoleNotFoundException("Such role id {" + dto.getRoleIds() + "}");
            Role role = optionalRole.get();

        }
        Werehouse werehouse = optionalWerehouse.get();
        User user = optionalUser.get();
        if (dto.getFirstname() != null && !dto.getFirstname().equals(user.getFirstname())) {
            user.setFirstname(dto.getFirstname());
        }
        if (dto.getLastname() != null && !dto.getLastname().equals(user.getLastname())) {
            user.setLastname(dto.getLastname());
        }
        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null && !dto.getPhone().equals(user.getPhone())) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getUniqueNumber() != null && !dto.getUniqueNumber().equals(user.getUniqueNumber())) {
            user.setUniqueNumber(dto.getUniqueNumber());
        }
        if (dto.getWerehouseId() != null) {
            user.setWerehouse(werehouse);
        }
        if (dto.getRoleIds() != null) {
            user.setRoles(roles);
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User bindRole(Long id, Set<Long> roleIds) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException("Such user id {" + id + "}");
        User user = optionalUser.get();

        Set<Role> roleSet = new HashSet<>();
        for (Long roleId : roleIds) {
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            if (optionalRole.isEmpty())
                throw new RoleNotFoundException("Such role id {" + roleId + "}");
            Role role = optionalRole.get();
            roleSet.add(role);
        }
        Set<Role> roles = user.getRoles();
        roles.addAll(roleSet);
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> findRoleName(Set<String> name) {
        List<User> userList = userRepository.selectAllUserRoleName(name);
        return userList;
    }
}
