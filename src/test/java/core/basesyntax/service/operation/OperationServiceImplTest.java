package core.basesyntax.service.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import core.basesyntax.model.FruitTransaction;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationServiceImplTest {
    private OperationStrategyImpl operationStrategy;
    private OperationHandler purchaseHandler;
    private OperationHandler supplyHandler;

    @BeforeEach
    void setUp() {
        purchaseHandler = new PurchaseOperation();
        supplyHandler = new SupplyOperation();
        operationStrategy = new OperationStrategyImpl(
                Map.of(
                        FruitTransaction.Operation.PURCHASE, purchaseHandler,
                        FruitTransaction.Operation.SUPPLY, supplyHandler
                ));
    }

    @Test
    void getExistingOperation() {
        assertEquals(purchaseHandler, operationStrategy.get(FruitTransaction.Operation.PURCHASE));
        assertEquals(supplyHandler, operationStrategy.get(FruitTransaction.Operation.SUPPLY));
    }

    @Test
    void getNnExistingOperation() {
        assertNull(operationStrategy.get(FruitTransaction.Operation.RETURN));
    }
}
