package second_ComputerNetwork;

import org.junit.Test;
import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ComputerNetworkTest {

    @Test
    public void shouldReturnNull() {

        //given
        ComputerNetwork computerNetwork = new ComputerNetwork();
        String input = "1.1.1.1";

        //when
        Map<String, Long> result = computerNetwork.getVertex(input);

        //then
        assertNull(result);
    }


    @Test
    public void shouldReturnNotNull() {

        //given
        ComputerNetwork computerNetwork = new ComputerNetwork();
        String input = "1.1.1.1";

        //when
        computerNetwork.addVertex(input);
        Map<String, Long> result = computerNetwork.getVertex(input);

        //then
        assertNotNull(result);
    }


    @Test
    public void shouldReturnGetedVertex() {

        //given
        ComputerNetwork computerNetwork = new ComputerNetwork();
        String input = "1.1.1.1";
        String input2 = "2.2.2.2";
        long ping = 777L;

        //when
        computerNetwork.addEdge(input, input2, ping);
        Map<String, Long> result = computerNetwork.getVertex(input);

        //then
        assertThat(result.isEmpty(), is(false));
        assertThat(result.containsKey(input2), is(true));
        assertThat(result.containsKey(input), is(false));
        assertThat(result.containsValue(ping), is(true));
        assertThat(result.containsValue(555L), is(false));
        assertThat(result.size(), is(1));
        assertThat(result.get(input2), is(ping));
    }


    @Test
    public void shouldReturnGetedEdge() {

        //given
        ComputerNetwork computerNetwork = new ComputerNetwork();
        String input = "1.1.1.1";
        String input2 = "2.2.2.2";
        String input3 = "3.3.3.3";
        String input4 = "4.4.4.4";
        long ping = 777L;

        //when
        computerNetwork.addEdge(input, input2, ping);
        computerNetwork.addEdge(input3, input4, ping);
        List<String[]> result = computerNetwork.getEdge(ping);

        //then
        assertThat(result.isEmpty(), is(false));
        assertThat(result.size(), is(2));
        assertThat(result.get(0).length, is(2));
        assertThat(result.get(1).length, is(2));
        assertThat(result.get(0), is(new String[]{input, input2}));
        assertThat(result.get(1), is(new String[]{input3, input4}));
        assertThat(result.get(0)[0], is(input));
        assertThat(result.get(0)[1], is(input2));
        assertThat(result.get(1)[0], is(input3));
        assertThat(result.get(1)[1], is(input4));
    }


    @Test
    public void shouldReturnPath() {

        //given
        ComputerNetwork computerNetwork = new ComputerNetwork();
        computerNetwork.addEdge("1.1.1.1", "2.2.2.2", 10L);
        computerNetwork.addEdge("2.2.2.2", "3.3.3.3", 20L);
        computerNetwork.addEdge("3.3.3.3", "4.4.4.4", 40L);

        //when
        Path path = computerNetwork.getPath("1.1.1.1", "4.4.4.4");

        //then
        assertThat(path.getPing(), is(70L));
        assertThat(path.getServers(), is(asList("1.1.1.1", "2.2.2.2", "3.3.3.3", "4.4.4.4")));
    }
}