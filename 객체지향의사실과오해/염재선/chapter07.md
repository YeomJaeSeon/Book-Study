# 함께 모으기

## 객체지향 설계안에 존재하는 세 가지 상호 연관된 관점
1. 개념 관점
- 사용자가 도메인을 바라보는 관점
2. 명세 관점
- 객체의 인터페이스에 대한 관점
3. 구현 관점
- 인터페이스를 구현하는데 필요한 속성과 메서드

-> 클래스는 세가지 관점 모두를 가지고 있어야 한다.

이번 챕터의 목표
1. 도메인 모델 -> 최종 코드 구현까지 러프하게 경험하기
2. 클래스를 개념, 명세, 구현 관점에서 바라본느 것이 무엇인지 느끼기

## 커피 전문점 도메인
- 커피 전문점도 객체지향 관점에서 보면 객체들로 구성된 작은 협력의 세상이다.
- 어떤 객체가 있는지 생각하고 객체간 어떤 관계가 있는지 생각해보자. 그리고 객체를 타입으로 분류(클래스로)해 보자.

1. 커피 전문점에는 손님 객체, 바리스타 객체, 메뉴판 객체, 메뉴판에 속한 메뉴 객체, 커피 객체가 있을 수 있다.(어떤 객체가 잇는지 생각)
2. 손님은 메뉴판을 통해 메뉴를 알게되고, 손님은 바리스타를 통해 커피를 주문한다. 바리스타는 커피를 만들어 손님에게 제공한다. 이렇게 객체간의 관계를 러프하게 생각해보자.

-> 네모, 마름모등으로 위의 객체의 타입과 타입간의 관계를 정리한 것이 **도메인 모델**이다.

이제 러프하게 커피 전문점의 도메인 모델을 그렸으니 필요한 메시지가 무엇인지 생각하고 메시지를 수신할 객체를 선택하여 객체들에 책임을 할당해보자. 그리고 각 객체들은 서로 협력할 것이다.

### 설계하고 구현하기
- 협력을 위한 객체의 책임이 무엇이 있을지 생각해보자.
- 먼저 커피샵에 오면 주문을 해야하니 '주문해라'라는 메시지가 있을 것이다.
- 위 '주문해라'라는 메시지는 손님이라는 객체가 수신할 것이다.
- 그럼 손님이란 객체는 해당 메시지에대한 책임을 다하기 위해 또 필요로 하는 메시지가 있는지 생각해보자.
- 손님은 커피를 주문하려면 메뉴를 알아야하기에 메뉴판에 '메뉴를 알려줘'의 메시지가 있을 것이다.
...

- 이런식으로 **메시지를 통해 객체가 어떤 책임이 필요한지 생각하며, 객체들간의 협력을 만들어보자.**

- 협력이 만들어졌으면 객체들의 공용 인터페이스(수신받을수 있는 메시지의 모음)을 생각해보자.
- 손님: 커피 주문하라
- 메뉴판: 메뉴 찾아라
- 바리스타: 커피 제조하라
- 커피: 커피 생성하라

-> 이 인터페이스들을 이제 코드로 만들어보자아
```typescript
export class Customer{
    public order(menuName: string){}
}

export class MenuItem{

}

export class Menu{
    public choose(name: string):MenuItem{}
}

export class Barista{
    public makeCoffee(menuItem: MenuItem):Coffee{}
}

export class Coffee{
    constructor(menuItem:MenuItem){
        this.menuItem = menuItem;
    }
}
```

-> 이젠 공용 인터페이스들을 구현하면된다.

- 구현내용은 여기에 쓰긴 너무많아 쓰지않겠지만, 구현을 하다보면 인터페이스의 모양이 달라지거나, 구현하며 객체의 상태등이 생기기도한다. 즉, 명세 관점에서 미리 모든걸 예측하는 것은 불가능하다. 

## 코드와 세 가지 관점
1. 개념
    - 도메인 모델의 개념들이 실제로 작성한 코드들에 그대로 반영되는 것을 볼 수 있다. 이렇게 도메인 모델과 클래스 사이의 간격이 좁으면 좁을 수록 기능을 변경하려고 뒤적뒤적해야하는 시간도 절약된다.
2. 명세
    - 외부에 드러나는 인터페이스 다른 객체와 협력하는 부분이다. 다른 객체(외부)에 드러나기 떄문에 수정하면 협력하는 객체에 모두 변경의 여파가 간다. 그렇기에 꼭 드러나야 하는 부분만 드러나게끔 하자.
3. 구현
    - 클래스 내부에 캡슐화 되어, 외부로 드러낼 필요 없는 구현부분은 드러내지 않는 것이 변경에 대한 파급효과를 줄이는데 중요하다.

-> 한 클래스 안에, 세가지 관점(개념, 명세, 구현)이 모두 포함되게끔 코드를 짜는 것이 **변경에 유연한 설게를 하는 지름길**이다.

## 도메인 개념을 참조 이유
- 메시지가 있을때 메시지를 수신할 객체를 선택하는데, 도메인 개념을 이용하면 메시지 수신할 객체를 찾기가 쉽다.
- 소프트웨어의 요구사항은 항상 변한느데, 변화에 쉽게 대응할 수 잇다.

## 인터페이스, 구현의 분리
- 명세와 구현을 분리하는 것은 필요한 기본중의 기본. 인터페이스에 구현이 노출되면 작은 변화에도, 다른 객체들과의 협력 모두가 수정이 필요.

# 내 생각
- 내가 만드려는 소프트웨어를 객체들로 구분해보고, 객체간 관계를 생각하며 협력을 고민하고ㅡ 인터페이스를 만들며 구현하는 객체지향적으로 설계하는 일련의 과정을 경험했는데, 되게 신선하고 재밌었다. 확실히 변경에 유연할 거란 생각이 든다.
- 코드 로 만든 클래스에 개념, 명세, 구현의 세 가지 관점이 모두 깃든다는 것이 요구사항이 항상 변경되는 소프트웨어의 세계에서 얼마나 중요한 것인지 느껴진다아..