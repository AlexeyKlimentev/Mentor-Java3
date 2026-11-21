package ru.klimentev.cement.service;

import ru.klimentev.cement.model.Order;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionService {
    private final BigDecimal pricePerKg;
    private final BigDecimal initialDiscount;
    private final BigDecimal discountStep;

    public PromotionService(BigDecimal pricePerKg, BigDecimal initialDiscount, BigDecimal discountStep) {
        this.pricePerKg = pricePerKg;
        this.initialDiscount = initialDiscount;
        this.discountStep = discountStep;
    }

    public Map<String, BigDecimal> calculate(List<Order> orders) {
        List<Order> sortedOrders = orders.stream()
                .sorted(Comparator.comparing(Order::time))
                .toList();
        Map<String, BigDecimal> result = new HashMap<>();
        BigDecimal currentDiscount = initialDiscount;
        for (Order order : sortedOrders){
            BigDecimal orderPrice = BigDecimal.valueOf(order.weight()).multiply(pricePerKg).multiply(BigDecimal.ONE.subtract(currentDiscount));
            result.merge(order.company(), orderPrice, BigDecimal::add);
            currentDiscount = currentDiscount.subtract(discountStep).max(BigDecimal.ZERO);
        }
        return result;
    }
}
