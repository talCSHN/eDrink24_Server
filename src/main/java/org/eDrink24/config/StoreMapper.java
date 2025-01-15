package org.eDrink24.config;

import org.apache.ibatis.annotations.Mapper;
import org.eDrink24.dto.store.StoreDTO;

import java.util.List;


@Mapper
public interface StoreMapper {

    // 매장 목록 조회
    public List<StoreDTO> showAllStore();

    // 사업자등록번호가 존재하는지 확인
    public StoreDTO checkBrNum(Integer storeId, Long brNum);
}
