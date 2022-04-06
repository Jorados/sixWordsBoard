### 웹 컨트롤러 개발 (스프링 웹 MVC 기술)
------------------------------------------
🥇스프링 공부를 하면서 이부분은 공부를 안했었는데 이 부분이 많이 부족하다는 것을 깨달아 이 기술 공부를 자세히 다룰 예정이다.  

#### :star:스프링 웹 MVC 기술 -> 백엔드 데이터들을 모델뷰 데이터로 전환시켜서 정보와 함께 HTTP로 쏴주는 거라고 보면된다.
#### Controller
   *  MemberController

      *  @ExceptionHandler(DuplicatedIdEx.class)를 통한 컨트롤러 내에서 발생하는 예외를 전부 잡아서 하나의 메서드로 처리.
      *  @GetMapping("/member/new") return "member/new"; / @PostMapping ("/member/new") return "redirect:/member/afternew/{memberName}";
      *  @GetMapping("/member/afternew/{memberName}")  return "member/afternew";
      *  @GetMapping("/member/{id}") / return "member/mypage";

   *  BoardController

      *  @ExceptionHandler(NoAuthorizationEx.class)를 통한 컨트롤러 내에서 발생하는 예외를 전부 잡아서 하나의 메서드로 처리.
      *  @GetMapping("/board/new") return "board/save"; / @PostMapping("/board/new") return "redirect:/board";
         * redirect해서 리턴하는 이유: POST/GET방식으로 DB작업이 있는 페이지를 끝내고 결과페이지를 일반적으로 포워딩 할 경우 F5나 새로고칭 등으로 서버에 동일한 요청을 보내어 문제가 발생할 가능성이 있어 주요 서비스에는 중복 요청이 들어가지 않도록 결과는 redirect 할 수 있도록 처리가 필요하다. !! 
      *   @GetMapping("/board") return "board/loginBoard"; 
      *   @GetMapping("/board/edit") return "board/edit"; / @PostMapping("/board/edit") return "redirect:/board";
      *   @GetMapping("/board/delete") return "board/delete"; / @PostMapping("/board/delete") return "redirect:/";

   *  LikeController
      
      ![image](https://user-images.githubusercontent.com/100845256/161971852-2bbf0793-a697-4a76-a30f-f2acd1c29874.png)
      *  loginMember를 받고 likeCheck와 boardId를 받아서 눌러져있으면 취소로직 안눌러져있으면 누르는 로직 가동 하게 해서 결과적으로 updateLikeCnt 반환.

   *  HomeController
      ![image](https://user-images.githubusercontent.com/100845256/161981475-30c1049b-09b2-4d29-9f52-311ae9addabf.png)
      *  domain에 있는 Pagination 객체를 생성하고 좋아요순 게시물을 orderedByL , 최신순 게시물을 orderedByD 라고 정하고 동적배열 List에 담는다.
      *  여기서 홈화면을 들어갈때 LoginController의 Member loginMember = loginService.login(dto.getLoginId(), dto.getPassword());의 유무 확인하고 있으면 loginHome 리턴

   *  LoginController
      * @ExceptionHandler(FailedLoginEx.class)를 통한 컨트롤러 내에서 발생하는 예외를 전부 잡아서 하나의 메서드로 처리.
      * @GetMapping("/member/login") return "member/login"; / @PostMapping("/member/login") return "redirect:" + redirectURL;
      * SessionConst라는 클래스를 만들어서 세선명을 LOGIN_MEMBER = "loginMember"; 설정해주고  @PostMapping에서 로그인할때 LOGIN_MEMBER,loginMember를 부여해준다.
   *  LoginCheckInterceptor
      * 여기서 미인증 사용자인지 아닌지를 체크한다. URI와 쿼리스트링을 받아와서 SessionConst.LOGIN.MEMBER가 있는지 없는지 판단.
   *  ErrorTestController
      *  @GetMapping("/500")  throw new RuntimeException("에러발생!");  실행중에 발생함. 시스템 환경적으로나 인풋값이 잘못된경우 
      *  @GetMapping("/400")  throw new IllegalStateException("에러발생!"); Controller의 @RequestMapping의 값이 중복되어 나타나는 에러


 

    


