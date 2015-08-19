~ number: 2
~ title: Bit Operations, Pointers, Linked Lists

# Navigation #

- [Your Tasks](#tasks)
    - [Task 1: Write JUnit Tests](#junit)
    - [Task 2: Adding with Bit Operations](#add)
    - [Task 3: Multiplying with Bit Operations](#multiply)
    - [Task 4X: Basic User Input (Optional)](#task4x)
    - [Task 4B: Using the Interface](#task4b)
    - [Task 5: Calculator History](#history)
    - [Task 6: clear and undo](#clear)
    - [Task 7: Advanced Calculator History Commands](#adv)
    - [More for Masters](#more)
- [Submission Instructions](#submit)


<a name="tasks"></a>

# Your Tasks #

In this assignment, we will implement a basic calculator using bitwise operators.  This calculator will maintain a history of calculations which we can operate on as well.  You will find some skeleton code available to you, and you are free to add any additional methods and fields you think will be helpful.

<a name="junit"></a>

## Task 1: Write JUnit Tests ##

Unfortunately, the staff procrastinated on creating this homework assignment and wrote the calculator code in a hurry!  In their rush, your TAs wrote a pretty buggy adder.

Your task is to write a minimum of 3 JUnit tests to test the addition code in the staff calculator in the included CalculatorTest.java skeleton. At least one of the tests must fail on the StaffCalculator, i.e. should catch a bug in the staff implementation of the adder. Of course, one silly way to induce a test failure is to write a broken test (3 + 2 = -187921). Don't do this! Instead, you'll need to come up with powerful enough simple tests that are able to find the bug in `StaffCalculator`.

Note that you have no idea what the implementation looks like. This is a good thing! We're treating the `add` method of StaffCalculator as a mysterious black box that adds numbers. There's no need to know what is inside to write good tests.

The method signature for addition is:

    public int add(int x, int y)

Throughout this course, we encourage you to write tests for every method you write, ideally even before you start the implementation of that method.  In later assignments, you'll be free to do less rigorous testing, but for now, we want to show that it's not so bad.

By completing this task, you've already written tests that you can use on your calculator implementation (coming later in this assignment). To test your own implementation, read the comments in the `setUp()` method of `CalculatorTest.java`.

<a name="add"></a>

## Task 2: Adding with Bit Operations ##

In this part of the assignment, you will implement the addition functionality of a calculator.  Write the `add` function, which takes in two integers and computes their sum.  You are *not* allowed to use any of the standard math operators, i.e. `+`, `-`, `/`, `*`, `%`, `++`, `--`, `+=`, `-=`, `*=`, `/=`, `%=`, or any equivalent method calls.  Instead, use the bitwise operators `&`, `|`, `^`, `~`, `<<`, `>>`, `>>>`, etc.  The one exception: if you choose to use a loop, you may use *one* standard math operator to increment the loop counter.

This may sound like a trivial task at first, but **you'll find that it is actually a rather challenging puzzle**. We encourage you to discuss strategies with other students. Most (all?) sensible solutions require some sort of loop. You should strive for clarity over concision. You may find it useful to create a helper method (but this is not required, and the most elegant solutions do not call for one).

Hint 1: Try working out a small example with a truth table of the addition on paper first before translating it into code.

Hint 2: Don't bother thinking about negative numbers at first. Chances are that any solution that works for positive numbers will automatically work for negative ones.

Your function should be located in the Calculator class with the following method signature:

    public int add(int x, int y) {
        /** YOUR CODE HERE **/
    }

<a name="mult"></a>

## Task 3: Multiplying with Bit Operations ##

Similar to the previous part, you will implement the multiplication functionality of a calculator.  Write the multiply function, which takes in two integers and computes their product.  As before, you may not use any of the standard math operators `+`, `-`, `/`, `*`, `%`, `++`, `--`, `+=`, `-=`, `*=`, `/=`, `%=`, or any equivalent method calls.  You are allowed to use the `add()` function you wrote in the previous part, and you should use at least one of the bitwise operators in a non-trivial manner.

Before you begin, write tests. In this case, the staff solution should pass all tests.

Your function should be located in the Calculator class with the following method signature:

    public int multiply(int x, int y) {
        /** YOUR CODE HERE **/
    }

Hint: Try to get multiplication to work where y is a power of 2 first.  Once you have that working, think about how you can get it to work with all integers.

<a name="task4x"></a>

## Task 4X: Basic User Input ##

**MORE FOR MASTERS**

This is an optional task for those of you who want a greater challenge. In this task, we'll learn how to process input from the user. We will not provide official support for this task in OH or Piazza, though if things aren't too busy we'll be happy to answer questions. If you are skipping this challenge, [click here](#task4b). There is no shame in skipping this! 

Create a new file `CalculatorUI.java`. Create a user interface for your `Calculator` by implementing the `main` method in `CalculatorUI` shown below:

    public static void main(String[] args) {
        while (true) {
            /** YOUR CODE HERE **/
        }
    }

This method should prompt the user with a `>` symbol to enter a command and respond as follows:

Example run:

    > 1 + 2
    3
    > 3 * 4
    12

Your calculator only needs to be able to support expressions containing exactly one arithmetic operator. 

**
*a* + *b*
**

Adds a and b together.  Note that this expression is entered in by the user as a String.  You will need to find some way of extracting out the integer corresponding to a and b.  For simplicity, you may assume that there will always be exactly one space between a, +, and b.  You may also assume that there will be no space between a negative sign and the corresponding integer. 

For example:

    1 + - 2  // Invalid expression.  (Space between - and 2)
    1 + -2   // Valid expression.
    1 + --2  // Invalid expression.  (We will not allow double negative signs)

**
*a* \* *b*
**

Like above, but multiplies *a* and *b* together.

**quit**

Exits the calculator.  This means that you should not prompt the user for more input.

### StdIn ###

To get input from the keyboard, you should use `StdIn` from the Princeton standard library. It is similar to the `In` class that you used in HW1, but takes input from your operation system's 'standard input' instead of from a file. Think of standard input as a hose running from your operating system into your program. The OS can deliver information from any source via standard input. By default, this source is from the keyboard.

Since this is an extra challenge, we will not explain `StdIn` in this document. See the [StdIn JavaDocs](http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdIn.html) and [StdIn documentation](http://introcs.cs.princeton.edu/java/15inout/) for written directions, or this [StdIn demo](https://www.youtube.com/watch?v=bstdXGYJCTo) for a video tutorial. You might also find the examples in the [examples directory](../) useful.

It is OK if your code crashes on invalid expressions. We will discuss handling errors later in the course.

<a id="task4b"></a>

## Task 4B: Using the Interface ##

Compile and run `StaffCalculatorUI` (or `CalculatorUI` if you created your own). Try typing expressions that involve only a single `+` or `*` sign and you should see results like the following. This UI is very limited and cannot handle longer expressions or real numbers.

    > 1 + 2
    3
    > 3 * -4
    -12

<a name="history"></a>

## Task 5: Calculator History ##

We'll now add the ability to print the history of equations that have been entered, along with the result, with the most recent equation first. This command will print one equation (along with its result) on each line.  Note that only equations should be stored; do not store other commands such as "sum", "history", etc.

Here is an example run:

    > 1 + 2
    3
    > 3 * 4
    12
    > history 1
    3 * 4 = 12
    > dump
    3 * 4 = 12
    1 + 2 = 3

The dump command prints all entires in the history. The history command prints the n most recent equations. Tasks 5A-5C provide a recommended sequence for implementing this functionality.

### Task 5A: Implementing the History Data Structure ###

To support the command above, we need to store: (1) a history of equations that were run, and (2) their corresponding results.  You may assume that (1) will be an equation represented as a String, and (2) will be an integer corresponding to the result.

The data structure we will use is the EquationList class you will find under the list directory.  This is similar to the linked list that was covered in lecture, and the diagram below shows an example of its use.  **You are not allowed to use any of Java's built-in list structures.**

    +-------------------+    +-------------------+
    | EquationList      |    | EquationList      |
    +-------------------+    +-------------------+
    | equation: "1 + 2" |    | equation: "3 * 4" |
    | result:   3       |    | result:   12      |
    | next:     --------+--->| next:     null    |
    +-------------------+    +-------------------+

Write the `saveEquation` method in `Calculator`. You will need to add at least one member variable to the `Calculator` class. You may create a constructor, but you are not required to do so.

    public void saveEquation(String equation, int result) {
        /** YOUR CODE HERE **/
    }

### Task 5B: Adding printHistory Helper Methods ###

Fill in the `printHistory(int n)` and `printAllHistory()` commands.

Hint: One of these methods is a special case of the other.  Can you write that method without copying-and-pasting your code?

    public void printHistory(int n) {
        /** YOUR CODE HERE **/
    }

    public void printAllHistory() {
        /** YOUR CODE HERE **/
    }

Optionally, you may write tests to ensure your `saveEquation` and `printHistory` methods work. Alternately, you may proceed to task 5C and use that for testing instead. Generally, I do not recommend performing system-level tests of basic functionality, but since this system is so simple, it is not too unreasonable in this case.

### Task 5X: Adding the history and dump Commands ###

If you're creating `CalculatorUI`, modify your main method so that it properly handles the `history` and `dump` commands.  Otherwise, you may want to look inside `StaffCalculatorUI` to see how and where the methods you wrote in this task are being used.

<a name="clear"></a>

## Task 6: clear and undo ##

We will next add an `undo` command that will allow us to remove the most recent entry from our history, and a clear command that will allow us to clear our entire history. 

**clear**

Clears all entries in history.  Subsequent runs of history should not print anything to the terminal, since every entry in history has been removed.

**undo**

Removes the most recently entered equation from history.  All other entries in history should remain unchanged.

For example:

    > 1 + 2
    3
    > 3 * 4
    12
    > 8 * -2
    -16
    > dump
    8 * -2 = -16
    3 * 4 = 12
    1 + 2 = 3
    > undo
    > dump
    3 * 4 = 12
    1 + 2 = 3
    > clear
    > dump
    >     

Your code that performs the `undo` and `clear` operations should be added to `undoEquation()` and `clearHistory()` respectively.  

## Task 6X: CalculatorUI ##

If you created your own CalculatorUI, update your main method to support these new commands. 

<a name="adv"></a>

## Task 7: Advanced Calculator History Commands ##

Our market research team has discovered that our Calculator users are clamoring for a bizarre feature: for this final part of the assignment, we will implement commands sum and product that will compute the sum and product respectively of all equations in the history. 

**sum**

Computes a sum over the result of each equation in history.  If there are no equations, we will return a sum of 0.

For example, if we again have `1 + 2` and `3 * 4` in our history, you should output:  (this is computed as (1 + 2) + (3 * 4))

    > sum
    15

**product**

Computes a product over the result of each equation in history.  If there are no equations, we will return a product of 1.  Using the same example as before, you should output:  (computed as (1 + 2) * (3 * 4))

    > product
    36

The code that implements these keywords should be in `cumulativeSum()` and `cumulativeProduct()` respectively. 

## Task 7X: Calculator UI ##

If you created your own CalculatorUI, update your main method to support these new commands. 

<a name="more"></a>

## More for Masters ##

If you still haven't had enough, here are some more things you might try:

 - "Bulletproof" your calculator.  Assume that the TA grading your program will attempt to run malformed input - for instance, `1 + ` (which is invalid because it's missing the second argument), or insert extra spaces to confuse the calculator - for instance, `   history`  or `1    + 2`.  Make sure that your calculator gracefully rejects the first case, and accurately handles the second case.  You may continue to assume that there will be no spaces separating a negative sign with the corresponding integer.
 - Support nested expressions.

<a name="submit"></a>

# Submission Instructions #

To recieve feedback from the autograder via email, push to the `ag/hw2` branch, following [the directions from lab 1](../../lab/lab1/lab1.html#autograder).

To submit, push to the `submit/hw2` branch, following [the directions from lab 1](../../lab/lab1/lab1.html#autograder).  Alternatively, refer to [this cheatsheet on Piazza](https://piazza.com/class/hx9h4t96ea8qv?cid=554).