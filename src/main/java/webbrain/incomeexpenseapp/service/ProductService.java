package webbrain.incomeexpenseapp.service;

import webbrain.incomeexpenseapp.dto.ProductCreateDto;
import webbrain.incomeexpenseapp.entity.Product;

public interface ProductService {
    Product save(ProductCreateDto dto);
}
