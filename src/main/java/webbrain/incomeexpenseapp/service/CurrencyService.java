package webbrain.incomeexpenseapp.service;

import webbrain.incomeexpenseapp.dto.CurrencyCreateDto;
import webbrain.incomeexpenseapp.entity.Currency;

import java.util.List;

public interface CurrencyService {
    Currency save(CurrencyCreateDto dto);

    List<Currency> findAll();

    Currency findById(Long id);

    Currency edit(Long id, CurrencyCreateDto dto);

    void delete(Long id);
}
