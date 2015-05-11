0bit-Engine
===========

Development is taking place on the [dev branch](https://github.com/ALPSquid/0Bit-Engine/tree/dev)

**Early Stages**: Note that the engine is in very early stages and parts are being refactored regularly.

The 0bit Engine is an engine based on the libGDX game framework. 
It aims to provide a solid, modular game architecture as well as many components that work out of the box. 
It should be comfortable to use for rapid prototyping and game jams as well as for larger projects that require more control.


Currently Implemented
-----------
- **Base Game, World and Screen systems**
	- Allows creation of a game with any number of Worlds and Screens. Screens can have any number of views (UIs) active at once.
	- Worlds each have an Ashley engine instance which can be used with the multitude of systems and components 0bit comes with.
	- World entities can be created programmatically or be loaded asynchronously from a file (such as an Overlap2D project).
	- Box2D and non-Box2D entities can exist at the same time
	- Comes with a loading and screen caching system
	
- **Screen UIs**
	- Uses an MVC-like architecture with Views, such as a HUD, being able to observe Models, such as a player entity. Push and pull are available to observers.
	- Views use a Scene2D stage and supports loading from a file (such as an Overlap2D project)
	- Includes custom actors for sprite animations and particle effects

- **Ashley Systems**
	- **Animation**: Manages sprite and Spriter animations
	- **Movement**: Moves Box2D and non-Box2D entities
	- **Physics**: Manages a Box2D world with included physics filters
	- **Box2D Lights**: The Box2D system includes a RayHandler for dynamic lighting
	- **Day-Night Cycle**: Simulates day-night cycles with skybox image rotation. Currently only supports the Box2D RayHandler
	- **Rendering**: Renders worlds using z-index sorting. Supports Box2D and non-Box2D entity rendering in the same world.
	- **AI**: Updates AI Controllers which can have any combination of Steerables and Behaviours. 
	Currently includes Patrol (movement between nodes), Wander (Random movement between nodes) and Proximity (entity detection) behaviours
	- **Messaging**: Allows entities to observe other entities as well as be observed by views
	- **Parallax**: Allows a parallax value to be applied to any entity. Movements are relative to the camera
	- **Camera**: Allows the camera to follow a specific entity with optional boundaries

- **Input System and Key Maps**
	- *Architecture is currently being changed*   
	Provides an input system that maps keys/controller buttons from a keymap to game actions which are then dispatched to observers.
	
- **Resource System**
	- Provides an asset manager instance for loading assets, including maps/levels, asynchronously while a loading screen is displayed and updated
	- Provides utility functions for creation bitmap fonts and sprite animations

- **Debug Overlay**
	- A UI View that provides resource information such as memory usage and world entities as well as the Bo2D Debug Renderer.

- **Logging System**
	- Uses the decorator pattern for logging. Comes with a default console logger.
	- Messages are in the format: {package.class}({method}, {ln}): {Message}

- **More Features and Examples Coming Soon**
    
    
	
Reference/Inspiration:  
 - LibGDX Wiki  
	https://github.com/libgdx/libgdx/wiki  
 - LibGDX Ashley  
	https://github.com/libgdx/ashley/wiki/Framework-overview  
 - FlashPunk  
	http://useflashpunk.net/basic/flashpunk-basics.html  
 - DeltaTime  
	http://www.koonsolo.com/news/dewitters-gameloop/  
