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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EditUserController {

    private UserRepository userRepository;
    private FriendRepository friendRepository;
    private int userId;
    private Friend searchGroup;
    private List<Friend> searchGroups;

    public EditUserController(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @GetMapping("/editUser")
    public String editUser(@RequestParam int id, Model model) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User presentUser = optionalUser.get();
            List<Friend> groups = presentUser.getFriends();
            userId = presentUser.getId();
            model.addAttribute("user", presentUser);
            model.addAttribute("groups", groups);
            model.addAttribute("searchGroup", searchGroup);
            model.addAttribute("searchGroups", searchGroups);
            return "editUser";
        } else
            return "index";
    }

    @PostMapping("/edituser")
    public String edituser(User user) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User presentUser = optionalUser.get();
            presentUser.setId(userId);
            presentUser.setUserName(user.getUserName());
            presentUser.setPassword(user.getPassword());
            presentUser.setFirstName(user.getFirstName());
            presentUser.setSurname(user.getSurname());
            presentUser.setDob(user.getDob());

            userRepository.save(presentUser);

            return "redirect:/users";
        } else
            return "index";
    }

    @PostMapping("/searchByName")
    public String search(Friend friend, Model model) {
        searchGroup = friendRepository.findByUsername(friend.getUsername());
        searchGroups = new ArrayList<>();
        searchGroups.add(searchGroup);
        model.addAttribute("searchGroups", searchGroups);
        return "redirect:/editUser?id=" + userId;
    }

    @PostMapping("/searchAllGroups")
    public String searchAll(Model model) {
        searchGroups = friendRepository.findAll();
        model.addAttribute("searchGroups", searchGroups);
        return "redirect:/editUser?id=" + userId;
    }

    @GetMapping("/backToUsers")
    public String backToUsers() {
        searchGroups = null;
        searchGroup = null;
        return "redirect:/users";
    }

    @GetMapping("/addGroupToUser")
    public String addGroupToUser(@RequestParam int id) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Friend> optionalFriend = friendRepository.findById(id);

        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            User presentUser = optionalUser.get();
            Friend presentFriend = optionalFriend.get();

            presentUser.getFriends().add(presentFriend);
            presentFriend.getUsers().add(presentUser);

            userRepository.save(presentUser);
            friendRepository.save(presentFriend);

            return "redirect:/editUser?id=" + userId;
        }else
            return "redirect:/";
    }

    @GetMapping("/removeGroupFromUser")
    public String removeGroupFromUser(@RequestParam int id) {
        Optional<Friend> optionalFriend = friendRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalFriend.isPresent() && optionalUser.isPresent()) {
            Friend presentFriend = optionalFriend.get();
            User presentUser = optionalUser.get();

            presentFriend.getUsers().remove(presentUser);
            presentUser.getFriends().remove(presentFriend);

            friendRepository.save(presentFriend);
            userRepository.save(presentUser);

            return "redirect:/editUser?id=" + userId;
        }else
            return "redirect:/";
    }
}
