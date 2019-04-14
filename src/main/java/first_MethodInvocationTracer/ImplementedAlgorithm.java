package first_MethodInvocationTracer;

import java.util.HashMap;
import java.util.Map;

class ImplementedAlgorithm implements Algorithm {
    private Map<String, Integer> names = new HashMap<>();

    @Override
    public void add(String input) {
        names.merge(input, 1, Integer::sum); //OR: names.merge(input, 1, (a,b) -> a+b);
//        //OR:
//        Integer count = names.get(input);
//        if (count == null) {
//            names.put(input, 1);
//        } else {
//            names.put(input, count + 1);
//        }
    }


    @Override
    public int count(String input) {
        Integer result = names.get(input);
        return result == null ? 0 : result;
    }
}
