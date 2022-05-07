package test.java.chap02;

import main.java.chap02.PasswordStrength;
import main.java.chap02.PasswordStrengthMeter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);// 기대값, 테스트할 값
    }

    //빈 테스트를 실행하는것도 테스트 환경 준비됐는지 확인하는데 의미가 있다.
    @Test
    void 모든_조건_충족_암호는_STRONG(){
        assertStrength("ab12!@AB", PasswordStrength.STRONG);

        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    void 조건_하나_미충족_길이8글자_미만_NORMAL(){
        assertStrength("ab12!@A", PasswordStrength.NORMAL);

        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    void 조건_하나_미충족_숫자포함하지않음_NORMAL(){
        assertStrength("ab!@ABQWER", PasswordStrength.NORMAL);
    }

    @Test
    void 비밀번호_null_인경우_INVALID(){
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void 비밀번호_빈_문자열_인경우_INVALID(){
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void 조건하나_미충족_대문자_없음_NORMAL(){
        assertStrength("abcd12!32b", PasswordStrength.NORMAL);
    }

    @Test
    void 조건_하나만_충족_8글자_이상_WEAK(){
        assertStrength("abcdefgabcdefg", PasswordStrength.WEAK);
    }

    @Test
    void 조건_하나만_충족_숫자_포함_WEAK(){
        assertStrength("1abcd", PasswordStrength.WEAK);
    }

    @Test
    void 대문자_하나만_충족_WEAK(){
        assertStrength("Abc", PasswordStrength.WEAK);
    }

    @Test
    void 모든_조건_충족하지_않음_WEAK(){
        assertStrength("a", PasswordStrength.WEAK);
    }
}

/**
 * 첫번째테스트 중요!! : 가장 쉽거나, 가장 예외적인 상황을 선택한다.
 *
 * //여기부턴 cycle
 * 둘! : 컴파일 에러부터 없앤다. 여기서 중요한건 테스트 패키지에 코드를 작성하도록! main에 작성하면 배포 대상이된다.
 * 셋! : 테스트가 성공하도록 코드를 점진적으로 수정해나간다. (테스트 성공을 위해 간단하고 점진적으로 - 너무 코드구현에 매몰되지말자.)
 * 넷!: 구현 코드를 테스트를 하며 리팩토링 하자!
 * 다섯!: 테스트코드도 코드이다. 리팩토링 하자! - (*주의* 무턱대고 리팩토링한다고 중복을 없애면 오히려 가독성이 떨어질수 있다!)
 *
 */