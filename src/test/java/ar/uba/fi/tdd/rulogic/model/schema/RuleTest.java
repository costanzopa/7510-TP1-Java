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
 * Created by costa on 1/11/2017.
 * Rule Test
 */
public class RuleTest {


    @Mock
    private Rule rule;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getLineRuleTest() {
        String expected = "hermano(nicolas, roberto).";
        when(rule.getLine()).thenReturn(expected);
        Rule testRule = new Rule("hermano(nicolas, roberto).");
        Assert.assertEquals(rule.getLine(),testRule.getLine());
    }

    @Test
    public void getRuleNameTest() {
        String expected = "hermano";
        when(rule.getName()).thenReturn(expected);
        Rule testRule = new Rule("hermano(nicolas, roberto).");
        Assert.assertEquals(rule.getName(),testRule.getName());
    }


    @Test
    public void getRuleArgumentsTest() {
        List<String> expected = new ArrayList<String>() {{add("nicolas");}};
        when(rule.getArguments()).thenReturn(expected);
        Rule testRule = new Rule("varon ( nicolas ) .");
        Assert.assertEquals(rule.getArguments(),testRule.getArguments());
    }

    @Test
    public void getRuleLeftSideInRuleTest() {
        String name = "tio";
        List<String> arguments = new ArrayList<String>() {{add("X"); add("Y"); add("Z");}};
        when(rule.getName()).thenReturn(name);
        when(rule.getArguments()).thenReturn(arguments);
        Rule testRule = new Rule(" tio(X, Y, Z):- varon(X),	hermano(X, Z),padre(Z, Y).");
        Assert.assertEquals(rule.getName(),testRule.getName());
        Assert.assertEquals(rule.getArguments(),testRule.getArguments());
        Assert.assertTrue(testRule.isValid());
    }


    @Test
    public void getRuleRightSideNamesInRuleTest() {
        List<Element> arguments = new ArrayList<Element>() {{add(new Fact("varon(X)"));
                                                             add(new Fact("hermano(X, Z)"));
                                                             add(new Fact("padre(Z, Y)."));}};
        when(rule.getRightSide()).thenReturn(arguments);
        Rule testRule = new Rule("tio(X, Y, Z):- varon(X),	hermano(X, Z),padre(Z, Y).");
        Assert.assertEquals(rule.getRightSide().get(0).getName(),testRule.getRightSide().get(0).getName());
        Assert.assertEquals(rule.getRightSide().get(1).getName(),testRule.getRightSide().get(1).getName());
        Assert.assertEquals(rule.getRightSide().get(2).getName(),testRule.getRightSide().get(2).getName());
        Assert.assertTrue(testRule.isValid());
    }

    @Test
    public void getRuleRightSideArgumentsInRuleTest() {
        List<Element> arguments = new ArrayList<Element>() {{add(new Fact("varon(X)"));
                                                             add(new Fact("hermano(X, Z)"));
                                                             add(new Fact("padre(Z, Y)."));}};
        when(rule.getRightSide()).thenReturn(arguments);
        Rule testRule = new Rule("tio(X, Y, Z):- varon(X),	hermano(X, Z),padre(Z, Y).");
        Assert.assertEquals(rule.getRightSide().get(0).getArguments(),testRule.getRightSide().get(0).getArguments());
        Assert.assertEquals(rule.getRightSide().get(1).getArguments(),testRule.getRightSide().get(1).getArguments());
        Assert.assertEquals(rule.getRightSide().get(2).getArguments(),testRule.getRightSide().get(2).getArguments());
    }

    @Test
    public void getEvaluateRuleWhenItsTrueTest() {
        Rule testRule1 = new Rule("tio(X, Y, Z):- varon(X),	hermano(X, Z),padre(Z, Y).");
        Element testRule2 = new Rule("tio(X, Y, Z):- varon(X),	hermano(X, Z),padre(Z, Y).");
        Assert.assertTrue(testRule1.evaluate(testRule2));
    }

    @Test
    public void getEvaluateRuleWhenItsFalseTest() {
        Rule testRule1 = new Rule("tio(X, Y, Z):- varon(X),	hermano(X, Z),padre(Z, Y).");
        Rule testRule2 = new Rule("tia(X, Y, Z):- mujer(X),	hermano(X, Z),padre(Z, Y).");
        Assert.assertFalse(testRule1.evaluate(testRule2));
    }

    @Test
    public void getInvalidRightSideRuleTest() {
        Rule testRule = new Rule("tio(X, Y, Z):- varon(X");
        Assert.assertFalse(testRule.isValid());
    }
}
