package org.eDrink24.service.inventory;

import org.eDrink24.dto.Inventory.InventoryDTO;

import java.util.List;

public interface InventoryService {
    public List<InventoryDTO> findAllByStoreId(int storeId);
}
