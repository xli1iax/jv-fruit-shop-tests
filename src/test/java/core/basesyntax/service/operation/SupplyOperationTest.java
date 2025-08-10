package core.basesyntax.service.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.storage.FruitShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SupplyOperationTest {
    private static final OperationHandler operationHandler = new SupplyOperation();

    @BeforeEach
    public void setup() {
        FruitShop.storage.clear();
        FruitShop.storage.put("Apple", 10);
    }

    @Test
    public void supplyUnknownFruit() {
        FruitTransaction transaction = new FruitTransaction(FruitTransaction
                .Operation.SUPPLY, "Coconut", 3);
        operationHandler.process(transaction);
        int actual = FruitShop.storage.get("Coconut");
        assertEquals(3, actual);
    }

    @Test
    public void supplyExitingFruit() {
        FruitTransaction transaction = new FruitTransaction(FruitTransaction
                .Operation.SUPPLY, "Apple", 3);
        operationHandler.process(transaction);
        int actual = FruitShop.storage.get("Apple");
        assertEquals(13, actual);
    }
}
