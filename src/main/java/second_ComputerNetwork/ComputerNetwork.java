package second_ComputerNetwork;

import java.util.*;

class ComputerNetwork implements Graph {
    private Map<String, Map<String, Long>> graph = new HashMap<>();

    Map<String, Map<String, Long>> getGraph() {
        return graph;
    }

    @Override
    public void addVertex(String ip) {
        if (!graph.containsKey(ip)) {
            graph.put(ip, new HashMap<>());
        }
    }


    @Override
    public void addEdge(String firstServer, String secondServer, long ping) {
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
                    String[] edge = {ip1, ip2};
                    result.add(edge);
                }
            }
        }
        return result;
    }


    @Override //Dijkstra’s shortest path algorithm
    public List<String> getPath(String firstServer, String targetServer) {
        Map<String, String> previous = new TreeMap<>();
        Map<String, Long> minPings = new TreeMap<>();
        minPings.put(firstServer, 0L);

        Comparator<String> comparator = getStringComparator(minPings);

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(comparator);
        priorityQueue.add(firstServer);
        while (!priorityQueue.isEmpty()) {
            String firstVertex = priorityQueue.poll();
            Map<String, Long> edges = graph.get(firstVertex);
            for (Map.Entry<String, Long> edge : edges.entrySet()) {
                String secondVertex = edge.getKey();
                long ping = edge.getValue();
                long minPing = minPings.getOrDefault(firstVertex, Long.MAX_VALUE) + ping;
                if (minPing < minPings.getOrDefault(secondVertex, Long.MAX_VALUE)) {
                    previous.put(secondVertex, firstVertex);
                    minPings.put(secondVertex, minPing);
                    priorityQueue.add(secondVertex);
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String vertex = targetServer; vertex != null; vertex = previous.get(vertex)) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    private Comparator<String> getStringComparator(Map<String, Long> minPings) {
        return (a, b) -> {
            if (minPings.getOrDefault(a, Long.MAX_VALUE) < minPings.getOrDefault(b, Long.MAX_VALUE)) {
                return -1;
            } else if (minPings.getOrDefault(a, Long.MAX_VALUE) > minPings.getOrDefault(b, Long.MAX_VALUE)){
                return 1;
            }else{
                return 0;
            }
        };
    }


    //Additionally - Dijkstra’s shortest path algorithm (in case when two or more paths are the shortest)
    List<String> getPaths(String firstServer, String targetServer) {
        TreeMap<String, TreeSet<String>> previous = new TreeMap<>();

        TreeMap<String, Long> minPings = new TreeMap<>();
        minPings.put(firstServer, 0l);

        Comparator<String> comparator = getStringComparator(minPings);

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(comparator);
        priorityQueue.add(firstServer);
        while (!priorityQueue.isEmpty()) {
            String firstVertex = priorityQueue.poll();
            Map<String, Long> edges = graph.get(firstVertex);
            for (Map.Entry<String, Long> edge : edges.entrySet()) {
                String secondVertex = edge.getKey();
                long ping = edge.getValue();
                long minPing = minPings.getOrDefault(firstVertex, Long.MAX_VALUE) + ping;
                if (minPing < minPings.getOrDefault(secondVertex, Long.MAX_VALUE)) {
                    TreeSet<String> previouses = new TreeSet<>();
                    previouses.add(firstVertex);
                    previous.put(secondVertex, previouses);
                    minPings.put(secondVertex, minPing);
                    priorityQueue.add(secondVertex);
                } else if (minPing == minPings.getOrDefault(secondVertex, Long.MAX_VALUE)) {
                    TreeSet<String> previouses = previous.get(secondVertex);
                    previouses.add(firstVertex);
                    minPings.put(secondVertex, minPing);
                    priorityQueue.add(secondVertex);
                }
            }
        }

        print(targetServer, previous, true);
        return null;
    }

    private Comparator<String> getStringComparator(TreeMap<String, Long> minPings) {
        return (a, b) -> {
            if (minPings.getOrDefault(a, Long.MAX_VALUE) < minPings.getOrDefault(b, Long.MAX_VALUE)) {
                return -1;
            } else if (minPings.getOrDefault(a, Long.MAX_VALUE) > minPings.getOrDefault(b, Long.MAX_VALUE)) {
                return 1;
            } else {
                return 0;
            }
        };
    }

    void print(String vertex, TreeMap<String, TreeSet<String>> previous, boolean last) {
        TreeSet<String> previouses = previous.get(vertex);
        if (previouses != null) {
            for (String p : previouses) {
                print(p, previous, false);
                System.out.print(vertex + " ");
                if (last) {
                    System.out.println("");
                }
            }
        } else {
            System.out.print(vertex + " ");
            if (last) {
                System.out.println("");
            }
        }
    }
}
