package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.converter.DataConverter;
import core.basesyntax.converter.DataConverterImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DataConverterImplTest {
    private static final DataConverter dataConverter = new DataConverterImpl();

    @Test
    public void convertEmptyString() {
        List<String> emptyStringList = new ArrayList<>();
        emptyStringList.add("");
        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(emptyStringList));
    }

    @Test
    public void convertStringArgumentsAmountAreNotThree() {
        List<String> tooManyArgumentsList = new ArrayList<>();
        tooManyArgumentsList.add("1,2,3,4");
        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(tooManyArgumentsList));
    }

    @Test
    public void convertStringArgumentsEqualThree() {
        List<String> correctList = new ArrayList<>();
        correctList.add("1,2,3");
        assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(correctList));
    }
}
