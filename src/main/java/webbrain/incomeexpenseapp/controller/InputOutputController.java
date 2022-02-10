package webbrain.incomeexpenseapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webbrain.incomeexpenseapp.dto.InputOutputDto;
import webbrain.incomeexpenseapp.dto.Response;
import webbrain.incomeexpenseapp.service.InputOutputService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/input_output")
@RequiredArgsConstructor
public class InputOutputController {
    private final InputOutputService inputOutputService;

    @PostMapping
    public Response save(@RequestBody InputOutputDto dto) {
        Response response = inputOutputService.save(dto);
        return response;
    }

    @PostMapping("/{input_output_id}/details")
    public Response saveDetail(@PathVariable("input_output_id") Long id, @RequestBody List<InputOutputDto.DetailDto> list) {
        Response response = inputOutputService.saveDetail(id, list);
        return response;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }


}
