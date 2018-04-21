package rest.managing.social.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rest.managing.social.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);
}
