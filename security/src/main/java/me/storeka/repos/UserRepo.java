package me.storeka.repos;

import me.storeka.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findUserByUsername(String username);

}
