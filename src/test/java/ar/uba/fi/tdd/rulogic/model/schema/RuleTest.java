package ar.uba.fi.tdd.rulogic.model.schema;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Created by costa on 1/11/2017.
 * Rule Test
 */
public class RuleTest {

    @Mock
    private Fact fact;

    @InjectMocks
    Rule rule;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void geRuleNameTest() {

    }
}
