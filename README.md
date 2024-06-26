# [항해플러스 과제] TDD 로 개발하기
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/keepbang/hanghae-tdd-java)](https://hits.seeyoufarm.com)

## 과제 요구사항
- PATCH  `/point/{id}/charge` : 포인트를 충전한다.
- PATCH `/point/{id}/use` : 포인트를 사용한다.
- GET `/point/{id}` : 포인트를 조회한다.
- GET `/point/{id}/histories` : 포인트 내역을 조회한다.
- 잔고가 부족할 경우, 포인트 사용은 실패하여야 합니다.
- 동시에 여러 건의 포인트 충전, 이용 요청이 들어올 경우 순차적으로 처리되어야 합니다.

## 기능 목록
- [x] 포인트 충전
- [x] 포인트 사용
- [x] 포인트 조회
- [x] 포인트 내역 조회
- [x] 동시에 여러건 요청이 들어올 경우 순차적 처리 로직 구현
- [X] 통합 테스트 진행

## 고민 사항
- lock을 획득 하고 작업 진행 중에 실패했을 경우 롤백 처리
- history와 UserPoint 저장의 우선순위
