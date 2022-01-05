package kr.co.js.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.js.domain.Item;

public interface ItemService {
	public List<Item> getList(HttpServletRequest request, HttpServletResponse response);

	public Item getItem(Integer itemid);
	
}
