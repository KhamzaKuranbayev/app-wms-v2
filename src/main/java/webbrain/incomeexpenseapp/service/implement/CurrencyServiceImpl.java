package webbrain.incomeexpenseapp.service.implement;

import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.CurrencyCreateDto;
import webbrain.incomeexpenseapp.entity.Currency;
import webbrain.incomeexpenseapp.exception.CurrencyNotFoundException;
import webbrain.incomeexpenseapp.repository.CurrencyRepository;
import webbrain.incomeexpenseapp.service.CurrencyService;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency save(CurrencyCreateDto dto) {
        Currency currency = new Currency(
                dto.getName()
        );
        Currency savedCurrency = currencyRepository.save(currency);
        return savedCurrency;
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> findAll = currencyRepository.findAll();
        return findAll;
    }

    @Override
    public Currency findById(Long id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty())
            throw new CurrencyNotFoundException("Such currency id {" + id + "} not found");
        Currency currency = optionalCurrency.get();
        return currency;
    }

    @Override
    public Currency edit(Long id, CurrencyCreateDto dto) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty())
            throw new CurrencyNotFoundException("Such currency id{" + id + "} not found");
        Currency currency = optionalCurrency.get();
        if(dto.getName() != null && !dto.getName().equals(currency.getName())){
            currency.setName(dto.getName());
        }
        return currency;
    }

    @Override
    public void delete(Long id) {
        currencyRepository.deleteById(id);
    }
}
