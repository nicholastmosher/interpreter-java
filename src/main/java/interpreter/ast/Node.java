package interpreter.ast;

import interpreter.ast.visitor.Visitor;

public abstract class Node {
    public abstract <T> T accept(Visitor<T> visitor);
}
