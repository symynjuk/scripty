package co.inventorsoft.scripty.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author lzabidovsky 
 */
@Entity
@Table(name = "projects")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	Long id;
	
	@Column(nullable = false, length = 50)
	String name;

	String description;
	
	String path;
	
	Boolean visibility;
	
	Boolean archive;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	User user;	
}
