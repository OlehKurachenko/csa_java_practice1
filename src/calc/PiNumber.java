package calc;

import java.io.Serializable;
import java.util.Random;

public class PiNumber implements Calculable {

    @Override
    public double calc() {
        return Math.PI;
    }
}
