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
    public void shouldPassWithAlphabetic(){
        shouldPass("[\"John\",\"Anna\",\"Peter\"]");
    }

    @Test
    public void shouldPassWithAlphabeticAndWithDigit(){
        shouldPass("[\"John\",50,\"Anna\",\"Peter\",60]");
    }

    @Test
    public void shouldPassWithAlphabeticAndWithWhitespace(){
        shouldPass("[\"John\", \"Anna\", \"Peter\"]" );
    }

    @Test
    public void shouldPassWithAlphabeticAndWithDigitAndWithWhitespace(){
        shouldPass("[\"John\", 50, \"Anna\", \"Peter\", 60]");
    }

    @Test
    public void shouldFailWithWhitespace(){
        shouldFail("[\"Jo hn\",\"Anna\",\"Peter\"]");
    }

    @Test
    public void shouldFailWithThreeBrackets(){
        shouldFail("[\"John\",\"Anna\",\"Peter\"]]");
    }
}
