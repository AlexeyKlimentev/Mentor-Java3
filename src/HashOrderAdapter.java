import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HashOrderAdapter implements OrderReader {
    @Override
    public List<Order> read(String path) {
        List<Order> orders = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            for (String line : lines) {
                String[] parts = line.split("#");
                LocalDateTime time = LocalDateTime.parse(parts[0]);
                String company = parts[1];
                double weight = Double.parseDouble(parts[2]);
                orders.add(new Order(time, company, weight));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }
}
