package ar.uba.fi.tdd.rulogic.model.schema;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by costa on 28/10/2017.
 * Facts Tests
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
        Fact testFact = new Fact("varon( nicolas ).");
        Assert.assertEquals(fact.getName(),testFact.getName());
    }

    @Test
    public void isValidTrueTest() {
        when(fact.isValid()).thenReturn(true);
        Fact testFact = new Fact("varon ( nicolas ) .");
        Assert.assertEquals(fact.isValid(),testFact.isValid());
    }

    @Test
    public void isValidFalseTest() {
        when(fact.isValid()).thenReturn(false);
        Fact testFact = new Fact("varon ( nicolas");
        Assert.assertEquals(fact.isValid(),testFact.isValid());
    }

    @Test
    public void getOneArgumentsTest() {
        List<String> expected = new ArrayList<String>() {{add("nicolas");}};
        when(fact.getArguments()).thenReturn(expected);
        Fact testFact = new Fact("varon ( nicolas).");
        Assert.assertEquals(fact.getArguments(),testFact.getArguments());
    }

    @Test
    public void getTwoArgumentsTest() {
        List<String> expected = new ArrayList<String>() {{add("nicolas"); add("roberto");}};
        when(fact.getArguments()).thenReturn(expected);
        Fact testFact = new Fact("hermano(nicolas, roberto).");
        Assert.assertEquals(fact.getArguments(),testFact.getArguments());
    }

    @Test
    public void evaluateTwoFactsEqualsTest() {
        Fact testFact = new Fact("hermano(nicolas, roberto).");
        Fact testFact2 = new Fact("hermano(nicolas, roberto).");
        Assert.assertTrue(testFact.evaluate(testFact2));
    }

    @Test
    public void evaluateTwoFactsNotEqualsTest() {
        Fact testFact = new Fact("hermano(nicolas, roberto).");
        Fact testFact2 = new Fact("varon(nicolas).");
        Assert.assertFalse(testFact.evaluate(testFact2));
    }
}
