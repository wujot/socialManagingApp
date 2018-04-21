package rest.managing.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rest.managing.social.model.Friend;
import rest.managing.social.model.User;
import rest.managing.social.repository.FriendRepository;
import rest.managing.social.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class GroupsController {

    private UserRepository userRepository;
    private FriendRepository friendRepository;

    public GroupsController(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @GetMapping("/groups")
    public String groups(Model model) {

        List<Friend> friends = friendRepository.findAll();
        List<User> users = userRepository.findAll();
        model.addAttribute("groups", friends);
        model.addAttribute("group", new Friend());
        model.addAttribute("numberOfUsers", users.size());
        return"groups";
    }

    @PostMapping("/addGroup")
    public String addUser(Friend friend) {
        friendRepository.save(friend);
        return "redirect:/groups";
    }

    @GetMapping("/deleteGroup")
    public String deleteUser(@RequestParam int id) {
        Optional<Friend> optionalFriend = friendRepository.findById(id);

        if (optionalFriend.isPresent()) {
            Friend presentFriend = optionalFriend.get();
            friendRepository.delete(presentFriend);
            return "redirect:/groups";
        } else
            return "index";
    }
}
