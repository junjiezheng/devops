package com.cherrypicks.devops.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetails implements Serializable{

	private static final long serialVersionUID = 2622652337030523663L;
	
	@Id
    @Column(name = "client_id")
	private String clientId;
	
	@Column(name = "resource_ids")
	private String resourceIds;
	
	@Column(name = "client_secret")
	private String clientSecret;
	
	@Column(name = "scope")
	private String scope;
	
	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;
	
	@Column(name = "web_server_redirect_uri")
	private String webServerRedirectUri;
	
	@Column(name = "authorities")
	private String authorities;
	
	@Column(name = "access_token_validity")
	private Integer accessTokenValidity;
	
	@Column(name = "refresh_token_validity")
	private Integer refreshTokenValidity;
	
	@Column(name = "additional_information")
	private String additionalInformation;
	
	@Column(name = "autoapprove")
	private String autoapprove;

}
