package com.dm.library.user.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	protected Long id;

	protected LocalDateTime createdOn;

	protected LocalDateTime updatedOn;
	
	@PrePersist
	public void prePersist() {
		createdOn = LocalDateTime.now();
		updatedOn = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedOn = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}



}
