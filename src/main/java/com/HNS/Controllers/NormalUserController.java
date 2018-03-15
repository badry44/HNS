package com.HNS.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HNS.Entity.stat;
import com.HNS.Entity.stores;
import com.HNS.Repositories.statRepositories;
import com.HNS.Repositories.storesRepositories;


@RequestMapping("/User")
@Controller
public class NormalUserController {
	@Autowired
	storesRepositories storeRepo;
	@Autowired
	statRepositories statRepo;
	@PostMapping("/AddStore")
	public String AddStore(Model model)
	{
		   model.addAttribute("store1",new stores());
		   model.addAttribute("warning","");

		return "Addstore";
	}
	
	@PostMapping("AddStore/Waitting")
	 public String AddStoreWait(@ModelAttribute stores store1, Model model,HttpSession session) {
		String ID=session.getAttribute("UserIdSession").toString();
		Integer UserId = Integer.parseInt(ID);
		store1.setStoreOwner(UserId);
		store1.setStoreState(1);
		System.out.println(ID);
		storeRepo.save(store1);
		   model.addAttribute("added","The Store has been added Successfully and waiting for admin Accept");
	      return "greeting";
	   }
	@GetMapping("ShowStat/{id}")
	public String AcceputStore(@PathVariable("id") String StoreId,Model model )
	{
		int id = Integer.parseInt(StoreId);
		stores ss = storeRepo.findByStoreId(id);
		
		stat s = statRepo.findByStoreId(id);
		model.addAttribute("StoreName",ss.getStoreName());
		System.out.println(ss.getStoreName());
		model.addAttribute("views" ,s.getNumberOfViews());
		System.out.println(s.getNumberOfViews());
		model.addAttribute("buy" ,s.getNumberOfUserBuy());
		System.out.println(s.getNumberOfUserBuy());
		return "ShowStat";
	}
	

}
