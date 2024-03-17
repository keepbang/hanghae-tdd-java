package io.hhplus.tdd.point.service;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.PointHistoryRepositoryImpl;
import io.hhplus.tdd.point.repository.UserPointRepository;
import io.hhplus.tdd.point.repository.UserPointRepositoryImpl;
import java.security.InvalidParameterException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PointServiceTest {

  private PointService service;
  private PointHistoryRepository pointHistoryRepository;
  private UserPointRepository userPointRepository;

  private static final long USER_ID = 1;

  /**
   * repository 메서드 실행을 위한 객체 생성
   */
  @BeforeAll
  public void setUp() {
    pointHistoryRepository = new PointHistoryRepositoryImpl(
      new PointHistoryTable()
    );
    userPointRepository = new UserPointRepositoryImpl(
      new UserPointTable()
    );

    service = new PointService(
      userPointRepository,
      pointHistoryRepository
    );

    service.charge(USER_ID, 1000L);
  }

  /**
   * 0이나 음수가 들어올경우 exception 을 발생시켜 잘못된 입력을 방지합니다.
   */
  @ParameterizedTest
  @ValueSource(longs = {
    0, -1
  })
  @DisplayName("0이나 음수가 들어올 경우 exception이 발생한다.")
  void invalidInputAmount(long amount) {
    // given
    // when
    // then
    Assertions.assertThatThrownBy(() -> service.charge(USER_ID, amount))
      .isInstanceOf(IllegalArgumentException.class);
  }

  /**
   * 포인트를 충전 할 경우 기존 저장되어있던 포인트에
   */


}