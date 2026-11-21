package ru.klimentev.cement.adapter;
import java.util.List;
import ru.klimentev.cement.model.Order;

public interface OrderReader {
    List<Order> read(String path);
}
