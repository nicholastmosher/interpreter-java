package interpreter.ast.visitor;

import interpreter.ast.*;

public class FormatVisitor implements Visitor<String> {
    @Override
    public String visitIdentifier(IdentifierNode identifier) {
        return identifier.ident;
    }

    @Override
    public String visitInteger(IntegerLiteralNode integer) {
        return String.format("%d", integer.integer);
    }

    @Override
    public String visitList(ListNode listNode) {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < listNode.items.size(); i++) {
            if (i != 0) listString.append(" ");
            Node child = listNode.items.get(i);
            listString.append(child.accept(this));
        }
        return String.format("(%s)", listString.toString());
    }
}
