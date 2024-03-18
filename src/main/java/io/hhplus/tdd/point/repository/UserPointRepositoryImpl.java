package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.common.exception.DatabaseException;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.domain.UserPoint;
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

}
