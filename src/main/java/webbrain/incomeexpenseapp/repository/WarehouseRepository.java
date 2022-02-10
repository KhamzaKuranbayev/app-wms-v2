package webbrain.incomeexpenseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webbrain.incomeexpenseapp.entity.Werehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Werehouse, Long> {
}
