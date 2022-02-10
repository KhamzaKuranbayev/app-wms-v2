package webbrain.incomeexpenseapp.controller;

import org.springframework.web.bind.annotation.*;
import webbrain.incomeexpenseapp.dto.CurrencyCreateDto;
import webbrain.incomeexpenseapp.entity.Currency;
import webbrain.incomeexpenseapp.service.CurrencyService;

import java.util.List;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @RequestMapping(value = "/api/v1/currencies", method = RequestMethod.POST)
    public Currency save(@RequestBody CurrencyCreateDto dto){
        Currency currency = currencyService.save(dto);
        return currency;
    }

    @RequestMapping(value = "/api/v1/currencies", method = RequestMethod.GET)
    public List<Currency> findAll(){
        List<Currency> currencyList = currencyService.findAll();
        return currencyList;
    }

    @RequestMapping(value = "/api/v1/currencies/{id}", method = RequestMethod.GET)
    public Currency findById(@PathVariable("id") Long id){
        Currency currency = currencyService.findById(id);
        return currency;
    }

    @RequestMapping(value = "/api/v1/currencies/{id}", method = RequestMethod.PUT)
    public Currency edit(@PathVariable("id") Long id, CurrencyCreateDto dto){
        Currency currency = currencyService.edit(id,dto);
        return currency;
    }

    @RequestMapping(value = "/api/v1/currencies/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        currencyService.delete(id);
        return "Successfully deleted";
    }

}
