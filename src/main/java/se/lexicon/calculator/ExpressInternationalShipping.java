package se.lexicon.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.lexicon.model.Destination;
import se.lexicon.model.ShippingRequest;
import se.lexicon.model.Speed;
import se.lexicon.service.ShippingCostCalculator;

@Component
public class ExpressInternationalShipping implements ShippingCostCalculator {

    @Value("${shipping.international.express.perKg}")
    private double costPerKg ;

    @Value("${shipping.international.express.base}")
    private double baseCost ;
    public boolean supports(ShippingRequest r) {
        return r.destination() == Destination.INTERNATIONAL && r.speed() == Speed.EXPRESS;
    }

    public double calculate(ShippingRequest r) {
        return baseCost + costPerKg * r.weightKg();
    }
}
