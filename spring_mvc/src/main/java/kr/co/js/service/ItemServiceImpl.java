package kr.co.js.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.js.dao.ItemDao;
import kr.co.js.dao.ItemHibernateDao;
import kr.co.js.dao.ItemMapper;
import kr.co.js.domain.Item;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	//private ItemDao itemDao;
	private ItemMapper itemDao;
	
	@Autowired
	private ItemHibernateDao itemHibernateDao; 
	
	@Override
	@Transactional
	public List<Item> getList(HttpServletRequest request, HttpServletResponse response) {
		//return itemDao.getList();
		return itemHibernateDao.getList();
	}

	@Override
	@Transactional
	public Item getItem(Integer itemid) {
		//return itemDao.getItem(itemid);
		return itemHibernateDao.getItem(itemid);
	}

	

}
