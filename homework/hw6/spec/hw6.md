~ number: 6
~ title: Asymptotics and BSTs

For this installment of Awesome Asymptotics, you'll create **BSTMap**, a BST-based implementation of the Map61B interface from HW5. 

After you've completed your implementation, you'll compare the performance of your implementation to the ULLMap from HW5 as well as the built-in Java TreeMap class (which also uses a BST). Then we'll close out with some practice problems with asymptotics.

1: BSTMap - Map61B Round Two!
=======

Create a class **BSTMap** that implements the **Map61B** interface using a BST (Binary Search Tree) as its core data structure. You must do this in a file named `BSTMap.java`. Just as in HW5, your implementation is required to implement all of the methods given in **Map61B** *except* for `remove` and `keyset`.

In your implementation you should assume that generic keys K in `BSTMap<K,V>` extend [Comparable<K>](http://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html).

The following resources might prove useful:

* BST code from pages 109 and 111 of [Data Structures Into Java](http://www-inst.eecs.berkeley.edu/~cs61b/fa14/book2/data-structures.pdf), from our course references page.
* Pages 396-405 from our optional Algorithms textbook.
* BST code from [our optional textbook](http://algs4.cs.princeton.edu/32bst/BST.java.html). 
* `ULLMap.java` (provided), a working unordered linked list based **Map61B** implementation.

Your BSTMap should also add an additional method `printInOrder()` (not given in the **Map61B** interface) that prints out your **BSTMap** in order of increasing Key. You will this helpful for testing your implementation!

2: So... How Fast Is It?
======

There are two interactive speed tests provided in `InsertRandomSpeedTest.java` and `InsertInOrderSpeedTest.java`. Do not attempt to run these tests before you've completed **BSTMap**. Once you're ready, compile with `javac oneOfTheTests.java` and run with `java oneOfTheTests`.

The `InsertRandomSpeedTest` class performs tests on element-insertion speed of your **BSTMap**, **ULLMap** (provided), and Java's built-in **TreeMap**. It works by asking the user for an input size `N`, then generates `N` Strings of length 10 and inserts them into the maps as <String,Integer> pairs.

Try it out and see how your data structure scales with `N` compared to the naive and industrial-strength implementations. Record your results in a file named `speedTestResults.txt`. There is no standard format required for your results, and there is no required number of data points.

Now try running `InsertInOrderSpeedTest`, which behaves similarly to `InsertRandomSpeedTest`, except this time the Strings in <String, Integer> key-value pairs are inserted in [lexicographically-increasing order](http://en.wikipedia.org/wiki/Lexicographical_order). If you observed anything interesting (hopefully you did), then you should explain this interesting thing along with your results in `speedTestResults.txt`.

Extra: Modify the testing classes so that they also compare the performance of your class to the built-in HashMap class, which uses an alternate technique for implementing maps (called hashing) that we'll develop next Wednesday.

3 Asymptotics: Put On Your Thinking Cap
=====
Given `B`, a **BSTMap** with `N` key-value pairs, and `(K, V)`, a random key-value pair, answer the following 8 questions in a file named `trueFalseAnswers.txt` in the following format: the question number, followed by a space, followed by your one-letter answer (T/F), followed by a space and your justification. Note that question 8 is not true/false and the answer should be of the form `O(...)`.

Formatting example of `trueFalseAnswers.txt` (not the right answers): 

        1 T easy problem 
        ...
        7 O(...) By my advanced analysis...
        8 F because i'm a genius. 
        ...
        171 T horse
        172 F horse

Unless otherwise stated, "big-Oh" bounds (e.g. `O(N)`) and "big-Theta" bounds (e.g. &Theta;(`N`)) refer to the **number of comparisons** in the given method call(s). 

Questions:

1. `B.put(K, V)` &isin; O(log(`N`)).
2. `B.put(K, V)` &isin; &Theta;(log(`N`)).
3. `B.put(K, V)` &isin; &Theta;(`N`).
4. `B.put(K, V)` &isin; O(`N`).
5. `B.put(K, V)` &isin; O(`N`<sup>2</sup>).
6. On average, the total number of comparisons to complete N random calls to `B.put(K, V)` followed by `B.containsKey(K)` &isin; &Tilde;2(ln(`N`))

        Note: We write g(N)~f(N) to represent that ~g(N)/f(N) -> 1 as N gets large.

7. For key `C` != `K`, running both `B.containsKey(K)` and `B.containsKey(C)` &isin; &Omega;(log(`N`)).
8. Let **BSTMap**s be comprised of a `root` Node (Key, Value pair) and two **BSTMap** subtrees called `left` and `right`. Further, assume the method `numberOfNodes(Node p)` returns the number of nodes of a **BSTMap** rooted in `p` and has a running time of &Theta;`(n)`, where `n` is the number of Nodes in the **BSTMap** rooted in `p`. What is the running time (in big O notation) of `mystery(B, z)` for some positive integer `z`? Give the tightest bound you can. Your answer should not contain any unnecessary multiplicative constants or additive factors.

        public Key mystery(BSTMap b, int z) {
            if (z > numberOfNodes(b.root)) 
                return null;
            if (numberOfNodes(b.left) == z-1)
                return b.root.key;
            else if (numberOfNodes(b.left) > z)
                return mystery(b.left, z); 
            else 
                return mystery(b.right, z-numberOfNodes(b.left) - 1);
        }

4:  Mehrheit Für Die Mitleid
======

Note: only do this part if you've finished the rest of the homework. Don't waste your time trying to figure out `remove()` unless you're in it for the pride, in which case go crazy Simba.

Implement the methods `remove(K key)`, `remove(K key, V value)`, and `keySet()` in your **BSTMap** class.

For `remove`, you should return null if the argument key does not exist in the **BSTMap**. 
Otherwise, delete the key-value pair (key,value) and return value.

<!--5: Enjoy your Spring Break
=====
You're done.

Hopefully that was fun :)-->