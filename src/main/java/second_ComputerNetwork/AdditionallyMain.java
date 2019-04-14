package second_ComputerNetwork;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AdditionallyMain {

    public static void main(String[] args) {
        ComputerNetwork computerNetwork = new ComputerNetwork();

        //for instance 1:
        String input = "1.1.1.1";
        Map<String, Long> result = computerNetwork.getVertex(input);
        System.out.println(result);

        //for instance 2:
        computerNetwork.addVertex(input);
        Map<String, Long> result2 = computerNetwork.getVertex(input);
        System.out.println(result2);

        //for instance 3:
        String input2 = "2.2.2.2";
        long ping = 777L;
        computerNetwork.addEdge(input, input2, ping);
        Map<String, Long> result3 = computerNetwork.getVertex(input);
        System.out.println(result3.containsKey(input2));
        System.out.println(result3.containsKey(input));
        System.out.println(result3.containsValue(ping));
        System.out.println(result3.containsValue(555L));
        System.out.println(result3.size());
        System.out.println(result3.get(input2));

        //for instance 4:
        String input3 = "3.3.3.3";
        String input4 = "4.4.4.4";
        computerNetwork.addEdge(input, input2, ping);
        computerNetwork.addEdge(input3, input4, ping);
        List<String[]> result4 = computerNetwork.getEdge(ping);
        System.out.println(result4.isEmpty());
        System.out.println(result4.size());
        System.out.println(result4.get(0).length);
        System.out.println(result4.get(1).length);
        System.out.println(Arrays.toString(result4.get(0)));
        System.out.println(Arrays.toString(result4.get(1)));
        for (String[] ips : result4) {
            for (String ip : ips) {
                System.out.println(ip);
            }
        }

        //for instance 5:
        computerNetwork.addEdge("1.1.1.1", "2.2.2.2", 10L);
        computerNetwork.addEdge("2.2.2.2", "3.3.3.3", 20L);
        computerNetwork.addEdge("3.3.3.3", "4.4.4.4", 40L);

        Path path = computerNetwork.getPath("1.1.1.1", "4.4.4.4");
        for (String server : path.getServers()) {
            System.out.println(server);
        }
    }
}
