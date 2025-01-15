package org.eDrink24.controller.admin;


import org.eDrink24.dto.Inventory.InventoryDTO;
import org.eDrink24.dto.admin.AdminDTO;
import org.eDrink24.service.admin.AdminOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminOrderController {

    AdminOrderService adminOrderService;

    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    // 발주시, 물건이 있으면 update, 없으면 insert
    @PostMapping(value = {"/updateOrInsertInventory/{storeId}/{productId}"})
    public ResponseEntity<String> updateOrInsertInventory(@PathVariable Integer productId, @PathVariable Integer storeId,
                                                            @RequestBody InventoryDTO inventoryDTO) {
        try {
            adminOrderService.updateOrInsertInventory(productId, storeId, inventoryDTO);
            return ResponseEntity.ok("Admin Order successful");
        } catch (Exception e)    {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // 예약픽업발주 신청 가져오기
    @GetMapping(value = {"/showReservationPickupPage/{storeId}"})
    public List<AdminDTO> showReservationPickupPage(@PathVariable int storeId) {
        return adminOrderService.showReservationPickupPage(storeId);
    }

    @GetMapping(value = {"/showAdminOrderList/{storeId}"})
    public List<InventoryDTO> showAdminOrderList(@PathVariable int storeId) {
        return adminOrderService.showAdminOrderList(storeId);
    }

}
