package ncs.exam.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ncs.exam.AbstractTest;
import ncs.exam.dto.Product;

public class ProductMapperTest extends AbstractTest{
	private static ProductMapper productDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		productDao=ProductMapperImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		productDao=null;
	}
	
	
	
	@Test
	public void test01SelectProductByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Product> list = productDao.selectProductByAll();
		System.out.println(list);
		log.debug(list.toString());
		Assert.assertNotNull(list);
	}
	
	@Test
	public void test02SelectProductByCode() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Product product = productDao.selectProductByCode("A002");
		System.out.println(product);
		log.debug(product.toString());
		Assert.assertNotNull(product);
	}
	
	

}
