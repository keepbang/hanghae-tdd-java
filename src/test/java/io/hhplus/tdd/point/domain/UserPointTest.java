package io.hhplus.tdd.point.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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

  /**
   * 0이나 음수가 들어올경우 exception 을 발생시켜 잘못된 입력을 방지합니다.
   */
  @ParameterizedTest
  @ValueSource(longs = {
      0, -1
  })
  @DisplayName("0이나 음수가 들어올 경우 exception이 발생한다.")
  void chargeTest_fail_invalidInputAmount(long amount) {
    // given
    UserPoint userPoint = new UserPoint(1L, 1000L, System.currentTimeMillis());
    // when
    // then
    assertThatThrownBy(() -> userPoint.charge(amount))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("포인트는 양수만 입력 가능합니다.");
  }

  /**
   * 포인트를 충전 할 경우 기존 저장되어있던 포인트에서 입력된 포인트만큼 증가됩니다.
   */
  @Test
  @DisplayName("point는 증가된 값이여야 한다.")
  void chargeTest_ok() {
    // given
    UserPoint userPoint = new UserPoint(1L, 1000L, System.currentTimeMillis());
    // when
    UserPoint chargedUserPoint = userPoint.charge(1L);
    // then
    assertThat(chargedUserPoint.point()).isEqualTo(1001L);
  }


}