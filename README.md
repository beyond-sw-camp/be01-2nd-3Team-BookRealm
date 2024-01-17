
<div align="center"><img src="https://github.com/choi3179/BookRealm/blob/dev/%EB%AC%B8%EC%84%9C%EC%9E%90%EB%A3%8C%20%EB%B0%8F%20%EC%8B%9C%EC%97%B0%EC%98%81%EC%83%81/BookRealm_logo.png" width="300"/></div>

<br /><br /><br />

# 📚 프로젝트 소개 

- 우리의 프로젝트는 도서를 손쉽게 찾아 구매하고 관리할 수 있는 온라인 서점 플랫폼입니다. 다양한 기능과 편의성을 제공하여 사용자가 즐겁고 효율적으로 도서를 검색하고 구매할 수 있도록 합니다.
- 편리하고 안전한 쇼핑을 통해 도서 소장과 읽기를 더욱 즐겁게 만들어줍니다.<br /><br /><br />

# 😀 팀원 및 역할
 👸**이영진** - 프로젝트 총괄책임, Spring Security 적용, 로그인/회원가입 구현, 마이페이지 구현, 주문 서비스 구현 <br />
 👧**김단아** - UI총괄책임, 도서 서비스 구현, 장바구니 서비스 구현 <br />
 👶**안우용** - 문서총괄책임, 리뷰 서비스 구현, 장바구니 서비스 구현, 주문 서비스 구현 <br />
 👳‍♂️**최원규** - GitHub 총괄책임, 관리자 서비스 구현, 네이버 도서검색 API 적용 <br />
 <br /><br /><br />

 # 기술 스택
 BACKEND<br />
 ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![apachmaven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white) ![SpringSecurity](https://img.shields.io/badge/springsecurity-6DB33F.svg?style=for-the-badge&logo=springsecurity&logoColor=white) <br />
 FRONTEND<br />
 ![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E) ![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white) ![thymeleaf](https://img.shields.io/badge/thymeleaf-005F0F.svg?style=for-the-badge&logo=thymeleaf&logoColor=white)<br />
버전 관리<br />
  ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)	![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)<br />
협업<br />
  ![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white) ![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)<br />

 
# 📝 프로젝트 요구사항 분석서
[요구사항 분석서](https://github.com/choi3179/BookRealm/blob/main/DB%EC%84%A4%EA%B3%84/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%20%EB%B6%84%EC%84%9D.txt) <br /><br /><br />
```
  회원은 도서를 구매할 수 있다.
  회원과 비회원은 (제목,작가, 베스트셀러 여부, 카테고리)를 기준으로 검색을 할 수 있다.
  회원은 회원가입을 통해 가입한다.
  회원은 장바구니에 도서를 저장할 수 있다.
  회원은 도서를 마이리스트에 저장할 수 있다.
  회원과 비회원은 장바구니에 있거나 검색한 도서를 구매할 수 있다.
  회원은 구매한 도서의 리뷰를 작성하고 별점을 줄 수 있다.
  회원은 구매한 도서를 교환, 환불 신청할 수 있다.
  회원과 비회원은 도서의 상세 정보를 확인할 수 있다.
  	-  제목, 작가, 카테고리, 가격, 수량
  	- 별점, 리뷰 
  
  
  관리자가 도서를 추가,삭제,수정, 조회할 수 있다.
  관리자는 구매완료된 도서를 조회할 수 있다.
  관리자는 회원 정보를 관리할 수 있다.
```

# ⚙ DB설계
<h3>✔ 개념적 설계</h3>

![image](https://github.com/choi3179/BookRealm/assets/145534055/eebaad93-cdd2-422a-aa01-ff4507f995f6) <br /><br />

<h3>✔ 논리적 설계</h3>

![이미지](https://github.com/choi3179/BookRealm/blob/main/DB%EC%84%A4%EA%B3%84/%EB%85%BC%EB%A6%AC%EC%A0%81%20%EC%84%A4%EA%B3%84.png) <br /><br />

<h3>✔ 물리적 설계</h3>

[물리적 설계 보기](https://github.com/choi3179/BookRealm/blob/main/DB%EC%84%A4%EA%B3%84/%EB%AC%BC%EB%A6%AC%EC%A0%81%20%EC%84%A4%EA%B3%84.sql)
