import java.util.Objects;

public class Call implements Comparable<Call> {
    String description;

    public Call() {
        description = "call from " + Thread.currentThread().getName();
    }

    @Override
    public int compareTo(Call o) {
        return this.description.equals(o.description) ? 0 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        Call o;
        if (obj instanceof Call) o = (Call) obj;
        else return false;
        return Objects.equals(this.description, o.description);
    }
}
