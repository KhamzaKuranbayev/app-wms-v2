package webbrain.incomeexpenseapp.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.InputOutputDto;
import webbrain.incomeexpenseapp.dto.Response;
import webbrain.incomeexpenseapp.entity.InputOutput;
import webbrain.incomeexpenseapp.entity.InputOutputProduct;
import webbrain.incomeexpenseapp.entity.Werehouse;
import webbrain.incomeexpenseapp.enums.InputOutputType;
import webbrain.incomeexpenseapp.exception.CurrencyNotFoundException;
import webbrain.incomeexpenseapp.exception.UserNotFoundException;
import webbrain.incomeexpenseapp.exception.WerehouseNotFoundException;
import webbrain.incomeexpenseapp.repository.*;
import webbrain.incomeexpenseapp.service.InputOutputService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InputOutputServiceImpl implements InputOutputService {
    private final InputOutputRepository inputOutputRepository;
    private final InputOutputProductRepository inputOutputProductRepository;
    private final WarehouseRepository warehouseRepository;
    private final UserRepository supplierRepository;
    private final CurrencyRepository currencyRepository;
    private final ProductRepository productRepository;

    @Override
    public Response save(InputOutputDto dto) {
        InputOutput inputOutput = new InputOutput(
                LocalDateTime.now(),
                warehouseRepository.findById(dto.getWarehouseId()).orElseThrow(() -> new WerehouseNotFoundException("Warehouse not found")),
                supplierRepository.findById(dto.getSupplierId()).orElseThrow(() -> new UserNotFoundException("Supplier Not Found")),
                currencyRepository.findById(dto.getCurrencyId()).orElseThrow(() -> new CurrencyNotFoundException("Currency Not Found")),
                dto.getFactureNumber(),
                UUID.randomUUID(),
                dto.getType()
        );
        InputOutput savedInputOutput = inputOutputRepository.save(inputOutput);
        InputOutputType type = dto.getType();
        String message;
        if (type == InputOutputType.INPUT)
            message = "Input";
        else
            message = "Output";

        return new Response(true, message + " Saved!", savedInputOutput);
    }

    @Override
    public Response saveDetail(Long id, List<InputOutputDto.DetailDto> list) {
        InputOutput inputOutput = inputOutputRepository.findById(id).orElseThrow(() -> new RuntimeException("Input/Output Not found"));
        Set<InputOutputProduct> set = new HashSet<>();

        for (InputOutputDto.DetailDto dto : list) {
            InputOutputProduct inputOutputProduct = new InputOutputProduct(
                    productRepository.findById(dto.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found")),
                    inputOutput,
                    dto.getAmount(),
                    dto.getPrice(),
                    dto.getExpireDate()
            );
            set.add(inputOutputProduct);
        }

        if (set.size() > 0)
            inputOutputProductRepository.saveAll(set);

        return new Response(true, "Success");
    }
}
