package cn.xiuminglee;

import cn.xiuminglee.dao.OrderDao;
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

}
