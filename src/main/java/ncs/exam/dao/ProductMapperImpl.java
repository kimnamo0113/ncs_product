package ncs.exam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ncs.exam.dto.Product;
import ncs.exam.jdbc.MyBatisSqlSessionFactory;

public class ProductMapperImpl implements ProductMapper {
	private String namespace="ncs.exam.dao.ProductMapper";
	
	private static final ProductMapperImpl instance = new ProductMapperImpl();
	
	public static ProductMapperImpl getInstance() {
		return instance;
	}
	
	private ProductMapperImpl() {
		super();
	}

	@Override
	public int insertProduct(Product pro) {
		try(SqlSession sqlSession=MyBatisSqlSessionFactory.openSession();){
			int result=sqlSession.insert(namespace+".insertProduct",pro);
			sqlSession.commit();
			return result;
		}
	}

	@Override
	public List<Product> selectProductByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectList(namespace + ".selectProductByAll");
		}
	}
	
	@Override
	public Product selectProductByCode(String code) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectOne(namespace + ".selectProductByCode",code);
		}
	}

}
