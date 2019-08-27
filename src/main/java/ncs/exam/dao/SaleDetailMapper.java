package ncs.exam.dao;

import java.util.List;

import ncs.exam.dto.SaleDetail;

public interface SaleDetailMapper {
	public int insertSaleDetail(SaleDetail saleDetail);
	public List<SaleDetail> selectByAll();
}
