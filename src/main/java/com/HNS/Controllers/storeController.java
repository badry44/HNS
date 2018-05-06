package com.HNS.Controllers;
import java.util.Optional;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.HNS.Entity.StoreProducts;
import com.HNS.Entity.User;
import com.HNS.Entity.collaborators;
import com.HNS.Entity.orderEn;
import com.HNS.Entity.products;
import com.HNS.Entity.stat;
import com.HNS.Entity.stores;
import com.HNS.Repositories.UserRepositories;
import com.HNS.Repositories.collaboratorsRepositories;
import com.HNS.Repositories.orderRepositories;
import com.HNS.Repositories.productsRepositories;
import com.HNS.Repositories.statRepositories;
import com.HNS.Repositories.storeProductsRepositories;
import com.HNS.Repositories.storesRepositories;
@Controller
public class storeController {
	@Autowired
	productsRepositories proRepo;
	@Autowired
	collaboratorsRepositories collRepo;
	@Autowired
	UserRepositories userRepo;
	@Autowired
	storesRepositories storeRepo;
	@Autowired
	orderRepositories orderRepo;
	@Autowired
	statRepositories statRepo;
	@Autowired
	storeProductsRepositories storeProductsRepo;
	@GetMapping("ShowStore/{id}")
	public String ShowStore (@PathVariable("id") String StoreId,Model model,HttpSession session)
	{
		int id = Integer.parseInt(StoreId);
		session.setAttribute("CurrentStoreIdSession", id);
		// Check who is Owner
		int UserId = Integer.parseInt(session.getAttribute("UserIdSession").toString());
		
		if (UserId==storeRepo.findByStoreId(id).getStoreOwner())
		model.addAttribute("UserType","owner");
		else
			model.addAttribute("UserType","notowner");
		
		return "ShowStore";
	}
	@PostMapping("ShowStat")
	public String ShowStat(Model model,HttpSession session )
	{
		String StoreId =session.getAttribute("CurrentStoreIdSession").toString(); 
		int id = Integer.parseInt(StoreId);
		stores storeOwned = storeRepo.findByStoreId(id);
		stat stat1 = statRepo.findByStoreId(id);
		model.addAttribute("StoreName",storeOwned.getStoreName());
		model.addAttribute("views" ,stat1.getNumberOfViews());
		model.addAttribute("buy" ,stat1.getNumberOfUserBuy());
		return "ShowStat";
	}
	@PostMapping("ProductsToStore")
	public String ProductsToStore(Model model,HttpSession session )
	{
		String ID=session.getAttribute("UserIdSession").toString();
		Integer UserId = Integer.parseInt(ID);
		Vector<orderEn> UserOrders = orderRepo.findByUserIdAndStoreId(UserId,0);
		model.addAttribute("Orders",UserOrders);
	return "AddProducts";	
	}
	@GetMapping("AddDone/{id}")
	public String DoAdd (@PathVariable("id") String OrderId,Model model,HttpSession session)
	{
		int id = Integer.parseInt(OrderId);
		orderEn order = orderRepo.findByOrderId(id);
		StoreProducts Store = storeProductsRepo.findByProductId(order.getProductId());
		String StoreId =session.getAttribute("CurrentStoreIdSession").toString();
		if (Store==null)
		{
			//storeProductsRepo
			Store = new StoreProducts(Integer.parseInt(StoreId),order.getProductId(),order.getAmounts(),order.getProductName());
			storeProductsRepo.save(Store);		
		}
		else
		{
			Store.setProductCount(Store.getProductCount()+order.getAmounts());
			storeProductsRepo.save(Store);
		}
		order.setStoreId(Integer.parseInt(StoreId));
		orderRepo.save(order);	
		String ID=session.getAttribute("UserIdSession").toString();
		Integer UserId = Integer.parseInt(ID);
		Vector<orderEn> UserOrders = orderRepo.findByUserIdAndStoreId(UserId,0);
		model.addAttribute("Orders",UserOrders);
		return "AddProducts";
	}
	@PostMapping("/AddCollaborator")
	public String AddCollaborator(Model model)
	{
		
		model.addAttribute("coll",new collaborators());
		model.addAttribute("Warrning","");
		return "AddCollaborator";
	}
	@PostMapping("/AddCollDone")
	public String AddCollaboratorDone(@ModelAttribute collaborators collaborator, Model model,HttpSession session)
	{
		String ReturnedView="ShowStore";
		String UserName = collaborator.getColloabratorUserName();
		Vector<User> isUser = userRepo.findByUserName(UserName);
		if (isUser.isEmpty())
		{
			model.addAttribute("coll",new collaborators());
			model.addAttribute("Warrning","This UserName Doesn't Exist");
			ReturnedView="AddCollaborator";
		}
		else
		{
			int storeId = Integer.parseInt(session.getAttribute("CurrentStoreIdSession").toString());
			stores store1 = storeRepo.findByStoreId(storeId);
			collaborators addedOne = new collaborators (store1.getStoreId(),Integer.parseInt(session.getAttribute("UserIdSession").toString()),isUser.get(0).getId(),collaborator.getColloabratorUserName());
			collRepo.save(addedOne);	
		}
		return ReturnedView;
	}
	@PostMapping("/ShowStoreProducts")
	public String ShowStoreProducts( Model model,HttpSession session)
	{
		int storeId = Integer.parseInt(session.getAttribute("CurrentStoreIdSession").toString());
		Vector<StoreProducts> AllProductsInStore=storeProductsRepo.findByStoreId(storeId);
		model.addAttribute("storeProducts",AllProductsInStore);
		return "ShowStoreProducts";
	}
	@PostMapping("/ShowOrders")
	public String ShowOrders( Model model,HttpSession session)
	{
		int storeId = Integer.parseInt(session.getAttribute("CurrentStoreIdSession").toString());
		Vector<orderEn> orders = orderRepo.findByStoreId(storeId);
		for (int i =0;i<orders.size();i++)
		{
			int userId = orders.get(i).getUserId();
			User user =userRepo.findByIdAndId(userId,userId);
			orders.get(i).setShippingAddress(user.getUserName());
		}
		model.addAttribute("orders",orders);
		return "ShowOrders";
	}
	@GetMapping("unDoDone/{id}")
	public String unDoOrder (@PathVariable("id") String OrderId,Model model,HttpSession session)
	{
		//deleting order
		int id = Integer.parseInt(OrderId);
		orderEn order=orderRepo.findByOrderId(id);
		order.setStoreId(0);
		orderRepo.save(order);
		// updating StoreProducts
		int storeId = Integer.parseInt(session.getAttribute("CurrentStoreIdSession").toString());
		StoreProducts product = storeProductsRepo.findByStoreIdAndProductId(storeId,order.getProductId());
		if (product.getProductCount()==order.getAmounts())
		{
			storeProductsRepo.delete(product);
		}
		else
		{
			product.setProductCount(product.getProductCount()-order.getAmounts());
			storeProductsRepo.save(product);
		}
		// Back to Show Orders
		return ShowOrders(model,session);
	}
	
	

}
