package org.eDrink24.controller.coupon;

import org.eDrink24.dto.coupon.CouponDTO;
import org.eDrink24.dto.store.StoreDTO;
import org.eDrink24.service.coupon.CouponService;
import org.eDrink24.service.store.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CouponController {

  CouponService couponService;

  public CouponController(CouponService couponService) {
      this.couponService = couponService;
  }

    // 쿠폰 목록 조회
    @GetMapping("/showAllCoupon/userId/{userId}")
    public ResponseEntity<List<CouponDTO>> showAllCoupon(@PathVariable Integer userId) {
        List<CouponDTO> coupons = couponService.showAllCoupon(userId);
        return ResponseEntity.ok(coupons);
    }


}
