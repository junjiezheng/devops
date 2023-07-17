package com.cherrypicks.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cherrypicks.devops.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{


	@Query(value="SELECT gender FROM user_info WHERE UPPER(user_name)=UPPER(:name)",nativeQuery=true)
	String getGenderByName();	

}
