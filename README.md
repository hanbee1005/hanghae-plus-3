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
- ```pr-ci.yml``` : 
- ```dev-cd.yml``` : 
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

### 통합 모니터링 시스템 구축
