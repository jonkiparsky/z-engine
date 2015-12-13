# Formatting #

**Feature To Implement**

**Current Status:** Not Started / In Development / In Testing / Done

**Date Started:** 01/01/2011

**By:** //sign

**Other Details:**
# To-Do List #

---

**Debug Mode**

**Current Status:** Not Started

**Date Started:**

**By:**

**Other Details:** So that designers are able to use commands to debug. Example would be to view layout of the game currently, listing all rooms and objects in the, with states. This will be expanded to include events and triggers. Other commands can be implemented for Debug mode.

---

**Restructure Packaging:**

**Current Status:** In Development

**Date Started:** 08/08/2011

**By:** Snow

**Other Details:** To restructure the source so that Engine and Game objects are separate. New structure will mean that designers can implement mechanics without needing to write code in the Engine source. Will allow for easier expansion.


---

**Long/short descriptions**

**Current Status:** Done

**Date Started:** 7th Aug

**By:** Snow

**Notes:** Implementation could be made better.

**Other Details:** Many games have two levels of description, where the first is a short description  ("You are in an office") and the latter is a more detailed description ("The walls are covered with pictures of a happy family, and there is a desk with lots of papers on it. One picture looks especially large and suggestive of something behind it")

---


**Invalid Input**

**Current Status:** Done

**Date Started:**8/6

**By:** jpk

**Other Details:** Implement handling of invalid input so that it doesn't produce run-time errors.


---


**Abbreviations**

**Current Status:** Done

**Date Started:** 7 August

**By:** jpk

**Other Details:** Abbreviations for common commands. 'n' for north, 't' for take, etc.

Implemented by a properties file, macros.properties, so easy to customize for new games
Properties files useful for externalizing strings.


---


**Externalize strings**

**Current Status:** Not Started

**Date Started:**

**By:** //sign

**Other Details:** No literal strings should be stored in source code.


---


**Logging System**

**Current Status:** Started

**Date Started:** 8 August

**By:** jpk

**Other Details:** So that error messages don't contain whole stack traces with exceptions. Makes debugging easier.

Reviewing logging, at least. It'll be a few days before anything turns up here.