package webbrain.incomeexpenseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbrain.incomeexpenseapp.dto.ProductDto;
import webbrain.incomeexpenseapp.entity.InputOutputProduct;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InputOutputProductRepository extends JpaRepository<InputOutputProduct, Long> {
    List<InputOutputProduct> findAllByInputOutputId(Long inputOutputId);

    @Query("select new webbrain.incomeexpenseapp.dto.ProductDto(io.product.id, io.product.name, io.expireDate, io.amount, io.price, io.inputOutput) from " +
            " InputOutputProduct io where io.expireDate < ?1")
    List<ProductDto> selectAllExpireDateSoon(LocalDateTime afterWeek);

}
