package opt.example;

import util.linalg.Vector;
import opt.EvaluationFunction;
import shared.Instance;

/**
 * A continuous peaks function
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class ContinuousPeaksEvaluationFunction implements EvaluationFunction {
    /**
     * The t value
     */
    private int t;

    /**
     * Track number of times value() is called.
     * Expected to be incremented by all value() calls.
     */
    public long valueCallCount = 0;
    
    /**
     * Make a new continuous peaks function
     * @param t the t value
     */
    public ContinuousPeaksEvaluationFunction(int t) {
        this.t = t;
    }

    /**
     * @see opt.EvaluationFunction#value(opt.OptimizationData)
     */
    public double value(Instance d) {
        Vector data = d.getData();
        int max0 = 0;
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == 0) {
                max0 = Math.max(max0, ++count);
            } else {
                count = 0;
            }
        }
        int max1 = 0;
        count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == 1) {
                max1 = Math.max(max1, ++count);
            } else {
                count = 0;
            }
        }
        int r = 0;
        if (max1 > t && max0 > t) {
            r = data.size();
        }
        this.valueCallCount += 1;
        return Math.max(max1, max0) + r;
    }
}
