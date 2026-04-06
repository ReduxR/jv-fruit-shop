package core.converter;

import core.model.Transaction;
import java.util.List;

public interface Converter {
    List<Transaction> convertCsvLines(List<String> lines);
}
