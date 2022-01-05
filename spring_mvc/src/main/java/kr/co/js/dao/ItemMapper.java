package kr.co.js.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import kr.co.js.domain.Item;

@Repository
public interface ItemMapper {
	@Select("select * from item")
	public List<Item> getList();
	
	
	@Select("select * from item where itemid=#{itemid}")
	public Item getItem(Integer itemid);
	
	

}
