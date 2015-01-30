0bit-Engine
===========

**Refactor in Progress** See 'refact' package for progress

**Early Stages**: Note that the engine is in very early stages and some aspects may not be suitable for your game, if something needs adding/changing, please let me know by contacting me or raising an issue!

LibGDX based game engine. Note that this started out to save me from writing the same behind-the-scenes management systems everytime I go to create a game. It may or may not grow into something fully fledged but I am attempting to make everything as generic as possible so the option is always there.

The 0bit Engine is an engine based on the libGDX game framework. It aims to manage the back-end game systems in a way that allows the developer to focus purely on gameplay. This means it could be comfortably used for rapid prototyping and game jams as well as for larger projects that require more control by extending the subsystems that are already in place or accessing libGDX directly. This will all be documented further in development.

I aim to keep the underlying engine concepts (Screens etc.) in line with libGDX as much as possible.


**Should easily manage:**
- Screens
- Assets
- Rendering 
- Debugging overlays and renderers
- World
- Input (including Controller support)
- GUIs
- Entities using Ashley
- Particles
- Logging (wrapper for the inbuilt LibGDX logging system)
- Physics (Box2D)
- Collision Detection (simple: custom, advanced: Box2D)
- Lua Scripting


Currently Implemented
-----------
- **Base Game, World and Screen systems**
	- Allows creation of a game with any number of World and Screens. Screens contain a reference to one World at a time but can have any number of views (UIs) active at once.
	- Worlds use unit based positioning and provide methods for converting between pixels and units.
	
- **Screen UIs**
	- Uses an MVC-like architecture with Views, such as a HUD, being able to observe Models, such as the player entity. Push and pull are available to observers.

- **Creation of World specific entities**

- **Animation System**

- **State System**

- **Movement system** 

- **Simple collision system**

- **Physics System (optional)**

- **Resource System**

- **Rendering System**

- **Debug Overlay**

- **Input System**

- **Logging System**
	There is an abstract logging class that can be extended to implement custom logging desitnations. By default, the Zbe class contains a static reference to a ConsoleLogger which, as you probably guessed, logs to the console using Gdx.app.log_type. The Logger constructor takes an optional Logger argument to chain loggers together. For example, you could pass a FileLogger to ConsoleLogger which would result in messages being printed to the console and a file. 
	Messages are in the format: {package.class}({method}, {ln}): {Message}

- **More Coming Soon**

	
Reference/Inspiration:
 - LibGDX Wiki
	https://github.com/libgdx/libgdx/wiki
 - LibGDX Ashley
	https://github.com/libgdx/ashley/wiki/Framework-overview
 - FlashPunk
	http://useflashpunk.net/basic/flashpunk-basics.html
 - DeltaTime
	http://www.koonsolo.com/news/dewitters-gameloop/
