package interpreter.ast;

import interpreter.ast.visitor.Visitor;

public class StringLiteralNode extends Node {
    public String contents;

    public StringLiteralNode(String contents) {
        this.contents = contents;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitString(this);
    }
}
