package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.domain.TransactionType;
import io.hhplus.tdd.point.domain.UserPoint;
import io.hhplus.tdd.point.dto.UserPointResponse;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

  // TODO: 순차적 처리를 위한 로직 구현을 위해
  //        charge 메소드에서 table에 Insert 하지 않고 point가 변경된 userPoint를 return
  public UserPointResponse charge(Long id, Long amount) {
    UserPoint userPoint = userPointRepository.charge(id, amount);

    insertPointHistory(id, amount, TransactionType.CHARGE, userPoint);

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

  public UserPointResponse use(Long id, long amount) {
    UserPoint userPoint = userPointRepository.use(id, amount);

    insertPointHistory(id, amount, TransactionType.USE, userPoint);

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

  private void insertPointHistory(Long id, Long amount, TransactionType type, UserPoint userPoint) {
    pointHistoryRepository.save(
        id,
        amount,
        type,
        userPoint.updateMillis()
    );
  }

}
