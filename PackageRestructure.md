# Package Restructuring #

---

## What is it? ##

Restructuring of source so that users can create games without having to delve in to Engine code.

## What will change? ##

Rooms, Items and Commands will be added outside Z-Engine, then implemented in. Additional features will also follow this design.

## How will it effect development? ##

Users will be able to easily create objects and integrate them with their game, whilst the Engine can parse them correctly.

## Other ##

Rooms and Items shouldn't be hard to externalize. Commands, on the other hand, will be difficult with the current way of doing things, as the primitive commands that are executed are in the Engine. Thoughts about how to change this?

## Done So Far ##
Restructuring of Rooms and Nouns (Items). Currently, you can create rooms and nouns by importing zengine.Noun or zengine.Room. I don't think verbs/prepositions will be that difficult to implement, in all honesty, as the way I've done it with nouns will work with the others. If there's a ClassNotFoundException while tokenising, then I check the external package provided while initializing Model. This allows us to implement default verbs and prepositions, whilst allowing the designer to implement their own. How they're going to implement their own, I don't know yet..