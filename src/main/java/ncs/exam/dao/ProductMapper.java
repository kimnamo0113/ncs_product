package ncs.exam.dao;

import java.util.List;

import ncs.exam.dto.Product;

public interface ProductMapper {
	public int insertProduct(Product pro);
	public List<Product> selectProductByAll();
	public Product selectProductByCode(String code);
}
