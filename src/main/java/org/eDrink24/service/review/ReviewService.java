package org.eDrink24.service.review;

import org.eDrink24.dto.Inventory.InventoryDTO;
import org.eDrink24.dto.admin.AdminDTO;
import org.eDrink24.dto.review.ReviewDTO;

import java.util.List;

public interface ReviewService {

    // 제품에 대한 모든 리뷰 보기
    public List<ReviewDTO> showProductReview(Integer productId);

    // 리뷰 작성 후 저장
    public Boolean addReview(ReviewDTO reviewDTO);

    // 내가 작성한 리뷰 확인하기
    public List<ReviewDTO> checkMyReview(Integer userId, Integer reviewsId);

    // 내가 작성한 리뷰 수정하기
    public Integer fixReviewContent(ReviewDTO reviewDTO);
}
