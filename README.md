# ScreenSceneP

## :clipboard: 목차

- :books: <a href="#outline">개요</a>
- :wrench: <a href="#tech">기술 스택</a>
- :scroll: <a href="#erd">ERD(Entity-Relation Diagram)</a>
- :family: <a href="#team">팀원 역할 소개</a>
- :bookmark_tabs: <a href="#function">기능 소개</a>
  - <a href="#fun0">홈화면
  - <a href="#fun1">1.&nbsp;로그인</a>
  	- 1-1. 회원가입
  - <a href="#fun2">2.&nbsp;영화 선택</a>
   	- 2-1. 영화 선택
  - <a href="#fun3">3.&nbsp;영화 촬영지 선택</a>
   	- 3-1. 영화 촬영지 선택
  - <a href="#fun4">4.&nbsp;경로 선택</a>
  	- 4-1. 경로선택
  - <a href="#fun5">5.&nbsp;마이페이지</a>
	- 5-1. 경로 확인
  - 5-2. 촬영지 주변 호텔 & 레스토랑 확인
  - 5-3. 경로 삭제
  - <a href="#fun6">6.&nbsp;내 정보 변경</a>
	- 6-1. 프로필 사진 변경
  - 6-2. 닉네임 변경
  - 6-3. 비밀번호 변경
- :bulb: <a href="#result">후기</a>
- :mag_right: <a href="#fullfill">보완할점</a>

# :books: <a name="outline">개요</a>
<br/>
<img src="https://github.com/Psh230412/FirstWeb/assets/134483516/d47f4916-dc44-4d24-ad16-1a850bb14f0a"/>

> **프로젝트** : 여행 경로 탐색
>
> **프로젝트 이름** : ScreenSceneP
>
> **분류** : 팀 프로젝트
>
> **제작 기간** : 2023.08.01 ~ 2023.08.16
>
> **프로젝트 주제** : 관심있는 영화의 촬영지를 찾아 여행 경로를 탐색해주는 웹사이트

<br/>

# :wrench: <a name="tech">기술 스택</a>

<h4>데이터베이스</h4>
<div align="left">
   <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
</div> 
<h4>백엔드</h4>
<div align="left">
    <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=Java&logoColor=white"/>
    <img src="https://img.shields.io/badge/Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white"/>
    <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
</div>
</div> 
<h4>프론트엔드</h4>
<div align="left">
   <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"/>
   <img src="https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"/>
   <img src="https://img.shields.io/badge/JAVASCRIPT-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"/>
</div>
<h4>API</h4>
<div align="left">
   <img src="https://img.shields.io/badge/Google%20Maps-4285F4?style=for-the-badge&logo=googlemaps&logoColor=white" />
   <img src="https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white" />
   <img src="https://img.shields.io/badge/GEOCODING-00874D?style=for-the-badge&logo=geocaching&logoColor=white" />
	<img src="https://img.shields.io/badge/maildotru-005FF9?style=for-the-badge&logo=geocaching&logoColor=white" />
 <img src="https://img.shields.io/badge/jsoup-0085F9?style=for-the-badge&logo=maildotru&logoColor=white" />
  <img src="https://img.shields.io/badge/SLF4J-0647F9?style=for-the-badge&logo=maildotru&logoColor=white" />
   <img src="https://img.shields.io/badge/commons%20fileupload-005FF9?style=for-the-badge&logo=maildotru&logoColor=white" />
	
</div>
<h4>협업도구</h4>
<div align="left">
   <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white" />
   <img src="https://img.shields.io/badge/FIGMA-F24E1E?style=for-the-badge&logo=figma&logoColor=white" />
</div>

# :scroll: <a name="erd">ERD</a>

## 초기 ERD

<img src="https://github.com/Psh230412/FirstWeb/assets/134483516/3b352032-c2c4-4e8b-b76b-4f2a3e6e805a" width="100%"/>

## 현재 ERD

<img src="https://github.com/Psh230412/FirstWeb/assets/134483516/71ce82e5-07df-423d-9f2f-30ee45f48631" width="100%"/>

### 변경이유

대부분의 데이터를 request와 session으로 이동시켜서 초기 구상 시 보다 간소화할 수 있었음

# :family: <a name="team">팀원 역할 소개</a>
<div><br/>
	
<h3>● 박상현</h3>

##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Back-End : 로그인, 로그아웃, 클래스 검색(목록/지도), 신청/문의 알림, 이메일 인증, 클래스 신청&결제, 클래스 문의 답변, 계정 관리(정보 수정, 비밀번호 변경, 회원 탈퇴)
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Front-End : 헤더, 클래스 검색 페이지, 클래스 상세 페이지

<h3>● 김명완</h3>

##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Back-End :  클래스 등록/수정/삭제, 내 클래스 조회, 클래스 활성/비활성, 문의 접수 내역 조회 
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Front-End : 홈, 클래스 등록 페이지, 클래스 관리 페이지

<h3>● 선보라</h3>

##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Back-End : 신청 내역 조회, 리뷰 작성, 리뷰 내역 조회, 리뷰 추천
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Front-End : 마이페이지 신청 및 리뷰 내역 상세 정보 모달

<h3>● 성지수</h3>

##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Back-End : 회원가입, 아이디 찾기, 커뮤니티, 댓글
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Front-end : 회원가입 페이지, 로그인 페이지, 아이디 찾기 모달, 커뮤니티 

<h3>● 이강현</h3>

##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Back-End : 장바구니 추가/조회/삭제, 클래스 신청
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Front-End : 장바구니 페이지, 결제 페이지, 결제 완료 페이지

<h3>● 이정빈</h3>

##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Back-End :  클래스 일정 등록 및 조회, 일정 수정 및 삭제, 클래스 신청 인원 관리, 클래스 관리자 승인 	
##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Front-End : 마이페이지, 일정 관리 페이지, 신청 관리 페이지, 관리자 페이지
</div><br/>
