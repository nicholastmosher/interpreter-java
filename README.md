# Interpreter-java

This is a toy interpreter written for a seminar at CSH.

All that's required to run it is to have the JDK installed!

## Running with Gradle

```java
$ ./gradlew install
$ build/install/interpreter-java/bin/interpreter-java
```

This will launch the Read-Eval-Print Loop (REPL) for this
tiny Lispy language.

## Expressions

This language is very minimal, every expression evaluates
to an integer. The only functions available are `add` and
`mult`. Each of those functions accepts 2 or more arguments,
all of which must evaluate to integers. Here are some
examples of valid expressions:

```
prompt> 5
=> 5
prompt> (add 2 3)
=> 5
prompt> (mult 2 3)
=> 6
prompt> (add (mult 4 5) 2)
=> 22
prompt> (add 1 2 3 (mult 2 2))
=> 10
```

To exit, press `^D` or `^C`.
