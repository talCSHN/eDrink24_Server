package org.eDrink24.controller.dibs;

import org.eDrink24.dto.basket.BasketDTO;
import org.eDrink24.dto.dibs.DibDTO;
import org.eDrink24.dto.order.OrderTransactionDTO;
import org.eDrink24.dto.store.StoreDTO;
import org.eDrink24.service.basket.BasketService;
import org.eDrink24.service.dibs.DibService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class DibController {

    DibService dibService;

    public DibController(DibService dibService) {
        this.dibService = dibService;
    }

    // 상품 찜하기(dibs 테이블에 저장)
    @PostMapping("/addDibs/{userId}")
    public ResponseEntity<String> addDibs(@RequestBody DibDTO dibDTO) {
        System.out.println("ADADDADADDA" + dibDTO);
        try {
            dibService.addDibs(dibDTO);
            dibService.addCountDibs(dibDTO.getProductId());
            return ResponseEntity.ok("Add Dib successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing purchase: " + e.getMessage());
        }
    }

    @DeleteMapping("/cancelDIb/{userId}/{productId}")
    public ResponseEntity<String> cancelDIb(@PathVariable Integer userId, @PathVariable Integer productId) {

        try {
            dibService.cancelDIb(userId, productId);
            dibService.deleteCountDibs(productId);
            return ResponseEntity.ok("Delete Dib successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing purchase: " + e.getMessage());
        }
    }

    // 찜 목록 조회
    @GetMapping("/showAllDibs/{userId}")
    public ResponseEntity<List<DibDTO>> showAllDibs(@PathVariable Integer userId) {
        List<DibDTO> dibs = dibService.showAllDibs(userId);
        return ResponseEntity.ok(dibs);
    }

}
