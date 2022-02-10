package webbrain.incomeexpenseapp.controller;

import org.springframework.web.bind.annotation.*;
import webbrain.incomeexpenseapp.dto.WerehouseCreateDto;
import webbrain.incomeexpenseapp.entity.Werehouse;
import webbrain.incomeexpenseapp.service.WerehouseService;

import java.util.List;

@RestController
public class WerehouseController {

    private final WerehouseService werehouseService;

    public WerehouseController(WerehouseService werehouseService) {
        this.werehouseService = werehouseService;
    }


    @RequestMapping(value = "/api/v1/werehouses", method = RequestMethod.POST)
    public Werehouse save(@RequestBody WerehouseCreateDto dto){
        Werehouse werehouse = werehouseService.save(dto);
        return werehouse;
    }

    @RequestMapping(value = "/api/v1/werehouses", method = RequestMethod.GET)
    public List<Werehouse> findAll(){
        List<Werehouse> werehouseList = werehouseService.findAll();
        return werehouseList;
    }

    @RequestMapping(value = "/api/v1/werehouses/{id}", method = RequestMethod.GET)
    public Werehouse findById(@PathVariable("id") Long id){
        Werehouse werehouse = werehouseService.findById(id);
        return werehouse;
    }

    @RequestMapping(value = "/api/v1/werehouses/{id}", method = RequestMethod.PUT)
    public Werehouse edit(@PathVariable("id") Long id, WerehouseCreateDto dto){
        Werehouse werehouse = werehouseService.edit(id,dto);
        return werehouse;
    }

    @RequestMapping(value = "/api/v1/werehouses{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        werehouseService.delete(id);
        return "Successfully deleted";
    }



}
