package kr.co.js;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.js.dao.SpringUserMapper;
import kr.co.js.domain.SpringUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DBTest {
	@Autowired
	private DataSource ds;

	@Test
	public void connectionTest() {
		System.out.println("데이터베이스:"+ds);

		try(Connection con = ds.getConnection()){
			System.out.println(con);
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	//@Autowired
	//private SqlSession sqlsession;

	//@Test
	//public void mybatisTest() {
	//System.out.println("MyBatis 객체:" + sqlsession);
	//}

	@Autowired
	private SpringUserMapper springUserMapper;

	@Test
	public void emailCheck() {
		//System.out.println(springUserMapper.emailCheck("ggangpae1@gmail.com"));
		//System.out.println(springUserMapper.emailCheck("ggangpae2@gmail.com"));
	}

	
	@Test
	public void joinTest() {
		SpringUser user = new SpringUser();
		user.setEmail("ggangpae1@gmail.com");
		user.setPw("1234");
		user.setNickname("쏠쏠");
		user.setImage("default.png");

		//insert,delete,update는 영향받은 행의 개수가 리턴됨
		System.out.println(springUserMapper.join(user));

	}

}


