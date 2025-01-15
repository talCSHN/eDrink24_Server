package org.eDrink24.service.dibs;

import org.eDrink24.dto.dibs.DibDTO;

import java.util.HashMap;
import java.util.List;

public interface DibService {

    // 상품 찜하기(dibs 테이블에 저장)
    public void addDibs(DibDTO dibDTO);

    public void addCountDibs(Integer productId);

    // 찜 목록 삭제
    public void cancelDIb(Integer userId, Integer productId);

    public void deleteCountDibs(Integer productId);

    // 찜 목록 조회
    public List<DibDTO> showAllDibs(Integer userId);


}
