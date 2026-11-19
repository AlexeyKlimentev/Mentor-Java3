import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultWriter {
    public void write(String path, Map<String, BigDecimal> results) {
        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : results.entrySet()){
            String result = entry.getKey() + " - " + entry.getValue().setScale(2, RoundingMode.HALF_UP);
            lines.add(result);
        }

        try {
            Files.write(Path.of(path), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
