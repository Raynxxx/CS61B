~ number: 9
~ title: Project 2 Lab

Overview
--------

For Project 2, two points of your project grade will be based on successful completion of a basic storage design plan, as well as an evaluation of at least four other design plans.

Your design plan will include:
 - A high level overview of your design plan.
 - A precise listing of all the files that change after each of a series of Gitlet commands.

You may submit your plan in either PDF or plaintext form. This plan is due at the usual lab due time (10 PM on Sunday plus a short grace period, in this case exactly 1 hour).

In this lab, you'll work through an example to understand how Gitlet works. You'll then develop a storage design plan, using an augmented version of HugLife as an example.

1: Understanding Gitlet
--------

Read the Gitlet spec, with a particular focus on the commands up through `checkout`. As a checkpoint for your understanding, run each of the commands below, and ensure that the results match your understanding. If anything is confusing, make sure to ask for help.

        java StaffGitlet init
        java StaffGitlet add dog.txt
        java StaffGitlet commit "initial commit of dog.txt"
        java StaffGitlet log
        java StaffGitlet add dog.txt
        cp manyDogs.txt dog.txt
        java StaffGitlet add dog.txt
        java StaffGitlet add dog.txt
        java StaffGitlet commit "replaced dog.txt with manyDogs"
        java StaffGitlet add manyDogs.txt
        java StaffGitlet add ketchupFriend.txt
        java StaffGitlet status
        java StaffGitlet commit "added manyDogs and ketchupFriend"
        java StaffGitlet commit YOLO
        cp doge.txt manyDogs.txt
        java StaffGitlet add manyDogs.txt
        java StaffGitlet commit "replaced manyDogs with doge"
        java StaffGitlet add dog.txt
        java StaffGitlet commit "added dog yet again"
        java StaffGitlet log
        java StaffGitlet checkout 1 dog.txt   # say yes
        ## open up dog.txt, what do you see?
        java StaffGitlet checkout dog.txt   # say yes
        ## open up dog.txt, what do you see?

        ### NEW COMMANDS FOLLOW (1:52 PM, March 19)    
        cp ketchupFriend.txt dog.txt
        java StaffGitlet add dog.txt
        cp doge.txt dog.txt
        java StaffGitlet commit dog.txt 
        # above command commits "doge", not "ketchupFriend"


This is not an exhaustive look at the Gitlet commands, but it's good enough to understand the basics.

2: Adding a Save State Feature for HugLife
--------

In part 1, we saw that the Gitlet command was capable of remembering the entire history of all commands (as we saw with the log command), and was also capable of remembering the states of files that had since been changed (like when we checked out the old version of dog.txt).

For a simple example of how this is possible, see Sarah Kim's description of the Serializable interface on [her website](http://www.sarahjikim.com/cs61b).

As a more complex example of how these things are possible, we'll now consider how one might endow HugLife with similar magical powers. Specifically, our new version of HugLife will have the following changes:

 - Simulations will run until they are stopped (there is no longer a maximum time as in Lab 6).
 - If the user closes the program and restarts it, and the user does not specify a world file, the simulation will pick up where it left off.
 - If the user presses "s" during execution, the state of the world will be saved. At the same time, a visual representation is saved that provides a visual picture of the saved state. 
 - The user should be allowed to reload states saved with the s command.

A design storage plan (similar to your required planning document for project 2) is provided <a href="HugLifeStorageDesignPlan.pdf">here</a>.

The resulting code can be found in the HugLifeSavable directory. Try experimenting with this new version of HugLife, by using the sequential example described in the HugLife storage design plan.

Make sure to see observe how the files change (e.g. how the contents of the savedStates folder change). For example, note that every time you run the program that the modification time of the quickSave.ser is changed, thus indicating that it has been updated.

You are not expected to look at the HugLifeSavable code during this lab, though you are certainly welcome to if you'd like. 

Of particular note: Observe that the state of the universe is stored in a .ser file. If you look at Grid.java, you'll see that it implements the Serializable interface like Sarah's cat example. Because Grid implements this interface, we are able to save Grid objects to a file (for MATLAB users, this is similar to using the save command to save a variable in workspace) without having to manually iterate through the entire Grid, storing data one-item-at-a-time. These .ser files may contain any number of Java objects. 

You'll probably want to use this same mechanism in your project (though it is not required). We will not be providing official support or tutorials for how to use the Serializable interface other than the brief tutorial given by Sarah. Indeed, one of the most important skills you'll gain as a programmer is how to use new and unfamiliar features without task-specific instruction provided by a course instructor. You're free to use any reference materials you'd like to this end. You will probably find chapter 14 of HeadFirstJava to be particularly handy, though there are also tutorials available on the web. 

3: Writing Your Storage Design Plan
--------

Templates for your design plan are provided in <a href="GitletStorageDesignPlanTemplate.txt">plain text</a>, <a href="https://docs.google.com/document/d/1VFO-DgTT1HzaKb0D15_m17JVcNzSQVfx9h4ZXBWg-uE/edit?usp=sharing">Google</a> docs, and <a href="GitletStorageDesignPlanTemplate.docx">docx</a> format (no LaTeX sorry). Using one of these design plan templates, produce a design plan. We encourage you to discuss this with other students during lab (or after lab).

If you decide to use a .ser file (or files) in your design plan, you do not need to describe the exact types of the instance variables that you'll be storing inside these files. In fact, you shouldn't think about your instance variables yet. For now, you should just stick to the high level design. 

4: Submitting Your Storage Design Plan
--------

Submit your design plan by Sunday March 22nd at 10:00 PM (though we recommend finishing earlier so you can chillax over your first Spring Break weekend). You'll have until March 31st at 10:00 PM to review 4 design plans. We expect that this should be a light time commitment. If you encounter a seriously flawed design plan that you're not sure how to grade, you can leave us a note at <a href="">this google form (not yet available)</a> and we'll step in. 

You must use one of our provided templates. You are welcome to add additional information AFTER the sequential example.

To submit your design plan, head to <a href="https://www.crowdgrader.org/crowdgrader/venues/join/917/qoboty_myqybe_tuvucu_qapovo">CrowdGrader</a> (requires Google login, please use your @berkeley.edu address).

Your submission will receive full credit as long as you actually tried -- even flawed submissions will be given full credit. Likewise, any reviews which show reasonable effort will be given  full credit as well. Feel free to borrow ideas from other student's designs (but make sure to cite them). Indeed drawing inspiration from your fellow students is one of the primary reasons we're having you do this.

If you'd prefer to receive and give feedback before Spring Break, then submit to <a href="https://www.crowdgrader.org/crowdgrader/venues/join/919/teqodo_gevefy_vidiby_gupivo">the speed-track URL </a> by Friday @ 10 PM instead.