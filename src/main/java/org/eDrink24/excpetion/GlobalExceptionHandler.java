package org.eDrink24.excpetion;

import lombok.extern.slf4j.Slf4j;
import org.eDrink24.excpetion.NotEnoughStock.NotEnoughStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 주문 시, 오늘픽업 재고부족으로 인한 예외
    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<String> notEnoughStockException(NotEnoughStockException e) {
        log.error("재고부족 예외: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    // 모든 예외를 처리하는 핸들러 추가
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        log.error("서버내부 예외: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버오류 발생: "+e.getMessage());
    }
}
