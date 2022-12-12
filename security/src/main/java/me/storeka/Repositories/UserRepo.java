package me.storeka.Repositories;

import me.storeka.Entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("from User u where u.username = :username")
    @EntityGraph(attributePaths = "roles")
    List<User> findUserWithRolesByUsername(String username);

    List<User> findUserByUsername(String username);

}
