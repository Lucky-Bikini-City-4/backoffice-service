package com.dayaeyak.backofficservice.backoffice.kafka.consumer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@Data
public class EventMessage {
    private String serviceName;   // 이벤트 발생 서비스
    private String eventType;     // USER_LOGIN, BOOKING_CREATED, PAYMENT_FAILED 등
    private String userId;        // 이벤트 관련 사용자
    private Map<String, Object> payload; // 이벤트 세부 데이터
    private long timestamp;       // 발생 시각
}
