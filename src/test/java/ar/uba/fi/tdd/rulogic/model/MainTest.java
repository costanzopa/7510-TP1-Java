package ar.uba.fi.tdd.rulogic.model;

import ar.uba.fi.tdd.rulogic.App;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by costa on 4/11/2017.
 * MAIN TEST
 */
public class MainTest {

    @Test
    public void testFileMain() throws Exception {
        String[] args = new String[2];
        args[0]="-f";
        args[1]="src/main/resources/number.db";
        String data = "add(two, one, one)\n"+"-q\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        App.main(args);
        System.out.flush();
        System.setOut(old);
        Assert.assertTrue(baos.toString().contains("NO"));
    }

    @Test
    public void testStringMain() throws Exception {
        String[] args = new String[1];
        args[0]="add(two, one, one)\n";
        String data = "add(two, one, one)\n"+"-q\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        App.main(args);
        System.out.flush();
        System.setOut(old);
        Assert.assertTrue(baos.toString().contains("SI"));
    }

    @Test
    public void testPrintHelpMain() throws Exception {
        String[] args = new String[0];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        App.main(args);
        System.out.flush();
        System.setOut(old);
        Assert.assertTrue(baos.toString().contains("./main"));
    }
}
