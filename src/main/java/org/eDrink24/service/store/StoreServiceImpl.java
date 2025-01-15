package org.eDrink24.service.store;

import org.eDrink24.config.StoreMapper;
import org.eDrink24.dto.store.StoreDTO;
import org.eDrink24.domain.store.Store;
import org.eDrink24.repository.store.StoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ModelMapper modelMapper;

    private StoreMapper storeMapper;

    public StoreServiceImpl(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    @Override
    public StoreDTO findByStoreId(int storeId) {
        Store store = storeRepository.findByStoreId(storeId);
        StoreDTO storeDTO = modelMapper.map(store, StoreDTO.class);
        return storeDTO;
    }

    @Override
    public List<StoreDTO> findAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream()
                .map(store -> modelMapper.map(store, StoreDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StoreDTO checkBrNum(Integer storeId, Long brNum) {
        return storeMapper.checkBrNum(storeId, brNum);
    }
}
