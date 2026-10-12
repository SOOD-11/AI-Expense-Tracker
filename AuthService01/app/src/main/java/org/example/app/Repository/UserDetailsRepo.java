package org.example.app.Repository;

import org.example.app.Services.Userdetails;
import org.example.app.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserDetailsRepo extends CrudRepository<User,Long> {

public User findByUsername(String username);

}
