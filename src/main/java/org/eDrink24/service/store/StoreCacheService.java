package org.eDrink24.service.store;

import org.eDrink24.domain.store.Store;
import org.eDrink24.dto.store.StoreDTO;
import org.eDrink24.repository.store.StoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreCacheService {
    private static final String STORE_CACHE_KEY = "storeCache";

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RedisTemplate<String, Object> redisStoreTemplate;

    @PostConstruct
    public void init() {
        cacheStore();
    }

    // 서버실행 시에 바로 캐싱
    @Scheduled(cron = "0 0 0 * * ?")
    public void cacheStore() {
        List<Store> stores = storeRepository.findAll();
        List<StoreDTO> storesDTO = stores.stream()
                .map(store -> modelMapper.map(store, StoreDTO.class))
                .collect(Collectors.toList());

        redisStoreTemplate.opsForValue().set(STORE_CACHE_KEY, storesDTO);
        System.out.println("complete caching stores");
    }

    public List<StoreDTO> getCashedStoresDTO() {
        return (List<StoreDTO>) redisStoreTemplate.opsForValue().get("storeCache");
    }
}
