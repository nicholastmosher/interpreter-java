package interpreter.ast.visitor;

import interpreter.ast.IdentifierNode;
import interpreter.ast.IntegerLiteralNode;
import interpreter.ast.ListNode;
import interpreter.ast.Node;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class EvaluationVisitorTest {

    @Test
    public void testIntLiteral() {
        Node expression = new IntegerLiteralNode(7);
        Optional<Integer> output = expression.accept(new EvaluationVisitor());
        assertEquals(output, Optional.of(7));
    }

    @Test
    public void testAddTwoLiterals() {
        Node expression = new ListNode(
                new IdentifierNode("add"),
                new IntegerLiteralNode(5),
                new IntegerLiteralNode(10)
        );

        Optional<Integer> output = expression.accept(new EvaluationVisitor());
        assertEquals(output, Optional.of(15));
    }

    @Test
    public void testAddManyLiterals() {
        Node expression = new ListNode(
                new IdentifierNode("add"),
                new IntegerLiteralNode(1),
                new IntegerLiteralNode(2),
                new IntegerLiteralNode(3),
                new IntegerLiteralNode(4),
                new IntegerLiteralNode(5)
        );

        Optional<Integer> output = expression.accept(new EvaluationVisitor());
        assertEquals(output, Optional.of(15));
    }

    @Test
    public void testAddNestedExpressions() {
        Node expression = new ListNode(
                new IdentifierNode("add"),
                new IntegerLiteralNode(1),
                new ListNode(
                        new IdentifierNode("add"),
                        new IntegerLiteralNode(2),
                        new IntegerLiteralNode(3)
                )
        );

        Optional<Integer> output = expression.accept(new EvaluationVisitor());
        assertEquals(output, Optional.of(6));
    }
}
