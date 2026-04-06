package core.strategy.handler;

import core.dao.TransactionDao;
import core.model.Transaction;

public abstract class OperationHandler {
    protected final TransactionDao transactionDao;
    
    public OperationHandler(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
    
    public abstract void handle(Transaction transaction);
}
