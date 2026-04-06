package core.strategy.handler;

import core.dao.TransactionDao;
import core.model.Transaction;

public class BalanceHandler extends OperationHandler {
    public BalanceHandler(TransactionDao transactionDao) {
        super(transactionDao);
    }
    
    @Override
    public void handle(Transaction transaction) {
        transactionDao.set(transaction.getType(), transaction.getQuantity());
    }
}
