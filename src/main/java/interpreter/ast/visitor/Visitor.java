package interpreter.ast.visitor;

import interpreter.ast.IdentifierNode;
import interpreter.ast.IntegerLiteralNode;
import interpreter.ast.ListNode;

public interface Visitor<T> {
    T visitIdentifier(IdentifierNode identifier);
    T visitInteger(IntegerLiteralNode integer);
    T visitList(ListNode listNode);
}
