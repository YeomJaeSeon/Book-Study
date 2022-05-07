package test.java.chap03;

import main.java.chap03.ExpiryDateCalculator;
import main.java.chap03.PayData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    // == 예외상황 - 이것도 우선적으로 테스트해야함 == //
    @Test
    void 납부일과_한달_뒤_일자가_같지_않음(){
        System.out.println(this); // 진짜 테스트 @Test애너테이션 붙은 메서드 실행할때마다 새로운 테스트 클래스의 객체(인스턴스)를 생성하는 구나
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 30))
                        .payAmount(10_000)
                        .build()
                ,
                LocalDate.of(2019, 2, 28)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10_000)
                        .build()
                ,
                LocalDate.of(2019, 6, 30)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 30))
                        .payAmount(10_000)
                        .build()
                ,
                LocalDate.of(2020, 2, 29)
        );
    }

    // == 가장 쉬운상황 - 이거부터 테스트함  ==//
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){
        System.out.println(this);
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021, 12, 13))
                        .payAmount(10_000)
                        .build()
                ,
                LocalDate.of(2022, 1, 13)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021, 1, 1))
                        .payAmount(10_000)
                        .build()
                ,
                LocalDate.of(2021, 2, 1)
        );
    }

    @Test
    void 첫_납부일과_만료일의_날짜가_다를때_만원_납부하면_다음_만료일은_첫납부일과_날짜가_같음(){
        System.out.println(this);

        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
    }

    @Test
    void 이만원_이상_납부시_비례해서_만료일_계산(){
        System.out.println(this);

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 5, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 6, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(50_000)
                        .build(),
                LocalDate.of(2019, 8, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(90_000)
                        .build(),
                LocalDate.of(2019, 12, 1)
        );
    }

    @Test
    void 첫_납부일과_만료일_날짜가_다를때_2만원_이상_납부(){
        System.out.println(this);

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 4, 30)
        );

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 3, 31))
                        .billingDate(LocalDate.of(2019, 4, 30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 7, 31)
        );
    }


    //파라미터가 세개이상이면 하나의 객체로 만들까?라는 고민도해보자.
    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();

        LocalDate expiryDate = cal.calculateExpiryDate(payData);

        assertEquals(expectedExpiryDate, expiryDate); //기대값, 테스트할 값
    }

    @Test
    void 십만원_납부하면_1년_서비스_제공(){
        System.out.println(this);

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2020, 1, 28)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2020, 3, 1)
        );
    }

    @Test
    void 십만원_이상_납부하면_1년서비스제공이_추가됨(){
        System.out.println(this);

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 1))
                        .payAmount(130_000)
                        .build(),
                LocalDate.of(2020, 4, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 1))
                        .payAmount(200_000)
                        .build(),
                LocalDate.of(2021, 1, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 1))
                        .payAmount(250_000)
                        .build(),
                LocalDate.of(2021, 6, 1)
        );
    }

    @Test
    void 윤달_마지막날에_10만원_납부(){
        System.out.println(this);

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 2, 29))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2021, 2, 28)
        );
    }
}
/**
 * 테스트코드 리팩토링은 고민좀해야한다. 리팩토링이 필요하다고 바로 리팩토링하지말자.
 * 왜?
 * **무엇을 테스트하는건지 한눈에 보여야 하는 것이 더 중요하기 때문에**
 *
 *
 * 가장 쉬운상황 & 예외상황부터 테스트하려하라 -> 그다음은 그다음으로 쉬운상황 & 예외상황을 생각하자.
 */