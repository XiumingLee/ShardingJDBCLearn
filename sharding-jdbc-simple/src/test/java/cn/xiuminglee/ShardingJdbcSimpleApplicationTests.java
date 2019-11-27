package cn.xiuminglee;

import cn.xiuminglee.dao.DictDao;
import cn.xiuminglee.dao.OrderDao;
import cn.xiuminglee.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcSimpleApplicationTests {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DictDao dictDao;

	/** 新增订单 */
	@Test
	public void insertOrder() {
		for(int i = 1; i < 10; i++){
			orderDao.insertOrder(new BigDecimal(i * 5),1L,"WAIT_PAY");
		}
		for(int i = 1; i < 10; i++){
			orderDao.insertOrder(new BigDecimal(i * 5),2L,"WAIT_PAY");
		}
	}

	/** 查询订单 */
	@Test
	public void selectOrderByIds() {
		List<Long> ids = new ArrayList<>();
		ids.add(406369478075482112L);

		List<Map> maps = orderDao.selectOrderByIds(ids);
		System.out.println(maps);
	}

	@Test
	public void selectOrderByUidAndIds() {
		List<Long> ids = new ArrayList<>();
		ids.add(406370267212808192L);
		Long userId = 2L;

		List<Map> maps = orderDao.selectOrderByUidAndIds(userId,ids);
		System.out.println(maps);
	}

	/// 以下是用户表测试
	@Test
	public void testInsertUser(){
		for (int i = 10 ; i<14; i++){
			Long id = i + 1L;
			userDao.insertUser(id,"姓名"+ id );
		}

	}
	@Test
	public void testSelectUserByIds(){
		List<Long> userIds = new ArrayList<>();
		userIds.add(1L);
		userIds.add(2L);
		List<Map> users = userDao.selectUserByIds(userIds);
		System.out.println(users);
	}

	@Test
	public void testSelectUserInfobyIds(){
		List<Long> userIds = new ArrayList<>();
		userIds.add(1L);
		userIds.add(2L);
		List<Map> users = userDao.selectUserInfobyIds(userIds);
		System.out.println(users);
	}

	/// 以下是公共表  字典表的测试
	@Test
	public void testInsertDict(){
		dictDao.insertDict(12L,"user_type","2","超级管理员");
		dictDao.insertDict(13L,"user_type","3","二级管理员");
	}
	@Test
	public void testDeleteDict(){
		dictDao.deleteDict(3L);
		dictDao.deleteDict(4L);
	}

}
