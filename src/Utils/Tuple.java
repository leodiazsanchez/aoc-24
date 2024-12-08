package Utils;

import java.util.Objects;

public class Tuple<X, Y> {
    public X x;
    public Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "(" + x + "," + y + ")";
    }


    // This is crucial for ensuring that .contains catches tuples with the same content, despite them being two different objects
    public int hashCode() {
        return Objects.hash(x, y); // Generate a hashCode based on x and y
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple<?, ?> tuple)) return false;
        return Objects.equals(x, tuple.x) && Objects.equals(y, tuple.y);
    }
}
