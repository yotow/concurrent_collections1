import java.util.concurrent.ConcurrentLinkedQueue;

public class ATS {

    private final ConcurrentLinkedQueue<Call> calls;

    private boolean workingTime;

    public ATS() {
        this.workingTime = true;
        calls = new ConcurrentLinkedQueue<>();
    }

    public boolean isWorkingTime() {
        return workingTime;
    }

    public void close(){
        workingTime = false;
    }

    public Call getCall() {
        return calls.poll();
    }

    public void putCall(Call call) {
        calls.offer(call);
    }
}
