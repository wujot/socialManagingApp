package rest.managing.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.managing.social.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
    Friend findByUsername(String name);
}
