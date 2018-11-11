package software.sigma.principles.exercise6;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArgsTest {
    @Test
    public void testCreateWithNoSchemaOrArguments() throws Exception {
        Args args = new Args("", new String[0]);
        assertEquals(0, args.cardinality());
    }

    @Test
    public void testWithNoSchemaButWithOneArgument() throws Exception {
        Args args = new Args("", new String[]{"-x"});
        assertFalse(args.isValid());
        assertEquals("Argument(s) -x unexpected.", args.errorMessage());
    }

    @Test
    public void testWithNoSchemaButWithMultipleArguments() throws Exception {
        Args args = new Args("", new String[]{"-x", "-y"});
        assertFalse(args.isValid());
        assertEquals("Argument(s) -xy unexpected.", args.errorMessage());
    }

    @Test
    public void testNonLetterSchema() {
        assertThrows(ParseException.class, () -> new Args("*", new String[]{}));
    }

    @Test
    public void testInvalidArgumentFormat() {
        assertThrows(ParseException.class, () -> new Args("f~", new String[]{}));
    }

    @Test
    public void testSimpleBooleanTruePresent() throws Exception {
        Args args = new Args("x", new String[]{"-x", "true"});
        assertEquals(1, args.cardinality());
        assertTrue(args.getBoolean('x'));
    }

    //TODO Currently fails...
    @Test
    public void testSimpleBooleanFalsePresent() throws Exception {
        Args args = new Args("x", new String[]{"-x", "false"});
        assertEquals(1, args.cardinality());
        assertFalse(args.getBoolean('x'));
    }

    //TODO Currently fails...
    @Test
    public void testMissingBooleanArgument() throws Exception {
        Args args = new Args("x", new String[]{"-x"});
        assertFalse(args.isValid());
    }

    //TODO Currently fails...
    @Test
    public void testInvalidBoolean() throws Exception {
        Args args = new Args("x", new String[]{"-x", "Truthy"});
        assertEquals(1, args.cardinality());
        assertFalse(args.getBoolean('x'));
    }

    @Test
    public void testMultipleBooleans() throws Exception {
        Args args = new Args("x,y", new String[]{"-xy", "true", "true"});
        assertEquals(2, args.cardinality());
        assertTrue(args.getBoolean('x'));
        assertTrue(args.getBoolean('y'));
    }

    @Test
    public void testSpacesInFormat() throws Exception {
        Args args = new Args("x, y", new String[]{"-xy", "true", "true"});
        assertEquals(2, args.cardinality());
        assertTrue(args.has('x'));
        assertTrue(args.has('y'));
        assertTrue(args.getBoolean('x'));
        assertTrue(args.getBoolean('y'));
    }

    //TODO Currently fails...
    @Test
    public void testInvalidArgumentValueFormat() throws Exception {
        Args args = new Args("x,y", new String[]{"xy", "true", "false"});
        assertFalse(args.isValid());
    }

    @Test
    public void testSimpleStringPresent() throws Exception {
        Args args = new Args("x*", new String[]{"-x", "param"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals("param", args.getString('x'));
    }

    @Test
    public void testMissingStringArgument() throws Exception {
        Args args = new Args("x*", new String[]{"-x"});
        assertFalse(args.isValid());
        assertEquals("Could not find string parameter for -x.",
                args.errorMessage());
    }

    @Test
    public void testSimpleIntPresent() throws Exception {
        Args args = new Args("x#", new String[]{"-x", "42"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42, args.getInt('x'));
    }

    @Test
    public void testInvalidInteger() throws Exception {
        Args args = new Args("x#", new String[]{"-x", "Forty two"});
        assertFalse(args.isValid());
        assertEquals("Argument -x expects an integer but was 'Forty two'.",
                args.errorMessage());
    }

    @Test
    public void testMissingInteger() throws Exception {
        Args args = new Args("x#", new String[]{"-x"});
        assertFalse(args.isValid());
        assertEquals("Could not find integer parameter for -x.",
                args.errorMessage());
    }
}