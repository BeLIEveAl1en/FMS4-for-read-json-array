package com.artem.validator;

public class StateMachine {

    private char[] str = new char[0];
    private int position = 0;
    private int counterOfQuotes = 0;
    private int counterOfBrackets = 0;
    private int buf = 0;
    private final State state = new State(0);

    public ValidationResult validate(String inputStr){
        str = inputStr.toCharArray();
        ValidationResult result;
        for (position = 0; position < str.length; position++){
            if (!validationSymbol())
                return ValidationResult.unexpectedSymbol(str[position], position);
        }
        if (state.getState() == 6 && counterOfQuotes == 0 && counterOfBrackets == 0){ //
            result = ValidationResult.valid();
        }
        else {
            result = ValidationResult.unexpectedEOF();
        }
        return result;
    }

    //  21/700+485
    public boolean validationSymbol() {
        char symbol = str[position];
        switch (state.getState()){
            case 0 :
                if (symbol == '['){
                    counterOfBrackets++;
                    state.setState(6);
                }
                else {
                    return false;
                }
                return true;

            case 1 :
                if (Character.isDigit(symbol)){
                    state.setState(1);
                }
                else if (symbol == ','){
                    state.setState(6);
                }
                else if (symbol == ']'){
                    counterOfBrackets--;
                    state.setState(6);
                }
                else {
                    return false;
                }
                return true;

            case 2 :
                if (Character.isDigit(symbol) || Character.isAlphabetic(symbol)){
                    state.setState(2);
                }
                else if (symbol == '"'){
                    counterOfQuotes--;
                    state.setState(6);
                }
                else if (Character.isWhitespace(symbol)){
                    return false;
                }
                return true;

            case 3 :
                if (symbol == 'r' && buf == 0){
                    buf++;
                }
                else if (symbol == 'u'  && buf == 1){
                    buf++;
                }
                else if (symbol == 'e'  && buf == 2){
                    buf = 0;
                    state.setState(6);
                }
                else {
                    return false;
                }
                return true;

            case 4 :
                if (symbol == 'a'  && buf == 0){
                    buf++;
                }
                else if (symbol == 'l'  && buf == 1){
                    buf++;
                }
                else if (symbol == 's'  && buf == 2){
                    buf++;
                }
                else if (symbol == 'e'  && buf == 3){
                    buf = 0;
                    state.setState(6);
                }
                else {
                    return false;
                }
                return true;

            case 5 :
                if (symbol == 'u'  && buf == 0){
                    buf++;
                }
                else if (symbol == 'l'  && buf == 1){
                    buf++;
                }
                else if (symbol == 'l'  && buf == 2){
                    buf = 0;
                    state.setState(6);
                }
                else {
                    return false;
                }
                return true;

            case 6 :
                if (Character.isDigit(symbol)){
                    state.setState(1);
                    state.setBufState();
                }
                else if (symbol == '"'){
                    counterOfQuotes++;
                    state.setState(2);
                    state.setBufState();
                }
                else if(Character.isWhitespace(symbol)){
                    state.setState(6);
                    state.setBufState();
                }
                else if (symbol == 't'){
                    state.setState(3);
                    state.setBufState();
                }
                else if (symbol == 'f'){
                    state.setState(4);
                    state.setBufState();
                }
                else if (symbol == 'n'){
                    state.setState(5);
                    state.setBufState();
                }
                else if (symbol == ','){
                    state.setState(6);
                    state.setBufState();
                }
                else if (symbol == ']' && state.getBufState() != 6){
                    counterOfBrackets--;
                    return true;
                }
                else {
                    return false;
                }
                return true;
        }
        return false;
    }
}
