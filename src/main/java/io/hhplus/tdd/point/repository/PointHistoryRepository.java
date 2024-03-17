package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.point.domain.PointHistory;
import io.hhplus.tdd.point.domain.TransactionType;

/**
 * create on 3/17/24.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface PointHistoryRepository {

  PointHistory save(Long id,
                    Long amount,
                    TransactionType transactionType,
                    Long updateMillis);

}
