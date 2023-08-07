# dodamdodam
## JPA와 Spring Security를 활용한 프로젝트입니다.
* 프로젝트 주제는 청년들의 문화 수준 증진을 통해 문화 강국으로 나아가는 것입니다.
* 화면은 클론코딩으로 제작하고 문구는 프로젝트 주제에 맞춰서 만들었습니다.
* 클론 코딩 사이트는 위시켓(https://makercenter.wadiz.kr/) 입니다

제가 작업한 퍼블리싱 부분은 마이페이지 입니다.

#### 백엔드 담당 분야
- 모집 게시판, 판매 게시판, 이벤트 게시판을 담당했습니다.

#### 담당 분야 활용 기술
- JPA 기술을 활용하여 Repository를 통해 DBMS에 접근하여 정보를 조회하고 화면에 전달했습니다.

##### ▶ 로그인, 회원가입
1. Redis
2. Spring Security
3. OAuth 2.0
4. Thymeleaf
5. Ajax & Rest

##### ▶ 결제
1. 부트페이 API

##### ▶ 판매 게시판
1. Thymeleaf
2. Ajax & Rest

## 데이터베이스 테이블(DB ERD)
![dodamdodam](https://github.com/dev-mwYoon/dodamdodam/assets/122762471/35c5c3c8-afc6-453e-9516-248be04971f1)


## 😃 느낀점
 &nbsp;JPA를 배우고 나서 진행한 첫 프로젝트는 제게 많은 깨달음을 안겨주었습니다. 이전에 Spring으로 작업할 때보다 시간이 훨씬 적게 들었고, 코드도 훨씬 깔끔하게 작성할 수 있었습니다. 이 경험을 통해 JPA의 강력한 ORM(Object-Relational Mapping) 기능이 개발 작업을 어떻게 효율적으로 만들어 주는지를 몸소 체감하게 되었습니다.

 &nbsp;프로젝트를 마치고 난 뒤에는 더욱 확신을 가지게 되었습니다. JPA를 사용함으로써 데이터베이스와의 상호작용을 보다 추상화하고 단순화할 수 있어서 개발 생산성이 높아지는 것을 몸소 경험했습니다. 이로써 개발자는 비즈니스 로직에 더 집중할 수 있고, 데이터베이스와의 복잡한 작업들을 JPA가 대신 처리해주므로 개발 생명주기를 단축하고 유지보수를 용이하게 할 수 있다는 것을 깨달았습니다.

 &nbsp;또한, JPA를 배우고 프로젝트를 수행함으로써 데이터베이스 설계와의 연계성이 얼마나 중요한지를 더욱 느낄 수 있었습니다. 객체 모델과 데이터베이스 모델 간의 일치를 유지하고 관리함으로써 데이터의 일관성을 유지할 수 있는 장점을 깨달았습니다. 이로써 데이터베이스 구조에 대한 더 깊은 이해와 설계의 중요성을 더욱 강조하게 되었습니다.

 &nbsp;이 첫 JPA 프로젝트를 통해 얻은 지식과 경험은 앞으로의 개발에 큰 자신감과 힘을 주었습니다. 더 나아가서는 JPA를 더 깊게 공부하고 활용하여 더 복잡하고 유용한 애플리케이션을 개발하고, 개발자로서의 역량을 더욱 키워나가고자 합니다.

## 😥 아쉬운점

&nbsp; 프로젝트를 진행하면서 처음 사용하는 기술을 적용하는 것은 어쩔 수 없이 시간과 노력을 요구하는 일이었습니다. 이 과정에서 저 또한 적응하는 데 시간이 걸렸고, 특히 제가 맡은 게시판 중 1개를 완성하지 못한 것은 아쉬웠습니다.
1. 시간 분배와 계획 관리의 어려움
&nbsp;처음 사용하는 기술을 배우고 적용하는 것은 예상치 못한 어려움을 초래했습니다. 이로 인해 프로젝트 내에서 시간을 효율적으로 분배하는 데 어려움을 겪었고, 결과적으로 모든 게시판을 완성하지 못한 점이 아쉬웠습니다.

2. 기능 구현 우선순위 설정의 부재
&nbsp; 모든 게시판을 완성하지 못한 이유 중 하나는 기능 구현의 우선순위를 명확하게 설정하지 못한 것입니다. 이로 인해 시간이 부족한 상황에서 핵심 기능을 완성하지 못하게 되었습니다.

