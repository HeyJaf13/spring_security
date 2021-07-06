package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/admin")
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "admin";
	}

	@GetMapping("/addnew")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "/adduser";
	}

	@PostMapping("/adduser")
	public String saveUser(@RequestParam("username") String username,
						   @RequestParam("city") String city,
						   @RequestParam("email") String email,
						   @RequestParam("password") String password,
						   @RequestParam(required = false, name = "ADMIN") String ADMIN,
						   @RequestParam(required = false, name = "USER") String USER) {

		Set<Role> roles = new HashSet<>();
		if (ADMIN != null) {
			roles.add(new Role(2, ADMIN));
		}
		if (USER != null) {
			roles.add(new Role(1, USER));
		}
		if(ADMIN==null&&USER==null){
			roles.add(new Role(1, USER));
		}
		User user = new User(username,city,email,password,roles);
		userService.addUser(user);
		return "redirect:/admin";
	}

	@GetMapping("/edituser/{id}")
	public String editUser(Model model,
						   @PathVariable("id") int id) {
		model.addAttribute("user", userService.getUser(id));
		return "edituser";
	}

	@PostMapping("/{id}")
	public String editUser(@ModelAttribute("user") User user, @PathVariable("id") int id,
						   @RequestParam(required = false, name = "ADMIN") String ADMIN,
						   @RequestParam(required = false, name = "USER") String USER)  {
		Set<Role> roles = new HashSet<>();
		if (ADMIN != null) {
			roles.add(new Role(2, ADMIN));
		}
		if (USER != null) {
			roles.add(new Role(1, USER));
		}
		if(ADMIN==null&&USER==null){
			roles.add(new Role(1, USER));
		}
		user.setRoles(roles);
		userService.editUser(user);
		return "redirect:/admin";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(userService.getUser(id));
		return "redirect:/admin";
	}

	@GetMapping("/user")
	public ModelAndView showUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	@GetMapping("/")
	public String login(){

		return "login";
	}
}
//admin-user-edituser -adduser -