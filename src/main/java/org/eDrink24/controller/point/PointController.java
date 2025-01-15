package org.eDrink24.controller.point;

import org.eDrink24.dto.coupon.CouponDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;
import org.eDrink24.dto.point.PointDTO;
import org.eDrink24.service.coupon.CouponService;
import org.eDrink24.service.point.PointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PointController {

  PointService pointService;

  public PointController(PointService pointService) {
      this.pointService = pointService;
  }

    // 종합포인트 조회
    @GetMapping("/showTotalPoint/{userId}")
    public ResponseEntity<Integer> showTotalPoint(@PathVariable Integer userId) {
        Integer points = pointService.showTotalPoint(userId);
        return ResponseEntity.ok(points);
    }

    // 마이페이지에서 포인트 클릭 시 개별 포인트 내역 보여줌
    @GetMapping("/api/showPoint/{userId}")
    public ResponseEntity<List<PointDTO>> showPoint(@PathVariable Integer userId) {
      List<PointDTO> points = pointService.showPoint(userId);
      return ResponseEntity.ok(points);
    }


}
