# Lab 6: HugLife

## Navigation

*   [Pre-lab](#pre-lab)
*   [Introduction](#introduction)
*   [Experimenting with the Sample Creature](#experiment)
*   [Developing the Plip Class](#devplip)
*   [Running the Plip Class](#runplip)
*   [The Clorus Class](#clorus)
*   [Future Work](#enrichment)

## <a name="pre-lab"></a> Pre-lab

None this week

## <a name="introduction"></a> Introduction

In this lab, you'll create a package named <tt>**creature**</tt> that will implement two creatures (or more, if you'd like) that will inhabit a world simulated by the <tt>**huglife package**</tt>. Along the way we'll learn how to debug small pieces of a much larger system, even if those small pieces happen to live inside another package.

Start the lab by booting up the HugLife simulator. To do this, use the following commands (make sure you're in the lab6 directory):

    $ javac -g huglife/*.java creatures/*.java
    $ java huglife.HugLife samplesolo

This starts up a world called samplesolo. You should see a little red square wandering around randomly.

Your job for this assignment is to add two classes to the creatures directory: <tt>Plip.java</tt> and <tt>Clorus.java</tt>. Eventually these two types of creatures will also inhabit the world, and unlike this red guy, they actually do [something interesting](http://youtu.be/AKQTvr2kRcs).

These classes will extend the <tt>huglife.Creature</tt> package, which provides a template that all creatures should follow.

#### How the Simulator Works

Creatures live on an NxN grid, with no wraparound. Each square may be empty, impassible, or contain exactly one creature. At each tic, exactly one creature takes a single action. Creatures choose actions in a round-robin fashion.

There is a global queue of all creatures in the world, waiting their turn to take an action. When a Creature is next in line to move, the world simulator tells that creature who its four neighbors are, and requests a choice of action from the creature (more specifically, the world simulator calls the Creature's chooseAction method, which takes as an argument a collection of all four neighbors).

Based on the identity of the four neighbors, the Creature chooses one of exactly five actions: MOVE, REPLICATE, ATTACK, STAY, or DIE. MOVE, REPLICATE, and ATTACK are directional actions, and STAY and DIE are stationary actions. For example, if the acting Creature sees a creature to its LEFT that it can eat, it might choose to ATTACK LEFT. If a creature takes a directional action, it also specifies either a direction or a location. One of your main tasks for this lab is to write the code that makes Creature decisions. Actions are returned as objects of type Action, which are fully described in Action.java.

The chosen Action is given to the simulator which enacts the changes to the world grid. You'll be responsible for writing the code that tracks the state of each Creature. For example, after the acting Creature eats another Creature, the acting Creature might become stronger, die, change colors, etc.

This will be accomplished by a callback to the creature, which (as described below) is required to provide move(), replicate(), attack(Creature c), and stay() methods that describe how the acting Creature's physical state will evolve after each of these respective actions. For example, if your creature chooses to replicate upwards by returning <tt>new Action(Action.ActionType.REPLICATE, Direction.TOP)</tt>, then the game simulator is guaranteed to later call the .replicate() method of the creature that made this choice. There is no die() method since the Creature is simply removed from the world entirely.

## <a name="experiment"></a> Experimenting with the Sample Creature

Open up Occupant.java, Creature.java, and SampleCreature.java, which you'll find in the directory for huglife package.

*   **Occupant**. Occupant is a general class for all possible things that can inhabit the grid of the huglife universe. You'll see that Occupants inherit a name, shared by all instances of that Occupant subtype. Furthermore, every occupant must provide a method which returns a color (more on this later). There are two special kinds of Occupants, with names "empty", and "impassible". Empty square and impassible squares represent unoccupied and unoccupiable squares, respectively.
*   **Creature**. Creature is a general class for all living things that can inhabit the grid of the huglife universe. All creatures have an energy level, and if that energy level ever falls below zero, the universe will choose the DIE action for them.
    Every creature must implement four **_callback_** methods: move(), replicate(), attack(), and stay() methods. These describe what the creature should do when each of these actions occurs.
    Creatures must also implement a ChooseAction method, and any reasonable creature will probably find the built-in getNeighborsOfType method useful for doing so.
*   **SampleCreature**. The two creatures you implement for this lab will look somewhat similar to the SampleCreature, so you'll want to consult this class while developing your classes.

Try making some changes to the sample creature, and see how your changes affect how things change when you run the HugLife simulator. As one of your experiments, you might have the SampleCreature react in some observable way when it sees a wall. You can do this by requesting a list of all neighbors of type "impassible" from the getNeighborsOfType method.

**Important: After you've experimented to your hearts content, use the git checkout command to revert your project directory to its original state. If you don't know how to do this, see the documentation for git.** 
If you get stuck on this, get a neighbor or a lab TA to help you. Maybe someone will even write something on the board (I've intentionally not given you the exact command here).

## <a name="devplip"></a> Developing the Plip Class

#### Basic Plip functionality

Now it's time to add a new type of creature to the world. Go into the creatures directory, and you'll see there is a class file named Plip there, waiting to be filled out.

Plips will be lazy (but motile) photosynthesizing creatures that mostly stand around and grow and replicate, but will flee if they happen to see their mortal enemy, the Clorus.

Let's start with just a few of the properties that we'll eventually need for our Plip class.

*   All Plip's name methods should return exactly "plip" with no spaces or capitalization. This is important, since creatures only know how to react to each other based on this name string. Note: This is a bit of a hack, but given what we know about Java, it's probably the best way. (Do you actually have to change anything to ensure this?)
*   Plips should lose 0.15 units of energy on a move action, and gain 0.2 units of energy on a stay action.
*   Plips should never have energy greater than 2. If an action would cause the plip to have energy greater than 2, then it should be set to 2 instead.
*   The color method for plips should return a color with red = 99, blue = 76, and green that varies linearly based on the energy of the Plip. If the plip has zero energy, it should have a green value of 63. If it has max energy, it should have a green value of 255. The green value should vary with energy linearly in between these two extremes.

It would be theoretically possible to test our Plip class by sticking them on a HugLife world grid and watching what they do (with gjdb or print statements) as they run amok. However, this would be a terrible idea. Instead, it's better to perform testing on the Plip class directly.

> Note on testing: It's not necessarily desirable to test everything! Use 
> tests only when you think they might reveal something useful, i.e. there is 
> some chance you'll get something wrong. Figuring out what to test is a bit 
> of an art

To test the Plip class, which is part of the creatures package, we can create a test class TestPlip that is also part of the creatures package. You'll see that a skeleton containing a few simple tests is provided.

One way to run this test file is as follows:

    $ javac huglife/*.java creatures/*.java
    $ java creatures.TestPlip

Try it out and you'll see that our test fails. Now after all that reading you can finally do something! Modify the Plip class according to the specifications above until all tests pass. **Make sure you don't forget to recompile in between calls to the test.**

Once you're done, you're well on your way to having a fully functional Plip.

#### The Plip replicate method

Do not start this part until your Plip class passes all the provided tests. Once you've done so, we'll work on adding the correct replication property to our Plips, namely:

*   When a plip replicates, it keeps 50% of its energy, and the other 50% goes to its offspring. No energy is lost in the replication process.

**The replicate test doesn't do anything yet**. Fill in the test for the replicate method. Make sure to test that the returned Plip is not the same Plip as the Plip whose replicate method was called. You can use the JUnit assertNotSame method for this purpose (do not confuse assertNotEquals with assertNotSame. see the JUnit documentation if the distinction is unclear)!

#### The Plip chooseAction method

All that's left is giving the Plip a brain. To do this, you'll be filling out the chooseAction method as follows.

*   The Plip should obey the following behavioral rules in order of preference:

    1.  If there are no empty spaces, the Plip should stay.
    2.  Otherwise, if the Plip has energy greater than 1.0, it should replicate to an available space.
    3.  Otherwise, if it sees a neighbor with name() equal to "clorus", it will move to any available empty square with probability 50%. It should choose the empty square randomly. As an example, if it sees a Clorus to the LEFT and to the BOTTOM, and "empty" to the TOP and RIGHT, there is a 50% chance it will move (due to fear of Cloruses), and if it does move, it will pick randomly between RIGHT and TOP.
    4.  Otherwise, it will stay.

These rules must be obeyed in this strict order! Example: If it has energy greater than 1, it will ALWAYS replicate before trying to run from a clorus.

#### Writing testChoose

Uncomment the @Test annotation tag for the testChoose method. This will allow the testChoose method to run when you invoke the <tt>$ java creatures.TestPlip</tt> command. The existing test checks the first rule, namely that if there are no empty spaces next to the plip, then it should stay.

Add tests for the choose method to your TestPlip class. Everything might look complicated, however, if you use SampleCreature as a guide, you should be able to figure out the syntax.

You might find it useful to look at the code for the Action class to see the various constructors for Actions.

Don't worry (yet) about testing the 50% rule if a Clorus is nearby. This isn't possible since you haven't created a Clorus class yet, and thus you won't be able to create a HashMap that involves Cloruses. Also this test is pretty tricky to write.

Later, once you write Clorus, you might find it interesting to come back and try to write a randomness test. One possibility is to might simply test that both choices are possible by making many calls and ensuring that each happens at least once. Performing a statistical test is probably a bit too much for lab today (though you're welcome to try).

#### Writing ChooseAction

After writing a set of tests that you feel happy about, edit the Plip class so that it makes the right choices. You'll want to look carefully at the SampleCreature class as a guide.

## <a name="runplip"></a> Running the Plip Class

Assuming your tests worked, you can now see how your Plips fare in the real HugLife world. Use the commands:

    $ javac huglife/*.java creatures/*.java
    $ java huglife.HugLife sampleplip

You should see your plips happily growing along. If something goes wrong, it's probably because your tests are not thorough enough. If this is the case, using the error messages, add new tests to TestPlip until something finally breaks. If you're still stuck, let a TA or a lab assistant know!

## <a name="clorus"></a> Introducing the Clorus

We'll now add another Creature and corresponding test to the creatures package. This time, we'll be implementing the Clorus, a fierce blue colored box that enjoys nothing more than snacking on hapless Plips.

This time, you'll create your TestClorus and Clorus classes from scratch (using what you've got so far as a guide).

The Clorus should obey the following rules exactly:

*   All Cloruses must have a name equal to exactly "clorus" (no capitalization or spaces)!
*   Cloruses should lose 0.03 units of energy on a move action, and **lose** 0.01 units of energy on a stay action.
*   Cloruses have no restrictions on their maximum energy.
*   The color method for Cloruses should always return the color R = 34, G = 0, B = 231.
*   If a Clorus attacks another Creature, it should gain that Creature's energy. This should happen in the attack() method, not in chooseAction(). You do not need to worry about making sure the attacked() creature dies &mdash; the simulator does that for you.
*   When replicating, Cloruses should receive half of their energy and their offspring should receive the other half.
*   Cloruses should obey exactly the following behavioral rules:

    1.  If there are no empty squares the Clorus will STAY (even if there are Plips nearby they could attack).
    2.  Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
    3.  Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
    4.  Otherwise, the Clorus will MOVE.

As before, write a TestClorus class. You probably don't need to test the move(), stay(), or color() methods, but you're welcome to. Instead, it's probably only necessary to test the Choose() action. Your tests for TestClorus should involve at least one of each type of action.

Once you've written tests, write the Clorus class itself, again from scratch.

**After you've written and tested the class _thoroughly_, go into HugLife.java and uncomment the lines in readWorld()**

## Showtime

We did it.

Now it's time to watch Cloruses and Plips battling it out. Use the following command to kick off a Manichaean struggle that will end either in eternal oscillations or in lonely immortals wandering the wastes forever.

**_Did you remember to edit `HugLife.java`?_**

    $ javac huglife/*.java creatures/*.java
    $ java huglife.HugLife strugggz

If you did everything right, it should hopefully look cool. You might consider tweaking the HugLife simulator parameters, including the world size and the pause time between simulation steps. Be warned that world sizes above ~50x50 are probably going to run fairly slowly.

## Building new things: Reading and Writing Worlds

You can write the world state in several ways:

1.  You can do a call to StdDraw.save(), which will save a picture of the world for you.
2.  You can call the the Grid class's writeWorld() method. The method prints out the the name of the creature and its x and y positions. This is handy if you want to later read in the current state of the world, though it's not as pretty as saving a picture.

**You can read a world state by calling the readWorld() method in the HugLife class. This takes in a .world file. Look at some of the ones provided to see how you can write one yourself.**

## Submission

**Don't forget to `git add Clorus.java TestClorus.java` before comitting!**

There is no autograder for this lab. If your simulation looks mostly right, you've done enough work to receive full credit.

## Enrichment

There's a lot one could do to improve the simulation. Possibilities include:

*   Finding more interesting starter worlds using the given creatures. Note that you can add "impassible" tiles which will act as black boxes that cannot be crossed.
*   Additional creatures: There's all kinds of strange possibilities, like creatures that take on the attributes of creatures they eat, creatures that gain energy based on the total population of their own species (using static variables), etc.
*   Evolution: Having creatures evolve with each generation.
*   Size: Giving each creature a size so that it doesn't necessarily fill up an entire box.
*   Being attacked: At the moment all attacked creatures automatically die. You could add an attacked() callback that returns true if the creature should die, and false otherwise.
*   Movement and sensing at a distance: At the moment, creatures can only see their immediate neighbors, and generally only take actios on squares immediately adjacent.
*   Generation of creatures through clicks. The StdDraw library provides the ability to provide user input with the mouse. One could imagine clicking to generate new creatures.
