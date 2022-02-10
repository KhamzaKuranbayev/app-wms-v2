package webbrain.incomeexpenseapp.controller;

import org.springframework.web.bind.annotation.*;
import webbrain.incomeexpenseapp.dto.CategoryCreateDto;
import webbrain.incomeexpenseapp.entity.Category;
import webbrain.incomeexpenseapp.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/api/v1/categories", method = RequestMethod.POST)
    public Category save(@RequestBody CategoryCreateDto dto){
        Category category = categoryService.save(dto);
        return category;
    }

    @RequestMapping(value = "/api/v1/categories", method = RequestMethod.GET)
    public List<Category> findAll(){
        List<Category> categoryList = categoryService.findAll();
        return categoryList;
    }

    @RequestMapping(value = "/api/v1/categories/{id}", method = RequestMethod.GET)
    public Category findById(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        return category;
    }

    @RequestMapping(value = "/api/v1/categories{id}", method = RequestMethod.PUT)
    public Category edit(@PathVariable("id") Long id, CategoryCreateDto dto){
        Category category = categoryService.edit(id, dto);
        return category;
    }

    @RequestMapping(value = "/api/v1/categories{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        categoryService.delete(id);
        return "Successfully deleted";
    }

}
