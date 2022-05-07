# JUnit 5 기초

## Junit 5 모듈 구성
1. JUnit 플랫폼: 테스팅 구동을 위한 런처, 테느트 엔진을 위한 api 제공(환경 조성)
2. JUnit 주피터: JUnit5 테스트 api제공 (api 제공)
3. JUnit 빈티지: JUnit version convertor

## @Test 애노테이션과 테스트 메서드

JUnit 으로 테스트 코드 작성하려면
1. 테스트로 사용할 클래스 작성
2. 메서드위에 @Test애노테이션 붙이기

만 하면 된다.

## 주요한 단언 메서드(then절에 사용되는 JUnit api methods)

- assertEquals, assertNotEquals ( Object.equals )
- assertSame, assertNotSame ( == )
- assertTrue, assertFalse
- assertNull, assertNotNull
- fail() - 자주 사용 X, 대신 assertThrows를 사용하자.
- assertThrows, assertDoesNotThrows : 발생한 예외 객체를 리턴한다.
  - 발생한 예외 객체로 추가적인 테스트를 하고싶을때 (ex) 발생한 예외 객체의 메시지를 테스트하고 싶을 때) 
    ```java
    MemberNotFoundException thrown assertThrows(MemberNotFoundException.class,
            () -> memberService.foundById(id));
    assertTrue(thrown.getMessage().equals("해당 아이디의 회원이 없습니다."));
    ```
- assertAll: assert 단언 메서드들은 실패하면 다음 코드를 실행하지 않고 예외를 발생시키는데, assertAll을 사용하면 일단 테스트를 모두 하고 어떤 테스트가 실패했는지 알수 있다.

## 테스트 라이프 사이클
테스트 메서드 하나 실행하면
1. @BeforeAll
2. 테스트 클래스의 객체 생성 
3. @BeforeEach
4. 테스트 메서드 실행(@Test붙은 메서드)
5. @AfterEach
6. (2, 3, 4, 5 반복)
7. @AfterAll

> @Test메서드 붙은 테스트 메서드 실행할때마다 테스트 할 클래스의 객체가 생성이 된다.(매번)

## 테스트시 주의할점
- **테스트 메서드간 실행 순서를 의존하지 말자** 왜냐면 JUnit은 버전마다 실행되는 테스트 순서가 다 다르다. 테스트 메서드 실행 순서가 다르면 틀리는 테스트는 만들면 안된다!
- **필드를 공유해서 테스트 하지 않기** 테스트 메서드간 하나의 필드를 공유해서 사용하면 실행순서에 따라 다른 값이 도출될 수 있다. 즉, 테스트가 터진다.

> 즉, 테스트 메서드들 끼린 **독립적**으로 동작해야 한다.

## @DisplayName, @Disabled
- 테스트 메서드만으로 설명이 부족할땐 `@DisplayName`애너테이션 사용하자
- 해당 테스트는 잠시 테스트하고 싶지 않을때(미완성이거나, 아직 부족한 테스트일떄)는 `@Disabled`애너테이션을 테스트 메서드 위에 추가하자

# 느낀점
- JUnit 5 를 구성하는 요소를 알게되었다. 사실 몰라도 사용하는데 어려움은 없긴하지만 라이브러리를 보고 이게 어떠한 의존성 모듈인지를 알게되니 좋았다.
- 다양한 단언 메서드들을 알게 되었고, 특히 assertThrows를 많이 사용하는데, 발생하는 예외 객체를 반환한다는 사실을 알게되서 좋았따.
- 테스트 라이프사이클중, `@Test`가 붙은 테스트 메서드가 실행 될 때마다, 테스트할 클래스의 객체를 만든다는 사실을 몰랐다. 테스트 메서드들은 서로 독립적으로 동작해야 하므로, 매 테스트마다 새로운 객체를 생성하는 것이 적절하다 생각된다.
- 테스트 메서드 이름만으로 이름짓기가 어려울떈 `@DisplayName`을 사용하면 좋겠다!