# 오늘 뭐 먹지? [JAVA]

## 프로그램 소개
<img width="958" alt="스크린샷 2021-07-28 오전 1 39 09" src="https://user-images.githubusercontent.com/78864775/127193700-17915b93-d2c0-4498-8074-d41f407842c4.png">
<img width="958" alt="스크린샷 2021-07-28 오전 1 39 28" src="https://user-images.githubusercontent.com/78864775/127193704-a1e23e36-76df-457c-8f79-fa35cd4c5ac0.png">
<br></br>

## 와이어 프레임
![image](https://user-images.githubusercontent.com/78864775/127352891-c804a663-1bc6-4e93-9ea3-2d053f41132e.png)
![image](https://user-images.githubusercontent.com/78864775/127352920-a194a2d6-49fc-4a9f-9a40-945280de01ed.png)
**주요 기능**
- 음식 재료 랜덤 선택
- 음식 재료 칼로리 및 개수 계산 & 출력
- 음식 메뉴 연결 & 출력
- 레시피 연결 & 출력
<br></br>

## 프로그램 진행 예시
1. 로그인 UI
![image](https://user-images.githubusercontent.com/78864775/127352930-bf4ada59-2433-4d13-b24e-6fcb94245383.png)
    - password가 맞을 시 연결
<br></br>
2. 냉장고 음식 재료 출력
![image](https://user-images.githubusercontent.com/78864775/127352946-ce2daf14-d5c7-4549-90a4-cdeebf2bb564.png)
  - 기능
    - 재료별 칼로리&개수 계산
    - 재료 선택 시, 테이블에 해당 정보 추가
    - 선택/선택 취소/전체 취소
    - '레시피 확인' 버튼 클릭 시, 선택된 재료에 따라 음식 메뉴 연결
    - 이전 화면 돌아가기
<br></br>
3. 음식 메뉴 (1) 순서별 이미지 & 텍스트 출력
![image](https://user-images.githubusercontent.com/78864775/127352964-87743992-a726-4f79-b37f-fbdecd07dcfa.png)
  - 기능
    - 앞서 선택된 재료와 수가 많은 순서대로 음식 메뉴 출력
    - 타이머 START/PAUSE/STOP
    - 이전 화면 돌아가기
<br></br>
3. 음식 메뉴 (2) 레시피 출력
![image](https://user-images.githubusercontent.com/78864775/127352981-b9f67672-c7fb-454f-9e9c-5e61a298c3ca.png)
  - 기능
    - 음식 메뉴 이미지 클릭 시, 레시피 출력
    - 음식 메뉴 이미지 클릭 시, 다른 이미지 흑백화 & 클릭 제한
