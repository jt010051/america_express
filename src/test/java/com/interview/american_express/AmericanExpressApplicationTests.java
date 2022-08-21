package com.interview.american_express;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.interview.american_express.domain.Product;
import com.interview.american_express.rest.Controller;

@SpringBootTest
class AmericanExpressApplicationTests {

	@Test
	void contextLoads() {
	}
@Test
 void addPrices() {
	
	var calc = new Product(100, "Bannana", .60, 2);
	var calc2 = new Product(101, "Kiwi", .40, 2);
double addition = calc.getPrice()+calc2.getPrice();
assertEquals(1, addition);



}
}
