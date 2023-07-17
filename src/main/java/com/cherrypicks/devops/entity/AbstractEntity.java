package com.cherrypicks.devops.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 4259646590369081170L;

	@Column(name = "is_deleted")
	private Integer isDeleted;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "updated_by")
	private String updatedBy;	
	

}
