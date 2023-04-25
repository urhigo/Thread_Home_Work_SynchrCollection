import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i + 1);
        }

        List<Integer> synchList = Collections.synchronizedList(arrayList);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 3; i++) {
            executorService.execute(new ThreadStart(countDownLatch, synchList));
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(synchList);

    }
}
