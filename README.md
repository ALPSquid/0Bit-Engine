0Bit-Engine
===========

LibGDX based game engine (currently a concept in progress). Note that this started out to save me from writing the same behind-the-scenes management systems everytime I go to create a game. It may or may not grow into something fully fledged but I am making everything as generic as possible so the option is always there.

The 0Bit Engine is a concept engine based on the libGDX game framework. It aims to manage the back-end game systems in a way that allows the developer to focus purely on gameplay. This means it could also be comfortably used for rapid prototyping and game jams like Ludum Dare as well as for projects that require more control by extending the subsystems that are already in place. This will all be documented further in development.

The engine is designed around the concept of subsystems which are accessed from a central resource. For example, there would be a manager that handles the loading, fetching and unloading of resources/assets and another manager that handles the creation, updating and disposing of enteties.

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
- Physics (Box2D option?)
- Collision Detection (simple: custom/LibGDX, advanced: Box2D(?))
- Lua Scripting


Currently Implemented
-----------
- **Base Game, World and Screen systems**
	-Allows creation of a a game with any number of Worlds and Screens (currently not Stage based unless you use libGDX directly).
	- Can optionally set the World units in pixels per unit for movement.
- **Creation of World specific entities**
	- Currently have a lot of (optional) built-in configuration and an automatic update() loop ready to use.
	- Has automatically managed top, bottom, left & right collision flags for simple directional collision handling.
	- Has default inbuilt states like Moving and Idle as well as optional physics specific states like Jumping and
Falling. You can use your own as well.
	- Sprites (textures) can be set and can have their dimensions changed within the engine, rather than having to scale your 'physical' assets.
	- Entity's are automatically disposed on world disposal
- **Animation System**
	- Animations can be created and set on a per entity basis with a single line of code.
	- Allows the creation of named animations from any number of sprite sheets including accessing specific frames in one method call
- **State System**
	- Automatically changes an entity's state to Moving/Idle as well as to Jumping or Falling (if the inbuilt physics type is set to platformer). Of course, you can use your own and will be able manage them all manually if you prefer.
- **Movement system** 
	- Velocity based system allowing the addition to or explicit setting of an entity's velocity.
	- Handles collisions during movement automatically.
	- Currently handles variable timesteps (primitively using simple Euler delta time integration) or fixed timesteps. In the future ta fixed timestep will be handled by setting a flag on game creation which will run a deWITTER style game loop.
- **Simple collision system**
	- Checks for collisions with a defined entity group. Also used by the movement system to handle collisions with user defined solid types like walls.
- **Physics System (optional)**
	- Allows entity's to use inbuilt physics systems to save you the hassle. 
	- Currently has 1 inbuilt physics type: Platformer which will apply world gravity and automatically manage the falling state and onGround flag of any object that uses this physics type.
- **Resource System**
	- Allows easy registration and retrieval of 'physical' resources like images and sounds.
	- Automatic disposal of registered resources.
	- Uses LibGDX's AssetManager for the backend to provide the option of asynchronous loading of resources.
- **Rendering System**
	- Has an inbuilt default renderer that renders all registered entities for the currently displayed world.
	- Viewport is by default set to the target width and height defined on game creation and uses a 'FitViewport' with automatic gutter (black bar) rendering with support for custom gutter textures. You'll be able to change these things easily.
	- Option to extend the default renderer class and implement your own functionality.
	- Inbuilt debug renderer that renderers all entity's bounds with configurable colours. This can be toggled on off with a user defined key.
	- Automatic camera following allowing you to specifiy and entity you want the camera to follow. You can also set the x & y min and max limits
- **Debug Overlay**
	- Built in overlay for monitoring values. By default the app version, FPS and Memory Usage are shown and you can track your own easily.
	- Togglable with SHIFT+user_defined_debug_key
- **Input System**
	- Automatic regisration of a global (Screen independant) input processor for use on everything but stages (they need registering manually, docs coming later). This allows the polling of pressed keys and provides access to GDX.input.isKeyJustPressed. Also allows registering multiple key maps which can be polled, e.g. ZeroBit.input().isKeyPressed("move_left") which could be A or left arrow key.
- **Logging System**
	- Has 3 options, Info, Debug and Error which are displayed depending on the value of log level.
	- Automatically appends the calling class' package, name, method name and line number to the message for easy debugging. E.g: "com.squidtopusstudios.zerobitengine.ZeroBitGame(dispose, ln 57): Exiting Game"
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
