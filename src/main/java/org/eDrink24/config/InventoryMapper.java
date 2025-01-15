package org.eDrink24.config;

import org.apache.ibatis.annotations.Mapper;
import org.eDrink24.dto.Inventory.InventoryDTO;

import java.util.Map;

@Mapper
public interface InventoryMapper {
    public InventoryDTO findInventoryForUpdate(Map<String, Integer> map);
    public int updateInventory(Map<String, Integer> map);
}
