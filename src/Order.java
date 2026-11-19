import java.time.LocalDateTime;

public record Order(
        LocalDateTime time,
        String company,
        double weight
) {
}