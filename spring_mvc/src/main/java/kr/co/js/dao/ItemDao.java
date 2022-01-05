package kr.co.js.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.js.domain.Item;

//@Repository
public class ItemDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<Item> getList(){
		return sqlSession.selectList("itemmapper.getlist");
	}
	
	public Item getItem(Integer itemid) {
		return sqlSession.selectOne("itemmapper.getitem",itemid);
	}

}
