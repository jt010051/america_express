package com.interview.american_express.rest;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.american_express.AmericanExpressApplication;
import com.interview.american_express.domain.Order;
import com.interview.american_express.domain.Product;
import com.interview.american_express.repository.OrderRepository;
import com.interview.american_express.repository.ProductRepository;

@RestController
@RequestMapping("/api/auth")
public class Controller {
	private static final Scanner scan = new Scanner(System.in);
	Product apple = new Product(1,"Apple", 0.60, 10);
	int selection=0;
	Product orange= new Product(2,"Orange", 0.25, 5);	
	int count=0;
	Order order;
	List <Product>o = new ArrayList<>();
	@Autowired
	ProductRepository productRepo;
	
	@GetMapping("products")
	public List<Product> getAllProducts(HttpServletResponse response) {
		return productRepo.findAll();
	}
	@Autowired
	OrderRepository orderRepo;
	
	@GetMapping("order")
	public List<Order> getAllOrders(HttpServletResponse response) {
		
		return orderRepo.findAll();
	}
	@GetMapping("/order/{id}")
	public Order getOrder(@PathVariable("id") Long id) {
		return orderRepo.findById(id).get();
	}
	@GetMapping("orderSummary")
	public String orderSummary(HttpServletResponse response) {
		count ++;
		
		order = new Order();
		
		
		try {
			
			if(apple.getQuantity()>0 && orange.getQuantity()>0) {
				System.out.println("Enter Selection 1-Apple or 2-Orange");
				 selection = scan.nextInt();
				 
				}
				else if(apple.getQuantity()==0) {
				selection=2;
				}
				else if(orange.getQuantity()==0){
					selection=1;
				}
				 if (orange.getQuantity()==0 && apple.getQuantity()==0) {
					System.out.println("Sorry, Were out of Stock");
					return "Sorry, Were out of Stock";
				}
				 input(selection);
			}
			
			catch(InputMismatchException e){
				String res ="Invalid Selection " +e +" End Code";
				System.out.println(res);
				return res;
				
			}
		int tempApp=order.getAppleQuan();
		int tempOran=order.getOrangeQuan();
			int appleTotal=0;
			int orangeTotal=0;
		
	
		if (order.getAppleQuan()>1) {
			for (int i=0; i<=tempApp; i++) {
				if(i%2==0) {
				appleTotal+=0;
				}
				else {
					appleTotal+=1;
				}
				
		}
	}
		if (order.getOrangeQuan()>2) {
			for (int i=0; i<=tempOran; i++) {
				if(i%3==0) {
				orangeTotal+=0;
				}
				else {
					orangeTotal+=1;
				}
				
		}
	}
		
		
	
	
		order.setId(count);	
		
	if(appleTotal>0) {
		order.setAppleQuan(appleTotal);
	}
	else {
		appleTotal=order.getAppleQuan();
		order.setAppleQuan(appleTotal);
	}
	if(orangeTotal>0) {
		order.setOrangeQuan(orangeTotal);

	}else {
		orangeTotal=order.getOrangeQuan();
		order.setOrangeQuan(orangeTotal);
	}
			
		order.setTotal(appleTotal*apple.getPrice()+orangeTotal*orange.getPrice());
productRepo.save(apple);
productRepo.save(orange);
orderRepo.save(order);
			
		String orderSummary="Order Summary: OrderID: "+order.getId() +" Apple Quantity: "+tempApp +" Price: $" +apple.getPrice() +"\n\t\tOranges Quantity: " +tempOran
		+" Price: $" +orange.getPrice()+"\n\n Total: \t$" +order.getTotal();
		System.out.println(orderSummary);
		
		
		List<Order> orders = new ArrayList<>();
		orders.add(order);
		return orderSummary;
		
		
		
	}
	private void input(int selection) {
	
	
		if(selection !=1 && selection !=2) {
			System.out.println("Invalid Input: Please Try Again");
			selection = scan.nextInt();
			input(selection);
			
		}
		else if (selection==1) {
			
			o.add(apple);
			System.out.println("How many Apples would you like? There are " +apple.getQuantity()+" available"); 
			try {
			int sel = scan.nextInt();
			if (sel>apple.getQuantity()) {
				System.out.println("Invalid Please Try again");
			input(1);
			}
			order.setAppleQuan(sel+order.getAppleQuan());
			apple.setQuantity(apple.getQuantity()-order.getAppleQuan());
		}catch(InputMismatchException e) {
			System.out.println("Invalid Selection " +e +"Please Try again");
			input(1);
		}
		}
		
		else if (selection==2) {
			o.add(orange);
			System.out.println("How many Oranges would you like? There are " +orange.getQuantity()+" available");
			
			try {
			int sel = scan.nextInt();
			if (sel>orange.getQuantity()) {
			System.out.println("Invalid Please Try again");
			input(2);
			}
			order.setOrangeQuan(sel+order.getOrangeQuan());
			orange.setQuantity(orange.getQuantity()-order.getOrangeQuan());
			}catch(InputMismatchException e) {
				System.out.println("Invalid Selection " +e +"Please Try again");
				input(2);
			}

		}
	
		
		System.out.println("Would You Like To add anything else to cart? Yes:No");
		String answer=scan.next();
		if(answer.toLowerCase().equals("Yes")) {
			if(apple.getQuantity()>0 && orange.getQuantity()>0) {
			System.out.println("Enter Selection 1-Apple or 2-Orange");
			input(selection);
			}
			else if(apple.getQuantity()==0 ) {
				System.out.println("We're out of Apples\n");
				input(2);
				}
			else if(orange.getQuantity()==0 ) {
				System.out.println("We're out of Oranges\n");
				input(1);
				}
			else {
				System.out.println("Sorry, The store is completly empty");
			}
		}
		
	}
	
}
