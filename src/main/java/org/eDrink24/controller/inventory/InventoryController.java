package org.eDrink24.controller.inventory;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.dto.Inventory.InventoryDTO;
import org.eDrink24.service.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/api/findInventoryByStoreId/{storeId}")
    public ResponseEntity<List<InventoryDTO>> findInventoryByStoreId(@PathVariable int storeId) {
        List<InventoryDTO> InventoryList = inventoryService.findAllByStoreId(storeId);
        return ResponseEntity.ok(InventoryList);
    }
}
