### heroku server 배포
------------------

1.첫번째로 헤로쿠 홈페이지에서 회원가입을 한다.     
2.heroku git 방식을 사용 할 것이기 때문에 필요한 heroku CLI를 설치해준다.     
3.cmd를 열고 헤로쿠에 로그인.   
![image](https://user-images.githubusercontent.com/100845256/167870316-08d8aa2f-6c62-4291-8284-33aac12d30dc.png)     


스프링 부트 프로젝트는 무조건 처음에 Procfile이라는 파일을 생성하고 설정을 해줘야한다.    
![image](https://user-images.githubusercontent.com/100845256/167874928-79b3b712-7e0b-41db-9f9d-2718f443906b.png)

<처음 연동 방법>       
heroku와 연결할 project폴더를 생성 한고, 해당 경로로 이동, 헤로쿠와 연결.     
cd 프로젝트경로/     
git init      
heroku git:remote -a 본인의앱이름    

<서버 연동 후 업데이트 방법>    
경로 설정 후,   
git add,   
git commit -am "make it better"  해주면 끝   
![image](https://user-images.githubusercontent.com/100845256/167871509-74b3e6ed-7c20-40c2-b895-8617b8b0b0f3.png)   

 
헤로쿠 기본 설정을 마친 후에 clearDB라는 mysql을 지원해주는 데이터베이스를 헤로쿠로 부터 지원받는다.   
![image](https://user-images.githubusercontent.com/100845256/167872251-b66385b5-f753-430b-b94d-0597af75e2fc.png)    
settings의 환경변수를 들어가서 sql의 아이디,비밀번호,주소 등을 받는다.   
![image](https://user-images.githubusercontent.com/100845256/167872357-0312f92e-9066-4749-91d3-0be7a98b8159.png)    
지원 받은 환경 변수들을 가지고 내 프로젝트 환경설정 파일에 서버용 db설정을 해준다.   
![image](https://user-images.githubusercontent.com/100845256/167872492-9779f923-7a89-4d04-906b-c610e27343fa.png)     
그 후에 mysql workbench에서 heroku에서 받은 cleardb 환경변수를 가지고 sql connection을 해준다.   
![image](https://user-images.githubusercontent.com/100845256/167873271-b7f8f2a5-ceab-4d3d-99fd-9ab97756cd44.png)
커밋해주면 이런식으로 실행 결과가 나타나고 사이트 주소를 알려준다. done이 뜨면 끝!   
![image](https://user-images.githubusercontent.com/100845256/167873738-adaa56f5-cff1-475b-bbd1-7ab72d508d87.png)  

헤로쿠 cmd창에 heroku logs -a sixwordsboard2 를 치면 서버의 로그기록을 볼 수 있다.
![image](https://user-images.githubusercontent.com/100845256/167874645-808332b7-1762-496e-b90a-c5993900cf71.png)     
![image](https://user-images.githubusercontent.com/100845256/167874714-ed5ca818-c3ab-44a4-9083-0bd3cdd5a341.png)     

## 이렇게 하면 cmd창에 적혀있는 사이트 주소를 웹에 치면 접속할 수 있게 된다!   


