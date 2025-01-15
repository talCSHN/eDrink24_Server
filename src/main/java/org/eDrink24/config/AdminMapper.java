package org.eDrink24.config;

import org.apache.ibatis.annotations.Mapper;
import org.eDrink24.dto.Inventory.InventoryDTO;
import org.apache.ibatis.annotations.Param;
import org.eDrink24.dto.admin.AdminDTO;

import java.util.List;

@Mapper
public interface AdminMapper {

    // 발주신청시 매장 내 해당재고 갯수 증가
    public void updateInventoryQuantity(InventoryDTO inventoryDTO);

    // 장바구니 목록에 이미 존재하는지 확인
    public InventoryDTO checkInventoryProduct(Integer productId, Integer storeId);

    // 해당재고 없으면 INVENTORY에 추가
    public void addProductToInventory(InventoryDTO inventoryDTO);

    // 발주내역 adminOrderHistory에 추가
    public void addAdminOrderHistory(InventoryDTO inventoryDTO);

    // 발주시 픽업유형 변경
    public void updatePickupType(InventoryDTO inventoryDTO);

    // 픽업완료버튼 클릭 시 IsCompleted true로 변경,
    public int changeIsCompleted(@Param("ordersId") Integer ordersId);

    // changeStatus PICKUPED, changeDate 버튼 클릭 한 시간으로 변경
    public int ChangeStatusAndDate(@Param("ordersId") Integer ordersId);

    // 픽업완료버튼 클릭 시 inventory테이블 수량 변경
    public int changeInventoryQuantity(@Param("ordersId") Integer ordersId);

    // 발주완료 목록 조회
    public List<InventoryDTO> showAdminOrderList(int storeId);

    // 픽업완료 내역페이지에서 픽업주문처리가 완료된 것만 보여줌. (isCompleted가 true)
    public List<AdminDTO> showPickupCompletedPage(int storeId);

    // 즉시픽업페이지에서 즉시픽업이 아직 이루어지지 않은 것만 보여줌. (isCompleted가 false)
    public List<AdminDTO> showTodayPickupPage(int storeId);

    public List<AdminDTO> showReservationPickupPage(int storeId);


}
