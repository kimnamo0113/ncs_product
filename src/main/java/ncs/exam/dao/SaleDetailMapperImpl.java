package ncs.exam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import ncs.exam.dto.SaleDetail;
import ncs.exam.jdbc.MyBatisSqlSessionFactory;

public class SaleDetailMapperImpl implements SaleDetailMapper {
	private String namespace="ncs.exam.dao.SaleDetailMapper";
	
	@Override
	public int insertSaleDetail(SaleDetail saleDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SaleDetail> selectByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();) {
			return sqlSession.selectList(namespace + ".selectByAll");
		}
	}

}
