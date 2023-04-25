import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadStart implements Runnable{

    CountDownLatch countDownLatch;
    List<Integer> list;
    public ThreadStart(CountDownLatch countDownLatch, List<Integer> list){
        this.countDownLatch = countDownLatch;
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + 10);
        }
        countDownLatch.countDown();
    }
}
