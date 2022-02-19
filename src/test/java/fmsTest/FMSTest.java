package fmsTest;

import com.artem.validator.StateMachine;
import com.artem.validator.ValidationResult;
import org.junit.Assert;
import org.junit.Test;

public class FMSTest {

    public void shouldPass(String str){
        StateMachine stateMachine = new StateMachine();

        ValidationResult result = stateMachine.validate(str);

        Assert.assertTrue(result.getComment(), result.isValid());
    }

    public void shouldFail(String str){
        StateMachine stateMachine = new StateMachine();

        ValidationResult result = stateMachine.validate(str);

        Assert.assertFalse(result.getComment(), result.isValid());
    }

    @Test
    public void shouldPassWithAlphabeticAndWithWhitespace(){
        shouldPass("[\"John\", \"Anna\", \"Peter\"]" );
    }

    @Test
    public void shouldPassWithAlphabeticAndWithDigitAndWithWhitespace(){shouldPass("[\"John\", 50, \"Anna\", \"Peter\", 60]");}

    @Test
    public void shouldPassWithAlphabeticAndWithDigitAndWithBooleanVariable(){shouldPass("[false, \"John\", 50, true, \"Anna\", null, \"Peter\", 60]");}

    @Test
    public void shouldPassWithWhitespace(){
        shouldFail("[\"Jo hn\",\"Anna\",\"Peter\"]");
    }

    @Test
    public void shouldFailWithThreeBrackets(){
        shouldFail("[\"John\",\"Anna\",\"Peter\"]]");
    }

    @Test
    public void shouldFailWithWrongLetterInFalse(){
        shouldFail("[ftlse, 50]");
    }

    @Test
    public void shouldFailWithWrongLetterInTrue(){
        shouldFail("[trug]");
    }

    @Test
    public void shouldFailWithWrongLetterInNull(){
        shouldFail("[nyll]");
    }

    @Test
    public void shouldFailWithExtraComma(){
        shouldFail("[\"John\", \"Anna\", \"Peter\", ]");
    }
}
