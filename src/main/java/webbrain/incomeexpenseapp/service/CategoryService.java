package webbrain.incomeexpenseapp.service;

import webbrain.incomeexpenseapp.dto.CategoryCreateDto;
import webbrain.incomeexpenseapp.entity.Category;

import java.util.List;

public interface CategoryService {
    Category save(CategoryCreateDto dto);

    List<Category> findAll();

    Category findById(Long id);

    Category edit(Long id, CategoryCreateDto dto);

    void delete(Long id);
}
