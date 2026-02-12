package se.lexicon.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.lexicon.model.ShippingRequest;

import java.util.List;
@Service
public class ShippingCalculatorFactory {

    private final List<ShippingCostCalculator> calculators;

    private ShippingCostCalculator defaultCalculator;

    public ShippingCalculatorFactory(List<ShippingCostCalculator> calculators, ShippingCostCalculator defaultCalculator) {
        this.defaultCalculator = defaultCalculator;
        this.calculators = calculators;
    }

    public ShippingCostCalculator getCalculator(ShippingRequest req) {
        if(defaultCalculator != null && defaultCalculator.supports(req)) {
            return defaultCalculator;
        }
        return calculators.stream()
                .filter(c -> c.supports(req))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No calculator for request: " + req));
    }
}
