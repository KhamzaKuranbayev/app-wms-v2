package webbrain.incomeexpenseapp.service.implement;

import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.WerehouseCreateDto;
import webbrain.incomeexpenseapp.entity.Werehouse;
import webbrain.incomeexpenseapp.exception.WerehouseNotFoundException;
import webbrain.incomeexpenseapp.repository.WarehouseRepository;
import webbrain.incomeexpenseapp.service.WerehouseService;

import java.util.List;
import java.util.Optional;

@Service
public class WerehouseServiceImpl implements WerehouseService {

    private final WarehouseRepository warehouseRepository;

    public WerehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Werehouse save(WerehouseCreateDto dto) {
        Werehouse savedWerehouse = new Werehouse(
                dto.getName(),
                dto.isActive()
        );
        return savedWerehouse;
    }

    @Override
    public List<Werehouse> findAll() {
        List<Werehouse> findAll = warehouseRepository.findAll();
        return findAll;
    }

    @Override
    public Werehouse findById(Long id) {
        Optional<Werehouse> optionalWerehouse = warehouseRepository.findById(id);
        if (optionalWerehouse.isEmpty())
            throw new WerehouseNotFoundException("Such werehouse id{" + id + "} not found");
        Werehouse werehouse = optionalWerehouse.get();
        return werehouse;
    }

    @Override
    public Werehouse edit(Long id, WerehouseCreateDto dto) {
        Optional<Werehouse> optionalWerehouse = warehouseRepository.findById(id);
        if (optionalWerehouse.isEmpty())
            throw new WerehouseNotFoundException("Such werehouse id{" + id + "} not found");
        Werehouse werehouse = optionalWerehouse.get();
        if(dto.getName() != null && !dto.getName().equals(werehouse.getName())){
            werehouse.setName(dto.getName());
        }
        werehouse.setActive(dto.isActive());
        return werehouse;
    }

    @Override
    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }
}
