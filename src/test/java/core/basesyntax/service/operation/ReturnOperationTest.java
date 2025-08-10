package core.basesyntax.service.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.storage.FruitShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReturnOperationTest {
    private static final OperationHandler operationHandler = new ReturnOperation();

    @BeforeEach
    public void setUp() {
        FruitShop.storage.clear();
        FruitShop.storage.put("Apple", 0);
    }

    @Test
    public void returnFruitThatShopDoesntContain() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction
                .Operation.RETURN, "Banana", 3);
        assertThrows(RuntimeException.class, () -> operationHandler.process(fruitTransaction));
    }

    @Test
    public void returnFruitThatShopContains() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction
                .Operation.RETURN, "Apple", 3);
        operationHandler.process(fruitTransaction);
        int actual = FruitShop.storage.get("Apple");
        assertEquals(3, actual);
    }
}
