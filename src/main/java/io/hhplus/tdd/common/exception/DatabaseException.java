package io.hhplus.tdd.common.exception;

/**
 * create on 3/17/24.
 * create by IntelliJ IDEA.
 *
 * <p> Database 처리중에 발생하는 Exception </p>
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public class DatabaseException extends RuntimeException {
  private final String exceptionMessage = "데이터베이스에서 에러가 발생했습니다.";

  public DatabaseException() {
    super("데이터베이스에서 에러가 발생했습니다.");
  }

  public DatabaseException(String message) {
    super(message);
  }
}
