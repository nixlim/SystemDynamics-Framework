package de.hsbo.fbg.systemdynamics.functions;

import de.hsbo.fbg.systemdynamics.model.Flow;
import de.hsbo.fbg.systemdynamics.model.Stock;

/**
 * This class extends the IntegrationType and represents the euler cauchy
 * calculation.
 *
 * @author Sebastian Drost
 * @author Matthias Stein
 */
public class EulerCauchyIntegration extends Integration {

    @Override
    public IFunction getIntegrationFunction(Stock stock) {
        return new IFunction() {
            @Override
            public double calculateEntityValue() {
                double result = stock.getCurrentValue();
                double inputSum = 0;
                double outputSum = 0;
                for (Flow input : stock.getInputFlows()) {
                    inputSum += input.getPreviousValue();
                }
                for (Flow output : stock.getOutputFlows()) {
                    outputSum += output.getPreviousValue();
                }
                return result + (inputSum - outputSum) * getDt();
            }
        };
    }
}
