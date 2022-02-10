package webbrain.incomeexpenseapp.controller;

import org.springframework.web.bind.annotation.*;
import webbrain.incomeexpenseapp.dto.RoleCreateDto;
import webbrain.incomeexpenseapp.entity.Role;
import webbrain.incomeexpenseapp.service.RoleService;

import java.util.List;

@RestController
public class RoleController {

    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/api/v1/roles", method = RequestMethod.POST)
    public Role save(@RequestBody RoleCreateDto dto){
        Role saveRole = roleService.save(dto);
        return saveRole;
    }

    @RequestMapping(value = "/api/v1/roles", method = RequestMethod.GET)
    public List<Role> findAll(){
        List<Role> roleList = roleService.findAll();
        return roleList;
    }

    @RequestMapping(value = "/api/v1/roles{id}", method = RequestMethod.GET)
    public Role findById(@PathVariable("id") Long id){
        Role role = roleService.findById(id);
        return role;
    }

    @RequestMapping(value = "/api/v1/roles/{id}", method = RequestMethod.PUT)
    public Role edit(@PathVariable("id") Long id, @RequestBody RoleCreateDto dto){
        Role role = roleService.edit(id, dto);
        return role;
    }

    @RequestMapping(value = "/api/v1/roles/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        roleService.delete(id);
        return "Successfully deleted";
    }




}
