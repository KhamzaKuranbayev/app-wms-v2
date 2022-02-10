package webbrain.incomeexpenseapp.service.implement;

import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.ProductCreateDto;
import webbrain.incomeexpenseapp.entity.Attachment;
import webbrain.incomeexpenseapp.entity.Category;
import webbrain.incomeexpenseapp.entity.Measurement;
import webbrain.incomeexpenseapp.entity.Product;
import webbrain.incomeexpenseapp.exception.AttachmentNotFoundException;
import webbrain.incomeexpenseapp.exception.CategoryNotFoundException;
import webbrain.incomeexpenseapp.exception.MeasurementNotFoundException;
import webbrain.incomeexpenseapp.repository.AttachmentRepository;
import webbrain.incomeexpenseapp.repository.CategoryRepository;
import webbrain.incomeexpenseapp.repository.MeasurementRepository;
import webbrain.incomeexpenseapp.repository.ProductRepository;
import webbrain.incomeexpenseapp.service.ProductService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MeasurementRepository measurementRepository;
    private final AttachmentRepository attachmentRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              MeasurementRepository measurementRepository,
                              AttachmentRepository attachmentRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.measurementRepository = measurementRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Product save(ProductCreateDto dto) {
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getCategoryId());
        if (optionalCategory.isEmpty())
            throw new CategoryNotFoundException("Such category id {" + dto.getCategoryId() + "}");
        Category category = optionalCategory.get();
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(dto.getMeasurementId());
        if (optionalMeasurement.isEmpty())
            throw new MeasurementNotFoundException("Such measurement id {" + dto.getMeasurementId() + "}");
        Measurement measurement = optionalMeasurement.get();
        Set<Attachment> attachmentSet = new HashSet<>();
        for (Long attachmentId : dto.getAttachmentsId()) {
            Optional<Attachment> attachmentRepositoryById = attachmentRepository.findById(attachmentId);
            if (attachmentRepositoryById.isEmpty())
                throw new AttachmentNotFoundException("Such attachment id{" + attachmentId + "}");
            Attachment attachment = attachmentRepositoryById.get();
            attachmentSet.add(attachment);
        }

        Product product = new Product(
                dto.getName(),
                UUID.randomUUID(),
                category,
                measurement,
                attachmentSet);
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

}
