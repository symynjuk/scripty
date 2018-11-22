package co.inventorsoft.scripty.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Map;

/**
 * @author A1lexen
 */

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

    @Column
    private String token;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = MapConverter.class)
    private Map<String, String> headers;

    @Column(columnDefinition = "TEXT")
    String body;

}

