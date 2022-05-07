package main.java.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    //파라미터가 세개이상이면 객체를 만들어 파라미터를 줄일까라는 생각을 통해 리팩토링을 해보자
    public LocalDate calculateExpiryDate(PayData payData){
        int addedMonths = payData.getPayAmount() >= 100_000 ?
                calculateAddedMonthWithServiceYear(payData) : payData.getPayAmount() / 10_000;

        if(payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        }else{
            return expiryDateNotUsingFirstBillingDate(payData, addedMonths);
        }
    }

    private LocalDate expiryDateNotUsingFirstBillingDate(PayData payData, int addedMonths) {
        return payData.getBillingDate().plusMonths(addedMonths);
    }

    private int calculateAddedMonthWithServiceYear(PayData payData) {
        return (payData.getPayAmount() / 100_000) * 12 + (payData.getPayAmount() % 100_000) / 10_000;
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = expiryDateNotUsingFirstBillingDate(payData, addedMonths);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

        if(!isSameDayOfMonth(candidateExp, dayOfFirstBilling)){ // 첫납부일 day와 후보만료일 day가 다르면
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp); // 후보 만료일의 마지막 day
            if(dayLenOfCandiMon < dayOfFirstBilling){
                return candidateExp.withDayOfMonth(
                        dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(
                    dayOfFirstBilling);
        }else{
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate candidateExp, int day) {
        return day ==
        candidateExp.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}

/**
 * 테스트 성공을 위한 구현 완급조절!
 * 1. 상수를 넣어서 테스트 성공하게
 * 2. 상수끼리 비교해서 테스트 성공하게
 * 3. 일반화
 *
 * 천천히 위순서를 따라가자. -> 점진적으로 구현을 진행할 수 있는 밑거름이된다.
 */