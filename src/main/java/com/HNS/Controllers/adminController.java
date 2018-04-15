package com.HNS.Controllers;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.HNS.Entity.brand;
import com.HNS.Entity.User;
import com.HNS.Entity.products;
import com.HNS.Entity.stores;
import com.HNS.Repositories.UserRepositories;
import com.HNS.Repositories.brandRepositories;
import com.HNS.Repositories.productsRepositories;
import com.HNS.Repositories.storesRepositories;
@Controller
public class adminController {
	
	@Autowired
	storesRepositories storeRepo;
	@Autowired
	brandRepositories brandRepo;
	@Autowired
	private productsRepositories ProRepo;
	@Autowired
	private UserRepositories userRepo;
	@GetMapping("greetingAdmin/{id}")
	public String AcceputStore(@PathVariable("id") String StoreId,Model model  )
	{
		int id = Integer.parseInt(StoreId);
		stores ss =storeRepo.findByStoreId(id);	
		ss.setStoreState(2);
		storeRepo.save(ss);
		 Vector<stores>st = storeRepo.findByStoreState(1);
		   model.addAttribute("StoresInUser",st);
		return "greetingAdmin";
	}
	
	 @PostMapping("/greetingAdmin")
	   public String SayHelloAdmin(@ModelAttribute products product, Model model) {
	   
		   ProRepo.save(product);
		   Vector<stores>st = storeRepo.findByStoreState(1);
		   model.addAttribute("StoresInUser",st);
		   model.addAttribute("added","The product has been added Successfully");
	      return "greetingAdmin";
	   }
	   @PostMapping("/AddProduct")
	   public String AddProduct(Model model)
	   {
	   	   model.addAttribute("product",new products());
	   	   Iterable<brand> brands;
	   	brands=brandRepo.findAll();
	   	Vector <brand> Brands = new Vector<brand>();
	   	for ( brand brand1 :brands)
	   	{
	   		Brands.add(brand1);
	   	}
	   	System.out.println(Brands.get(0).getBrandName());
	   	model.addAttribute("Brands",Brands);
	   	return "AddProduct";
	   }
	   @PostMapping("/AddBrand")
	   public String AddBrand(Model model)
	   {
	   	   model.addAttribute("brand",new brand());
	   	return "AddBrand";
	   }
	   @PostMapping("/greetingAdminBrand")
	   public String SayHelloAdminAfterBrand(@ModelAttribute brand brand, Model model) {
	   
		   brandRepo.save(brand);
		   model.addAttribute("added","The Brand has been added Successfully");
		   Vector<stores>st = storeRepo.findByStoreState(1);
		   model.addAttribute("StoresInUser",st);
	      return "greetingAdmin";
	   }
	   @GetMapping("/AdminStat")
	   public String showAdminStat(Model model)
	   {
		   
		   //Number Of Users in System
		   int NumberOfUsers = NumberOfUsersfn();
		   model.addAttribute("NumberOfUsers",NumberOfUsers);
		// Avrage Price Products , Sum Of Products
		   Iterable <products> AvgProduct;
		   AvgProduct= ProRepo.findAll();
		   int ProductSum=0;
		   int NumberOfProducts =0;
		   for (products product :AvgProduct)
		   {
			   ProductSum+=product.getProductPrice();
			   NumberOfProducts++;
		   }
		   int avgProducts = ProductSum/NumberOfProducts;
		   model.addAttribute("ProductAvg",avgProducts);
		   model.addAttribute("NumberOfProducts",NumberOfProducts);
		   //
		   // Max Product price
		   Integer MaxProductId = GetMaxProduct();
		   products MaxProduct= ProRepo.findByProductId(MaxProductId);
		   model.addAttribute("MaxProductName",MaxProduct.getPrdouctName());
		   model.addAttribute("MaxProductPrice",MaxProduct.getProductPrice());
		   //
		   // min Product Price
		   Integer MinProductId = getMinProduct();
		   products MinProduct= ProRepo.findByProductId(MinProductId);
		   model.addAttribute("MinProductName",MinProduct.getPrdouctName());
		   model.addAttribute("MinProductPrice",MinProduct.getProductPrice());
		   
		   return "AdminStat";
	   }
	   @GetMapping("/AllProductsStat")
	   public String AllProductsStat(Model model)
	   {
		   Vector<products> AllProducts = ProRepo.findAll();
		   model.addAttribute("All",AllProducts);
		   return "AllProductsStat";
	   }
	   public int NumberOfUsersfn()
	   {
		   Iterable <User> Users;
		   Users=userRepo.findAll();
		   int NumberOfUsers =0;
		   //Get Number Of Users
		   for (User user :Users)
		   {
			   if (user.getUserType()==1)
				   NumberOfUsers++;
		   }
		   return NumberOfUsers;
	   }
	   public Integer GetMaxProduct()
	   {
		   Iterable <products> AllProducts;
		   AllProducts= ProRepo.findAll();
		  Integer MaxProductId=0 ;
		  double MaxProductPrice=0;
		   for (products product :AllProducts)
		   {
			   if (product.getProductPrice()>MaxProductPrice)
			   {
				   MaxProductPrice = product.getProductPrice();
				   MaxProductId = product.getProductId();
				   
			   }
		   }
		   return MaxProductId;
	   }
	   public Integer getMinProduct()
	   {
		   Iterable <products> AllProducts;
		   AllProducts= ProRepo.findAll();
		  Integer MinProductId=0 ;
		  double MinProductPrice=999;
		   for (products product :AllProducts)
		   {
			   if (product.getProductPrice()<MinProductPrice)
			   {
				   MinProductPrice = product.getProductPrice();
				   MinProductId = product.getProductId();
				   
			   }
		   }
		   return MinProductId;
	   }
	   
	  

}
