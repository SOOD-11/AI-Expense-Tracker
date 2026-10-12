package com.example.userService.Repository;

import com.example.userService.entities.UserInfoDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfoDto,String> {



    // why we use it optional becasue it will nicely handle null  pointer exception . like if its not present what to do lease
    Optional<UserInfoDto> findByUserId(String s);




}
