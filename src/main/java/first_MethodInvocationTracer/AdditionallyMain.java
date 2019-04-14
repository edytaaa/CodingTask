package first_MethodInvocationTracer;

public class AdditionallyMain {
    public static void main(String[] args) {

        ImplementedAlgorithm implementAlgorithm = new ImplementedAlgorithm();

        //for instance 1:
        int catCount = implementAlgorithm.count("cat");
        System.out.printf("catCount will be equals to %d \n", catCount);

        //for instance 2:
        implementAlgorithm.add("cat");
        implementAlgorithm.add("cat");
        implementAlgorithm.add("cat");
        int catCount2 = implementAlgorithm.count("cat");
        System.out.printf("catCount will be equals to %d \n", catCount2);

        //for instance 3:
        int dogCount = implementAlgorithm.count("dog");
        System.out.printf("dogCount will be equals to %d \n", dogCount);

        //for instance 4:
        implementAlgorithm.add("dog");
        int dogCount2 = implementAlgorithm.count("dog");
        System.out.printf("dogCount will be equals to %d \n", dogCount2);
    }
}

