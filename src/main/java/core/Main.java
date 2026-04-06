package core;

import core.converter.Converter;
import core.converter.ConverterCsvImpl;
import core.dao.TransactionDao;
import core.dao.TransactionDaoImpl;
import core.generator.ReportGenerator;
import core.generator.ReportGeneratorImpl;
import core.model.Transaction;
import core.service.ShopService;
import core.service.ShopServiceImpl;
import core.service.io.CsvReaderImpl;
import core.service.io.CsvWriterImpl;
import core.service.io.Reader;
import core.service.io.Writer;
import core.strategy.OperationStrategy;
import core.strategy.OperationStrategyImpl;
import core.strategy.handler.BalanceHandler;
import core.strategy.handler.OperationHandler;
import core.strategy.handler.PurchaseHandler;
import core.strategy.handler.ReturnHandler;
import core.strategy.handler.SupplyHandler;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Reader reader = new CsvReaderImpl();
        List<String> lines = reader.readFile("FileInput.csv");
        
        TransactionDao transactionDao = new TransactionDaoImpl();
        
        Converter converter = new ConverterCsvImpl(transactionDao);

        List<Transaction> transactions = converter.convertCsvLines(lines);

        for (Transaction t : transactions) {
            System.out.println(t.getOperation() + " " + t.getType() + " " + t.getQuantity());
        }
        
        OperationHandler balanceHandler = new BalanceHandler(transactionDao);
        OperationHandler supplyHandler = new SupplyHandler(transactionDao);
        OperationHandler returnHandler = new ReturnHandler(transactionDao);
        OperationHandler purchaseHandler = new PurchaseHandler(transactionDao);
        
        Map<Transaction.Operation, OperationHandler> handlers = Map.of(
                Transaction.Operation.BALANCE, balanceHandler,
                Transaction.Operation.RETURN, returnHandler,
                Transaction.Operation.PURCHASE, purchaseHandler,
                Transaction.Operation.SUPPLY, supplyHandler
        );

        OperationStrategy operationStrategy = new OperationStrategyImpl(handlers);

        ShopService shopService = new ShopServiceImpl(operationStrategy, transactionDao);
        shopService.process(transactions);
        Map<String, Integer> processedData = shopService.getData();
        
        ReportGenerator generator = new ReportGeneratorImpl();
        String report = generator.generateReport(processedData);

        Writer writer = new CsvWriterImpl();
        writer.writeToFile(report, "FileOutput.csv");
    }
}
