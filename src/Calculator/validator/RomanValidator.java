package Calculator.validator;

import Calculator.service.ListBuffer;

public class RomanValidator {
    public boolean isValid(ListBuffer lb, Character roman, int nextIndex) {
        int indexOfCurrentRoman = nextIndex - 1;
        int indexOfPreviousRoman = nextIndex - 2;
        if (indexOfPreviousRoman >= 0) {
            Character previousChar = lb.getListForCharsFromExample().get(indexOfPreviousRoman);
            if (Character.isDigit(previousChar)
                    || previousChar.equals(')')) {
                return false;
            }
        }
        if (nextIndex < lb.getListForCharsFromExample().size()) {
            Character nextChar = lb.getListForCharsFromExample().get(nextIndex);
            if (!(nextChar.equals('I')
                    || nextChar.equals('V')
                    || nextChar.equals('X')
                    || nextChar.equals('L')
                    || nextChar.equals('C')
                    || nextChar.equals('D')
                    || nextChar.equals('M'))) {
                if (Character.isDigit(lb.getListForCharsFromExample().get(nextIndex))
                        || nextChar.equals('(')) {
                    return false;
                }
                lb.getListForRomanChars().removeAll(lb.getListForRomanChars());
            }
        } else if (nextIndex == lb.getListForCharsFromExample().size()) {
            lb.getListForRomanChars().removeAll(lb.getListForRomanChars());
        }
        char[] romans = {'I', 'X', 'C', 'M', ' ', ' ', 'D', 'L', 'V'};

        switch (roman) {
            case 'I':
                lb.getListForRomanChars().add(roman);
                int indexOfRomanDecimal = 0;
                if (!isIXCMValid(indexOfCurrentRoman, indexOfRomanDecimal, lb, romans)) {
                    return false;
                }
                break;
            case 'X':
                lb.getListForRomanChars().add(roman);
                if (lb.getListForRomanChars().contains('V')) {
                    return false;
                }
                indexOfRomanDecimal = 1;
                if (!isIXCMValid(indexOfCurrentRoman, indexOfRomanDecimal, lb, romans)) {
                    return false;
                }
                break;
            case 'C':
                lb.getListForRomanChars().add(roman);
                if (lb.getListForRomanChars().contains('I')
                        || lb.getListForRomanChars().contains('V')) {
                    return false;
                }
                indexOfRomanDecimal = 2;
                if (!isIXCMValid(indexOfCurrentRoman, indexOfRomanDecimal, lb, romans)) {
                    return false;
                }
                break;
            case 'M':
                lb.getListForRomanChars().add(roman);
                if (lb.getListForRomanChars().contains('I')
                        || lb.getListForRomanChars().contains('V')
                        || lb.getListForRomanChars().contains('X')
                        || lb.getListForRomanChars().contains('L')
                        || lb.getListForRomanChars().contains('D')) {
                    return false;
                }
                indexOfRomanDecimal = 3;
                if (!isIXCMValid(indexOfCurrentRoman, indexOfRomanDecimal, lb, romans)) {
                    return false;
                }
                break;
            case 'V':
                if (!lb.getListForRomanChars().contains('V')) {
                    lb.getListForRomanChars().add(roman);
                } else {
                    return false;
                }
                break;
            case 'L':
                if (!lb.getListForRomanChars().contains('L')
                        && !lb.getListForRomanChars().contains('V')) {
                    lb.getListForRomanChars().add(roman);
                } else {
                    return false;
                }
                break;
            case 'D':
                if (!lb.getListForRomanChars().contains('D')
                        && !lb.getListForRomanChars().contains('L')
                        && !lb.getListForRomanChars().contains('X')
                        && !lb.getListForRomanChars().contains('I')) {
                    lb.getListForRomanChars().add(roman);
                } else {
                    return false;
                }
                break;

        }
        return true;
    }

    public boolean isIXCMValid(int indexOfRoman, int romanDecimal, ListBuffer lb, char[] romans) {
        int listSize = lb.getListForCharsFromExample().size();
        int nextRomanDecimal = romanDecimal + 1;
        int previousRomanDecimal = romanDecimal - 1;
        int romanHalfDecimal = romans.length - 1 - romanDecimal;
        int secondIndex = indexOfRoman + 1;
        int thirdIndex = indexOfRoman + 2;
        int fourthIndex = indexOfRoman + 3;

        if (listSize == 0) {
            return true;
        }
        if (secondIndex < listSize) {
            Character secondChar = lb.getListForCharsFromExample().get(secondIndex);
            if (secondChar.equals('+')
                    || secondChar.equals('-')
                    || secondChar.equals('*')
                    || secondChar.equals('/')) {
                return true;
            }
            if (secondChar.equals(romans[nextRomanDecimal])
                    || secondChar.equals(romans[romanHalfDecimal])) {
                if (thirdIndex <= listSize) {
                    Character thirdChar = lb.getListForCharsFromExample().get(thirdIndex);
                    if (thirdChar.equals(romans[romanDecimal])
                            || thirdChar.equals(romans[romanHalfDecimal])) {
                        return false;
                    }
                }
            }
        } else return true;
        if (thirdIndex < listSize) {
            Character thirdChar = lb.getListForCharsFromExample().get(thirdIndex);
            if (thirdChar.equals('+')
                    || thirdChar.equals('-')
                    || thirdChar.equals('*')
                    || thirdChar.equals('/')) {
                return true;
            }
            if (thirdChar.equals(romans[romanHalfDecimal])
                    || thirdChar.equals(romans[nextRomanDecimal])) {
                return false;
            }
        } else {
            return true;
        }
        if (fourthIndex < listSize) {
            Character fourthChar = lb.getListForCharsFromExample().get(fourthIndex);
            if (fourthChar.equals('+')
                    || fourthChar.equals('-')
                    || fourthChar.equals('*')
                    || fourthChar.equals('/')) {
                return true;
            }
            if (fourthChar.equals(romans[romanDecimal])) {
                if (romanDecimal > 0) {
                    Character thirdChar = lb.getListForCharsFromExample().get(thirdIndex);
                    return thirdChar.equals(romans[previousRomanDecimal]);
                } else {
                    return false;
                }
            } else {
                return !fourthChar.equals(romans[nextRomanDecimal]);
            }
        }
        return true;
    }
}
