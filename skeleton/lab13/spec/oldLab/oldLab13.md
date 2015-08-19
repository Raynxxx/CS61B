~ number: 13
~ title: Graphs
 
Part A. The abstract graph
----

#### Linked lists --> trees --> graphs

One of the first data structures we studied in this course was the linked list, which consisted of a set of nodes connected in sequence. Then we looked at trees, which were a generalized version of linked lists: you still connected nodes in sequence, but one node could branch off leading to multiple others. Now we'll look at a generalization of a tree, called a _graph_.

In a graph, we still have a collection of nodes, but each node in a graph can be connected to any other node without limitation. This means there isn't necessarily a hierarchical structure like you get in a tree. For example, here's a tree, where every node is descendent from one another:

![Tree](Tree.png)

Now suppose you add an edge back up the tree. This is no longer a tree (notice the hierarchical structure is broken -- is C descendent from A or is A descendent from C?), but it is still a graph!

![BackEdge](BackEdge.png)

There are other edges that are not allowed in a tree. For example, now node E looks like it is descendent from two nodes. Again this is no longer a tree, but it is a graph.

![CrossEdge](CrossEdge.png)

In general a graph can have any possible connection between nodes:

![Graph](Graph.png)

#### Overview of graphs

First, let's go over some terminology. An element in a graph is called a _vertex_ (we typically don't call them nodes). Vertices usually represent the objects in our graph, namely the things that have the relationships such as people, places, or things. A connection between two vertices is called an _edge_. An edge represents some kind of relationship between two vertices.

Quite a few examples of graphs exist in the everyday world:

- __Road maps__ are a great example of graphs. Each city is a vertex, and the edges that connect these cities are the roads and freeways. An abstract example of what such a graph would look like can be found [here](http://inst.eecs.berkeley.edu/~cs61b/fa05/diagrams/freeway-graph.png). For a more local example, each building on the Berkeley campus can be thought of as a vertex, and the paths that connect those buildings would be the edges.

- __Facebook__ depends on graphs. Each person that participates is a vertex, and an edge is established when two people claim to be friends or associates of each other.

- For a more technical example, a __computer network__ is also a graph. In this case, computers and other network machinery (like routers and switches) are the vertices, and the edges are the network cables. Or for a wireless network, an edge is an established connection between a single computer and a single wireless router. 

In more formal mathematical notation, a vertex is written as a variable, such as `v0`, `v1`, `v2`, etc. An edge is written as a pair of vertices, such as `(v0, v1)`, or `(v2, v0)`.

#### Directed vs. undirected graphs

Once again, an element in a graph is called a _vertex_, and a connection between two vertices is called an _edge_.

If all edges in a graph are showing a relationship between two vertices that works in either direction, then it is called an _undirected graph_. A picture of an undirected graph looks like this:

![UndirectedGraph](UndirectedGraph.png)

But not all edges in graphs are the same. Sometimes the relationships between two vertices sometimes only go in one direction. Such a relationship is called a _directed_ graph. An example of this could be a city map, where the edges are sometimes one-way streets between locations. A two-way street would actually have to be represented as two edges, one of them going from location A to location B, and the other from location B to location A.

In terms of a visual representation of a graph, an undirected graph does not have arrows on its edges (because the edge connects the vertices in both directions), whereas each edge in a directed graph does have an arrow that points in the direction the edge is going. An example directed graph appears below.

![DirectedGraph](DirectedGraph.png)

More formally, an edge from a vertex `v0` to a vertex `v1` is printed as the pair (`v0, v1)`. In a directed graph, the pair is ordered; thus even if the edge `(v0,v1)` exists, `(v1,v0)` might not. In an undirected graph, the pair isn't ordered, so the edge `(v0,v1)` is the same as the edge `(v1,v0)`.

#### Discussion: Give another application of graphs

Give another situation that can be modeled with graphs. Describe what the vertices are, and define the conditions under which two vertices are connected by an edge. Can your example be represented as an undirected graph, or does it have to be a directed graph? 

#### A few more graph definitions

Now that we have the basics of what a graph is, here are a few more terms that might come in handy while discussing graphs.

| _Term_              | _Definition_                                                                                                                          |
|---------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| Edge                | A single connection between two vertices                                                                                              |
| Adjacent            | Two vertices are adjacent if there is an edge connecting them                                                                         |
| Connected           | A graph is connected if every vertex has a path to all other vertices. (Also describes two nodes if there is an edge connecting them) |
| Neighbor            | Two vertices are neighbors if there is an edge connecting them                                                                        |
| Set of neighbors    | The set of all nodes that are adjacent to/connected to/neighbors of a node                                                            |
| Incident to an edge | A vertex that is an endpoint of an edge is incident to it                                                                             |
| Path                | A sequence of edges that can be followed from one vertex to another                                                                   |
| Cycle               | A special kind of path that ends at the same vertex where it originally started                                                       |

#### Test: edge count vs. vertex count
Write your answers to these questions in the provided file `solutions.txt`. Each answer should be the letter of the correct answer [A, B, C, or D].

__Q1:__

Suppose that G is an _directed_ graph with N vertices. What's the maximum number of edges that G can have? Assume that a vertex cannot have an edge pointing to itself, and that for each vertex u and vertex v, there is at most one edge (u,v).

- __A__: N
- __B__: N^2
- __C__: N*(N-1)
- __D__: N*(N-1)/2

__Q2:__
Now suppose the same graph G in the above question is an undirected graph. Again assume that no vertex is adjacent to itself, and at most one edge connects any pair of vertices. What's the maximum number of edges that G can have?

- __A__: half as many edges as in the directed graph
- __B__: the same number of edges as in the directed graph
- __C__: twice as many edges as in the directed graph

__Q3:__

What's the _minimum_ number of edges that a connected undirected graph with N vertices can have?

- __A__: N-1
- __B__: N
- __C__: N^2
- __D__: N*(N-1)
- __E__: N*(N-1)/2

#### Data structures for graphs

Now that we know how to draw a graph on paper and understand the basic concepts and definitions, we can consider how a graph should be represented inside of a computer. We want to be able to get quick answers for the following questions about a graph:

- Are given vertices `v` and `w` adjacent?

- Is vertex `v` incident to a particular edge `e`?

- What vertices are adjacent to `v`?

- What edges are incident to `v`?

Most of today's lab will involve thinking about how fast and how efficient each of these operations is using different representations of a graph.

Imagine that we want to represent a graph that looks like this:

![DirectedGraph](DirectedGraph.png)

One data structure we could use to implement this graph is called an _adjacency list_. In such a data structure, an array is created that has the same size as the number of vertices in the graph. Each position in the array represents one of the vertices in the graph. Then, each location in the array points to a list that contains indexes to other vertices, which are the vertices neighbors. 

The adjacency list that represents the above graph looks like this:

![AdjacencyList](AdjacencyList.png)

Another data structure we could use to represent the edges in a graph is called an _adjacency matrix_. In this data structure, we also have an array with each position representing a vertex in the graph. However, instead of the array pointing to a linked list, it points to another array representing possible neighbors. This matrix just contains boolean values, true when there is an edge between the two given vertices, false when no edge exists. Thus, each vertex has a row and a column in the matrix, and the value in that table says true or false whether or not that edge exists. 

The adjacency matrix that represents the above graph looks like this:

![AdjacencyMatrix](AdjacencyMatrix.jpg)

#### Discussion: Representing a graph with a linked structure

A third data structure we could use to represent a graph is simply an extension of the linked nodes idea, where each vertex is an object that contains pointers to other vertices. This may seem like the most straightforward approach: aren't the adjacency list and adjacency matrix roundabout in comparison?

Discuss reasons why the adjacency list or adjacency matrix might be preferred for a graph.

On the flipside, notice that we could also represent a tree using an ajacency matrix or list. Discuss reasons why an adjacency list or adjacency matrix might not be preferred for a tree.

#### Test: which data structure is more efficient?

__Q4:__

 Which is more efficient - an adjacency matrix or an adjacency list?

- __A__: Adjacency Matrix
- __B__: It depends
- __C__: Adjacency List

#### Test: graph data structure trade-offs

Deciding how to store data is all about trade-offs. This quiz focuses on the BIG picture of which representation is more efficient in a given situation. The rest of the lab focuses on exactly how efficient things are.

__Q5:__

Which is most SPACE efficient if you have a lot of edges in your graph?

- __A__: Adjacency Matrix
- __B__: Adjacency List
- __C__: It depends

__Q6:__

Which is most SPACE efficient if you have very few edges in your graph?

- __A__: Adjacency Matrix
- __B__: Adjacency List
- __C__: It depends

__Q7:__

Which is most TIME efficient for adding an edge if you have a lot of edges in your graph?

- __A__: Adjacency Matrix
- __B__: Adjacency List
- __C__: It depends
- __D__: They are the same

__Q8:__

Which is most TIME efficient for adding an edge if you have very few edges in your graph?

- __A__: Adjacency Matrix
- __B__: Adjacency List
- __C__: It depends
- __D__: They are the same

__Q9:__

Which is most TIME efficient for returning a list of edges from one node if you have very few edges in your graph?

- __A__: Adjacency Matrix
- __B__: Adjacency List
- __C__: It depends
- __D__: They are the same

__Q10:__

Which is most TIME efficient for returning a list of edges from one node if you have a lot of edges in your graph?

- __A__: Adjacency Matrix
- __B__: Adjacency List
- __C__: It depends
- __D__: They are the same

#### Test: time estimates for accessing graph information

__Q11:__

Using an adjacency matrix, how long in the worst case does it take to determine if vertex v is adjacent to vertex w? (Assume vertices are represented by integers.)

- __A__: constant time
- __B__: time proportional to the number of neighbors of vertex v
- __C__: time proportional to the number of vertices in the graph
- __D__: time proportional to the number of edges in the graph

__Q12:__

Using an array of adjacency lists, how long in the worst case does it take to determine if vertex v is adjacent to vertex w? (Assume vertices are represented by integers.)

- __A__: constant time
- __B__: time proportional to the number of neighbors of vertex v
- __C__: time proportional to the number of vertices in the graph
- __D__: time proportional to the number of edges in the graph

#### Test: memory use in adjacency lists

Suppose we are representing a graph with N vertices and E edges.

There are N<sup>2</sup> booleans stored in an adjacency matrix. Therefore the memory required to store an adjacency matrix is N<sup>2</sup> times the memory required to store a `boolean` value. Assume that references and integers each use 1 unit of memory.

![Adjacency list](AdjacencyList.png)

__Q13:__

The amount of memory required to represent the graph as an array of adjacency lists is proportional to what?

- __A__: N*E
- __B__: N+E
- __C__: E

Part B. Programs that process graphs
----

#### Graph traversal

Earlier in the course, we used the general traversal algorithm to process all elements of a tree:

    Stack fringe = new Stack ();
    fringe.push (myRoot);
    while (!fringe.isEmpty()) {
        // select a tree node from fringe
        TreeNode node = fringe.pop();

        // process the node
        [do whatever processing operation here...]

        // add node's children to fringe if not null
        fringe.push(node.myRight);
        fringe.push(node.myLeft);
          // Note: If this was a tree with more than
          //       two children, we'd push ALL of the
          //       children onto the stack.
    }

The code just given returns tree values in depth-first order. If we wanted a breadth-first traversal of a tree, we'd replace the `Stack` with a queue (the `LinkedList` class in `java.util` would be fine).

Analogous code to process every vertex in a connected graph is

    Stack fringe = new Stack();
    fringe.push (some vertex);
    while (!fringe.isEmpty()) {
        // select a vertex from fringe
        // process the vertex
        // add the vertex's neighbors to fringe
    }

This doesn't quite work, however. A graph may contain a _cycle_ of vertices, and if that is the case here, the code loops infinitely.

The fix is to keep track of vertices that we've visited already (and put into the fringe), in order not to process them twice. Here is pseudocode:

    Stack fringe = new Stack ();
    fringe.add (some vertex);
    while (!fringe.isEmpty()) {
        // select a vertex from fringe
        // process the vertex
        // mark the vertex as visited
        // add the vertex's unvisited neighbors to fringe
    }

As with tree traversal, we can visit vertices in depth-first or breadth-first order merely by choosing a stack or a queue to represent the fringe. Typically though, because graphs are usually interconnected, the ordering of vertices can be scrambled up quite a bit. Thus, we don't worry too much about using a depth-first or a breadth-first traversal. 

If we use an adjacency list, we can run depth-first search in O(|V| + |E|) time. If we use an adjacency matrix, we can run it in O(|V|^2) time. Thus, it is asympotically faster to use an adjacency list if the graph is sparse (|E| << |V|^2).

#### Exercise: practice graph traversal

Below is the psuedocode for traversing a graph. It might help you to print out copies of the graph [here](PicturesOfTheGraphs.pdf). Try to figure out the order in which the nodes are visited if you start at different vertices. You can check your answers for starting at vertices 1 - 5 on the next page.

    Stack fringe = new Stack ();
    fringe.add (some vertex);
    while (!fringe.isEmpty()) {

    // select a vertex from fringe
    // if the vertex is not yet visited:
            // process the vertex
            // add the vertex's neighbors to fringe
            // mark the vertex as visited

} 

For this problem assume that we add the vertex's unvisited edges to the stack in the order __higher number to lower number__.

![CrazyGraph](CrazyGraph.jpg)

![GraphNeighborList](GraphNeighborList.png)

#### Practicing graph traversal

Here are the solutions for nodes 1 and 2 so you can check your understanding.

| Starting vertex | Order of visiting remaining vertices |
|-----------------|--------------------------------------|
| 1               | 3, 4, 2, 5, 7, 6, 9, 8, 10           |
| 2               | 4, 3, 1, 6, 7, 5, 8, 9, 10           |
| 3               | ?                                    |
| 4               | ?                                    |

Fill in the table for starting vertices 3 and 4. Write your answers in the provided file `solutions.txt` under the heading __DFS__. 

Now assume we do a breath-first traversal on the same graph. We will still add the vertex's unvisited edges to the queue in the order __higher number to lower number__. Fill in the following table for starting vertices 3 and 4. Write your answers in the provided file `solutions.txt` under the heading __BFS__. 

| Starting vertex | Order of visiting remaining vertices |
|-----------------|--------------------------------------|
| 1               | 3, 9, 7, 6, 4, 10, 8, 5, 2           |
| 2               | 5, 4, 8, 7, 9, 6, 3, 10, 1           |
| 3               | ?                                    |
| 4               | ?                                    |

#### Exercise: `Graph.java`

Below is framework code for a class `Graph`. It implements a graph of `Integer`s using an adjacency list.

Fill in some of the basic methods: `addEdge`, `addUndirectedEdge`, `isAdjacent`, and `inDegree`.

Then complete the `DFSIterator` inner class. As its name suggests, it should iterate through all of the vertices in the graph in DFS order, starting from a vertex that is passed in as an argument.

#### Exercise: using an iterator to find a path, part 1

Complete the method `pathExists` in `Graph.java`, which returns whether or not any path exists that goes from a vertex `v` to a vertex `w`. Remember that a path is any set of edges that exists which you can follow that such you travel from one vertex to another. You may find it helpful for your method to call the given method `visitAll`.

For an example of an undirected graph this should work on, try testing it with the following two graphs:

 
    addUndirectedEdge(0,2); 
    addUndirectedEdge(0,3); 
    addUndirectedEdge(1,4); 
    addUndirectedEdge(1,5); 
    addUndirectedEdge(2,3); 
    addUndirectedEdge(2,6); 
    addUndirectedEdge(4,5); 

     
    addEdge(0,1); 
    addEdge(1,2); 
    addEdge(2,0); 
    addEdge(2,3); 
    addEdge(4,2); 

#### Exercise: using an iterator to find a path, part 2

Now you will actually find a path from one vertex to another if it exists. Write code in the body of the method named `path` that, given two `int`s that represent a start vertex and a finish vertex, returns an `ArrayList` of `Integer`s that represent vertices that lie on the path from the start to the finish. If no such path exists, you should return an empty `ArrayList`.

Algorithm hint: Pattern your method on `visitAll`, with the following differences. First, add code to stop calling `next` when you encounter the finish vertex. Then, trace back from the finish vertex to the start, by first finding a visited vertex u for which (u, finish) is an edge, then a vertex v visited earlier than u for which (v, u) is an edge, and so on, finally finding a vertex w for which (start, w) is an edge. Collecting all these vertices in the correct sequence produces the desired path. We recommend that you try this by hand with a graph or two to see that it works.

#### Topological sort

A _topological_ sort (sometimes also called a _linearization_) of a directed graph is a list of the vertices in such an order that if there is a directed path from vertex `v` to vertex `w`, then `v` precedes `w` in the list. (The graph must be acyclic in order for this to work. Directed acyclic graphs are common enough to be referred to by their acronym: _DAGs_.)

Here is an example of a DAG:

![DAG](DAG.png)

In the above DAG, a few of the possible topological sorts could be:

    A B C D E F G H
    A C B E G D F H
    B E G A D C F H

Notice that the topological sort for the above DAG has to start with either A or B, and must end with H. (For this reason, A and B are called _sources_, and H is called a _sink_.)

Another way to think about it is that the vertices in a DAG represent a bunch of tasks you need to complete on a to-do list. Some of those tasks cannot be completed until others are done. For example, when getting dressed in the morning, you may need to put on shoes and socks, but you can't just do them in any order. The socks must be put on before the shoes.

The edges in the DAG represent dependencies between the tasks. In this example above, that would mean that task A must be completed before tasks C and D, task B must be completed before tasks D and E, E before G, C and D before F, and finally F and G before H. A topoological sort gives you an order in which you can do the tasks (it puts the tasks in a linear order).

#### The topological sort algorithm

The algorithm for taking a graph and finding a topological sort uses an array named `currentInDegree` with one element per vertex. `currentInDegree[v]` is initialized with the in-degree of each vertex `v`.

The algorithm also uses a fringe. The fringe is initialized with all vertices whose in-degree is 0. When a vertex is popped off the fringe and added to a results list, the `currentInDegree` value of its neighbors are reduced by 1. Then the fringe is updated again with vertices shows in-degree is now 0.

#### Exercise: Implement topological sort

Your task is to fill in the blanks in the `TopologicalIterator` class so that it successively returns vertices in topological order as described above. The `TopologicalIterator` class will resemble the `VertexIterator` class, except that it will use a `currentInDegree` array as described above, and instead of pushing unvisited vertices on the stack, it will push only vertices whose in-degree is 0.

C. Wikipedia as a graph
----

#### Philosophy is the root of all knowledge

The internet is a (very) large directed graph! Web pages are vertices, and links are edges between them.

Wikipedia is a particulary interesting graph, which we will investigate in this homework assignment. Here's an experiment to try:

Visit a Wikipedia page. Click on the first link in the main body text that's not in parentheses, and you'll end up on another Wikipedia page. Again, click on the first link in the main body text that's not in parentheses. Continue. Eventually, you'll likely end up at pages like "Philosophy", "Human", "Logic", and "Consciousness". 

Try it again, starting with other pages. Again, most likely you will end up at pages like "Philosophy", "Consciousness", etc. According to [Wikipediam themselves](http://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy), roughly 94% of pages eventually lead to "Philosophy"!
