# 테스트코드 작성 순서

- **가장 쉬운 상황부터 테스트**
  - 왜? 어려운 상황의 테스트부터 진행하면 해당 어려운 테스트를 성공하기 위해 매우 복잡한 코드 구현이 필요함. 우리는 쉬운 테스트 상황을 먼저 가정해서 쉬운 구현부터 점진적으로 해나가는 것이 목표이다.!
  - (개발의 리듬, 짧은 주기, 잦은 리팩토링, 개발자의 집중력 향상 등을 돕는다.)
- **예외 상황 부터 테스트**
  - 예외상황을 마지막에 생각하면 다 구현을 한다음 코드의 구조를 바꿔야하는 상황이 생길 수 있다.
  - 예외상황은 if else if else 조건문이 자주 사용된다. 이는 코드의 구조자체가 바뀌는 것이므로, 처음부터 예외상황을 생각해서 코드 구조를 미리 잡아놓는다!

# 완급조절

테스트 성공을 위해 코드를 구현하는데 있어, 곧바로 코드를 일반화하여 성공하려 하지마라. 단계적으로 차근차근 구현하라

1. 상수를 통해 테스트 성공 시키기
2. 추가된 테스트의 상수 비교로 테스트 성공시키기
3. 일반화 하기

plus 기능을 테스트한다하면

```java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void plus() {
        Calculator cal = new Calculator();
        int result = cal.plus(4, 6);
        assertEquals(10, result);
    }
}

```
- 1단계: 위테스트 성공위해 상수 넣기
```java
class Calculator{
    public int plush(int num1, int num2){
        return 10;
    }
}
```

```java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void plus() {
        Calculator cal = new Calculator();
        int result = cal.plus(4, 6);
        assertEquals(10, result);

        int result = cal.plus(4, 10);
        assertEquals(14, result);
    }
}

```

- 2단계 : 상수끼리 비교하여 상수 리턴
```java
class Calculator{
    public int plush(int num1, int num2){
        if(num1 == 4 && num2 == 10) return 14;
        return 10;
    }
}
```

- 3단계 : 일반화 단계
```java
class Calculator{
    public int plush(int num1, int num2){
        return num1 + num2;
    }
}
```

- 너무 쉬운데 그냥 바로 일반화하면 안되나? 굳이 이런 방법으로 코드를 구현해야하나?
  - 이렇게 한번에 코드를 구현하는 범위를 완급조절하면 나중에 점진적으로 코드를 구현할 때 밑거름이 된다. - 연습의 일종!

# 지속적인 리팩토링
- 테스트 통과한 뒤엔, 리팩토링을 진행한다. 
- TDD를 하며 지속적인 리팩토링은 가독성 높은 코드를 만드는데 큰 도움을 준다.
> 테스트코드도 리팩토링 해야한다! 그치만 테스트는 '어떤걸 테스트하는지 알아야 한다'. 너무 과도한 리팩토링으로 어떤걸 테스트하는 지 한눈에 보이지 않으면 테스트코드의 존재 자체가 의미가 없어짐.

# 추가적으로 알게된 내용
- TDD를 진행하며 해야할 테스트를 알게 될 것이다. 할일 목록에 해당 테스트를 해야한다는걸 넣어놔라! 처음부터 테스트할 모든 목록을 아는 것은 불가능에 가깝기에.
- 리팩토링과정은 비교적 짧게잡아서 TDD를 짧은 호흡과 짧은 리듬으로 진행하라!
- 테스트를 어떻게 시작해야할지 모를때는 'then' 절부터 생각해라. 그다음 테스트를 위해 어떤 테스트 코드가 필요할지 생각하자.
- 무조건 쉬운 상황부터 그리고 예외적인 상황부터 테스트하라!
- 테스트성공을 위한 구현은 완급조절을 통해 구현!

# 이번장을 통해 느낀점
- 쉬운상황이나 예외적인 상황을 먼저 테스트 -> 구현 -> 리팩토링의 과정이 뭔가 게임같이 재밌었다. 파란불이 들어오면 너무 기분이 좋았고, 빨간불이 들어오면 '뭐가 잘못이지?'같은 고민을 하는게 나는 재밌었다.
- 역시 안정적이다. 테스트가 성공하면 코드 구현이 제대로 이루어지고 있다는 반증이기에 너무 안정적인 느낌을 받아 좋았다. 단순히 구현만 하거나, 구현하고 테스트할 때는, 해당 구현에서 놓친 테스트가 있지않을까?등의 불안함이 있었는데 해당 불안함이 싹 없어졌다.
- 실제 구현을 어떻게해야하는지에대한 감이 잡혀서 너무 좋았다. 한번의 큰 기능을 구현하기에 앞서 고민하다 포기한 적이 종종 있었는데, 가장 쉬운상황, 가장 예외적인 상황부터 테스트하고 완급조절하며 구현을 하는 일종의 cycle(flow)를 알게되니 너무 좋았다.
- 지속적인 리팩토링을 통해 깔끔한 코드를 유지하는게 너무 좋았다. 이전엔, 구현이 다 끝난다음 리팩토링을 하였기에 리팩토링 도중 '기능이 고장나면 어떡하지'라는 고민과 두려움이 있었는데 이를 해결해주었다.