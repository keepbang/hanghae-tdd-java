package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.point.domain.UserPoint;

/**
 * create on 3/17/24.
 * create by IntelliJ IDEA.
 *
 * @author Gibyung Chae (Keepbang)
 * @version 1.0
 * @since 1.0
 */
public interface UserPointRepository {

  UserPoint charge(long id, long amount);

  UserPoint use(Long id, long amount);
}
