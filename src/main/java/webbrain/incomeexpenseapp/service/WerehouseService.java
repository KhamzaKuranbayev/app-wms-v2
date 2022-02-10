package webbrain.incomeexpenseapp.service;

import webbrain.incomeexpenseapp.dto.WerehouseCreateDto;
import webbrain.incomeexpenseapp.entity.Werehouse;

import java.util.List;

public interface WerehouseService {
    Werehouse save(WerehouseCreateDto dto);

    List<Werehouse> findAll();

    Werehouse findById(Long id);

    Werehouse edit(Long id, WerehouseCreateDto dto);

    void delete(Long id);
}
