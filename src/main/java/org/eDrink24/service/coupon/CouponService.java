package org.eDrink24.service.coupon;

import org.eDrink24.dto.coupon.CouponDTO;

import java.util.List;

public interface CouponService {

    // 쿠폰 목록 조회
    public List<CouponDTO> showAllCoupon(Integer userId);


}
