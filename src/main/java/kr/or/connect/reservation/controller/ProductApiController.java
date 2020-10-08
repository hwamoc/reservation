package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path="/api/products")
public class ProductApiController {
	public static final Integer LIMIT = 4;
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> list(@RequestParam(name="categoryId", required=false, defaultValue="") int categoryId, @RequestParam(name="start", required=false, defaultValue="1") int start) {
		
		List<Product> list = productService.getProducts(categoryId, start, LIMIT);
		
		int count = productService.getCount(categoryId);
		int pageCount = count / LIMIT;
		if(count % LIMIT > 0)
			pageCount++;
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i = 0; i < pageCount; i++) {
			pageStartList.add(i * LIMIT);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		map.put("pageStartList", pageStartList);
		
		return map;
	}
}
