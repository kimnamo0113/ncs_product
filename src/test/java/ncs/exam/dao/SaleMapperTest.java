package ncs.exam.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ncs.exam.AbstractTest;
import ncs.exam.dto.Sale;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SaleMapperTest extends AbstractTest{
	private static SaleMapper saleDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		saleDao =SaleMapperImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		saleDao=null;
	}
	
	
	@Test
	public void test01SelectSaleByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Sale> list = saleDao.selectByAll();
		
		Assert.assertNotNull(list);
	}
	
	@Test
	public void test02SelectSaleProc() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Sale> list = saleDao.procPrice();
		log.debug(list.toString());
		Assert.assertNotNull(list);
	}
	
	@Test
	public void test03SelectMarginProc() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Sale> list = saleDao.procMargin();
		log.debug(list.toString());
		Assert.assertNotNull(list);
	}
	
	@Test
	public void test04SelectSumProc() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Sale sale = saleDao.procSum();
		log.debug(sale.toString());
		Assert.assertNotNull(sale);
	}
}
