package com.example.ProductServcie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ProductServcie.dto.Coupondto;
import com.example.ProductServcie.model.Product;
import com.example.ProductServcie.repo.ProductRepo;

@RestController
@RequestMapping(value="/productApi")
public class ProductController {
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private RestTemplate restTemplate;//Object Relational Mapping dng here
	
	@Value("${couponService.url}")
	private String couponServiceUrl;
	
	@PostMapping("/products")
	public Product create(@RequestBody Product p) {
		Coupondto couponDto = restTemplate.getForObject(couponServiceUrl+p.getCouponCode(),Coupondto.class );
		p.setPrice(p.getPrice().subtract(couponDto.getDiscount()));
		return prepo.save(p);
		
	}

}
