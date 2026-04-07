package core.service.impl;

import core.db.Storage;
import core.service.TransactionDao;
import java.util.Map;

public class TransactionDaoImpl implements TransactionDao {
    private final Storage storage;
    
    public TransactionDaoImpl(Storage storage) {
        this.storage = storage;
    }
    
    @Override
    public void set(String fruit, int quantity) {
        storage.getStorage().put(fruit, quantity);
    }

    @Override
    public void add(String fruit, int quantity) {
        storage.getStorage().merge(fruit, quantity, Integer::sum);
    }

    @Override
    public void subtract(String fruit, int quantity) {
        if (!storage.getStorage().containsKey(fruit)) {
            throw new RuntimeException("Cannot find fruit: " + fruit);
        }
        
        int currentValue = storage.getStorage().get(fruit);
        int newValue = currentValue - quantity;
        
        if (newValue < 0) {
            throw new RuntimeException("Negative balance for: " + fruit);
        }
        storage.getStorage().put(fruit, newValue);
    }

    @Override
    public Map<String, Integer> getAll() {
        return storage.getStorage();
    }
}
