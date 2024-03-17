package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.domain.TransactionType;
import io.hhplus.tdd.point.domain.UserPoint;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * create on 3/17/24.
 * create by IntelliJ IDEA.
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

  public UserPoint charge(Long id, Long amount) {
    amountValidation(amount);

    UserPoint userPoint = userPointRepository.charge(id, amount);

    pointHistoryRepository.save(
      id,
      amount,
      TransactionType.CHARGE,
      userPoint.updateMillis()
    );

    return userPoint;
  }

  /**
   * 입력된 포인트 검증.
   *
   * @param amount   입력된 포인트
   * @throws IllegalArgumentException  0이나 음수일 경우 예외 발생
   */
  private void amountValidation(Long amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("포인트는 양수만 입력 가능합니다.");
    }
  }

}
