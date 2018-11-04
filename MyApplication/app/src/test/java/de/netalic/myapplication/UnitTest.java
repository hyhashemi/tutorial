package de.netalic.myapplication;

import org.junit.Test;

import de.netalic.myapplication.utils.StringHandler;

import static org.junit.Assert.*;

public class UnitTest {

    @Test
    public void converterTest(){
        String empty = "";
        String notNumber = "1fr";
        String number = "123";

        assertEquals(123, StringHandler.converter(number));
        assertNotSame(null, StringHandler.converter(notNumber));
        assertNotNull(StringHandler.converter(empty));
    }

    @Test
    public void replacerTest(){
        String notContain = "bcd";
        String correct = "abcd";

        assertEquals("bcd", StringHandler.replacer(notContain));
        assertEquals("bbcd", StringHandler.replacer(correct));
    }

    @Test
    public void charTest(){
        String chars = "abcd";
        String string = "ac3*";

        assertTrue(StringHandler.checkOnlyContainChar(chars));
        assertFalse(StringHandler.checkOnlyContainChar(string));
    }
}
