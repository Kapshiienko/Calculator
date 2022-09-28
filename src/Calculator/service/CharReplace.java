package Calculator.service;

public class CharReplace {
    public String doReplaceSpaces(String inputString) {
        if (inputString.contains(" ")) {
            return inputString.replaceAll("\\s", "");
        } else {
            return inputString;
        }
    }
}