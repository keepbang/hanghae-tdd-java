package io.hhplus.tdd.point.dto;

import io.hhplus.tdd.point.domain.TransactionType;
import java.time.LocalDateTime;

/**
 * create on 3/18/24. create by IntelliJ IDEA.
 *
 * <p> 포인트 충전/사용 내역 Response dto </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record PointHistoryResponse(
    Long userId,
    TransactionType type,
    Long amount,
    LocalDateTime dateTime
) {

}
