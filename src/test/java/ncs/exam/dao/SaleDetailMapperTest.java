package ncs.exam.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import ncs.exam.AbstractTest;
import ncs.exam.dto.SaleDetail;

public class SaleDetailMapperTest extends AbstractTest{
	private static SaleDetailMapper saleDetailDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		saleDetailDao = new SaleDetailMapperImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		saleDetailDao=null;
	}
	
	
	
	@Test
	public void test01SelectProductByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<SaleDetail> list = saleDetailDao.selectByAll();
		System.out.println(list.size());
		log.debug(list.toString());
		Assert.assertNotNull(list);
	}
	
}
