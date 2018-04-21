package rest.managing.social.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;

    @ManyToMany
    @JoinTable(name = "user_group",
            joinColumns = { @JoinColumn(name = "friend_id")},
            inverseJoinColumns= { @JoinColumn(name = "user_id")})
    @JsonIgnore
    List<User> users;

    public Friend() {}

    public Friend(String username) {
        this.id = id;
        this.username = username;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
