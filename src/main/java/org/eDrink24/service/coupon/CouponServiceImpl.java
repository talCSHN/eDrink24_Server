package org.eDrink24.service.coupon;

import org.eDrink24.config.CouponMapper;
import org.eDrink24.dto.coupon.CouponDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    CouponMapper couponMapper;

   public CouponServiceImpl(CouponMapper couponMapper) {
      this.couponMapper = couponMapper;
   }

    @Override
    public List<CouponDTO> showAllCoupon(Integer userId) {
        return couponMapper.showAllCoupon(userId);
    }
}
