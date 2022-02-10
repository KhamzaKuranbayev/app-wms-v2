package webbrain.incomeexpenseapp.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webbrain.incomeexpenseapp.dto.ProductCreateDto;
import webbrain.incomeexpenseapp.entity.Product;
import webbrain.incomeexpenseapp.service.ProductService;

@RestController
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/api/v1/products", method = RequestMethod.POST)
    public Product save(@RequestBody ProductCreateDto dto){
        Product product = productService.save(dto);
        return product;
    }
}
