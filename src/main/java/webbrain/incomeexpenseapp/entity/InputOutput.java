package webbrain.incomeexpenseapp.entity;

import lombok.*;
import org.hibernate.Hibernate;
import webbrain.incomeexpenseapp.enums.InputOutputType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "input_output")
public class InputOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    private Werehouse werehouse;

    @ManyToOne
    private User supplier;

    @ManyToOne
    private Currency currency;

    @Column(nullable = false)
    private String factureNumber;

    @Column(nullable = false)
    private UUID code;

    @Enumerated(EnumType.STRING)
    private InputOutputType type = InputOutputType.INPUT;

    public InputOutput(LocalDateTime date, Werehouse werehouse, User supplier, Currency currency, String factureNumber, UUID code, InputOutputType type) {
        this.date = date;
        this.werehouse = werehouse;
        this.supplier = supplier;
        this.currency = currency;
        this.factureNumber = factureNumber;
        this.code = code;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InputOutput that = (InputOutput) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
