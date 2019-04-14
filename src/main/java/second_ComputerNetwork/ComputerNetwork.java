package second_ComputerNetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ComputerNetwork implements Graph {
    private Map<String, Map<String, Long>> graph = new HashMap<>();


    @Override
    public void addVertex(String ip) {
        if (!graph.containsKey(ip)) {
            graph.put(ip, new HashMap<>());
        }
    }


    @Override
    public void addEdge(String firstServer, String secondServer, Long ping) {
        Map<String, Long> nestedMap = graph.computeIfAbsent(firstServer, k -> new HashMap<>());
        nestedMap.put(secondServer, ping);
        //OR:
//        Map<String, Long> nestedMap = graph.get(firstServer);
//        if(nestedMap == null){
//            graph.put(firstServer, nestedMap = new HashMap<>());
//        }
//        nestedMap.put(secondServer, ping);
    }


    @Override
    public Map<String, Long> getVertex(String ip) {
        return graph.get(ip);
    }


    @Override
    public List<String[]> getEdge(long ping) {
        List<String[]> result = new ArrayList<>();
        for (String ip1 : graph.keySet()) {
            Map<String, Long> nestedMap = graph.get(ip1);
            for (String ip2 : nestedMap.keySet()) {
                if (ping == nestedMap.get(ip2)) {
                    String[] adge = {ip1, ip2};
                    result.add(adge);
                }
            }
        }
        return result;
    }


    @Override //Only beginning method
    public Path getPath(String firstServer, String secondServer) {
        Path path = new Path();
        path.addServer(firstServer, 0);
        return getPath(path, firstServer, secondServer);
    }

    private Path getPath(Path path, String currentServer, String lastServer) {
        Map<String, Long> edges = graph.get(currentServer);
        if (edges != null) {
            for (Map.Entry<String, Long> nextServer : edges.entrySet()) {
                path.addServer(nextServer.getKey(), nextServer.getValue());
                if (nextServer.getKey().equals(lastServer)) {
                    return path;
                }else{
                    return getPath(path, nextServer.getKey(), lastServer);
                }
            }
        }
        return null;
    }
}
