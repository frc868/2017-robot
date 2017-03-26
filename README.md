# FRC 868 - 2017 Robot "Ratchet"

![Travis CI Status](https://travis-ci.com/frc868/2017-robot.svg?token=2H69xuvDWYHku9YjLRkf&branch=master)

### Classpath Issues

To get the CTRE Library Javadocs, you have to manually add the directory to your `.classpath` file. The directory it is located in is user-specific. You may need to run the following command:

```
git update-index --skip-worktree .classpath
```

so that your changes to the `.classpath` file aren't committed. If you commit and push these changes, it will screw up everyone else's Javadocs, so please don't. I don't *think* you'll have to run this command, you should be able to just add the Javadoc yourself and the `.classpath` should already be skipped in the worktree, though I'm really not sure.

This was mainly an oversight by the CTRE dev team, as their installer didn't include an environment variable pointing to the Javadoc location.

### Memory Overflow Issues

We've been having some issues with the robot code using a huge amount of memory and causing the JRE to crash with an `out-of-memory exception`. The main issue is anything being constructed in the UpdateSmartDashboard commands and methods in subsystems. For some reason, the Garbage Collector will not free the "unused" commands that were constructed and they will continue piling up on the heap until it crashes.

tl;dr: **Don't construct commands (or anything) in a method that will be called repeatedly!**

### Git Guidelines

Features should always be done on a separate branch. **Don't** merge into master yourself, you won't be able to push your change; instead, make a pull request on github (or let us do it for you). 


While working on your branch, it is necessary to do regular `git merge master` to prevent merge conflicts from piling up. *Yes, this can result in 20+ commits labeled `Merge branch master...` before you end up merging your branch to master.* To prevent this, when the branch is finished (and tested!) you should run `git rebase master` to cleanup the history. Yes, you may have a few merge conflicts with this, but in the vast majority of cases (assuming your `git merge master` has already fixed the conflict before) you should be able to run `git rebase --skip` to fix the conflict right away. Lastly, to push the rebased branch, you must run `git push --force-with-lease origin <branch>`. **DO NOT run `git push --force`** as this is very dangerous!
