package io.hhplus.tdd.point.dto;

import java.time.LocalDateTime;

/**
 * create on 3/18/24. create by IntelliJ IDEA.
 *
 * <p> 사용자 포인트 정보 Response dto </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public record UserPointResponse(
    Long id,
    Long point,
    LocalDateTime updateDateTime
) {
}
