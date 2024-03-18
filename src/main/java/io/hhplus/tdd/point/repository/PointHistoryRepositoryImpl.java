package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.common.exception.DatabaseException;
import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.point.domain.PointHistory;
import io.hhplus.tdd.point.domain.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * create on 3/17/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Repository
@RequiredArgsConstructor
public class PointHistoryRepositoryImpl implements PointHistoryRepository {

  private final PointHistoryTable pointHistoryTable;

  @Override
  public PointHistory save(Long id, Long amount, TransactionType transactionType,
      Long updateMillis) {
    try {
      return pointHistoryTable.insert(id, amount, transactionType, updateMillis);
    } catch (InterruptedException e) {
      throw new DatabaseException();
    }
  }
}
