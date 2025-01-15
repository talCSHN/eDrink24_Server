package org.eDrink24.config;

import org.apache.ibatis.annotations.Mapper;
import org.eDrink24.dto.order.OrderTransactionDTO;
import org.eDrink24.dto.point.PointDTO;
import org.eDrink24.dto.product.DetailProductDTO;
import org.eDrink24.dto.product.ProductDTO;

import java.util.List;
import java.util.Map;


@Mapper
public interface PointMapper {

    // 회원 테이블에 있는 totalPoint를 보여줌
    public Integer showTotalPoint(Integer userId);

    // 주문 후 적립되는 개별 포인트 내역 보여줌
    public List<PointDTO> showPoint(Integer userId);

}
