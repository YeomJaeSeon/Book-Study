package main.java.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password){
        if(password == null || password.isEmpty()) return PasswordStrength.INVALID;
        int metCounts = getMetCriteriaCounts(password);

        if(metCounts <= 1) return PasswordStrength.WEAK;
        if(metCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String password) {
        int metCounts = 0;
        if(password.length() >= 8) metCounts++;
        if(meetsContainingNumberCriteria(password)) metCounts++;
        if(meetsContainingUpperCriteria(password)) metCounts++;
        return metCounts;
    }

    private boolean meetsContainingUpperCriteria(String password) {
        for(char ch: password.toCharArray()){
            if(ch >= 'A' && ch <= 'Z'){
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingNumberCriteria(String password) {
        for(char ch : password.toCharArray()){
            if(ch >= '0' && ch <= '9'){
                return true;
            }
        }
        return false;
    }
}
