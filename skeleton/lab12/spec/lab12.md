~ number: 12
~ title: The Sounds of Sorting (and Radix sort)

Navigation
-----------------

- [The Sounds of Sorting](#sort)
- [Radix Sort](#radix)

<a name="sort"></a> The Sounds of Sorting
--------------------------------
This week in lab you're going to be building our own inferior version of the sorting visualizer [that we saw in class](https://www.youtube.com/watch?v=kPRA0W1kECg). 

We've provided with you two files in the sorting directory for our visualization/audibilization.

#### SortSounds.java  

SortSounds is the top level class that coordinates the animations of our sorting algorithms. When it starts up, it creates an array of length N called `toSort`, and based on command line arguments, initiates one of 6 different sorts: quicksort, merge sort, selection sort, insertion sort, shell sort, and heap sort.

To run SortSounds, you can use the following syntax:

    java sorting/SortSounds [-n <number>] [-s <sortName>] [-o <order>]

Where number is the number of elements that you are sorting, sortName is the name of the sort you want to see, and order is if you want an in-order, reversed, or randomized array. The default behavior if you don't include any of the options will be to run a visualization with a randomized array with 32 items for all the possible sorts. Valid sortNames are 'quick', 'merge', 'selection', 'insertion', 'shell', and 'heap'. Valid orders are 'in-order', 'reversed', and 'randomized'.

The chosen sorting algorithm is expected to make calls to the following three animation methods, providing us with the ability to see and hear what the algorithm is doing as it works:

    static void drawRectangle(Color c, int i)
    static void clearRectangle(int i)
    static void play(int i)

As the docstrings for the methods say, drawRectangle will draw a rectangle of Color c, based on the item in the int array we are sorting at index i. For example, if `toSort[7] == 9`, then drawRectangle(7) will draw a rectangle at x-coordinate 7 with height 9. Similarly, clearRectangle will clear the rectangle based on the item at index i, completely removing it from the screen.

play will play a sound based on the int at index i. The tone ranges from 400Hz to 800Hz. You can change this by changing the LOW_TONE and HIGH_TONE fields in SortSounds, though I believe that anything higher than 1000Hz is unpleasant and anything below 200Hz cannot be heard with typical laptop speakers.

Let's start by running the following:

    javac -g sorting/*.java
    java sorting/SortSounds -s merge

You'll see the merge sort algorithm execute, displaying its state as it runs by calling the three animation methods provided. When it's done, you'll see that SortSounds finishes with a glorious rainbow flourish.

Now try running the following:

    javac -g sorting/*.java
    java sorting/SortSounds -s quick


You'll see that the data is drawn (as with merge sort), but then immediately gets overwritten by the rainbow flourish. The problem is that quicksort never reported back to the SortSounds method, so nothing was animated. Let's change this.

<!--
So let's see this in action. Take a look at the initialize and finish methods in SortSounds. Then, run SortSounds. Since we haven't implemented any of the logic behind, there won't be any fancy switching of rectangles, but you can at least get a feel of what drawRectangle and play are doing.

So from the lab12 directory, compile everything in the sorting directory by running

    javac -g sorting/*.java

Afterwards, let's run SortSounds!

    java sorting/SortSounds -s quick

This will run the visualization of quicksort. As you see, nothing really amazing. In fact, we don't even clear any rectangles and the rainbow finish at the end just happens on top of the original rectangles. So how can we start making this look like the video? Well, that's where the next file comes in. -->

#### Sort.java

<!--
In <tt>SortSounds.java</tt>, we call call one of the 6 sorting algorithms I've implemented in <tt>Sort.java</tt>. The problem is, I haven't put any of the visualization code...for most of the sorting algorithms. I've actually already included the visualization code for merge sort and shell sort. I did shell sort because we didn't really cover it and I did merge sort because of the fact that our drawRectangle() method relies on the elements in the internal toSort array, while this implementation of merge sort uses a temporary array. What happens is then for this visualization of merge sort, you'll instead see a red line sweep through the rectangles that represents the merging of parts of the array.

Take a look at the mergesort and shellsort methods to see how and when you use the drawRectangle, clearRectangle, and play methods. You can run these two sort visualizations with

    java sorting/SortSounds -s merge
    java sorting/SortSounds -s shell

Pretty cool right? Now it's your job to finish the rest of the modifications so you can visualize the rest of the sorting algorithms.-->

If you look inside Sort.java, you'll see implementations for quicksort, merge sort, selection sort, insertion sort, shell sort, and heap sort. 

We can divide these sorts into two categories:

1. Exchange based sorts: Quicksort, selection sort, insertion sort, shell sort, and heap sort
2. Mergesort

Each of our exchange based sorts make use of a helper method called `exch` that exchanges two elements. We recommend that you modify the exch method so that it makes interesting calls to drawRectangle, clearRectangle, and play. For inspiration, you might look at the drawRectangle, clearRectangle, and play calls that are made by the merge sort algorithm.

Other improvements you might consider:

 - Animate the `less` method, which is also used by all six algorithms for making comparisons. We recommend against playing sounds inside `less`, though you're welcome to try it out to see what happens.
 - Show the current minimum element in seleection sort.
 - Make insertion sort more aesthetically pleasing when the array is already sorted.
 - Make the pivot stand out in Quicksort.
 - Avoid playing sounds or visualizating when an item exchanges with itself.
 - The sounds are generated using a modified version of the synthesizer package you developed for hw4. Try making a new SoundGenerator, or replace the given EnvelopedSineWave with a GuitarString.

Note from Daniel: I really hope you had fun doing this part of the lab. For reference, here is a gif of my implementation. Your implementation might look different from mine, and that's fine. Exercise your creative freedom with this!

![sorts](sorts.gif)

<!--
You should first start with swapReferences. You should highlight the rectangles that are going to be swapped in the color of your choice. Then after the swap, play their sounds and then redraw the rectangles in their new positions. Remember to clear the rectangles in between!

After that, you should be able to see heapsort in action!

Let's then turn our attention to insertion sort. Without any modifications, it would still visualize and audiblize great, except in the case where the array is already sorted. To account for that case, you should highlight each array access and play the sound of the element when you access it.

Selection sort looks great already. However, remember that selection sort looks for the maximum element, so wouldn't it be nice to highlight the current maximum that the algorithm has?

Finally, I've provided an in-place implementation of quicksort. For quicksort, you should make the pivot stand out in the visualization, as well as highlight the array indexes as you sweep through.  

#### Let's run this!-->



<a name="radix"></a> Radix Sort
------------------------------

In this part of lab you'll write an implementation of radix sort for integers. We've provided you with method declarations in <tt>radix/Sorts.java</tt>. For this implementation of radix sort, you'll be sorting positive integers with a radix of __16__, i.e. there will be 16 different 'characters' in our 'alphabet'.

What does this mean? Let's consider the decimal number 9731. If R=10 (i.e. our radix is 10), then we'd say that our alphabet consists of [0, 1, 2, 3, 4, 5, 6, 7, 8, 9], and thus our number is a sequence of 4 digits in this alphabet, namely 9, 7, 3, and 1.

What happens if R=16, i.e. What is our alphabet, and how would we represent 9731 in that alphabet? The most natural approach is to say that our alphabet consists of all possible combinations of 4 bits [0000, 0001, 0010, 0011, 0100, 0101, 0110, 0111, 1000, 1001, 1010, 1011, 1100, 1101, 1110, and 1111]. To represent 9731, we'd represent it as a sequence of digits from this alphabet of 4 bit chunks. Since 9731 is 00000000000000000010011000000011, in terms of our basic alphabet, we have:

    0000 0000 0000 0000 0010 0110 0000 0011

As we learned earlier in the semester, Java represents integers in binary. This makes it possible for us to implement radix sort using bit masking (the & operator) and shifting (the << and >> operators) in order to get specific digits.

For this part of the lab, there are two methods that you have to implement:

    public static int[] countingSort(int[] keys, int whichDigit)

countingSort() is the procedure that we described in lecture 32, sorting only on the whichDigith digit of each key. Since we have 32 bit integers, and each alphabet character is 4-bits, then each key in the int[] array consists of 8 digits (i.e. whichDigit should only be a number between 0 and 7). If whichDigit is 0, you should use the rightmost digit, and if it is 7, you should use theleftmost digit. Note that you going to __return__ an array. Do __NOT__ change the actual keys array.

    public static int[] radixSort(int[] keys)

radixSort() will fully implement the radix sort algorithm. Given the countingSort procedure above, radixSort will be very easy to write.

Extra for experts: Compare the runtime of your radix sort compared to Arrays.sort. Which is faster for short arrays? Long arrays? 

<!--
#### Part 2: Write Out Radix Sort

We've provided you with the <tt>Random</tt> class. You can run this class with the command

    java radix.Random

The program will then output 8 numbers in ___binary___ in a predetermined range in the <tt>radix.txt</tt> file. Your job is then to perform radix sort on these numbers with a radix of ___16___. This should be a radix sort by LSD.

Write every iteration of radix sort on a new line of the <tt>radix.txt</tt>. Separate each number with a space. So for example if initially your <tt>radix.txt</tt> looks like this:

    1 11 10 111 101 110 0 100

Then afterwards your <tt>radix.txt</tt> should look like this

    1 11 10 111 101 110 0 100
    0 1 10 11 100 101 110 111

It's imperative that you put each round of the sort on a new line because that's how the autograder is going to parse your .txt file.

For those of you who want extra practice with radix sort, feel free to run the program and keep generating different <tt>radix.txt</tt> files to sort.

>   Note that these two parts play with each other. You can generate numbers 
>   for your implementation of radix sort by using the Random class. You can
>   also see if you sorted your number correctly by implementing radix sort 
>   and using the numbers you generated in <tt>radix.txt</tt> as a test case. 
>   But for the purpose of this lab, you only have to do one option.

>   Also, I want to mention here that if you want to put radix sort into the 
>   visualization and audibilization from the previous part of the lab, feel 
>   free to! I personally don't thing that radix sort is visualized as nicely
>   as comparison-based sorts, but if you find out a way to prove me wrong, 
>   then feel please be my guest.

<a name="trie"></a> Trie to Find a Word
-------------------------------

Ok, so first I think I have to beg for forgiveness at my bad pun. I really tried there. Get it, tried. Ok I'll stop now.

You'll be dealing a lot with tries in proj3. This part of the lab is a teaser for what tries can do. There is an implementation of a Trie provided in the find directory. Hopefully you'll be able to get some practice with tries and see something pretty quick, simple, and cool that you can do with tries.

For this part of the lab you're going to implement the Finder class. Make a <tt>Finder.java</tt> file in the find directory. Remember to include the package at the top.

You have to be able to run with program with this command:
    java find.Finder fileName

The Finder class will take at the command line the name of an input file. I suggest going [here](https://www.gutenberg.org) to find some cool books and download their .txt files to play around with.

The class will then parse the file once and then load all of the words and their line numbers into a trie. You should then prompt the user for an input word and then output the line numbers that the word can be found at. In order to do this parsing, you should look into the Scanner class in the Java Standard Library. You will be using this class a lot in proj3.

In Java, capital letters and lowercase letters are not equal. That means that if you insert "The" and "the" into the trie, they will map to two different nodes. Also, depending on how strictly you parse, you can end up with punctuation in your trie. So "the," could be added to the trie and count as a word. I'm not going to require you to be fancy and account for these differences, however, when you have some free time and want think back to this lab, perhaps you'll want to account for those things.

>Some food for thought:
> - Why do you think tries are a good data structure for this application? 
>   What would be some of the trade-offs between using a trie for this vs 
>   other data structures, such as balanced tree, like a red-black tree, or 
>   a hash-table?
> - Why do we want to preprocess the file before instead of just scanning the 
>   file again and again? After all, the latter would be easier to implement 
>   right?
-->

References
----------
- Jonathan Shewchuk's CS61B lab12 for implementations of mergesort and overall structure of [Sorts.java](http://www.cs.berkeley.edu/~jrs/61b/lab/lab12/Sort.java)
- Selection sort, insertion sort, quicksort, heap sort, and shell sort adapted from Algorithms 4th edition.