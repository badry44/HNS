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

import com.HNS.Entity.orderEn;
import com.HNS.Entity.products;
import com.HNS.Entity.stat;
import com.HNS.Entity.stores;
import com.HNS.Repositories.orderRepositories;
import com.HNS.Repositories.productsRepositories;
import com.HNS.Repositories.statRepositories;
import com.HNS.Repositories.storesRepositories;


@RequestMapping("/User")
@Controller
public class NormalUserController {
	@Autowired
	storesRepositories storeRepo;
	@Autowired
	statRepositories statRepo;
	@Autowired
	productsRepositories prodRepo ;
	@Autowired
	orderRepositories orderRepo ;
	@PostMapping("/AddStore")
	public String AddStore(Model model)
	{
		   model.addAttribute("store1",new stores());
		   model.addAttribute("warning","");

		return "Addstore";
	}
	
	@PostMapping("/greeting")
	 public String AddStoreWait(@ModelAttribute stores store1, Model model,HttpSession session) {
		System.out.println("Done !"+session.getAttribute("UserIdSession").toString());
		String ID=session.getAttribute("UserIdSession").toString();
		Integer UserId = Integer.parseInt(ID);
		store1.setStoreOwner(UserId);
		store1.setStoreState(1);
		
		
		storeRepo.save(store1);
		stat StoreStates = new stat(store1.getStoreId(),0,0);
		statRepo.save(StoreStates);
		   model.addAttribute("added","The Store has been added Successfully and waiting for admin Accept");
		   
	      return "greeting";
	   }
	
	@GetMapping("/ShowProducts")
	public String ShowProducts(Model model,HttpSession session)
	{
		Vector<products> AllProducts = prodRepo.findAll();
		
		//Updating Price
		String ID=session.getAttribute("UserIdSession").toString();
		Integer UserId = Integer.parseInt(ID);
		
		double priceUpdateing = 1-IsStoreOwner(UserId)-IsFirstTime(UserId);
		for (int i =0;i<AllProducts.size();i++)
		{
			double currentPrice = AllProducts.get(i).getProductPrice();
			int CurrentProductId = AllProducts.get(i).getProductId();
			double UpdatedPrice = priceUpdateing-IsSecondTimeTOBuy(UserId,CurrentProductId);
			AllProducts.get(i).setProductPrice(currentPrice*UpdatedPrice);
			System.out.println(UpdatedPrice);
			int presnt = (int) ((1-UpdatedPrice)*100);
			AllProducts.get(i).setNumberOfBuyers(presnt);
		}
		model.addAttribute("products",AllProducts);
		
		return "ShowProducts";
	}
	@GetMapping("Buy/{id}")
	public String BuyProducts(@PathVariable("id") String ProductId,Model model ,HttpSession session)
	{
		int id = Integer.parseInt(ProductId);
		String ProductName = prodRepo.findByProductId(id).getPrdouctName();
		String warning ="";
		orderEn Getorder = new orderEn();
		session.setAttribute("ProductIdSession", id);
		session.setAttribute("ProductNameSession", ProductName);
		model.addAttribute("order",Getorder);
		model.addAttribute("warning",warning);
		
		return "Buy";
	}
	@PostMapping("/BuyDone")
	public String BuyDone (@ModelAttribute orderEn order1, Model model,HttpSession session)
	{
		String ID=session.getAttribute("UserIdSession").toString();
		Integer UserId = Integer.parseInt(ID);
		order1.setUserId(UserId);
		String ProductID=session.getAttribute("ProductIdSession").toString();
		Integer ProID = Integer.parseInt(ProductID);
		
		order1.setProductId(ProID);
		String ProductName = session.getAttribute("ProductNameSession").toString();
		order1.setProductName(ProductName);
		int ProductId = order1.getProductId();
		products CurrentProd=prodRepo.findByProductId(ProductId);
		if (order1.getAmounts()>CurrentProd.getProductCount())
		{
			
			String warning = "The Current Product hasn't the amounts that u want";
			model.addAttribute("order",order1);
			model.addAttribute("warning",warning);
			System.out.println(CurrentProd.getProductCount());
			return "Buy";
		}
		else
		{
			products SavedProduct=prodRepo.findByProductId(ProID);
			int NumberOfBuyers = SavedProduct.getNumberOfBuyers()+1;
			SavedProduct.setNumberOfBuyers(NumberOfBuyers);
			order1.setStoreId(0);
			prodRepo.save(SavedProduct);
			orderRepo.save(order1);
			CurrentProd.setProductCount(CurrentProd.getProductCount()-order1.getAmounts());
			prodRepo.save(CurrentProd);
			return "BuyDone";
		}
		
		
	}
	public double IsStoreOwner(int userId)
	{
		System.out.println(userId);
		Vector <stores> Store = storeRepo.findByStoreOwner(userId);
		if (Store.isEmpty())
			{return 0;}
		else
			return 0.15;
	}
	public double IsSecondTimeTOBuy(int userId,int productId)
	{
		Vector<orderEn> orders = orderRepo.findByUserIdAndProductId(userId,productId);
		if (orders.isEmpty())
			return 0;
		else
			return 0.1;
	}
	public double IsFirstTime(int userId)
	{
		Vector<orderEn> orders =orderRepo.findByUserId(userId);
		if (orders.isEmpty())
			return 0.05;
		else
			return 0;
	}
	
	

}
