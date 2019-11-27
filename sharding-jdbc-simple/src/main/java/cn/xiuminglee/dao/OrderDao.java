package cn.xiuminglee.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Mapper
@Component
public interface OrderDao {
    /**
     * 插入订单
     * @param price 订单价格
     * @param userId 下单用户id
     * @param status 订单状态
     * @return
     */
    @Insert("insert into t_order(price,user_id,status)values(#{price},#{userId},#{status})")
    int insertOrder(@Param("price") BigDecimal price, @Param("userId")Long userId, @Param("status")String status);

    /**
     * 根据id列表查询订单
     * @param orderIds 订单ids
     * @return
     */
    @Select("<script>" +
            "select" +
            " * " +
            " from t_order t " +
            " where t.order_id in " +
            " <foreach collection='orderIds' open='(' separator=',' close=')' item='id'>" +
            " #{id} " +
            " </foreach>" +
            "</script>")
    List<Map> selectOrderByIds(@Param("orderIds") List<Long> orderIds);


    /**
     * 根据用户id和id列表查询订单
     * @param userId 用户id
     * @param orderIds 订单ids
     * @return
     */
    @Select("<script>" +
            "select" +
            " * " +
            " from t_order t " +
            " where t.order_id in " +
            " <foreach collection='orderIds' open='(' separator=',' close=')' item='id'>" +
            " #{id} " +
            " </foreach>" +
            " and user_id = #{userId} " +
            "</script>")
    List<Map> selectOrderByUidAndIds(@Param("userId") Long userId,@Param("orderIds") List<Long> orderIds);
}
