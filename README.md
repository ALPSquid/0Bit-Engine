0Bit-Engine
===========

LibGDX based game engine (currently a concept in progress)

The 0Bit Engine is a concept engine based on the libGDX game framework. It aims to manage the back-end game systems in a way that allows the developer to focus purely on gameplay. This means it could also be comfortably used for rapid prototyping and game jams like Ludum Dare as well as for projects that require more control by extending the subsystems that are already in place. This will all be documented further in development.

The engine is designed around the concept of subsystems which are accessed from a central resource. For example, there would be a manager that handles the loading, fetching and unloading of resources/assets and another manager that handles the creation, updating and disposing of enteties.

- Should easily manage (using singletons):
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
	- Physics (Box2D option)
	- Collision Detection (simple: custom/LibGDX, advanced: Box2D)
	
	
Reference/Inspiration:
 - LibGDX Wiki
	https://github.com/libgdx/libgdx/wiki
 - LibGDX Ashley
	https://github.com/libgdx/ashley/wiki/Framework-overview
 - FlashPunk
	http://useflashpunk.net/basic/flashpunk-basics.html
 - DeltaTime
	http://www.koonsolo.com/news/dewitters-gameloop/
