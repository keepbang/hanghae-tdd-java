package io.hhplus.tdd.point.domain;

public record UserPoint(
    Long id,
    Long point,
    Long updateMillis
) {

  public UserPoint increase(long amount) {
    amountValidation(amount);

    long chargedPoint = this.point + amount;
    return new UserPoint(this.id, chargedPoint, this.updateMillis);
  }

  public UserPoint decrease(long amount) {
    amountValidation(amount);
    balanceCheck(amount);

    long balance = this.point - amount;
    return new UserPoint(this.id, balance, this.updateMillis);
  }

  /**
   * 포인트 잔고 체크.
   *
   * @param amount 사용할 포인트
   * @throws IllegalArgumentException 잔고가 부족할 경우 예외 발생
   */
  private void balanceCheck(long amount) {
    if (this.point < amount) {
      throw new IllegalArgumentException("잔고가 부족합니다.");
    }
  }

  /**
   * 입력된 포인트 검증.
   *
   * @param amount 충전할 포인트
   * @throws IllegalArgumentException 0이나 음수일 경우 예외 발생
   */
  private void amountValidation(Long amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("포인트는 양수만 입력 가능합니다.");
    }
  }
}
