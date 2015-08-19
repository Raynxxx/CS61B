~ number: 3
~ title: Object-Oriented Programming

This homework is intended to reinforce various OOP concepts and give you more practice with linked lists before the midterm. Parts 2, 3, and 4 will cover material up through Friday's (Feb 13th) lecture. 

The solutions are available at [this link](../sol/hw3sol.zip). The password on the zip file is "skeleton master". You can literally submit exactly the files in this hw if you wish, though I don't recommend it since you won't learn anything.

Part 1: Basic inheritance exercises
----

In this part, think about the following:

- Overriding methods and fields
- Casting
- Dynamic vs. static type

Consider the following two classes `Animal` and `Fox`. 

    public class Animal {
        String name;
        public Animal() {
            name = "SuperAnimal";
            System.out.println(name);
        }
        public void speak() {
            System.out.println("I'm an animal");
        }
    }
    
    public class Fox extends Animal{
        String name;
        public Fox() {
        }
        public Fox(String s) {
            name = s;
            System.out.println(name);
        }
        public void speak() {
            System.out.println("Ringding");
        }
    }

We see that `Fox` is a subclass of `Animal` with a shadowed field (`name`) and overridden method (`speak`).

Note that shadowed fields and overridden static methods are based on the _static_ type of the object (Q3), which is different from overridden instance methods.
The questions in this section will explore this in more detail.

__Answer the following questions in `answers.txt`.__ Each answer should be one letter or word. Make sure you should understand these questions thoroughly. 

#### Q1:
Suppose we run the following code:

    Animal a0 = new Animal();   // Line 1
    Fox f0 = new Fox();         // Line 2
    a0.speak();                 // Line 3
    f0.speak();                 // Line 4
    ((Animal) f0).speak();      // Line 5
    ((Fox) a0).speak();         // Line 6

(a) What does the second line print? If it doesn't print anything, write None.

(b) Will the fifth line cause an error? (Yes or No)

(c) What does the fifth line print? If it doesn't print anything or causes an error, write None.

(d) Will the sixth line cause an error? (Yes or No)

(e) What does the sixth line print? If it doesn't print anything or causes an error, write None.

(f) Selection of overridden instance (non-static) methods is based on the ____ type. (static or dynamic)

#### Q2:
Suppose we run the following code:

    Animal a1 = new Fox();
    a1.speak();
    ((Animal) a1).speak();

(a) Will the following line cause a (A) compile-time error or (B) run-time error. (A or B)
    
    Fox f1 = new Animal();

(b) What does the second line print?

(c) What does the third line print?

(d) The third line uses the ____ type to select which speak() method to run. (static or dynamic)

#### Q3:
Suppose we run the following code:

    Animal a2 = new Animal();
    System.out.println(a2.name);
    Animal a3 = new Fox("SuperFox");
    System.out.println(a3.name);
    System.out.println(((Animal) a3).name);

(a) Calling the Fox() constructor with one argument implicitly calls super(). (True or False)

(b) What does the fourth line print?

(c) What does the fifth line print?

(d) Selection of shadowed fields is based on the ____ type. (static or dynamic)

Part 2: Recursive linked data structure
----

You have been provided with skeleton code for the class `SortedComparableList`, which stores `Comparables` in _ascending_ order as a recursive linked list. Each list contains a variable for `head`, which is a `Comparable`, and `tail`, which is the rest of the list (which is also a `SortedComparableList`).

__First__, fill in the two constructors.

__Next__, fill in the three methods `insert(Comparable c)`, `get(int i)`, and `extend(SortedComparableList that)`.

__Then__, fill in the methods `subTail(SortedComparableList L, int start)`, `sublist(SortedComparableList L, int start, int len)`, `expungeTail(SortedComparableList L, int len)`, `squish()`, and `twin()`.

#### Method descriptions:

- `insert(Comparable c)` inserts the passed in Comparable into the correct location in the list. Remember that the list is sorted in ascending order.

- `get(int i)` returns the i-th element of the list. The first element, which is in location 0, is the 0th element. You can assume that i takes on the values [0, length of list - 1].

- `extend(SortedComparableList that)` adds every item in THAT to this list. The added elements should be placed in their correct location based on ascending order.

- `subTail(SortedComparableList L, int start)` returns a SortedComparableList consisting of the elements of L starting from position start, and going all the way to the end. The head of the list L is the 0th element of the list. This method cannot be destructive; it should NOT modify L.

- `sublist(SortedComparableList L, int start, int len)` returns the sublist consisting of LEN items from list L, beginning with item START (where the first item is 0). Does not modify the original list elements. You can assume that start and len are always >= 0. 

- `expungeTail(SortedComparableList L, int len)` removes items from L at position len+1 or later.

- `squish()` removes consecutive duplicates in the list so that only one consecutive copy remains. You can assume the list is already in sorted order when this method is called. More details can be found in the java file.

- `twin()` duplicates each Comparable so that for every original Comparable, there will end up being two consecutive Comparables. You can assume the list is in sorted order when this method is called. More details can be found in the java file.

#### Helpful notes:

- Consider cases where you are inserting an element into the beginning of the list.

- All `Comparables` have an implementation for the `compareTo` method, which you should use when determining where to place an element. This methods returns a negative integer, zero, or a positive integer if this object is less than, equal to, or greater than the object passed in as an argument.

- A `toString()` method is provided for you if you wish to print the list.

Part 3: Function container class
----

You have been provided with the interface `IntUnaryFunction`, which has one method, `apply(int x)`, that takes in an int, applies some function to it, and returns the result as an int.

You will also find skeleton code for `ApplicableIntList`, which is similar to the linked list you implemented in Part 2. `ApplicableIntList` is also a recursive linked list, but stores only ints. It has a method `apply(IntUnaryFunction f)` that applies `f` to each of its elements.

__First__, fill in the two constructors.

__Then__, fill in the three methods `insert(int i)`, `get(int i)`, and `apply(IntUnaryFunction f)`. For `insert` and `get`, you can use modified versions of your implementations for Part 2.

#### Method descriptions:

- `insert(Comparable c)` inserts the passed in Comparable into the correct location in the list. Remember that the list is sorted in ascending order.

- `get(int i)` returns the i-th element of the list. The first element, which is in location 0, is the 0th element. You can assume that i takes on the values [0, length of list - 1].

- `apply(IntUnaryFunction f)` applies the function f to every item in this list. The items should still be in ascending order after applying the function.

#### Helpful notes:

- Consider cases where you are inserting an element into the beginning of the list.

- The list remains in ascending order after applying some function.

- A `toString()` method is provided for you if you wish to print the list.

Part 4: Comparators
----

In this section you will learn how to use Comparators. The java `Comparator` interface has two methods:

- `int compare(T o1, T o2)` returns a negative integer, zero, or positive integer if the first argument is less than, equal to, or greater than the second.

- `boolean equals(Object obj)` indicates whether some object is "equal to" this comparator.

Since an object already has an `equals` method, you will need to implement only the `compare` method for the purposes of this homework. You can learn more by looking up the java documentation for the Comparator interface.

In Homework 1, you created a `Planet` class to help simulate the motion of objects in a plane. Copy over `Planet.java` and any libraries you used into this homework's directory.

_1_: We will modify the `Planet` class to include a `private double radius`. You should change the constructor as well.

_2_: Add two more public methods to the `Planet` class that return the mass and radius so we can access this information from other classes.

_3_: Open up `MassComparator.java`. This class implements the `Comparator` interface so you will have to fill in the `compare` method, which compares the mass of the input planets. Since this method returns an int but the mass values are doubles, be sure to do any rounding after computing the difference.

_4_: Create a class `RadiusComparator` that behaves like `MassComparator` but bases calculations on a Planet's radius.

_5_: Open up `MaxPlanet.java`. This class has a single static method `maxPlanet` that takes in as arguments an array of Planets and a Comparator. It returns the Planet with the maximum value according to the passed in Comparator.

## Submission Instructions ##

To recieve feedback from the autograder via email, push to the `ag/hw3` branch, following [the directions from lab 1](../../lab/lab1/lab1.html#autograder).

To submit, push to the `submit/hw3` branch, following [the directions from lab 1](../../lab/lab1/lab1.html#autograder).  Alternatively, refer to [this cheatsheet on Piazza](https://piazza.com/class/hx9h4t96ea8qv?cid=554).
