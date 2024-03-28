package io.hhplus.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import io.hhplus.tdd.point.dto.PointHistoryResponse;
import io.hhplus.tdd.point.service.PointService;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TddIntegrationTest {

  @Autowired
  private PointService service;

  /**
   * 동시에 충전과 사용이 일어날 경우를 테스트 합니다.
   */
  @Test
  void 동시성_테스트() throws InterruptedException {
    // given
    long userId = 1L;
    int numberOfThreads = 10;
    ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    CountDownLatch latch = new CountDownLatch(numberOfThreads);

    // when
    // 초기 포인트 설정
    service.charge(userId, 1000L);

    for (int i = 0; i < numberOfThreads; i++) {
      int charged = (i % 2); // 충전과 사용을 번갈아가면서 하기 위한 로직
      executorService.execute(() -> {
        try {
          if (charged == 0) {
            service.charge(userId, 100L);
          } else {
            service.use(userId, 100L);
          }
        } catch (IllegalArgumentException e) {
					e.printStackTrace();
				} finally {
          latch.countDown();
        }
      });
    }
    latch.await();

    // then
    List<PointHistoryResponse> histories = service.pointHistoriesById(userId);
    assertThat(histories).hasSize(numberOfThreads + 1); // 초기 포인트 충전도 포함
    assertThat(service.userPointById(userId).point())
        .isEqualTo(1000L); // 같은 포인트로 충전 5번 사용 5번이므로 결과값은 초기 포인트 충전값과 같아야한다.

    executorService.shutdown();
  }

}
