package core.strategy.handler;

import core.dao.TransactionDao;
import core.model.Transaction;

public class SupplyHandler extends OperationHandler {
    public SupplyHandler(TransactionDao transactionDao) {
        super(transactionDao);
    }
    
    @Override
    public void handle(Transaction transaction) {
        transactionDao.add(transaction.getType(), transaction.getQuantity());
    }
}
