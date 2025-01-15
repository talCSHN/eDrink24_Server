package org.eDrink24.service.store;

import org.eDrink24.dto.store.StoreDTO;

import java.util.List;

public interface StoreService {
    public StoreDTO findByStoreId(int storeId);
    public List<StoreDTO> findAllStores();
    public StoreDTO checkBrNum(Integer storeId, Long brNum);
}
