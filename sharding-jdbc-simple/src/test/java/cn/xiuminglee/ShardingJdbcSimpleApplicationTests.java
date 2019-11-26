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
			orderDao.insertOrder(new BigDecimal(i),1L,"SUCCESS");
		}
	}

	/** 查询订单 */
	@Test
	public void selectOrderByIds() {
		List<Long> ids = new ArrayList<>();
		ids.add(406087215895019521L);
		ids.add(406087217371414528L);
		ids.add(406087217614684161L);
		ids.add(406087216465444864L);

		List<Map> maps = orderDao.selectOrderByIds(ids);
		System.out.println(maps);
	}

}
