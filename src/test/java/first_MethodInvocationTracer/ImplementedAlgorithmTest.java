package first_MethodInvocationTracer;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ImplementedAlgorithmTest {

    @Test
    public void shouldReturnZero_First() {

        //given
        ImplementedAlgorithm implementAlgorithm = new ImplementedAlgorithm();
        String input = "cat";

        //when
        int result = implementAlgorithm.count(input);

        //then
        assertThat(result, is(0));
    }


    @Test
    public void shouldReturnThree() {

        //given
        ImplementedAlgorithm implementAlgorithm = new ImplementedAlgorithm();
        String input = "cat";

        //when
        implementAlgorithm.add(input);
        implementAlgorithm.add(input);
        implementAlgorithm.add(input);
        int result = implementAlgorithm.count(input);

        //then
        assertThat(result, is(3));
    }


    @Test
    public void shouldReturnZero_Second() {

        //given
        ImplementedAlgorithm implementAlgorithm = new ImplementedAlgorithm();
        String input = "cat";
        String input2 = "dog";

        //when
        implementAlgorithm.add(input);
        implementAlgorithm.add(input);
        implementAlgorithm.add(input);
        int result = implementAlgorithm.count(input2);

        //then
        assertThat(result, is(0));
    }


    @Test
    public void shouldReturnOne() {

        //given
        ImplementedAlgorithm implementAlgorithm = new ImplementedAlgorithm();
        String input = "cat";
        String input2 = "dog";

        //when
        implementAlgorithm.add(input);
        implementAlgorithm.add(input);
        implementAlgorithm.add(input);
        implementAlgorithm.add(input2);
        int result = implementAlgorithm.count(input2);

        //then
        assertThat(result, is(1));
    }
}