package interpreter;

import interpreter.ast.Node;
import interpreter.ast.visitor.EvaluationVisitor;
import interpreter.ast.visitor.FormatVisitor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Interpreter {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        prompt();
        while (input.hasNextLine()) {

            String line = input.nextLine();
            Lexer lexer = new Lexer(line);
            List<Token> tokens = lexer.tokens();
            Optional<Node> maybeExpression = Parser.parse(tokens);

            if (!maybeExpression.isPresent()) {
                System.out.printf("Failed to parse tokens `%s`\n", tokens);
                continue;
            }

            Node expression = maybeExpression.get();
            Optional<Integer> maybeValue = expression.accept(new EvaluationVisitor());

            if (!maybeValue.isPresent()) {
                System.out.printf("Failed to evaluate expression '%s'\n", expression.accept(new FormatVisitor()));
                prompt();
                continue;
            }

            Integer value = maybeValue.get();
            System.out.printf("=> %d\n", value);
            prompt();
        }

        System.out.println("All done!");
    }

    public static void prompt() {
        System.out.print("prompt> ");
    }
}
