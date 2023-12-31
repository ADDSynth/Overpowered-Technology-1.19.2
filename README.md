
# Introduction
Welcome to the source code repository for the Overpowered Technology Minecraft mod, by ADDSynth.

The source code is licensed under GNU GPL v3.0 or Later, with The Commons Clause.

**Last Updated:** April 10, 2023

<!-- a double space after a line = a line break -->

<!-- ***This Readme file outlines many details regarding this source code project.*** -->
<!--
   Since this file was recently created, the contents of this file may
   not be entirely accurate (especially in the License section) and thus subject
   to change in the future. ***

-->

The source code, including this file, will continually be improved upon as time goes on.

Owner  
: ADDSynth

* [Links](#Links)
* [About the Project](#About-the-Project)
* [How to Build](#How-to-Build)
* [Format Rules](#Source-Code-Formatting)
* [License](#License)

# Links

You can Download Overpowered Technology on CurseForge.  
[Download on CurseForge](https://www.curseforge.com/minecraft/mc-mods/overpowered)

For information about the mod itself, you can view the  
[online reference for Overpowered Technology](http://www.gyrostudiostechnology.com/minecraft/mods/overpowered/index.html).

We are currently searching for a new platform to host the online reference allowing users to
edit pages and allowing multiple languages.

You can help translate this mod! All my projects are now on [CrowdIn](https://crowdin.com/profile/addsynth).
You will need to create a free CrowdIn account to translate through CrowdIn.  
However, I prefer you translate directly through Github by submitting pull requests
with updated [language files](https://github.com/ADDSynth/Overpowered-Technology-1.18/tree/main/src/main/resources/assets/overpowered/lang).

If you wish to support the development of Overpowered Technology, consider donating.
To donate with PayPal, click the following link. A PayPal account is required.
This is temporary as we're currently in the process of switching to a new method
of donating.  
[https://paypal.me/ADDSynth](https://paypal.me/ADDSynth)

Any videos about Overpowered Technology I'll post to [my channel on YouTube](https://www.youtube.com/channel/UC1JNtz7hUU5xfF4QZPv1hbg).

# About the Project

I'm still not all that familiar with how Github works. But I'm pretty sure you can clone
the repository and make pull requests if you think there's something you can improve
about the mod. Please, please, please, please make your pull request for the LATEST
Minecraft version. I have ways of copying your code down to the other Minecraft versions.

For feature requests click on the Issues tab and make an Issue about it.

If you are intent on becoming an active developer for this or other Github projects, then
we recommend you download and use the [Github Desktop](https://desktop.github.com/) program.
You can use it to easily make commits, pull requests, or branches.

<!--
Although I suppose its possible to have multiple Github branches, one for each version
of Minecraft like most mod developers do, but I never liked to do that. I feel there are
significant changes from one version of Minecraft to the next, and each version should
be their own project.
-->

I'm slowly starting to understand the importance of having more than one branch,
but until then, I only work on the Master branch, and I often overwrite the commit history to fix
something in the last commit I submitted, so I recommend you try to stay a few commits
behind if possible.

We use several additional Task Tags rather than the standard TODO.
Please configure your IDE to recognize these Task Tags.

| Task Tag | Priority | Description |
|----------|----------|-------------|
| FIX      |   High   | When I know for sure something causes a crash that needs fixing.            |
| PRIORITY |   High   | Something that needs to be worked on next or soon, before the next release. |
| FUTURE   |  Normal  | Describes features I plan to add in the future, but can't right now.        |
| OPTIMIZE |  Normal  | Code that can be upgraded or improved.                                      |
| REMOVE   |  Normal  | Code that is scheduled to be removed at a later time. Must specify when to remove.       |
| TEST     |  Normal  | Further testing is required to see if there's a better way to do something. |
| DELETE   |   Low    | Commented code that can be deleted when I'm sure I won't miss it.           |
| FEATURE  |   Low    | Describes a feature that would be nice to have, but ultimately not needed at the moment. |
| MAYBE    |   Low    | Suggests an alternative. |
| NOTE     |   Low    | Leaves a note about the code, so I can find it later. |
| REPLICA  |   Low    | Code that is an exact duplicate of someone else's code, because I need a custom version. Must check for any differences each time the external code is updated. |
| UNUSED   |   Low    | Code that was added, but doesn't do anything. Safe to leave as is. |

# How to Build

<!--
First, go to [Minecraft Forge Files](https://files.minecraftforge.net/) and download the **Mdk**
for the version of Minecraft you want.

Extract the contents of the zip file to your preferred location. The resulting folder now becomes
your project folder.
-->

Looks like you just need to download a copy of this repository and extract the zip file to
your preferred location.

Use a Console, Terminal, or Command Prompt to navigate to the folder you extracted.

**To Build:**  
Just type \'gradlew build\' and the output .jar file will be in build/libs.

**To setup Development workspace:**  
Type the command \'gradlew eclipse\', or replace eclipse with your preferred IDE. You can type
\'gradlew tasks --all\' to get a list of all Gradle tasks.

# Source Code Formatting

Source code formatting rules have been separated into their own text file to promote reuse
in other projects.

We prefer the source code be formatted in a specific way, but we're not strict about it.
We won't decline or turn away pull requests if they do not follow these format rules.
I'll probably just reformat it myself later in the future anyway.

# License

This source code is licensed under the GNU General Public License v3.0 (or later) with The Commons Clause.  
[GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0-standalone.html)  
[The Commons Clause](https://commonsclause.com/)

If you're just wondering if you can include this mod in modpacks, the answer is YES, as long
as you credit ADDSynth as the author and provide a link back to the CurseForge project page.

If you're in a hurry and don't want to read the full license\(s\), here's a quick rundown of what you can and can't do:

1. You may copy any part of the source code to use in your own projects,
   as long as it's a small part or sample. Giving credit is not required but appreciated.
   My code then becomes a part of your project and you are free to do with as you wish.
2. You are NOT allowed to copy large or significant portions of the source code to use in
   your own projects, or claim it as your own code. If you are unsure what constitutes
   'a large portion' then ask me first.
3. You are NOT allowed to sell any portion of the source code, or the entire source code,
   or its compiled form. You are not allowed to make any profit from my work.
4. You are allowed to clone the repository and make derivatives of the original source code.
   However, you must explain that this is a modified version of the original. You must credit
   the original source repository. You are not allowed to make any profit from derivitive works.
   And any derivatives must be licensed under a similar or more restrictive license.
5. If you just want to make one change or fix, or otherwise improve upon the source code,
   it might be better to make a pull request or feature request, so that it gets included into
   the main project. Anyone is allowed to make pull requests and/or make changes to the source
   code, subject to verification by the owner (ADDSynth). Once approved your pull request
   becomes a part of the commit history, which states WHO did WHAT, and WHEN. Your source code
   then becomes a part of this project, and owned by ADDSynth. Anyone who makes significant
   contributions to this project will be listed in the Credits section above.
