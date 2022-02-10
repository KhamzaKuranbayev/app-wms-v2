package webbrain.incomeexpenseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webbrain.incomeexpenseapp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
