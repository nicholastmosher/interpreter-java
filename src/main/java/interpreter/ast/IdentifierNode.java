package interpreter.ast;

import interpreter.ast.visitor.Visitor;

public class IdentifierNode extends Node {
    public String ident;
    public IdentifierNode(String ident) {
        this.ident = ident;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitIdentifier(this);
    }
}
