package webbrain.incomeexpenseapp.service.implement;

import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.CategoryCreateDto;
import webbrain.incomeexpenseapp.entity.Category;
import webbrain.incomeexpenseapp.exception.CategoryNotFoundException;
import webbrain.incomeexpenseapp.repository.CategoryRepository;
import webbrain.incomeexpenseapp.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(CategoryCreateDto dto) {
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getParentId());
        if (optionalCategory.isEmpty())
            throw new CategoryNotFoundException("Such category id{" + dto.getParentId() + "}");
        Category category1 = optionalCategory.get();
        Category category = new Category(
                dto.getName(),
                category1,
                dto.isActive()
        );
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> findAll = categoryRepository.findAll();
        return findAll;
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            throw new CategoryNotFoundException("Such category id{" + id + "}");
        Category category = optionalCategory.get();
        return category;
    }

    @Override
    public Category edit(Long id, CategoryCreateDto dto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            throw new CategoryNotFoundException("Such category id{" + id + "}");
        Optional<Category> categoryOptional = categoryRepository.findById(dto.getParentId());
        if (categoryOptional.isEmpty())
            throw new CategoryNotFoundException("Such parent id{" + dto.getParentId() + "}");
        Category category1 = categoryOptional.get();
        Category category = optionalCategory.get();
        if (dto.getName() != null && !dto.getName().equals(category.getName())) {
            category.setName(dto.getName());
        }
        if (dto.getParentId() != null && !dto.getParentId().equals(category.getParent().getId())) {
            category.setParent(category1);
        }
        category.setActive(dto.isActive());
        return category;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
