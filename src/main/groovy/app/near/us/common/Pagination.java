package app.near.us.common;
import java.util.List;

public record Pagination<T>(
    List<T> data,
    int page,
    int size,
    long current,
    int totalPages,
    boolean hasNext
) { }
