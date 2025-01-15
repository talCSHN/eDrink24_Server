package org.eDrink24.controller.store;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.dto.store.StoreDTO;
import org.eDrink24.service.store.StoreCacheService;
import org.eDrink24.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class StoreController {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreCacheService storeCacheService;

    @GetMapping("/api/findStore/{storeId}")
    public ResponseEntity<StoreDTO> findStore(@PathVariable("storeId") int storeId) {
        try {
            StoreDTO storeDTO = storeService.findByStoreId(storeId);
            return ResponseEntity.ok(storeDTO);
        } catch (NoSuchElementException e) {
            // 특정 예외 상황 처리: 매장을 찾지 못한 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/findStore/findAll")
    public ResponseEntity<List<StoreDTO>> findAllStore() {
        try {
            List<StoreDTO> storesDTO = storeCacheService.getCashedStoresDTO();
            if (storesDTO == null || storesDTO.isEmpty()) {
                storesDTO = storeService.findAllStores();
                return ResponseEntity.ok(storesDTO);
            }
            return ResponseEntity.ok(storesDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/checkBrNum/{storeId}/{brNum}")
    public ResponseEntity<StoreDTO> checkBrNum(@PathVariable Integer storeId, @PathVariable Long brNum) {
        StoreDTO storeDTO =storeService.checkBrNum(storeId, brNum);
        if(storeDTO == null) {
            return ResponseEntity.status(404).body(new StoreDTO());
        } else{
            return  ResponseEntity.ok(storeDTO);
        }

    }
}
