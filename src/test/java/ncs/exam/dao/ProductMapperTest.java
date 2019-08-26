package ncs.exam.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import ncs.exam.AbstractTest;
import ncs.exam.dto.Product;

public class ProductMapperTest extends AbstractTest{
	private static ProductMapper productDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		productDao=new ProductMapperImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		productDao=null;
	}
	
	
	
	@Test
	public void test01SelectProductByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		List<Product> list = productDao.selectProductByAll();
		System.out.println(list.size());
		log.debug(list.toString());
		Assert.assertNotNull(list);
	}

}
