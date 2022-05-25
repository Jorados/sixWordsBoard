## :+1: JPA 엔티티매핑(본체 연결)
-------------------------------
목차
   * 객체와 테이블 매핑
   * 데이터베이스 스키마 자동 새성
   * 필드와 컬럼 매핑
   * 기본 키 매핑
   
엔티티 매핑
   * 객체와 테이블 매핑:@Entity,@Table
   * 필드와 컬럼 매핑:@Column
   * 기본 키 매핑:@Id
   * 연관관계 매핑:@ManyToOne,@JoinColumn

#### :star: 객체와 테이블 매핑   
@Entity  
   * @Entity가 붙은 클래스는 JPA가 관리,엔티티라 한다.   
   * JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수   
   * 주의!:기본 생성자 필수(파라미터 없는 public , protected 생성자)   
   * final 클래스,enum,interface,inner클래스 사용x   
   * 저장할 필드에 final 사용x   

#### :star: 필드와 컬럼 매핑   
매핑 어노테이션 정리   
   * @Coliumn : 컬럼 매핑   
   * @Temporal : 날짜 타입 매핑   
   * Enumerated : enum 타입 매핑   
   * @Lob : BLOB,CLOB 매핑    
   * @Transient : 특정필드를 컬럼에 매핑하지 않음(매핑 무시)    
  
#### :star: 기본 키 매핑 어노테이션     
   * @id / @GeneratedValue     
   * @GeneratedValue의 IDENTITY전략 특징 : 기본 키 생성을 데이터베이스에 위임한다. 등등 코드로는 @GeneratedValue(strategy = GenerationType.IDENTITY)라고 치면 된다.
   * @GeneratedValue의 SEQUENCE전략 특징 : 데이터베이스 시퀀스는 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트
   * 권장하는 식별자 전략 : 기본 키 제약 조건 > null아님,유일,변하면 안된다.







