package first_MethodInvocationTracer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ImplementedAlgorithmTest {


    private ImplementedAlgorithm implementAlgorithm;

    @BeforeEach
    void initializeComputerNetwork() {
        implementAlgorithm = new ImplementedAlgorithm();
    }


    @Test
    void withoutSavingInputsInMemoryAndCountMethodShouldReturnZero() {

        //given
        String input = "cat";

        //when
        int result = implementAlgorithm.count(input);

        //then
        assertThat(result, is(0));
    }


    @Test
    void savingInputsInMemoryAndCountMethodShouldReturnThreeCat() {

        //given
        String input = "cat";

        //when
        implementAlgorithm.add(input, input, input);
        int result = implementAlgorithm.count(input);

        //then
        assertThat(result, is(3));
    }


    @Test
    void savingInputsInMemoryAndCountMethodShouldReturnZeroDog() {

        //given
        String input = "cat";
        String input2 = "dog";

        //when
        implementAlgorithm.add(input, input, input);
        int result = implementAlgorithm.count(input2);

        //then
        assertThat(result, is(0));
    }


    @Test
    void savingInputsInMemoryAndCountMethodShouldReturnThreeCatOrOneDog() {

        //given
        String input = "cat";
        String input2 = "dog";

        //when
        implementAlgorithm.add(input, input, input, input2);
        int result = implementAlgorithm.count(input);
        int result2 = implementAlgorithm.count(input2);

        //then
        assertThat(result, is(3));
        assertThat(result2, is(1));
    }


    @ParameterizedTest
    @MethodSource("inputs")
    void savingInputsInMemoryAndCountMethodShouldReturnOneCatOrOneDog(String input) {
        implementAlgorithm.add(input);
        System.out.println(input); //Additionally
        int result = implementAlgorithm.count(input);
        assertThat(result, is(1));
    }
    private static Stream<String> inputs() {
        List<String> list = Arrays.asList("cat", "dog");
        return list.stream();
    }

    //OR:
    @ParameterizedTest
    @CsvSource({"cat", "dog"})
    void savingInputsInMemoryAndCountMethodShouldReturnOneCatOrOneDogg(String input) {
        implementAlgorithm.add(input);
        for (String i : input.split(",")) {
            System.out.println(i); //Additionally
            int result = implementAlgorithm.count(i);
            assertThat(result, is(1));
        }
    }


    @ParameterizedTest
    @CsvSource({"cat, cat, dog, cat"})
    void savingInputsInMemoryAndCountMethodShouldReturnThreeCatAndOneDog(String first, String second, String third, String fourth) {
        implementAlgorithm.add(first, second, third, fourth);

        int result1 = implementAlgorithm.count(first);
        int result2 = implementAlgorithm.count(second);
        int result4 = implementAlgorithm.count(fourth);
        assertThat(result1, is(3));
        assertThat(result2, is(3));
        assertThat(result4, is(3));

        int result3 = implementAlgorithm.count(third);
        assertThat(result3, is(1));
    }


    @TestFactory
    Collection<DynamicTest> SavingInputsInMemoryCountMethodShouldReturnTreeTimesThreeCat() {
        String input = "cat";
        String input2 = "dog";

        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            int finalI = i;
            Executable executable = () -> {
                implementAlgorithm = new ImplementedAlgorithm();
                if (finalI == 0)
                    implementAlgorithm.add(input, input, input);
                else if (finalI == 1)
                    implementAlgorithm.add(input, input, input, input2);
                else if (finalI == 2)
                    implementAlgorithm.add(input, input, input2, input, input2);

                int result = implementAlgorithm.count(input);
                assertThat(result, is(3));
            };

            String name = "Test name " + (i + 1);

            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }
}