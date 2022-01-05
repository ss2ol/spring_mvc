package kr.co.js;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.js.domain.Item;
import kr.co.js.service.ItemService;

@RestController
public class JSONController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/item.csv", method=RequestMethod.GET)
	public String itemcsv() {
		return "csv,xml,json";
	}
	
	
	@RequestMapping(value="/itemrest.json",method = RequestMethod.GET)
	public List<Item> itemrest(HttpServletRequest request,HttpServletResponse response ) {
		
		/*Map<String,Object> map = new HashMap<>();
		map.put("name", "jinsol");
		return map;
		*/
		
		/*
		 Item item = new Item();
		 item.setItemid(1);
		 */
		
		
		List<Item> list = itemService.getList(request, response);
		return list;
		
	}

}
