import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int WAIT_TIME = 3000;
    private static final long WORKING_DAY_TIME = 10_000;


    public static void main(String[] args) {

        System.out.println("---------------- Рабочий день начался. Станция открылась");
        ATS ats = new ATS();

        List<String> workers = new Stack<>();
        workers.add("Kate");
        workers.add("Roma");
        workers.add("Gleb");
        workers.add("Vlad");

        System.out.println("---------------- Линия звонков стала доступна. Можно звонить");
        ExecutorService atsExecutor = Executors.newFixedThreadPool(1);
        atsExecutor.execute(() -> {
            for (int i = 0; i < workers.size() * 3; i++) {
                try {
                    Thread.currentThread().setName("caller " + i);
                    ats.putCall(new Call());
                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        initWorkersThreadsFromList(ats, workers);

        try {
            Thread.sleep(WORKING_DAY_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("---------------- Рабочий день закончился. Станция закрылась");
        atsExecutor.shutdown();
        ats.close();
    }

    private static void initWorkersThreadsFromList(ATS ats, List<String> workers) {
        for (String worker : workers) {
            try {
                System.out.println("---------------- Сотрудник " + worker + " Прибыл на рабочее место");

                Thread.sleep(WAIT_TIME);

                Thread thread = new Thread(() -> new Worker(ats, worker).takeCall(), worker);
                thread.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
