package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.domain.TransactionType;
import io.hhplus.tdd.point.domain.UserPoint;
import io.hhplus.tdd.point.dto.PointHistoryResponse;
import io.hhplus.tdd.point.dto.UserPointResponse;
import io.hhplus.tdd.point.exception.CustomLockException;
import io.hhplus.tdd.point.handler.LockHandler;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * create on 3/17/24. create by IntelliJ IDEA.
 *
 * <p> 포인트 비즈니스 로직 service </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class PointService {

  private final UserPointRepository userPointRepository;
  private final PointHistoryRepository pointHistoryRepository;
  private final LockHandler lockHandler;

  public UserPointResponse charge(Long id, Long amount) {
    boolean isLocked = false;
    try {
      isLocked = lockHandler.userLock(id);

      if (!isLocked) {
        throw new CustomLockException("포인트가 충전되지 않았습니다.");
      }

      UserPoint chargedUserPoint = userPointRepository.userPointById(id)
          .increase(amount);

      UserPoint userPoint = userPointRepository.charge(id, chargedUserPoint.point());
      insertPointHistory(id, amount, TransactionType.CHARGE, userPoint.updateMillis());
      return convertUserPointResponse(userPoint);
    } finally {
      lockHandler.userUnLock(id, isLocked);
    }
  }

  public UserPointResponse use(Long id, long amount) {
    boolean isLocked = false;
    try {
      isLocked = lockHandler.userLock(id);

      if (!isLocked) {
        throw new CustomLockException("포인트가 사용되지 않았습니다.");
      }

      UserPoint usedPoint = userPointRepository.userPointById(id)
          .decrease(amount);
      UserPoint userPoint = userPointRepository.use(id, usedPoint.point());

      insertPointHistory(id, amount, TransactionType.USE, userPoint.updateMillis());
      return convertUserPointResponse(userPoint);
    } finally {
      lockHandler.userUnLock(id, isLocked);
    }
  }

  private void insertPointHistory(Long id, Long amount, TransactionType type, Long millis) {
    pointHistoryRepository.save(
        id,
        amount,
        type,
        millis
    );
  }

  public UserPointResponse userPointById(long id) {
    UserPoint userPoint = userPointRepository.userPointById(id);

    return convertUserPointResponse(userPoint);
  }

  private UserPointResponse convertUserPointResponse(UserPoint userPoint) {
    return new UserPointResponse(
        userPoint.id(),
        userPoint.point(),
        LocalDateTime.ofInstant(
            Instant.ofEpochMilli(
                userPoint.updateMillis()
            ), ZoneId.systemDefault()
        )
    );
  }

  public List<PointHistoryResponse> pointHistoriesById(long userId) {
    return pointHistoryRepository.selectAllByUserId(userId)
        .stream()
        .map(history -> new PointHistoryResponse(
            history.userId(),
            history.type(),
            history.amount(),
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(
                    history.timeMillis()
                ), ZoneId.systemDefault()
            )
        ))
        .sorted(Comparator
            .comparing(PointHistoryResponse::dateTime)
            .reversed())
        .toList();
  }
}
