package webbrain.incomeexpenseapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long size;

    /**
     * TRUE - UPLOAD TO DB, FALSE - FILE SYSTEM
     */
    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private byte[] data;

    @Column(nullable = false)
    private String path;

    public Attachment(String name, String originalName, String contentType, Long size, boolean status, byte[] data) {
        this.name = name;
        this.originalName = originalName;
        this.contentType = contentType;
        this.size = size;
        this.status = status;
        this.data = data;
    }

    public Attachment(String name, String originalName, String contentType, Long size, boolean status, String path) {
        this.name = name;
        this.originalName = originalName;
        this.contentType = contentType;
        this.size = size;
        this.status = status;
        this.path = path;
    }
}
