package second_ComputerNetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;


class ComputerNetworkTest {

    private ComputerNetwork computerNetwork;

    @BeforeEach
    void initializeComputerNetwork() {
        computerNetwork = new ComputerNetwork();
    }


    @Test
    void addingOnlyUniqueVertexShouldReturnSizeTwo() {

        //given
        String input = "1.1.1.1";
        String input2 = "1.1.1.1";
        String input3 = "2.2.2.2";

        //when
        computerNetwork.addVertex(input);
        computerNetwork.addVertex(input2);
        computerNetwork.addVertex(input3);

        //then
        assertThat(computerNetwork.getGraph().isEmpty(), is(false));
        assertThat(computerNetwork.getGraph().size(), is(2));
    }


    @Test
    void withoutAddingVertexGettingVertexMethodShouldReturnNull() {

        //given
        String input = "1.1.1.1";

        //when
        Map<String, Long> result = computerNetwork.getVertex(input);

        //then
        assertNull(result);
        //OR;
        assertThat(result, is(nullValue()));
    }


    @Test
    void addingVertexAndGettingVertexMethodShouldReturnNotNull() {

        //given
        String input = "1.1.1.1";

        //when
        computerNetwork.addVertex(input);
        Map<String, Long> result = computerNetwork.getVertex(input);

        //then
        assertNotNull(result);
        //OR:
        assertThat(result, is(notNullValue()));
    }

    @Test
    void gettingNestedMapFromGraphShouldReturnNullValue() {
        //given
        String input = "1.1.1.1";

        //when
        Map<String, Long> nestedMap =  computerNetwork.getGraph().get(input);

        //then
        assumingThat(null == nestedMap, () -> {
            assertThat(computerNetwork.getGraph().put(input, nestedMap), is(nullValue()));
        });
    }

    @Test
    void gettingNestedMapFromGraphShouldReturnNotNullValue() {
        //given
        String input = "1.1.1.1";
        String input2 = "2.2.2.2";
        long ping = 777;

        //when
        Map<String, Long> nestedMap = computerNetwork.getGraph().get(input);
        computerNetwork.addEdge(input, input2, ping);

        //then
        assumingThat(nestedMap != null, () -> {
            assertThat(computerNetwork.getGraph().put(input, nestedMap), is(nestedMap));
            assertThat(computerNetwork.getGraph().put(input, nestedMap), is(not(nullValue())));
            assertThat(nestedMap.size(), is(1));
            assertThat(nestedMap.get(input2), is(ping));
        });
    }


    @Test
    void addingEdgeAndGettingVertexMethodShouldReturnVertexIPAndAdjacentVerticesIPWithPingBetweenThem() {

        //given
        String input = "1.1.1.1";
        String input2 = "2.2.2.2";
        long ping = 777;

        //when
        computerNetwork.addEdge(input, input2, ping);
        Map<String, Long> result = computerNetwork.getVertex(input);

        //then
        assertThat(result.isEmpty(), is(false));
        assertThat(result.containsKey(input2), is(true));
        assertThat(result.containsKey(input), is(false));
        assertThat(result.containsValue(ping), is(true));
        assertThat(result.containsValue(555), is(false));
        assertThat(result.size(), is(1));
        assertThat(result.get(input2), is(ping));

        //OR:
        assertAll("Return vertex IP and adjacent vertices IP with ping between them",
                () -> assertThat(result.isEmpty(), is(false)),
                () -> assertThat(result.containsKey(input2), is(true)),
                () -> assertThat(result.containsKey(input), is(false)),
                () -> assertThat(result.containsValue(ping), is(true)),
                () -> assertThat(result.containsValue(555), is(false)),
                () -> assertThat(result.size(), is(1)),
                () -> assertThat(result.get(input2), is(ping))
        );
    }


    @Test
    void addingEdgeAndGettingEdgeMethodShouldReturnAllEdgesWithGivenPingAndAllConnectedVerticesWithThem() {

        //given
        String input = "1.1.1.1";
        String input2 = "2.2.2.2";
        String input3 = "3.3.3.3";
        String input4 = "4.4.4.4";
        long ping = 777;

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

        //OR:
        assertAll("Return all edges with given ping and all connected vertices with them",
                () -> assertThat(result.isEmpty(), is(false)),
                () -> assertThat(result.size(), is(2)),
                () -> assertThat(result.get(0).length, is(2)),
                () -> assertThat(result.get(1).length, is(2)),
                () -> assertThat(result.get(0), is(new String[]{input, input2})),
                () -> assertThat(result.get(1), is(new String[]{input3, input4})),
                () -> assertThat(result.get(0)[0], is(input)),
                () -> assertThat(result.get(0)[1], is(input2)),
                () -> assertThat(result.get(1)[0], is(input3)),
                () -> assertThat(result.get(1)[1], is(input4))
        );
    }


    @Test
    void addingEdgeAddingVertexAndGettingPathMethodShouldReturnShortestPathBetweenTwoGivenIPs() {

        //given
        String input = "1.1.1.1";
        String input2 = "2.2.2.2";
        String input3 = "3.3.3.3";
        String input4 = "4.4.4.4";
        String input5 = "5.5.5.5";

        long ping = 1;
        long ping2 = 10;
        long ping3 = 15;
        long ping4 = 2;
        long ping5 = 7;
        long ping6 = 4;


        //when
        computerNetwork.addVertex(input);
        computerNetwork.addVertex(input2);
        computerNetwork.addVertex(input3);
        computerNetwork.addVertex(input4);
        computerNetwork.addVertex(input5);

        computerNetwork.addEdge(input, input2, ping);
        computerNetwork.addEdge(input, input3, ping2);

        computerNetwork.addEdge(input2, input3, ping3);
        computerNetwork.addEdge(input2, input4, ping4);

        computerNetwork.addEdge(input4, input3, ping5);
        computerNetwork.addEdge(input4, input5, ping4);

        computerNetwork.addEdge(input5, input3, ping6);

        List<String> result = computerNetwork.getPath(input, input3);


        //then
        assertThat(result.isEmpty(), is(false));
        assertThat(result.size(), is(5));

        assertThat(result.get(0), is(input));
        assertThat(result.get(1), is(input2));
        assertThat(result.get(2), is(input4));
        assertThat(result.get(3), is(input5));
        assertThat(result.get(4), is(input3));

        assertThat(result, is(asList(input, input2, input4, input5, input3)));
        assertThat(result, is(asList("1.1.1.1", "2.2.2.2", "4.4.4.4", "5.5.5.5", "3.3.3.3")));

        //OR:
        assertAll("Return shortest path between two given IPs",
                () -> assertThat(result.isEmpty(), is(false)),
                () -> assertThat(result.size(), is(5)),

                () -> assertThat(result.get(0), is(input)),
                () -> assertThat(result.get(1), is(input2)),
                () -> assertThat(result.get(2), is(input4)),
                () -> assertThat(result.get(3), is(input5)),
                () -> assertThat(result.get(4), is(input3)),

                () -> assertThat(result, is(asList(input, input2, input4, input5, input3))),
                () -> assertThat(result, is(asList("1.1.1.1", "2.2.2.2", "4.4.4.4", "5.5.5.5", "3.3.3.3")))
        );
    }


    //Additionaly:
    @Test
    void addingEdgeAddingVertexAndGettingPathMethodShouldReturnShortestPathsBetweenTwoGivenIPs() {

        //given
        String input = "1.1.1.1";
        String input2 = "2.2.2.2";
        String input3 = "3.3.3.3";
        String input4 = "4.4.4.4";
        String input5 = "5.5.5.5";

        long ping = 1;
        long ping2 = 10;
        long ping3 = 8;
        long ping4 = 2;
        long ping5 = 7;
        long ping6 = 4;


        //when
        computerNetwork.addVertex(input);
        computerNetwork.addVertex(input2);
        computerNetwork.addVertex(input3);
        computerNetwork.addVertex(input4);
        computerNetwork.addVertex(input5);

        computerNetwork.addEdge(input, input2, ping);
        computerNetwork.addEdge(input, input3, ping2);

        computerNetwork.addEdge(input2, input3, ping3);
        computerNetwork.addEdge(input2, input4, ping4);

        computerNetwork.addEdge(input4, input3, ping5);
        computerNetwork.addEdge(input4, input5, ping4);

        computerNetwork.addEdge(input5, input3, ping6);


        //then
        assertThat(computerNetwork.getPaths(input, input3), is(nullValue()));
        //Answer:
        //1.1.1.1 2.2.2.2 3.3.3.3
        //1.1.1.1 2.2.2.2 4.4.4.4 5.5.5.5 3.3.3.3


    }

}