~ number: 8
~ title: Asymptotics

1: Experimentally determining running times
--------

In this lab you will experimentally determine the running time of various programs. For this lab, you may assume that the running time of all the programs is aN^b for some constants a and b, where N is the size of the input. This is not entirely accurate since there are often lower order terms as well, but it gives a good approximation of the running time, especially for larger inputs. You only need to explicitly find a in the first part of the lab. In later parts, it is enough to just find the big theta running time of the functions.

To begin, look at Stopwatch.java (from the Princeton standard library) and Asymptotics.java. Stopwatch.java is used for timing the programs. Asymptotics.java includes several functions that you will determine the running time of. Look at functions 1 through 3 in Asymptotics.java and try to figure out the big-theta running time of each function (recall that big-theta notation ignores constants and lower order terms). Put these in the lab8.txt file provided.

Now try to determine the running time of functions 1 through 3 experimentally. You can run functionx with an input of n by running `java Asymptotics x n` (where x and n are both positive integers). For each function, record the measurements for at least four different inputs in lab8.txt as a table. 

Now use these measurements to try to determine the running time of each function. Give the running time in tilde notation as ~aN^b where a and b are real numbers and put your results in lab8.txt. Do not round a and b in your answer.

When trying to determine the value of b, the following observation might be useful: if the running time is of the form aN^2 for some a, then every time N is doubled the running time increases by a factor of 4 because a(2N)^2 = 4(aN^2). In general, every time that N is multiplied by m, the running time will be multiplied by m^2. Similar results hold for other values of b.

You may not get perfect results, but you should be able to get a rough approximation of the running time. Don't worry too much about how accurate your value for a is.

Note: You will get better results if you choose inputs large enough that the function takes at least .1 seconds to run (and even better results for inputs that take at least 1 second to run). You may need to experiment around to find large enough inputs. For better results, it is also better to make several measurements for each input and take the average of these rather than taking a single measurement.

2: Match the running time
--------

For the remainder of this lab, we will adopt the common real-world convention of abusing O(f(N)) to mean Theta(f(N)), even though the [latter is a stronger statement](https://docs.google.com/presentation/d/1TiW9dvmk9396GhuSy788_1yEFu_LUPA8ytsm_NQpBMQ/pub?start=false&loop=false&delayms=3000&slide=id.g63937f84c_0251).

Now that you have had some practice figuring out the running times of a few functions, you will now get the chance to write some code of your own to try to match the given running times. Fill in the bodies of function4 and function5 in Asymptotics.java so that they meet the requirements listed below (for this exercise, don't worry about constant factors).

* function4: Fill in the body of function4 so that it has a worst case running time of O(n^(1/2)) and so that it returns true if and only if the argument it is given is a prime number. (Recall that a number is prime if it is only divisible by itself and one.)
* function5: Fill in the body of function5 so that it has a worst case running time of O(n) and a best case running time of O(log(n)).

Once again you can use `java Asymptotics x n` to run functionx with input n. Run function4 and function5 several times (at least 3 times for function4 and at least 4 times for function5) to confirm that they have the required running times. Add the results to lab8.txt.

3: Measuring the running times of removing from two data structures
--------

Now look at Asymptotics2.java. It contains functions that insert and then remove a specified number of random values between 0 and 1 from a specified data structure (in this case either an ArrayList or a HashSet). In particular, either a new ArrayList or a new HashSet is created and a specified number of random values between 0 and 1 are inserted. These same values are then removed from the data structure, but in a different (random) order then the order in which they were inserted. Record the time that this takes for at least 4 different input sizes and put the results in lab8.txt. Use these measurements to determine the average running time of inserting and removing a single value from each data structure. This time (unlike part 1) don't worry about constant factors (i.e. give the running times in O() notation instead of tilde notation).

If n is a positive integer (which represents the number of random values you want to be inserted and then removed) then you can run Asymptotics.java as follows:

* `java Asymptotics2 ArrayList n`
* `java Asymptotics2 HashSet n`

In java, an ArrayList is a data structure implemented using an array that is resized each time it gets full. A HashSet is an implementation of a data structure that we will cover soon in this class.

Warning: Since the values are random and they are removed in a random order, there may be more variation in the running time than in earlier questions. Also, HashSet in particular may seem to have some irregularities in the way that its running time increases. We'll learn in lecture next week how HashSet works, which will provide some insight as to why this irregularity occurs.

4: Determining the running time of a sorting algorithm
--------

Now look at Sorts.java. Both functions in Sorts.java sort a list by moving each element of the list (starting from the beginning of the list) backwards through the list until it is greater than or equal to the element to its right. (This sorting algorithm is called insertion sort. Later in this class we will learn a variety of other sorting algorithms.) The first function in Sorts.java sorts an ArrayList and the second one sorts a linked list. So both functions use the same algorithm, the only difference is the data structure.

Experimentally determine the running times of the two sorting functions in Sorts.java. Once again, you can ignore constant factors. Put your results in lab8.txt.

You can run Sorts.java as follows:

* `java Sorts sort1 n`: runs the ArrayList-based function on an ArrayList of n random values
* `java Sorts sort2 n`: runs the linked list-based function on a linked list of n random values

If you find a significant difference in the running times of these functions, try to figure out why this difference exists.

Note: Since the list being sorted is generated randomly each time, there may be more variance in individual running times than in previous parts of this lab. Thus you may need to take more measurements to accurately determine the running time (though you are only required to make four measurements).

5: Extra practice
--------

Consider the following piece of code:

    for (long i = 0; i < n; i++) {
        long j = i*i;
        while (j <= n) {
            j += 1;
        }
    }

Try to figure out the asymptotic running time of this piece of code (in terms of n) using O() notation. Add your initial guess to the file lab8.txt. Then experimentally test your answer using Stopwatch.java (you can use Asymptotics.java as a reference) and add your measurements to lab8.txt (make at least four measurements). Finally, using the results of your experiments, determine the asymptotic running time of the code and add it to lab8.txt. If this answer does not match your initial guess, try to figure out why.
