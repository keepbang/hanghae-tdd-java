package io.hhplus.tdd.point.domain;

public record UserPoint(
        Long id,
        Long point,
        Long updateMillis
) {

  public UserPoint charge(long amount) {
    amountValidation(amount);

    long chargedPoint = this.point + amount;
    return new UserPoint(this.id, chargedPoint, this.updateMillis);
  }

  /**
   * 입력된 포인트 검증.
   *
   * @param amount   입력된 포인트
   * @throws IllegalArgumentException  0이나 음수일 경우 예외 발생
   */
  private void amountValidation(Long amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("포인트는 양수만 입력 가능합니다.");
    }
  }
}
