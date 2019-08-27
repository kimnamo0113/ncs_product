package ncs.exam.dao;

import java.util.List;

import ncs.exam.dto.Sale;

public interface SaleMapper {
	public int insertSale(Sale sale);
	public List<Sale> selectByAll();
	public List<Sale> procPrice();
}
