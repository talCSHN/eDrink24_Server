package org.eDrink24.config;

import org.apache.ibatis.annotations.Mapper;
import org.eDrink24.dto.coupon.CouponDTO;

import java.util.List;


@Mapper
public interface CouponMapper {

    // 쿠폰 목록 조회
    public List<CouponDTO> showAllCoupon(Integer userId);

}
