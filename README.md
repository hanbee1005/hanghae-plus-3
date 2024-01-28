# 항해 플러스 3기 개인 프로젝트
항해 플러스 3기를 진행하면서 실습한 내용들을 정리한 프로젝트입니다.

### 기술 스택
- Java 17
- SpringBoot 3.2.0
- Gradle
- MySQL, Redis, JPA

## Chapter 1. CI/CD 배포 파이프라인 구축
### 환경 분리
- local
- dev
- stg
- prod

### Github Action 분리
- ```pr-ci.yml``` : develop 브랜치로 pr이 들어온 경우 실행되는 파일로 테스트/빌드를 진행합니다. (test는 test-container를 사용)
- ```dev-cd.yml``` : develop 브랜치로 push가 일어나는 경우 테스트/빌드 및 AWS ECR로 이미지를 푸시하고 ECS 배포까지 진행하게 됩니다.
- ```prod-cd.yml``` : 

## Chapter 2. TDD 서버 구축
### Layered Architecture
```
com.hanghae.hanghaeplus3
    ├── account
    │   ├── controller
    │   │   ├── AccountRestController.java
    │   │   ├── request
    │   │   │   └── BalanceChargeRequest.java
    │   │   └── response
    │   │       ├── ChargeBalanceResponse.java
    │   │       └── FindBalanceResponse.java
    │   ├── repository
    │   │   ├── AccountJpaRepository.java
    │   │   ├── AccountRepositoryImpl.java
    │   │   └── entity
    │   │       └── AccountEntity.java
    │   └── service
    │       ├── AccountRepository.java
    │       ├── AccountService.java
    │       └── domain
    │           └── Account.java
    ├── config
    ├── constant
    ├── exception
    ├── member
    ├── order
    ├── payment
    ├── product
    ├── BaseTimeEntity.java
    ├── CommonResponse.java
    └── HanghaePlus3Application.java
```
### TDD
- 구현 기능
  - 이커머스(e-commerce) 상품 구매 서비스
  - 주요 API
    - 계좌 잔액 조회
    - 계좌 잔액 충전
    - 상품 조회
    - 상품 구매
    - 인기 상품 조회
- 주요 포인트
  - 동시성 테스트
    - 따닥 방지
    - 동시 재고 차감 방지
  - 캐시
    - 인기 상품 조회

### 통합 모니터링 시스템 구축
