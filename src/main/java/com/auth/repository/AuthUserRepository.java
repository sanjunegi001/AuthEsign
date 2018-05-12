package com.auth.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;


import com.auth.model.User;

public interface AuthUserRepository extends CrudRepository<User, Serializable>{

	Iterable<User> findAll();

	public User save(User authusermodel);
}
