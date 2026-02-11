package se.lexicon.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.lexicon.model.Destination;
import se.lexicon.model.ShippingRequest;
import se.lexicon.model.Speed;
import se.lexicon.service.ShippingCostCalculator;

@Component
public class StandardDomesticShipping implements ShippingCostCalculator {

   @Value("${shipping.domestic.standard.perKg}")
    private double costPerKg ;

   @Value("${shipping.domestic.standard.base}")
    private double baseCost ;

    public boolean supports(ShippingRequest r) {
        return r.destination() == Destination.DOMESTIC && r.speed() == Speed.STANDARD;
    }

    public double calculate(ShippingRequest r) {
        return baseCost + costPerKg * r.weightKg();
    }
}