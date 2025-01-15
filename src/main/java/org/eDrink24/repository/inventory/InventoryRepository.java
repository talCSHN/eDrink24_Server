package org.eDrink24.repository.inventory;

import org.eDrink24.domain.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    public List<Inventory> findAllByStoreId(int storeId);
}
