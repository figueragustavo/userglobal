package com.global.users.DAO;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.users.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {
	
	@Transactional
	@Modifying
	@Query("update User u set u.token = ?1 where  u.id = ?2")
	int updateTokenById(String token, UUID id);
	
	Optional<User> findUserByEmail(String email);

}
