package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.point.domain.PointHistory;
import io.hhplus.tdd.point.domain.TransactionType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * create on 3/17/24. create by IntelliJ IDEA.
 *
 * <p> PointHistoryTable 접근을 위한 Repository </p>
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
    return pointHistoryTable.insert(id, amount, transactionType, updateMillis);
  }

  @Override
  public List<PointHistory> selectAllByUserId(long userId) {
    return pointHistoryTable.selectAllByUserId(userId);
  }
}
