package ncs.exam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ncs.exam.dto.Sale;
import ncs.exam.jdbc.MyBatisSqlSessionFactory;

public class SaleMapperImpl implements SaleMapper {
	private String namespace="ncs.exam.dao.SaleMapper";
	
	private static final SaleMapperImpl instance = new SaleMapperImpl();
	
	public static SaleMapperImpl getInstance() {
		return instance;
	}
	
	private SaleMapperImpl() {
		
	}
	
	
	@Override
	public int insertSale(Sale sale) {
		try(SqlSession sqlSession=MyBatisSqlSessionFactory.openSession();){
			int result=sqlSession.insert(namespace+".insertSale",sale);
			sqlSession.commit();
			return result;
		}
	}

	@Override
	public List<Sale> selectByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectList(namespace + ".selectByAll");
		}
	}

	@Override
	public List<Sale> procPrice() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectList(namespace + ".procPrice");
		}
	}

	@Override
	public List<Sale> procMargin() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectList(namespace + ".procMargin");
		}
	}

	@Override
	public Sale procSum() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectOne(namespace + ".procSum");
		}
	}

}
