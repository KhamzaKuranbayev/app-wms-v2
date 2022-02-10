package webbrain.incomeexpenseapp.controller;

import org.springframework.web.bind.annotation.*;
import webbrain.incomeexpenseapp.dto.UserCreateDto;
import webbrain.incomeexpenseapp.entity.User;
import webbrain.incomeexpenseapp.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/v1/users", method = RequestMethod.POST)
    public User save(@RequestBody UserCreateDto dto){
        User user = userService.save(dto);
        return user;
    }

    @RequestMapping(value = "/api/v1/users", method = RequestMethod.GET)
    public List<User> findAll(){
        List<User> userList = userService.findAll();
        return userList;
    }

    @RequestMapping(value = "/api/v1/users/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping(value = "/api/v1/users{id}", method = RequestMethod.PUT)
    public User edit(@PathVariable("id") Long id, UserCreateDto dto){
        User user = userService.edit(id, dto);
        return user;
    }

    @RequestMapping(value = "/api/v1/users{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "Successfully deleted";
    }

    @RequestMapping(value = "/api/v1/users{id}/bind_role", method = RequestMethod.POST)
    public User bindRole(@PathVariable("id") Long id,
                         @RequestParam Set<Long> roleIds){
        User user = userService.bindRole(id, roleIds);
        return user;
    }

    @RequestMapping(value = "/api/v1/users/findRoleName", method = RequestMethod.POST)
    public List<User> findRoleName(@RequestParam Set<String> name){
        List<User> userList = userService.findRoleName(name);
        return userList;
    }
}
