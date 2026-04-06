package core.dao;

import core.db.Storage;
import java.util.Map;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public void set(String fruit, int quantity) {
        Storage.storage.put(fruit, quantity);
    }

    @Override
    public void add(String fruit, int quantity) {
        Storage.storage.merge(fruit, quantity, Integer::sum);
    }

    @Override
    public void subtract(String fruit, int quantity) {
        int currentValue = Storage.storage.get(fruit);
        int newValue = currentValue - quantity;
        
        if (newValue < 0) {
            throw new RuntimeException("Negative balance for: " + fruit);
        }
        Storage.storage.put(fruit, newValue);
    }

    @Override
    public Map<String, Integer> getAll() {
        return Storage.storage;
    }
}
