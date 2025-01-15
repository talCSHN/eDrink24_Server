package org.eDrink24.dto.store;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;


@Alias("StoreDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class StoreDTO {
    // 직렬화, 역직렬화 할때 해당 UID를 통해서 클래스버전을 확인 => 호환성 문제
    // 자바에서 직렬화는 바이트스트림으로 코드를 변환해 다른 JVM이나 프로그램으로 보내줌
    // 예시로 DB에 데이터를 저장할때, 직렬화를 통해 데이터를 보내고 이를 DB가 역직렬화한 후에 저장함
    private static final long serialVersionUID = 1L;

    private Integer storeId;
    private String storeName;
    private String storeAddress;
    private String storePhoneNum;
    private Double latitude;
    private Double longitude;
    private Long brNum;

}

