package kr.co.js;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import kr.co.js.service.SpringUserService;

@RestController
public class SpringUserController {
	@Autowired
	private SpringUserService springUserService;

}
