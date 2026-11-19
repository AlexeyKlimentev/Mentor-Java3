import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        OrderReader reader1 = new PipeOrderAdapter();
        OrderReader reader2 = new HashOrderAdapter();

        List<Order> allOrders = new ArrayList<>();
        allOrders.addAll(reader1.read("discount_day.txt"));
        allOrders.addAll(reader2.read("discount_day_without_ext"));
        PromotionService first = new PromotionService(new BigDecimal("10"), new BigDecimal("0.50"), new BigDecimal("0.05"));
        Map<String, BigDecimal> results = first.calculate(allOrders);
        ResultWriter writer = new ResultWriter();
        writer.write("result.txt", results);
    }
}