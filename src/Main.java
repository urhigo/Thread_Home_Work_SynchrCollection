import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args){
        ArrayList <Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            arrayList.add(i + 1);
        }

        List<Integer> synchList = Collections.synchronizedList(arrayList);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable runnable = () ->{
            for (int i = 0; i < synchList.size(); i++) {
                synchList.set(i, synchList.get(i) + 10);
                System.out.println(synchList);
            }

        };

        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);


        executorService.shutdown();

        System.out.println(synchList);

    }
}
