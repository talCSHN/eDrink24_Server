package org.eDrink24.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eDrink24.dto.Inventory.InventoryDTO;
import org.eDrink24.dto.admin.AdminDTO;
import org.eDrink24.dto.review.ReviewDTO;

import java.util.List;

@Mapper
public interface ReviewMapper {

    // 제품에 해당하는 리뷰를 볼수 있게함.
    public List<ReviewDTO> showProductReview(Integer productId);

    // 주문내역에 changeStatus가 PICKUPED인지 확인
    public Boolean isOrderPickuped(Integer orderId);

    // 주문상태가 PICKUPED이면 리뷰 저장
    public Integer insertReview(ReviewDTO reviewDTO);

    // 내가 작성한 리뷰 확인하기
    public List<ReviewDTO> checkMyReview(Integer userId, Integer reviewsId);

    // 내가 작성한 리뷰 수정하기
    public Integer fixReviewContent(ReviewDTO reviewDTO);
}
