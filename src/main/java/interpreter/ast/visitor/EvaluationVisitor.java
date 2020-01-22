package interpreter.ast.visitor;

import interpreter.ast.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EvaluationVisitor implements Visitor<Optional<Integer>> {
    @Override
    public Optional<Integer> visitIdentifier(IdentifierNode identifier) {
        System.out.println("Error, cannot evaluate an identifier");
        return Optional.empty();
    }

    @Override
    public Optional<Integer> visitInteger(IntegerLiteralNode integer) {
        return Optional.of(integer.integer);
    }

    @Override
    public Optional<Integer> visitList(ListNode listNode) {
        if (listNode.items.isEmpty()) {
            System.out.println("Error, cannot evaluate an empty list");
            return Optional.empty();
        }

        Node first = listNode.items.get(0);
        if (!(first instanceof IdentifierNode)) {
            System.out.println("Error, cannot evaluate a list whose first item is not an identifier");
            return Optional.empty();
        }

        IdentifierNode ident = (IdentifierNode) first;
        List<Node> args = listNode.items.subList(1, listNode.items.size());

        switch (ident.ident) {
            case "add":
                if (args.size() < 2) {
                    System.out.println("Error, add function requires at least 2 arguments");
                    return Optional.empty();
                }

                List<Integer> addArgs = new ArrayList<>();
                for (Node argument : args) {
                    Optional<Integer> evaluatedArgument = argument.accept(this);
                    if (!evaluatedArgument.isPresent()) {
                        return Optional.empty();
                    }
                    addArgs.add(evaluatedArgument.get());
                }
                return Optional.of(Primitives.add(addArgs));

            case "mult":
                if (args.size() < 2) {
                    System.out.println("Error, mult function requires at least 2 arguments");
                    return Optional.empty();
                }

                List<Integer> multArgs = new ArrayList<>();
                for (Node argument : args) {
                    Optional<Integer> evaluatedArgument = argument.accept(this);
                    if (!evaluatedArgument.isPresent()) {
                        return Optional.empty();
                    }
                    multArgs.add(evaluatedArgument.get());
                }
                return Optional.of(Primitives.mult(multArgs));

            default:
                System.out.printf("Undefined function '%s'\n", ident.ident);
                return Optional.empty();
        }
    }
}

class Primitives {
    public static int add(List<Integer> ints) {
        int result = 0;
        for (Integer i : ints) {
            result += i;
        }
        return result;
    }

    public static int mult(List<Integer> ints) {
        int result = 1;
        for (Integer i : ints) {
            result *= i;
        }
        return result;
    }
}
