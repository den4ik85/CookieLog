import com.company.sample.service.CookieLogServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CookieLogServiceImplTest {

    private CookieLogServiceImpl service;

    @Before
    public void setUp() {
        service = new CookieLogServiceImpl();
    }

    @Test
    public void shouldRetrieveMostActiveCookieForDate() throws Exception {
        List<String> expectedResult = new LinkedList<>();
        expectedResult.add("AtY0laUfhglK3lC7");
        expectedResult.add("SAZuXPGUrfbcn5UA");
        List<String> actualResult = service.getActiveCookieForDate("cookies.csv", "2018-12-09");
        assertEquals(expectedResult, actualResult);
    }
}
