package core.basesyntax.service.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.storage.FruitShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BalanceOperationTest {
    private static final OperationHandler operationHandler = new BalanceOperation();

    @BeforeEach
    public void setUp() {
        FruitShop.storage.clear();
        FruitShop.storage.put("Coconut", 52);
        FruitShop.storage.put("Apple", 42);
    }

    @Test
    public void processExitingFruit() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.BALANCE,
                "Apple", 44);
        operationHandler.process(fruitTransaction);
        int actual = FruitShop.getQuantity("Apple");
        assertEquals(44, actual);
    }

    @Test
    public void processUnknownFruit() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.BALANCE,
                "DragonFruit", 8);
        operationHandler.process(fruitTransaction);
        int actual = FruitShop.getQuantity("DragonFruit");
        assertEquals(8, actual);
    }
}
