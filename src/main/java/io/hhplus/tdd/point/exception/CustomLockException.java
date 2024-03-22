package io.hhplus.tdd.point.exception;

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
public class CustomLockException extends RuntimeException {

  public CustomLockException(String message) {
    super(message);
  }
}
