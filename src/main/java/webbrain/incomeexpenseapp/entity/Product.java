package webbrain.incomeexpenseapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private UUID code;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Measurement measurement;

    @ManyToMany
    private Set<Attachment> attachments = new HashSet<>();

    private Integer expirePeriod;

    public Product(String name, UUID code, Category category, Measurement measurement, Set<Attachment> attachments) {
        this.name = name;
        this.code = code;
        this.category = category;
        this.measurement = measurement;
        this.attachments = attachments;
    }
}
