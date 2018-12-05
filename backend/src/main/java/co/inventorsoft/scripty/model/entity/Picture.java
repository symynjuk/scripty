package co.inventorsoft.scripty.model.entity;

import com.mysql.cj.jdbc.Blob;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="images")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PICTURE_ID")
    Long id;

    @Column
    @Lob
    byte[] content;

    @Column
    String contentType;

    public boolean isEmpty() {
        return content.length == 0;
    }
}

