package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	public List<Product> getProducts(Integer categoryId, Integer start, Integer limit);
	public int getCount(Integer categoryId);
}
