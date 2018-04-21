package rest.managing.social.controller;

import org.hibernate.boot.jaxb.internal.UrlXmlSource;
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
public class EditGroupController {

    private FriendRepository friendRepository;
    private UserRepository userRepository;

    private int groupId;
    private User searchUser;
    private List<User> searchUsers;

    public EditGroupController(FriendRepository friendRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/editGroup")
    public String editUser(@RequestParam int id, Model model) {
        Optional<Friend> optionalFriend = friendRepository.findById(id);

        if (optionalFriend.isPresent()) {
            Friend presentGroup = optionalFriend.get();
            List<User>users = presentGroup.getUsers();
            groupId = presentGroup.getId();
            model.addAttribute("group", presentGroup);
            model.addAttribute("users", users);
            model.addAttribute("searchUser", searchUser);
            model.addAttribute("searchUsers", searchUsers);
            return "editGroup";
        } else
            return "index";
    }

    @PostMapping("/editgroup")
    public String edituser(Friend friend) {
        Optional<Friend> optionalFriend = friendRepository.findById(groupId);

        if (optionalFriend.isPresent()) {
            Friend presentGroup = optionalFriend.get();
            presentGroup.setId(groupId);
            presentGroup.setUsername(friend.getUsername());

            friendRepository.save(presentGroup);

            return "redirect:/groups";
        } else
            return "index";
    }

    @GetMapping("/addUserToGroup")
    public String addUserToGroup(@RequestParam int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Friend> optionalFriend = friendRepository.findById(groupId);

        if (optionalUser.isPresent() && optionalFriend.isPresent()) {
            User presentUser = optionalUser.get();
            Friend presentFriend = optionalFriend.get();

            presentUser.getFriends().add(presentFriend);
            presentFriend.getUsers().add(presentUser);

            userRepository.save(presentUser);
            friendRepository.save(presentFriend);

            return "redirect:/editGroup?id=" + groupId;
        }else
            return "redirect:/";
    }

    @GetMapping("/removeUserFromGroup")
    public String removeGroupFromUser(@RequestParam int id) {
        Optional<Friend> optionalFriend = friendRepository.findById(groupId);
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalFriend.isPresent() && optionalUser.isPresent()) {
            Friend presentFriend = optionalFriend.get();
            User presentUser = optionalUser.get();

            presentFriend.getUsers().remove(presentUser);
            presentUser.getFriends().remove(presentFriend);

            friendRepository.save(presentFriend);
            userRepository.save(presentUser);

            return "redirect:/editGroup?id=" + groupId;
        }else
            return "redirect:/";
    }

    @PostMapping("/searchUserByName")
    public String search(User user, Model model) {
        searchUser = userRepository.findByUserName(user.getUserName());
        searchUsers = new ArrayList<>();
        searchUsers.add(searchUser);
        model.addAttribute("searchUsers", searchUsers);
        System.out.println(searchUser.getUserName());
        return "redirect:/editGroup?id=" + groupId;
    }

    @PostMapping("/searchAllUsers")
    public String searchAll(Model model) {
        searchUsers = userRepository.findAll();
        model.addAttribute("searchUsers", searchUsers);
        return "redirect:/editGroup?id=" + groupId;
    }

}
