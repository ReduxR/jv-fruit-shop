package core.strategy.handler;

import core.dao.TransactionDao;
import core.model.Transaction;

public class PurchaseHandler extends OperationHandler {
    public PurchaseHandler(TransactionDao transactionDao) {
        super(transactionDao);
    }
    
    @Override
    public void handle(Transaction transaction) {
        transactionDao.subtract(transaction.getType(), transaction.getQuantity());
    }
}
