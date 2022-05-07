package test.java.chap02;

import main.java.chap02.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void 더하기(){
        int result = Calculator.plus(1, 2);

        assertEquals(3, result); // 첫번째 인자는 기대하는 값(expectation), 두번째 인자는 실제값(real value)
        assertEquals(5, Calculator.plus(4, 1));
    }
}
