package webbrain.incomeexpenseapp.service;

import webbrain.incomeexpenseapp.dto.InputOutputDto;
import webbrain.incomeexpenseapp.dto.Response;

import java.util.List;

public interface InputOutputService {
    Response save(InputOutputDto dto);
    Response saveDetail(Long id, List<InputOutputDto.DetailDto> dto);
}
