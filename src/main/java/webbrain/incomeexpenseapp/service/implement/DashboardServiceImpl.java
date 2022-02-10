package webbrain.incomeexpenseapp.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.InputOutputDto;
import webbrain.incomeexpenseapp.dto.Response;
import webbrain.incomeexpenseapp.entity.InputOutput;
import webbrain.incomeexpenseapp.entity.InputOutputProduct;
import webbrain.incomeexpenseapp.enums.InputOutputType;
import webbrain.incomeexpenseapp.repository.InputOutputProductRepository;
import webbrain.incomeexpenseapp.repository.InputOutputRepository;
import webbrain.incomeexpenseapp.service.DashboardService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/8/2022
 * Time: 7:12 PM
 */

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final InputOutputRepository inputOutputRepository;
    private final InputOutputProductRepository inputOutputProductRepository;

    @Override
    public Response getIncomeByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type) {
        List<InputOutput> list = inputOutputRepository.selectIncomeByDateRange(start, end, type);
        List<InputOutputDto.GetDto> result = new ArrayList<>();

        for (InputOutput inputOutput : list) {
            List<InputOutputProduct> details = inputOutputProductRepository.findAllByInputOutputId(inputOutput.getId());
            InputOutputDto.GetDto dto = new InputOutputDto.GetDto(
                    inputOutput.getDate(),
                    inputOutput.getWerehouse(),
                    inputOutput.getSupplier(),
                    inputOutput.getCurrency(),
                    inputOutput.getCode(),
                    inputOutput.getType(),
                    details);
            result.add(dto);
        }
        return new Response(true, "Income Products by date range", result);
    }
}
