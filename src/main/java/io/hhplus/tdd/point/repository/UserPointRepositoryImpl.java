package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.common.exception.DatabaseException;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.domain.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * create on 3/17/24. create by IntelliJ IDEA.
 *
 * <p> Table 접근을 위한 Repository </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Repository
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository {

  private final UserPointTable userPointTable;

  @Override
  public UserPoint charge(long id, long amount) {
    try {
      UserPoint chargedUserPoint = userPointTable.selectById(id)
          .increase(amount);

      return userPointTable.insertOrUpdate(id, chargedUserPoint.point());
    } catch (InterruptedException e) {
      throw new DatabaseException();
    }
  }

  @Override
  public UserPoint use(Long id, long amount) {
    try {
      UserPoint chargedUserPoint = userPointTable.selectById(id)
          .decrease(amount);

      return userPointTable.insertOrUpdate(id, chargedUserPoint.point());
    } catch (InterruptedException e) {
      throw new DatabaseException();
    }
  }

  @Override
  public UserPoint findById(long id) {
    try {
      return userPointTable.selectById(id);
    } catch (InterruptedException e) {
      throw new DatabaseException();
    }
  }

}
