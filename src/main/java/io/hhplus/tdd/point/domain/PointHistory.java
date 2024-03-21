package io.hhplus.tdd.point.domain;

public record PointHistory(
    long id,
    long userId,
    TransactionType type,
    long amount,
    long timeMillis
) {

}
