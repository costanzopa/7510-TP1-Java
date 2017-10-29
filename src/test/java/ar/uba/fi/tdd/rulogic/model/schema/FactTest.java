package ar.uba.fi.tdd.rulogic.model.schema;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Created by costa on 28/10/2017.
 */
public class FactTest {
    @Mock
    private Fact fact;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getFactNameTest() {
        String expected = "varon";
        when(fact.getName()).thenReturn(expected);
        Fact testFact = new Fact("varon ( nicolas ) .");
        Assert.assertEquals(fact.getName(),testFact.getName());
    }

    @Test
    public void isValidTrueTest() {
        boolean expected = true;
        when(fact.isValid()).thenReturn(expected);
        Fact testFact = new Fact("varon ( nicolas ) .");
        Assert.assertEquals(fact.isValid(),testFact.isValid());
    }

    @Test
    public void isValidFalseTest() {
        boolean expected = false;
        when(fact.isValid()).thenReturn(expected);
        Fact testFact = new Fact("varon ( nicolas");
        Assert.assertEquals(fact.isValid(),testFact.isValid());
    }

}
