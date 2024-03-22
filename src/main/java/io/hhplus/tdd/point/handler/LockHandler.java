package io.hhplus.tdd.point.handler;

import io.hhplus.tdd.point.exception.CustomLockException;
import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Component;

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
@Component
public class LockHandler {

  private final ConcurrentHashMap<Long, ReentrantLock> map = new ConcurrentHashMap<>();

  private static final long TIME_OUT_SECONDS = 3;

  public void userLock(Long id) {
    ReentrantLock lock = map.getOrDefault(id, new ReentrantLock());
    LocalTime time = LocalTime.now().plusSeconds(TIME_OUT_SECONDS);

    while(lock.isLocked()) {
      lock = map.get(id);

      if (time.isBefore(LocalTime.now())) {
        throw new CustomLockException("포인트가 사용되지 않았습니다.");
      }
    }
    lock.lock();
    map.put(id, lock);
  }

  public void userUnLock(Long id) {
    ReentrantLock lock = map.getOrDefault(id, new ReentrantLock());

    if (lock.isLocked()) {
      lock.unlock();
    }
  }

  public boolean isLocked(Long id) {
    return map.getOrDefault(id, new ReentrantLock())
        .isLocked();
  }
}
