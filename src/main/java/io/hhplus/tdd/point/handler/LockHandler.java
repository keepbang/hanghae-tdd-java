package io.hhplus.tdd.point.handler;

import io.hhplus.tdd.point.exception.CustomLockException;
import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
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

  private final ConcurrentHashMap<Long, Lock> map = new ConcurrentHashMap<>();

  public boolean userLock(Long id) {
    Lock lock = map.getOrDefault(id, new ReentrantLock(true));
    boolean isLocked = false;
    try {
      isLocked = lock.tryLock(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new CustomLockException("포인트 작업이 오래 걸립니다. 잠시후 다시 시도해 주세요.");
    }

    map.put(id, lock);

    return isLocked;
  }

  public void userUnLock(Long id, boolean isLocked) {
    Lock lock = map.get(id);

    if (lock != null && isLocked) {
      lock.unlock();
      map.put(id, lock);
    }
  }

  public boolean isLocked(Long id) {
    return ((ReentrantLock) map.getOrDefault(id, new ReentrantLock()))
        .isLocked();
  }
}
