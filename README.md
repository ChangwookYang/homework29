# 주문 시스템

### 프로젝트 구조

- 사용 기술

  - Java 11
  - Spring Boot
  - JPA (Java Persistence API)
  - JPQL (Java Persistence Query Language)
  - MapStruct
  - Pessimistic Locking  
  - Gradle
  - H2 Database


### 구현방향에 대한 내용
- 엔티티 설계

  - Product (상품)
  - Stock (재고)
  - Order (주문)
  - OrderProducts (주문상품)

- SoldOutException 구현
  - RuntimeException 상속
  - StockService.java 에서 decrease 메서드 호출 시 발생
  - Multi Threads SoldOutException 동작 단위테스트 구현

- OrderServiceTest.java
  - 다수의 쓰레드에서 초과주문되는 횟수만큼 SoldOutException 발생
  - StockRepository.java에서 Pessimistic write lock을 사용하여 동시성 문제 해결
  
- H2 database 사용
  - data.sql로 상품 데이터 초기화
