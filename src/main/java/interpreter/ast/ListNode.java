package interpreter.ast;

import interpreter.ast.visitor.Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListNode extends Node {
    public List<Node> items;

    public ListNode(Node... nodes) {
        items = new ArrayList<>(Arrays.asList(nodes));
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitList(this);
    }
}
