public class Worker extends Thread{
    private static final long CALLING_TIME = 4000;
    private static final long WAITING_TIME = 2000;
    private final ATS ats;
    private final String name;

    public Worker(ATS ats, String name) {
        this.ats = ats;
        this.name = name;
    }

    public void takeCall() {
        while (ats.isWorkingTime()) {
            try {
                Thread.sleep(WAITING_TIME);
                Call call = ats.getCall();
                if (call != null) {
                    System.out.println("Worker " + name + " takes call " + call.description);
                    Thread.sleep(CALLING_TIME);
                    System.out.println("Worker " + name + " off call " + call.description);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
