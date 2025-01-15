package org.eDrink24.service.point;

import org.eDrink24.dto.coupon.CouponDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;
import org.eDrink24.dto.point.PointDTO;

import java.util.List;

public interface PointService {

    // 쿠폰 목록 조회
    public Integer showTotalPoint(Integer userId);

    // 주문 후 적립되는 개별 포인트 내역 보여줌
    public List<PointDTO> showPoint(Integer userId);

}
