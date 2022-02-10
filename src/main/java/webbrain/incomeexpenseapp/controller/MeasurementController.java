package webbrain.incomeexpenseapp.controller;

import org.springframework.web.bind.annotation.*;
import webbrain.incomeexpenseapp.dto.MeasurementCreateDto;
import webbrain.incomeexpenseapp.entity.Measurement;
import webbrain.incomeexpenseapp.service.MeasurementService;

import java.util.List;

@RestController
public class MeasurementController {

    private final MeasurementService measurementService;
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @RequestMapping(value = "/api/v1/measurement", method = RequestMethod.POST)
    public Measurement save(@RequestBody MeasurementCreateDto dto){
        Measurement measurement = measurementService.save(dto);
        return measurement;
    }

    @RequestMapping(value = "/api/v1/measurement", method = RequestMethod.GET)
    public List<Measurement> findAll(){
        List<Measurement> measurementList = measurementService.findAll();
        return measurementList;
    }

    @RequestMapping(value = "/api/v1/measurement/{id}", method = RequestMethod.GET)
    public Measurement findById(@PathVariable("id") Long id){
        Measurement measurement = measurementService.findById(id);
        return measurement;
    }

    @RequestMapping(value = "/api/v1/measurement/{id}", method = RequestMethod.PUT)
    public Measurement edit(@PathVariable("id") Long id, MeasurementCreateDto dto){
        Measurement measurement = measurementService.edit(id, dto);
        return measurement;
    }

    @RequestMapping(value = "/api/v1/measurement/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        measurementService.delete(id);
        return "Successfully deleted";
    }

}
