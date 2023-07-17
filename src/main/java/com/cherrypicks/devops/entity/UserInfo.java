package com.cherrypicks.devops.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"userName","birth"},callSuper=false)
@Entity
@Table(name = "user_info")
public class UserInfo extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = -196151784318297751L;
	
	@Id
    @Column(name = "user_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)//id自增
	private Long userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_pwd")
	private String userPwd;
	
	@Column(name = "userAge")
	private Integer userAge;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "birth")
	private Date birth;
	
}
