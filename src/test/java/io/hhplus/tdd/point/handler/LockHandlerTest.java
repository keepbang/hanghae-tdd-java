package io.hhplus.tdd.point.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.hhplus.tdd.point.exception.CustomLockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * create on 2024/03/21.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Gibyoung Chae (keepbang)
 * @version 1.0
 * @since 1.0
 */
class LockHandlerTest {

  LockHandler handler = new LockHandler();

  /**
   * lock을 획득하고 unlock을 안할 경우 exception 발생.
   *
   * @throws InterruptedException
   */
  @Test
  @DisplayName("lock을 획득하고 unlock을 안할 경우 exception 발생")
  void userLockTest_fail()  {
    // given
    Long userId = 1L;
    handler.userLock(userId); // lock 획득
    // when
    // then
    assertThatThrownBy(() -> handler.userLock(userId))
        .isInstanceOf(CustomLockException.class); // 같은 아이디로 lock 획득
  }

  /**
   * lock을 획득하고 unlock을 할 경우 lock 상태는 false 여야한다..
   *
   * @throws InterruptedException
   */
  @Test
  @DisplayName("lock을 획득하고 unlock을 할 경우 true return")
  void userUnLockTest_ok()  {
    // given
    Long userId = 1L;
    boolean isLocked = handler.userLock(userId);// lock 획득
    handler.userUnLock(userId, isLocked); // 같은 아이디로 lock 획득
    // when
    boolean isLock = handler.isLocked(userId);

    // then
    assertThat(isLock).isFalse();
  }

}