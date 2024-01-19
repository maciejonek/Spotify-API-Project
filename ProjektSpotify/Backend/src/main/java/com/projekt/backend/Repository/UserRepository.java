package com.projekt.backend.Repository;

import com.projekt.backend.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
