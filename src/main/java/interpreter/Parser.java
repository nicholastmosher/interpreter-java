package interpreter;

import interpreter.ast.IdentifierNode;
import interpreter.ast.IntegerLiteralNode;
import interpreter.ast.ListNode;
import interpreter.ast.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Parser {

    public static Optional<Node> parse(List<Token> tokens) {
        LinkedList<ListNode> stack = new LinkedList<>();

        for (Token token : tokens) {

            switch (token.type) {
                case LPAREN:
                    // Create a new, empty ListNode and push onto stack
                    ListNode list = new ListNode();
                    stack.push(list);
                    break;
                case RPAREN:
                    // Check if there is a node in the stack
                    if (stack.isEmpty()) {
                        return Optional.empty();
                    }

                    ListNode head = stack.pop();
                    if (stack.isEmpty()) {
                        // This will ignore all other tokens, we can fix that later
                        // TODO fix that later
                        return Optional.of(head);
                    }

                    // If the stack is not empty, add the list that just ended to it
                    stack.peek().items.add(head);
                    break;
                case IDENTIFIER:
                    DataToken identifier = (DataToken) token;
                    Node identNode = new IdentifierNode(identifier.contents);

                    if (stack.isEmpty()) {
                        return Optional.of(identNode);
                    }

                    stack.peek().items.add(identNode);
                    break;
                case INT_LITERAL:
                    DataToken integer = (DataToken) token;
                    Node intNode = new IntegerLiteralNode(Integer.parseInt(integer.contents));

                    if (stack.isEmpty()) {
                        return Optional.of(intNode);
                    }

                    stack.peek().items.add(intNode);
                    break;
            }
        }

        return Optional.empty();
    }
}
