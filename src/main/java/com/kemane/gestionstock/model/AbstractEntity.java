package com.kemane.gestionstock.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@CreatedDate
	@Column(name = "date_creation")
	private Instant creationDate;
	
	//@LastModifiedDate
	@Column(name = "date_modification")
	private Instant lastModifiedDate;

	@PrePersist
	void prePersist(){
		creationDate = Instant.now();
	}
	@PreUpdate
	void preUpdate(){
		lastModifiedDate = Instant.now();
	}
}
