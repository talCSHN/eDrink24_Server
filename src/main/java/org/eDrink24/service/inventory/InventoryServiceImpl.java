package org.eDrink24.service.inventory;

import org.eDrink24.domain.inventory.Inventory;
import org.eDrink24.dto.Inventory.InventoryDTO;
import org.eDrink24.repository.inventory.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<InventoryDTO> findAllByStoreId(int storeId) {
        List<Inventory> inventories = inventoryRepository.findAllByStoreId(storeId);
        return inventories.stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDTO.class))
                .collect(Collectors.toList());
    }
}
