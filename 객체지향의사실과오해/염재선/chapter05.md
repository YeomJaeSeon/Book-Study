# 책임과 메시지

## 자율적인 책임
- 객체에게 주어진 책임이 적당히 추상적(너무 추상적이여도안됨)이라면 책임은 자율적이다. 이러한 자율적인 책임이 객체지향에서의 객체들간의 협력을 유연하게 만든다.
    - 책임이 타율적이면 객체는 너무 구체화된 책임을 수행하는데 있어, 유연한 설계를 만들지 못한다.

## 메시지와 메서드
- 객체가 책임을 수행하는건 **메시지**를 요청받았을 떄 이다.
- 메시지가 자율적이여야 객체가 수행하는 책임도 자율적이다.
- 메시지를 통해서 객체 외부와 내부를 분리한다. (메시지를 요청한 외부 객체는 단순히 요청한 메시지를 객체가 적절히 수행해 응답할지만 알지, 객체가 받은 메시지에 대해 어떤 방법으로 수행할지는 모른다.)
    - 객체 외부와 내부를 분리함을 통해, 최소한만 외부로 드러나게하여(외부로 드러나는것은 메시지) 협력의 관점에서 객체 내부가 변경하더라도 변경에 대한 파급효과가 크지않아 변경에 유리해질수있다.

## 메시지를 따라라
- 당연히 책임은 메시지가 요청받았을 떄 수행되므로 메시지를 먼저 생각하는 것이 중요하다.
    - 메시지를 생각하고, 이 메시지를 어떤 객체가 수행할수있을 지를 생각한다. 그래야 객체지향의 객체들의 협력이 유연해 질 수 있다.
- 위에서 말했던 것처럼, 메시지가 자율적이어야 책임이 자율적이다. 

## 객체 인터페이스
- 객체가 수신하는 메시지의 목록을 인터페이스라고한다.
    - 메시지는 '인터페이스'와, 수신받는 객체의 '책임'을 결정한다.

## 인터페이스와 구현의 분리
- 메시지를 통해서, 객체 외부 내부를 분리할수있다고 하였다. 인터페이스는 메시지의 집합이므로 외부에 드러난건, 인터페이스고 내부에 숨기는건 '구현'이라고 한다.
    - 구현은 객체의 상태, 행동이다. (행동을 구현이라고 한건, 어찌됐던 행동을 구현한 코드들은 객체 내부에 존재하기 때문이다. 인터페이스에는 행동의 이름이 있다고 생각하자.)
- 인터페이스와 구현을 분리하는 것은, **변경에 대한 파급효과를 줄여**주고, 객체를 **자율적으로**만들게 도와준다.
    - 외부로 드러나지 않을 부분은 객체 내부의 구현으로 숨기기에 당연히 변경에 유연해지고 다른 객체가 객체 내부의 구현에 접근하여 '이거해라, 저거해라'등 지시하지 못하지 당연히 자율적이게 될것이다.

## 책임의 자율성이 협력의 품질을 결정한다.
- **책임의 자율성이 중요하다. 그보다 더 중요한건 책임의 자율성은 메시지가 만든다는 것이다.**

> 메시지를 먼저 생각해라. 그다음 해당 메시지를 어떤 객체가 수신할지를 생각해라. 그러면 자연스레 자율적인 책임, 인터페이스와 구현에 대한 분리로 얻는 다양한 장점들을 얻을 수 있다. 객체지향은 협력이라는 관점에서 생각해야지, 객체 개개인 하나하나를 생각하면 안된다. 객체지향은 각자 책임을 가진 여러 객체들이 협력하여 사용자가 요구하는 기능을 만드는 매커니즘이기 때문이다. 여기서 객체지향의 아름다움을 느꼈다.

# 내 생각
- 5장에선 자율적인 책임을 강조하였고, 그보다 더 강조한건 메시지이다. 어플리케이션을 만들때 객체간 소통할수있는 메시지를 먼저 생각하라는데 이부분은 정말 낯설었다. 뭔가 기능을 만들때 항상 ERD부터 설계하였고, 각각의 객체가 필요한 상태와 메서드만 먼저 고민했기 때문이다. 그런데 5장을 읽고 나선, 어떤 기능을 만들기 위해, 어떤 메시지가 필요한지, 그리고 이 메시지는 어떤 객체가 수신할수있는지를 먼저 고민하면 객체는 자율적인 책임을 갖게될것이고, 변경에 유리하게 될것이다라는 것을 느꼈다.
- 그렇다면, 메시지를 먼저 생각하기 위해선 어떻게해야하지? 어떤 객체들이 필요할 것이란건 생각하지 않고, 메시지를 먼저 생각하고 '이러한 객체가 필요할 것이다'라는걸 생각하며 개발을 시작해야하나? 아니면 '대충 이정도의 객체들이 필요하겠군'이라는 생각을 먼저하고, 메시지를 생각한다음 '이 메시지는 이 객체에게 요청해야겠다'라는 방식으로 해야하나?
