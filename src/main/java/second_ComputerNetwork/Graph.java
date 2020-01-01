package second_ComputerNetwork;

import java.util.List;
import java.util.Map;

interface Graph {

    void addVertex(String ip);


    void addEdge(String firstServer, String secondServer, long pig);


    Map<String, Long> getVertex(String ip);


    List<String[]> getEdge(long ping);

    
    List<String> getPath(String firstServer, String secondServer);
}
