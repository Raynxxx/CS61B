~ number: 5
~ title: Generics

Important note about this homework: One of the main difficulties of this homework is getting the syntax right and in particular having the right signatures for classes and methods. You should be able to compile all of your files (except for ArrayList61B.java) without getting any compiler warnings. If you get any warnings, you should try to figure out what is causing them and edit your code so that you no longer get any. Do not ignore the warnings: While you will still be able to compile and run your code with warnings, it probably means you are doing something wrong.

Also, for this homework don't worry about efficiency. In HW6 and HW7 (after proj1), we will improve the performance of our implementations.

1: Generic linked list
=======

Earlier in this course, we dealt with various uses for linked lists. However, there was a small problem: say we wanted to use a linked list of strings and a linked list of integers. Using what we had covered in the course when we first looked at linked lists, there were two solutions:

* Make two separate linked list classes: one for strings and one for integers.
* Make a single linked list class that stores `Objects` and then use type casting to convert things stored in the linked list into either strings or integers (as we saw in the [OldSchoolVessel](https://github.com/Berkeley-CS61B/lectureCode/blob/master/lec14/live1/OldSchoolVessel.java) and [OldSchoolVesselTest](https://github.com/Berkeley-CS61B/lectureCode/blob/master/lec14/live1/OldSchoolVesselTest.java) classes in lecture 14).

Both of these methods have disadvantages. The first method requires us to do twice as much work as we would like, while the second method involves a lot of unnecessary and potentially error-prone type casting (notice the ugly cast in OldSchoolVesselTest.java)

We recently covered a third method of dealing with this problem: generics. 

We have provided you with a class called ObjectList. This is an example of the second approach mentioned above. Your task is to modify this class so that it uses generics. Start by creating an empty file called GenericList.java. Then copy the contents of ObjectList.java into this file and edit it so that instead of being a list of `Object`s it uses a generic type. You should not need to change much code (mostly variable declarations and method signatures). Refer to [the code from lecture](https://github.com/Berkeley-CS61B/lectureCode/blob/master/lec14/live2/Vessel.java) if you get stuck.

The provided GenericListTest should pass when you complete your changes. If you do everything right, this first part of the HW will require surprisingly little work. This is intentional.

When you're done, remember that **you should not get any warnings when compiling your code**. If you are, try to figure out why you are getting warnings and fix any problems you find. If someone linked from Google tries to convince you to suppress the warnings, don't listen to their lies.

2. An Unordered Linked List Map (ULLMap)
======

Warning: This problem might be intimidating at first, but it mostly uses ideas we've seen before (with the exception that we have generic types). 

One of the most important ADTs that we'll use in 61B is the [map](http://en.wikipedia.org/wiki/Associative_array), also called a dictionary, associative array, or symbol table. While we've seen these in lecture, lab, and discussion (not to mention 61A), as a refresher, the basic idea is that a map stores pairs of correspondences between pairs of objects. These correspondences are called key-value pairs. 

The two most important operations of a map are given below:

* Get: given a key, return the corresponding value (i.e. the other half of the key-value pair) if one exists. Otherwise return null.
* Put: given a key and a value, store the key-value pair in the map. If the key is already present in the map, replace its preexisting value with the given value.

You have been provided with the Map61B interface, which we'll implement in 3 different ways over the next 3 homeworks. A Map61B is a simplified version of the Java Map interface, comprising the two operations listed above, as well as additional utility operations. In this part of the homework, we'll implement Map61B using a data structure we already know: the unordered linked list (unordered because we won't care about the order of the entries, unlike the SortedComparableList in [HW3](http://berkeley-cs61b.github.io/public_html/materials/hw/hw3/hw3.html) which always maintained order). 

You have been provided with starter code for the ULLMap class. To store the key-value pairs, this class uses a nested class called Entry. This nested class is essentially a linked list of key-value pairs. Note that this is not the best or most efficient way to implement a map, but it has the virtue of being simple. Later in this class we will learn more efficient ways to implement a map.

Some parts of the ULLMap class (and the Entry class) are missing and some are incorrect. Fill in the missing parts to agree with the comments and fix the parts that are incorrect (these will be marked by comments). Make sure that the ULLMap class is generic, i.e. the types of the keys and values should be generic types. Don't worry about the case where any keys or values are null. Methods that currently throw UnsupportedOperationExceptions are optional challenge problems, though you may implement them if you'd like additional practice.

Unlike our previous generic classes, maps have two formal type parameters. See the [pair example](http://docs.oracle.com/javase/tutorial/java/generics/types.html) in the official Java Generics documentation for an example. As always, you can use whatever string you want as your formal type parameter name (e.g. the Wheeler lecture's [Vessel.java](https://github.com/Berkeley-CS61B/lectureCode/blob/master/lec14/live1/Vessel.java) used Blarg).

Start by simply modifying ULLMap so that it compiles. Once you've done this, start filling in the methods.

Tips: 

Hint 1: Make sure that you use .equals instead of == throughout, otherwise you may get strange behavior. 

Hint 2: It's a bit more straightforward to do this one without a sentinel node (IMO).

Hint 3: If you're stuck, feel free to use [this somewhat similar data structure](http://algs4.cs.princeton.edu/31elementary/SequentialSearchST.java.html) as a guide.

Hint 4: Write tests. Some starter tests can be found in ULLMapTest.java.

Challenge Problems (not graded).
 
 - Complete the implementation of the clear() method.
 - Complete the implementation of the keySet() method.
 - Complete the implementation of the remove() methods.

3: Creating a Map Iterator
=====

Many important interfaces in the Java standard library use generics, for example Comparators, Comparables, Iterators, and Iterables. In HW3, we got to see Comparators and Comparables in action. Now we'll use the Iterator and Iterable interfaces.

As discussed in lecture, the Iterator interface has three methods:

* `public boolean hasNext()`: This method should return true if there are more elements left to return.
* `public E next()`: Returns the next element (where E is the formal type parameter of the iterator).
* `public void remove()`: Remove the most recently returned element. Often this method is not actually implemented.

As we saw in lecture 16, the purpose of an `Iterator` is to provide a way to look at each element of a class (typically some data structure) in turn. Typically, an `Iterator` is implemented as a nested class. 

Since the names of these nested classes vary, it is common to provide a method called `iterator()` that returns an instance of this nested class. To make it clear when an `iterator()` method is available, a class should implement the `Iterable` interface, which has one method:

* `public Iterator<E> iterator()`: This method returns an object of type `Iterator` that iterates over the elements of the data structure. Once again, E is the formal type parameter of the iterator.

Here, when we say that the object is of type `Iterator`, we mean that the object is something that implements the `Iterator` interface.

Modify the `ULLMap` class so that it implements the `Iterable` interface. To do this, add a new nested class to the `ULLMap` class and call it `ULLMapIter`. This nested class should implement the `Iterator` interface (except for the remove method: for this you can just throw a new `UnsupportedOperationException`). This iterator should iterate over the keys of the `ULLMap` (so each time `next()` is called it should return a key in the `ULLMap` until all of them have been returned). The `iterator` method of the `ULLMap` class should return a new instance of `ULLMapIter`.

Hint 1: Iterator and Iterable are both generic interfaces, so remember to specify what type of objects will be returned by the iterator. You'll also need to import java.lang.Iterator (otherwise you'll get a "cannot find symbol" error).

Hint 2: You will probably need to add a constructor method and an instance variable to the ULLMapIter class.

Hint 3: You may find the [reading for lecture 15](http://www.cs.berkeley.edu/~jrs/61b/lec/13) to be a useful reference, specifically the Iterator section.

Hint 4: To implement multiple interfaces, simply separate the names of the interfaces by commas. See [this link](http://tutorials.jenkov.com/java/interfaces.html#implementing-multiple-interfaces) for an example.


4: Inverting A Map (and Generic Methods)
======

Consider the following definition of the inverse of a map: if A is a map, then B is an inverse of A if:

* For any value v stored in A, v is a key in B.
* For any key-value pair (k, v) in B, (v, k) is a key-value pair in A.

Add a static generic method called invert to the ULLMap class. This method should take a map as its argument and return a valid inverse of this map (according to the definition of inverse above). Note that if mutliple keys in the original map correspond to the same value, then you are free to choose any of these keys to use as the value in the new map.

Example: Suppose A is the map with the following key-value pairs: (1, "a"), (4, "h"), (0, "i"), (18, "p"), (9, "h"), (10, "h"). Then the following are all valid inverses of A:

* ("a", 1), ("h", 4), ("i", 0), ("p", 18)
* ("a", 1), ("h", 9), ("i", 0), ("p", 18)
* ("a", 1), ("h", 10), ("i", 0), ("p", 18)

Don't worry about the case where any of the keys or values are null.

Hint 1: It is possible to use the fact that ULLMaps are Iterables to write a very short solution to this problem. Out solution is 8 lines including braces.

Hint 2: If the ULLMap passed in as the argument has K as the type of its keys, then the map that the invert method returns should have K as the type of its values.

Hint 3: The hardest part of writing this method will be just writing out the method signature. It will need to be a generic method. See [this stack overflow post](http://stackoverflow.com/questions/1759549/java-generics-multiple-generic-parameters) for some hints.

When you're done, try removing the formal type parameters from your method declaration, why do you get a compile error that looks something like: "non-static type variable Blarg cannot be referenced from a static context"?

5: ArrayList61B
======
In this part of the assignment, we'll implement our own ArrayList that *is-a* bona fide [Java List](http://docs.oracle.com/javase/7/docs/api/java/util/List.html).

In 61B, we have seen that one limitation of arrays in Java is that they have a fixed length, which makes them somewhat awkward to use for implementing the list ADT. In [lecture 8](https://docs.google.com/presentation/d/1K7G22wEGgqf3fIMP8IwL2YLZAM8hJlfhZ085Q9pe5Ic/edit?pli=1#slide=id.g625dc7e36_0216), we showed how we could use array doubling to create a seemingly somewhat hackish (but actually quite common) implementation of a list using an array.

Here's the strategy: We will use two private instance variables: an array that will store the elements of the list and an integer that will store the current number of elements in the list. When an element is added, we will try to add it to the array. If the array is full, we will create a new, larger array and copy the elements of the old array into this new one. Specifically, we will create a new array that is twice the size of the old one (later in this class we will formally analyze why doubling the size is a good choice).

Your task is to implement a resizing array based implementation of [AbstractList](http://docs.oracle.com/javase/7/docs/api/java/util/AbstractList.html), using the [AList](http://github.com/Berkeley-CS61B/lectureCode/tree/master/lec8/live2/AList.java) class developed in lecture as a guide. Begin by creating a new class called ArrayList61B. This class should be generic, with one generic type parameter (which will be assumed below to be E, though of course you don't have to call it E if you'd like to use something else) and should extend the generic abstract class AbstractList (which you will need to import with `import java.util.AbstractList`). It should have the following methods:

* `public ArrayList61B(int initialCapacity)`: This constructor should initialize the size of the internal array to be `initialCapacity` and throw an IllegalArgumentException if `initialCapacity` is less than 1.
* `public ArrayList61B()`: This constructor should initialize the size of the internal array to be 1.
* `public E get(int i)`: This method should return the ith element in the list. This method should throw an IllegalArgumentException if i is less than 0 or greater than or equal to the number of elements in the list.
* `public boolean add(E item)`: This method should add `item` to the end of the list, resizing the internal array if necessary. This method should always return true (if you are curious about this, read the api for AbstractList).
* `public int size()`: This method should return the number of elements in the list. Note that this is not necessarily the same as the number of elements in the internal array.

A nice benefit of extending the AbstractList class is that you can now call any methods that AbstractList implements on instances of ArrayList61B, e.g. [addAll](http://docs.oracle.com/javase/7/docs/api/java/util/AbstractList.html#addAll(int,%20java.util.Collection)).

Note: Unfortunately, it is not really possible to do part 5 of this HW without getting some compiler warnings. This is because of the way that Java handles generics and arrays. In particular, Java does not let you instantiate an array with a generic type. So for this exercise, here is the best solution. If you want to declare and instantiate an array called A with size 10 that stores elements of type T where T is a generic type parameter, you can do so as follows:

`T[] A = (T[]) new Object[10];`

The compiler will yell at you, but for this exercise only, don't worry.

Aside: In general, it is best to avoid mixing generics and arrays in Java. If you want to use an array that stores a generic type, you will generally use the class `ArrayList` in the Java standard library (which is implemented in basically the same way that you implemented ArrayList61B in this exercise). For more information on why you can't instantiate an array with a generic type, google "type erasure" and "covariance." 

Hint: Don't forget that AbstractList itself is a generic class.

6: MaxArrayList61B (optional)
=====

Suppose we would now like to extend ArrayList61B so that it only adds elements if they are larger than all elements added so far. Once again we are faced with a problem: there are many different classes in Java that have a natural order, but not all Java classes have an order and even the ones that do are ordered in a variety of different ways. So how can we write code that will be able to handle any Java class that has a natural ordering? Once again, generics provide a solution: there is a Java interface called `Comparable`. Any Java class that implements this interface must provide a method called `compareTo` that indicates wherther the instance is less than, equal to or greater than another instance of the class.

Create a new class called MaxArrayList61B that extends ArrayList61B and that requires its generic type to implement the Comparable interface. Override the add method of ArrayList61B so that it only adds elements if they are greater (according to compareTo) than all elements that have been added to the list so far. Also, add should only return true if the list was actually changed (i.e. if the element was actually added) and false otherwise.

Make sure that you are able to compile MaxArrayList61B.java without getting any compiler warnings (the only file you are allowed to get compiler warnings for is ArrayList61B.java).

Hint 1: When you are declaring a generic class you can put restrictions on its generic type. For instance if you wanted a generic class called Adder whose generic type has to be numerical you could declare: 

    public class Adder<T extends Number>

Hint 2: Comparable is itself a generic type and you should keep this in mind in order to avoid compiler warnings.

Hint 3: It is possible to implement the add method in a very simple way that does not involve rewriting any code. If you find yourself writing a lot of code for this exercise, keep in mind that you can add instance variables to MaxArrayList61B and that you can access the add method of ArrayList61B by using the super keyword.

Resources
--------

* [__Java generics tutorial__](http://docs.oracle.com/javase/tutorial/java/generics/index.html): This provides a pretty good guide to using generics. In particular, you may find the section on bounded type parameters useful. Also, in case you are curious, you can learn more details of how generics work in Java (such as wildcard types and type erasure).

* __Documentation for Java classes__: [Comparator](http://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html), [Iterator](http://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html), [Iterable](http://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html), [AbstractList](http://docs.oracle.com/javase/7/docs/api/java/util/AbstractList.html)
