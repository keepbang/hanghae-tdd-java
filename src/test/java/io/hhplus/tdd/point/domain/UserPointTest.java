package io.hhplus.tdd.point.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * create on 3/18/24. create by IntelliJ IDEA.
 *
 * <p> UserPoint Test </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
class UserPointTest {

  private UserPoint userPoint;

  @BeforeEach
  void setUp() {
    userPoint = new UserPoint(1L, 1000L, System.currentTimeMillis());
  }


  /**
   * 0이나 음수가 들어올경우 exception 을 발생시켜 잘못된 입력을 방지합니다.
   */
  @ParameterizedTest
  @ValueSource(longs = {
      0, -1
  })
  @DisplayName("0이나 음수가 들어올 경우 exception이 발생한다.")
  void increaseTest_fail_invalidInputAmount(long amount) {
    // given
    // when
    // then
    assertThatThrownBy(() -> userPoint.increase(amount))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("포인트는 양수만 입력 가능합니다.");
  }

  /**
   * 포인트를 충전 할 경우 기존 저장되어있던 포인트에서 입력된 포인트만큼 증가됩니다.
   */
  @Test
  @DisplayName("point는 증가된 값이여야 한다.")
  void increaseTest_ok() {
    // given
    // when
    UserPoint increase = userPoint.increase(1L);
    // then
    assertThat(increase.point()).isEqualTo(1001L);
  }

  /**
   * 입력된 포인트보다 잔고가 작을 경우 예외 발생.
   */
  @Test
  @DisplayName("point 잔고 부족")
  void decreaseTest_fail_balanceCheck() {
    // given
    // when
    // then
    assertThatThrownBy(() -> userPoint.decrease(1001L))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("잔고가 부족합니다.");

  }

  /**
   * 남은 포인트는 기존 포인트에 입력된 포인트만큼 감소한 값입니다.
   */
  @ParameterizedTest
  @CsvSource(value = {
      "999, 1",
      "1000, 0"
  })
  @DisplayName("point는 감소된 값이여야 한다.")
  void decreaseTest_ok(long amount, long balance) {
    // given
    // when
    UserPoint decrease = userPoint.decrease(amount);
    // then
    assertThat(decrease.point()).isEqualTo(balance);

  }

}