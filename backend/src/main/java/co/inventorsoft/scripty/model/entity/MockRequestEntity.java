package co.inventorsoft.scripty.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@Entity
@Table(name = "requests")
public class MockRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int status;

    @Column
    private String contentType;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "headers")
    @MapKeyColumn(name="header_key", length=50)
    @Column(name="header_value")
    private Map<String, String> headers;

    @Column
    String body;

}

