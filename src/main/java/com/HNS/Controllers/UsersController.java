package com.HNS.Controllers;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HNS.Entity.User;
import com.HNS.Entity.collaborators;
import com.HNS.Entity.products;
import com.HNS.Entity.stores;
import com.HNS.Repositories.UserRepositories;
import com.HNS.Repositories.collaboratorsRepositories;
import com.HNS.Repositories.productsRepositories;
import com.HNS.Repositories.storesRepositories;

@Controller
public class UsersController {
	@Autowired
private UserRepositories repo;
	
	@Autowired
	private storesRepositories storeRepo;
	@Autowired 
	private productsRepositories productRepo;
	@Autowired
	private collaboratorsRepositories collaboratorRepo;
	Iterable <products> ShowProducts()
	{
		Iterable <products> AllProducts = productRepo.findAll();
		return AllProducts;
	}
   @RequestMapping("/")
   public String index() {
      return "index";
   }
	@GetMapping("/erorr/{statuId}")
   public String erorr(@PathVariable("statuId") String status )
   {
	   
	   return "errorPage";
   }
   @GetMapping("/Login")
public String Login(Model model)
{
	   model.addAttribute("user",new User());
	   String Warrning = "";
	   model.addAttribute("Warrning",Warrning);
	return "Login";
}
   @GetMapping("/Register")
public String Register(Model model)
{
	   model.addAttribute("user",new User());
	   model.addAttribute("warning","");
	return "Register";
}
   

  
   @PostMapping("/greeting")
   public String sayHello(@ModelAttribute User user, Model model,HttpSession session) {
      Vector<User>us = new Vector<User>();
		us = repo.findByUserName(user.getUserName());
      if (!us.isEmpty())
      {
    	  model.addAttribute("warning","This UserName is Exist");
    	  return "Register";
      }
      else
      {
      repo.save(user);
      session.setAttribute("nameSession", user.getUserName());
	   session.setAttribute("UserIdSession", user.getId());
	  
      return "greeting";
      }
   }
   @PostMapping("/greetingLogin")
   public String greetingLogin(@ModelAttribute User user, Model model,HttpSession session) {
	   {
		   Iterable <User> Users;
		   Users = repo.findAll();
		   String Returned="Login";
		   String Warrning ="Wrong User Name or Password";
	
		   for (User us :Users)
		   {
			   
			   if (us.getUserName().equals(user.getUserName()) &&us.getPassword().equals(user.getPassword()))
			   {
				  
				   Vector<stores> st= new Vector<stores>();
				   if (us.getUserType()==1)
				   {
						   model.addAttribute("products",ShowProducts());
					   Returned = "greeting";
					   st = storeRepo.findByStoreOwnerAndStoreState(us.getId(), 2);
					   
					   //Get not onwed Stores
					   
					   Vector<collaborators> StoresNotOwned = collaboratorRepo.findByColloaboratorUserId(us.getId());
					  
					  
					   Vector<stores> NotOwnStores = new Vector<stores>();
					   if (!StoresNotOwned.isEmpty())
					   {
						   

						   for (int i =0;i<StoresNotOwned.size();i++)
						   {
							   stores store = storeRepo.findByStoreId(StoresNotOwned.get(i).getStoreId());
							   NotOwnStores.add(store);
							   
						   }
					   }
					   model.addAttribute("NotOwnStores",NotOwnStores);
				   }
				   else
				   {
					   st = storeRepo.findByStoreState(1);
					   Returned = "greetingAdmin";
					   model.addAttribute("added"," ");
					   
				   }
				   model.addAttribute("StoresInUser",st);
				   
				  
				   session.setAttribute("nameSession", us.getUserName());
				   session.setAttribute("UserIdSession", us.getId());
				   break;
			   }
			   
		   }
		   
		   model.addAttribute("Warrning", Warrning);
		   return Returned;
		   
		   
	   }
   }
   
}