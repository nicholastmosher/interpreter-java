package interpreter.ast.visitor;

import interpreter.ast.IdentifierNode;
import interpreter.ast.IntegerLiteralNode;
import interpreter.ast.ListNode;
import interpreter.ast.StringLiteralNode;

public interface Visitor<T> {
    T visitIdentifier(IdentifierNode identifier);
    T visitInteger(IntegerLiteralNode integer);
    T visitString(StringLiteralNode string);
    T visitList(ListNode listNode);
}
