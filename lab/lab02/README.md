~ number: 2
~ title: Unit Testing with JUnit and IntLists

Navigation
----------------

- [Pre-lab](#pre-lab)
- [Introduction](#introduction)
- [An Arithmetic Bug](#arithmetic)
- [Testing Triangles](#triangles)
- [Application: IntLists](#intlists)
- [Recap](#recap)

<a name="pre-lab"></a> Pre-lab
-------------------------------

None this week, but make sure that you finish Lab 1: Intro to git!

<a name="introduction"></a> Introduction
--------------------------------
In this lab, you will learn about Unit Testing, JUnit, Destructive vs NonDestructive methods, and IntLists.

Your job for this assignment is to fix the bug in `Arithmetic.java`, create unit tests for `Triangle.java`, and to create and test methods for `IntList.java`.

####What is JUnit?
[JUnit](http://junit.org/) is a Unit Testing Framework for Java.

####What is Unit Testing?
Unit Testing is the best way to rigorously test each method of your code and ultimately ensure that you have a working project.
The “Unit” part of Unit Testing comes from the idea that you can break your program down into units, or the smallest testable part of an application.
Therefore, Unit Testing enforces good code structure (each method should only do “One Thing”), and allows you to consider all of the edge cases for each method and test for them individually.  In this class, you will be using JUnit to create and run tests on your code to ensure its correctness.  And when JUnit tests fail, you have an excellent starting point for debugging.

####JUnit Syntax
JUnit provides some special functionality on top of what you can normally do in java.

Ultimately, JUnit provides a testing framework, so you can test your code without stressing about details (formatting and printing of error messages, counting failures and succsses, etc.).

So what is different about a JUnit java file?  Go ahead and navigate to the Arithmetic directory and open `ArithmeticTest.java`.
The first thing you'll notice are the imports at the top.  These imports are what give you access to the JUnit methods and functionality that you'll need to run JUnit tests.
Next, you'll see that there are two methods in `ArithmeticTest.java`: `testProduct` and `testSum`
These methods follow this format:

```
@Test·
public void testMethod() {
    assertEquals(<expected>, <actual>);
}
```

`assertEquals` is a very common method used in JUnit tests. It tests whether a variable's actual value is equivalent to its expected value. 

Basically, each method that is a test will be preceded by a `@Test` annotation, and can have one or more `assertEquals` or `assertTrue` methods (provided by the JUnit).

The main method in a JUnit testing file will actually run all of the tests.  You don't need to memorize this syntax, just know that what it does is run all of the methods which are preceded by @Test. The follow line will run all tests in `SomeTest` when put in the `main` method of your test program. 

```
jh61b.junit.textui.runClasses(SomeTest.class);
```

In the case of `ArithmeticTest.java`, the name of the .class file is ArithmeticTest and we would have something like:

```
/* Run the unit tests in this file. */
public static void main(String... args) {
    System.exit(jh61b.junit.textui.runClasses(ArithmeticTest.class));
}
```

In order to execute the tests, compile and run the tests using the following commands in the command line:

```
javac *.java
java ArithmeticTest
```

This will run all of the tests and tell you if they passed/failed. If you are using your own computer, you will need to complete lab1c before compilation will work.

<a name="arithmetic"></a>  Arithmetic
--------------------------------
After running your tests, you should get a JUnit report. Notice it includes a failure! This tells you which test failed (`testSum` in `ArithmeticTest`), what the expected and actual values were, and on what line the failure occured. The output on your console should be something like this: 

```
java ArithmeticTest
Time: 0.005
There were 1 failures:

1) testSum(ArithmeticTest)
    expected:<11> but was:<30>
    at ArithmeticTest.testSum:25 (ArithmeticTest.java)

Ran 2 tests. 1 failed.
make: *** [check] Error 1
```


Notice that the other test ran without any failures.  Now, you should go investigate why testSum failed.  Look at `testSum` to understand what its testing for,
and then make the appropriate change in `Arithmetic.java`.

After fixing the bug, execute the compilation and execution commands again:

```
javac *.java
java ArithmeticTest
```

If you've fixed the bug, it should look like this:

```
java ArithmeticTest
Time: 0.005
Ran 2 tests. All passed.
```

One thing to notice here is that even though `testSum` included many `assert` statements, you only saw the first failure (even though you know that all of the asserts would have failed!)
This is because JUnit tests are short-circuiting – as soon as one of the asserts in a method fails, it will output the failure and move on to the next test.

<a name="triangles"></a> Testing Triangles
--------------------------------

Now move on to the Triangles directory.
In this directory we have a class, `Triangle.java`, which has a constructor that takes in 3 ints, and constructs a Triangle with the inputs as sides.
We have a method, `triangleType`, which will return one of the following:

 - "At least one length is less than 0!" if any of the sides is negative
 - "The lengths of the triangles do not form a valid triangle!" if for any side a, b, and c: (a + b) <= c
 - "Equilateral" if all the sides are the same length
 - "Isosceles" if any two sides are the same length (and wasn't already determined to be an "Equilateral" type)
 - "Scalene" otherwise.

We've already handled the edge cases for you (negative sides and invalid triangles).

It is your job to determine whether the triangle is equilateral, isosceles, or scalene.

HINT:  Look into Java's if, else if, and else
 statements. 
 
HINT:  Logical OR is `||`, and logical AND is `&&` in Java. For example, `(5 < 3) || (9 < 10)` would evalute to `true`, since one of these two things is true. 

Once you are fairly certain you are correctly handling all of the different cases for `triangleType`, let's move on to testing it!

Open `TriangleTest.java`, and observe the `testScalene` method.  Now, fill in `testEquilateral`, and additionally create tests for isosceles triangles, negative sides, and invalid sides.

Make sure to fill in the `main` method so that it runs all of the `TriangleTest` classes' tests!

Now that you've filled out the the tests, try running them:

First, run `javac *.java` in the Triangles directory.  Pay attention to any errors, and fix them before running your tests.

Make sure `java TriangleTest` passes before moving on.

<a name="intlists"></a> Application: IntLists
--------------------------------

###Introduction to IntLists

Now a real-CS61B application of JUnit tests: IntLists.

As discussed in Monday's lecture, an `IntList` is our CS61B implementation for a linked list of integers, which you will have already seen if you took 61A, 61AS, or other introductory programming course. 

As we saw in lecture, an IntList has a `head` and `tail` property.  The `head` is the first element of the list (an `int`).  The `tail` is the remaining elements of the list (another IntList!). 

See `IntList.java` in the IntList directory for a refresher. We've added a method called `list` that makes it easier to create IntLists. For example, to create an `IntList` containing the numbers 0, 1, 2, and 3, we could use the method as follows:

```
IntList myList = IntList.list(0, 1, 2, 3);
```

Which will create the IntList 0 -> 1 -> 2 -> 3 -> null

- `myList.head`  returns 0
- `myList.tail` returns 1 -> 2 -> 3 -> null
- `myList.tail.tail.tail` returns 3 -> null
- `myList.tail.tail.tail.tail` returns null

Observe that the `IntList.list` method makes it much easier to create IntLists compared to the naive approach we used in class:

```
IntList myList = new IntList(0, null);
myList.tail = new IntList(1, null);
myList.tail.tail = new IntList(2, null);
myList.tail.tail.tail = new IntList(3, null);
```

###Destructive vs. Nondestructive

Let's consider a method `dSquareList` that will destuctively square every item in a list (similar to what we saw in discussion):

```
IntList origL = Intlist.list(1, 2, 3)
dSquareList(origL);
// origL is now (1, 4, 9)
```

Where `dSquareList` is defined as follows:

```
public static void dSquareList(IntList L) {
    while (L != null) {
        L.head = L.head * L.head;
        L = L.tail;
    }
}
```

This is a classic example of a destructive method. It iterates through the list and squares each item, causing the values linked by `L` to change. In other words, after calling this method once on `L`, every element in `L` will be squared.

NOTE: The choice to return void rather than a pointer to `L` was an arbitrary decision. Different languages and libraries use different conventions (and people get quite grumpy about which is the "right" one).

Examining the code above, we see that the `origL` variable contains a reference to the created `IntList`. The value of this variable never changes. By contrast, the `L` variable in `dSquareList` moves around inside the method when the line `L = L.tail` is executed.

As we iterate through the method, `origL` always points the the beginning of the `IntList`, but `L` moves to the right until it reaches the null value at the end.

The reason that `dSquareList` is destructive is because we change the values of the **underlying `IntList`**.  As we go along, we square each value.

By the end of the method, `L` is null, and `origL` is still pointing at the beginning of the `IntList`, but every value in the `IntList` that `origL` points to is now squared.

If these ideas don't yet make total sense, ask a TA or lab assistant to draw a diagram of the code execution. Pointers and IntLists might seem confusing at first, but it's important that you understand these concepts!

Now, look at at `squareListIterative` and `squareListRecursive`. These methods are both *non-destructive*. That is, the underlying IntList passed into the methods does **not** get modified.

Look at the recursive version - try to reason why this is non-destructive. This code is just like what you saw in discussion. If you don't understand this yet, you should make sure you do before proceeding. 

Now look at `squarelistIterative`. The iterative version of a non-destructive method is often quite a bit messier than the recursive version, since it takes some careful pointer action to create a new `IntList`, build it up, and return it. Try to understand what this code is doing, but don't stress if it doesn't all make sense right away.

Finally, look at the test method `testDSquareList` in `IntListTest.java`.  Notice that this test checks whether or not `dSquareList` is destructive.

You're done reading code for now. Phew! Create a test for `squareListRecursive` that checks that it is **not** destructive. You will probably also want to test whether or not `squareListRecursive` is correct.

### Implementing Destructive vs NonDestructive Methods

Finally, let's get our hands dirty by writing our own methods: `dcatenate` and `catenate`.

Both methods take in two IntLists, and concatenatesthem together. So `catenate(IntList A, IntList B)` and `dcatenate(IntList A, IntList B)` both result in an `IntList` which contains the elements of `A` followed by the elements of `B`.

The only difference between these two methods is that `dcatenate` modifies the original `IntList` `A` (so it's destructive) and `catenate` does not.

In real world development, it is common to write tests before writing implementations. We recommend that you try out this approach for this course, though if you end up hating the idea, you're welcome to abandon it. Writing tests first ensures that you really understand what you're trying to do, since you can only write tests once you full understand a problem.

To complete the lab:

 - Start by adding `testDCatenate` and `testCatenate` methods, which test for both correctness and whether or not the methods are destructive.
 - Compile and run your tests. Since dcatenate and catenate both return null, your tests should fail. This is a good thing!
 - Now fill in dcatenate or catenate, and run them against your tests. Revise your code until it passes your tests.
 - Repeat for the method you haven't yet completed.

IntList problems can be tricky to think about, and there are always several approaches which can work.  Don't be afraid to pull out pen and paper or a whiteboard and work out some examples! 

Feel free to use either recursion or iteration. For extra practice, try both!

It's also often useful to think about base cases (when `A` is null, for example) - this works especially well for building up a recursive solution.

<a name="recap"></a> Recap
-------------------------------
In this lab, we went over:

- Unit testing in general
- JUnit syntax and details
- Writing JUnit tests 
- Non-destructive vs. Destructive methods 
- IntLists and pointers
- Writing IntList methods destrutively, non-destructively, recursively, and iteratively 
- Using JUnit to test for correctness and destructiveness


