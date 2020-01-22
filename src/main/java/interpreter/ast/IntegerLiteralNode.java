package interpreter.ast;

import interpreter.ast.visitor.Visitor;

public class IntegerLiteralNode extends Node {
    public int integer;
    public IntegerLiteralNode(int integer) {
        this.integer = integer;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitInteger(this);
    }
}
