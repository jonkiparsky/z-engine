# Introduction #
Following this section, jot down thoughts, ideas, potential hurdles, possible features as they come to you. Dating and signing them would be helpful.
If there's an actual bug, it should go in the bug tracking.


# Notes #

**22/08/2011 (EDT) - jpk:** The most obvious answer would be some sort of mini-state machine, but that's sort of antithetical to the project as a whole. The answer that's more consistent with the project might lie in the Context idea. If I enter "yes", the machine should look for a question to answer. If there's a question, it applies the answer to it. Questions would have to expire, so you need a sense of time, but maybe I can do something with this. My notion is to store the Context in the State, but we should keep alive the possibility of extracting it to its own class if that seems sensible.

**23/08/2011 (GMT) - Snow:** So I'm curious as to how we're going to manage questions. So an input would prompt a question, which would require further input. I'll have a think about it. It may be easy though, and I just can't think of it right now. The main problem with how I'm imagining it is that when we execute a command, we can't then parse further input to use in that execute method. I found this through trying to implement a Confirm Quit, and can't think of a way to do it. Thoughts?

**8/8/2011 (EDT) - jpk:** I'd suggest that items which require discussion get their own page ie, for debug mode: what does debug mode look like? Does it tie in with logging? Do we have different debug modes for developing the engine and for game designers? All of this can be discussed on a separate page, and the discussion will be preserved in the page history - email discussion is useful, but ephemeral. Items which don't require such substantial discussion can just go on the bug list, and if you don't like the way I implement it you can go in and tweak it. Which is another sort of discussion, and is also preserved in the repo.

**8/8/2011 (GMT) - Snow:** I think that tracking the TODO list in the Issues system would probably be a good thing. It provides a place to provide feedback and discussion on the specific topic in a centralized place, and keeps track of progress as well. Possibly do away with TODO list and just use the issues system?

**8/5/2011 (GMT) - Snow:** So far, Phrases for Nouns works okay, from the little I've tested it. It doesn't even matter which order you have input, so Turn On Flashlight and Turn Flashlight On will both work. Although, so will Flashlight On Turn, so that will need to be fixed. There's likely more errors that will need to be handled that I haven't found yet as well. Next step is to try and incorporate PPhrases so that you can add on to the NPhrases to make sure it works as planned.

**8/4/2011 (EDT) - jpk:** TO DO - logging. Would be nice to be able to log errors and not have to get the whole stack trace on an exception.


**8/4/2011 (EDT) - jpk:** Interesting. I'll let it bubble around a bit. Technically, the "it" should be able to pick up the best NP in the available context and apply it to the PPhrase.

**8/5/2011 - i.Snowblind:** Possible implementation of Prepositional Phrases. Essentially, through using 3 phrase types (Noun Phrase, Preposition Phrase, Prepositional Noun (wording?) Phrase), the parser should be able to determine context of prepositions. Example -- "Turn on the flashlight" would be a Noun Phrase, but "Put flashlight on the table" would be a Prepositional Noun Phrase + a Preposition Phrase, split in to "Put flashlight" and "on the table". In theory, this should work. This doesn't include Preposition stranding though ("Take sword and kill troll with it", 'it' being the stranded preposition), so may need changing if this is to be included.

**8/4/2011 - jon.kiparsky:** Unit testing might be a good idea, but so far it hasn't risen to the top of my stack. This is always the problem with unit testing: you never get to it until you're well into things. Not sure what the tests would test, though, because I don't have requirements.