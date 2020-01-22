package interpreter.ast;

import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void testListNode() {
        ListNode listNode = new ListNode();
        assertEquals(listNode.toString(), "()");
    }

    @Test
    public void testStringNode() {
        StringLiteralNode stringNode = new StringLiteralNode("value");
        assertEquals(stringNode.toString(), "\"value\"");
    }

    @Test
    public void testStringNodeInList() {
        ListNode listNode = new ListNode(
                new StringLiteralNode("one"),
                new StringLiteralNode("two"),
                new StringLiteralNode("three")
        );
        assertEquals(listNode.toString(), "(\"one\" \"two\" \"three\")");
    }
}
