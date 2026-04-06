package core.strategy.handler;

import core.dao.TransactionDao;
import core.model.Transaction;

public class ReturnHandler extends OperationHandler {
    public ReturnHandler(TransactionDao transactionDao) {
        super(transactionDao);
    }
    
    @Override
    public void handle(Transaction transaction) {
        transactionDao.add(transaction.getType(), transaction.getQuantity());
    }
}
