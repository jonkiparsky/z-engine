# Introduction #

It's easier to work towards a defined target, so this page should help us define that target
Please edit this for better clarity or better concordance with your own ideas. Also, please rewrite any first-person or second-person reference you find. This should end up as a statement of policy, not of opinion. :)

# Goals of 0.1 Release #
  * a build of the code that an interested game writer could use to develop a minimal game, and an interested programmer could use to study our use of reflection in parsing
  * a benchmark release: the minimal build
  * useful practice in assembling a build - we'll probably do some things wrong, we might as well do them wrong sooner rather than later.
  * if it attracts interested parties, great

# Desired features #

(for discussion: please edit, whether to expand on the point or to disagree, or whatever)

  1. package structure that's easy to work with
  1. effective javadoc or other documentation, which means
  1. exposing only the methods we want to expose, might have to review access on some methods
  1. core "kernel" commands available to allow developers to customize their interface. We don't need to be completist about it, but this is worth some serious thought. For example, we have a go() method which allows the game to transfer a player to an adjacent room. What if Dougie the Designer wants to teleport a character to an arbitrary room? This kernel could be a lot of fun to develop, but it should be done carefully, and with some discussion. I suggest entering either use cases ("we should enable teleporting") or exposed methods (move()) as "enhancements" on the issues list
  1. tutorial
  1. small sample game which exercises the features


# Non-critical features for 0.1 #
Things I'm not very worried about for 0.1:

  1. end-user satisfaction: This is for developers to play with, we shouldn't get too hung up trying to make it shiny at the expense of things we care about. (though any misfeatures should be entered on the bug list, and prioritized correctly)
  1. perfection: this will be a 0.1 release after all... should be good, but perfect is maybe not needed just yet
  1. speed: I'm also in no rush to push something. I'd like to make a build available when it's worth doing, not "by next Thursday".

# Additional #

Maybe putting together a tutorial on how to use it for 0.1 would be good. I'm not sure whether doing this early will be good or not, but I imagine it will be easier to extend as features are added, than doing it once there is a lot to cover.

Javadocs would be easier than other documentation I imagine.

RE: extension of commands. I think it would be good to have a base of standard commands, and then allow users to extend and add their own based on their needs. Say, for example, one designer wants a medieval setting, so there's torches, swords, etc. They'd set alight to the torch. But another designer wants to do a sci-fi, so he'd have commands that are unique.

Agreed: the designer should have a small set of default commands (listed below) and a flexible set of tools to manipulate the environment.

## Default command list: ##
  * directions
  * go
  * take
  * drop
  * inventory
  * search
  * etc.

## API hooks ##
  * getInventory() : returns List of items in player's possession
  * move(Room r): transfer to a given room
  * echo():  Display a message
  * roomContents(): returns List of visible items in the room
  * roomExits(): returns List of visible exits
  * etc.

It seems likely that we'd end up rewriting the default commands in terms of this API, so formatting of the "inventory" command would move out of the State and into Inventory.java. For example, inventory.execute() would getInventory(), do the formatting and build a String, and do an echo() to put the information on the screen.