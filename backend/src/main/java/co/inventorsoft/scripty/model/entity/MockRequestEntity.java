package co.inventorsoft.scripty.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
    private String method;

    @Column
    private String contentType;

    @Column
    @ColumnDefault("utf-8")
    private String charset;

    @Column
    private String token;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = MapConverter.class)
    private Map<String, String> headers;

    @Column(columnDefinition = "TEXT")
    String body;

}

