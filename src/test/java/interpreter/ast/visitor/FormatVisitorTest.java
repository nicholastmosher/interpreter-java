package interpreter.ast.visitor;

import interpreter.ast.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class FormatVisitorTest {

    @Test
    public void testFormatList() {
        Node ast = new ListNode(
                new IdentifierNode("add"),
                new IntegerLiteralNode(2),
                new IntegerLiteralNode(3)
        );

        String output = ast.accept(new FormatVisitor());
        assertEquals(output, "(add 2 3)");
    }

    @Test
    public void testFormatString() {
        Node ast = new ListNode(
                new IdentifierNode("print"),
                new ListNode(
                        new IdentifierNode("append"),
                        new StringLiteralNode("Hello"),
                        new StringLiteralNode(", world!")
                )
        );

        String output = ast.accept(new FormatVisitor());
        assertEquals(output, "(print (append \"Hello\" \", world!\"))");
    }
}
