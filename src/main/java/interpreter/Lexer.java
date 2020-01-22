package interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static Pattern IDENT_PATTERN = Pattern.compile("^((?<ident>\\w+)).*");
    private static Pattern INT_PATTERN = Pattern.compile("^((?<int>\\d+)).*");

    private String input;
    private String slice;

    public Lexer(String input) {
        this.input = input;
        this.slice = input;
    }

    private Optional<Token> next() {
        slice = slice.trim(); // Skip whitespace
        if (slice.isEmpty()) return Optional.empty();

        if (slice.charAt(0) == '(') {
            slice = slice.substring(1);
            return Optional.of(new PlainToken(TokenType.LPAREN));
        }

        if (slice.charAt(0) == ')') {
            slice = slice.substring(1);
            return Optional.of(new PlainToken(TokenType.RPAREN));
        }

        Matcher intMatcher = INT_PATTERN.matcher(slice);
        if (intMatcher.matches()) {
            // Take the length of the whole match and slice it
            int length = intMatcher.group(1).length();
            slice = slice.substring(length);
            return Optional.of(new DataToken(TokenType.INT_LITERAL, intMatcher.group("int")));
        }

        Matcher identMatcher = IDENT_PATTERN.matcher(slice);
        if (identMatcher.matches()) {
            // Take the length of the whole match and slice it
            int length = identMatcher.group(1).length();
            slice = slice.substring(length);
            return Optional.of(new DataToken(TokenType.IDENTIFIER, identMatcher.group("ident")));
        }

        return Optional.empty();
    }

    public List<Token> tokens() {
        slice = input;
        List<Token> tokens = new ArrayList<>();

        Optional<Token> maybeToken;
        while ((maybeToken = this.next()).isPresent()) {
            tokens.add(maybeToken.get());
        }
        return tokens;
    }
}

enum TokenType {
    LPAREN,
    RPAREN,
    IDENTIFIER,
    INT_LITERAL;

    public String toString() {
        switch (this) {
            case LPAREN:
                return "(";
            case RPAREN:
                return ")";
            case IDENTIFIER:
                return "<ident>";
            case INT_LITERAL:
                return "<int>";
            default:
                throw new RuntimeException("Impossible case");
        }
    }
}

abstract class Token {
    public TokenType type;

    public abstract boolean equals(Object o);
    public abstract int hashCode();
    public abstract String toString();
}

class PlainToken extends Token {
    public PlainToken(TokenType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlainToken)) return false;
        PlainToken other = (PlainToken) o;
        return other.type.equals(this.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return type.toString();
    }
}

class DataToken extends Token {
    public String contents;
    public DataToken(TokenType type, String contents) {
        this.type = type;
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataToken dataToken = (DataToken) o;
        return contents.equals(dataToken.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }

    @Override
    public String toString() {
        switch (type) {
            case IDENTIFIER:
            case INT_LITERAL:
                return contents;
            default:
                return type.toString();
        }
    }
}
