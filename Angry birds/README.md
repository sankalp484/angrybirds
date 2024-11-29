# Angry Birds

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

## Features

### Gameplay
- **Levels**:
    - **Level 1**: Simple glass structures with medium pigs.
    - **Level 2**: Combination of glass and wood structures with medium and small pigs.
    - **Level 3**: Mix of wood and rock with medium and big pigs.

- **Bird Types**:
    - 🟥 **Red Bird**: Standard bird with no special abilities.
    - 🟨 **Yellow Bird**: Boosts its speed mid-flight.
    - 🟦 **Blue Bird**: Splits into three smaller birds mid-flight.

- **Materials**:
    - 🟦 **Glass**: Breaks with one hit (low durability).
    - 🟫 **Wood**: Breaks with two hits (medium durability).
    - ⚫ **Rock**: Breaks with three hits (high durability).

- **Pigs**:
    - 🐷 **Small Pig**: Breaks with one hit.
    - 🐖 **Medium Pig**: Requires two hits.
    - 🐗 **Big Pig**: Requires three hits.

### Physics
- Powered by the **Box2D physics engine**, providing realistic trajectories, collisions, and object destruction.

### User Interface
- **Main Menu**:
    - Start a new game.
    - Access saved games.
    - Exit the game.
- **Level Selection**: Choose from three levels.
- **Pause Menu**:
    - Resume the game.
    - Replay the current level.
    - Return to the main menu.
    - Save game progress.
- **Win Screen**: Transition to the next level or return to the main menu.
- **Lose Screen**: Replay the level or return to the main menu.

### Dynamic Catapult
- A functional slingshot that allows players to aim by dragging and releasing.
- Displays trajectory prediction during aiming.

### Bird Abilities
- Each bird has a unique ability activated mid-flight by tapping the screen:
    - **Red Bird**: No special ability.
    - **Yellow Bird**: Boosts speed.
    - **Blue Bird**: Splits into three smaller birds.

### Saving and Loading
- Save game progress and load it later from the saved games menu.

### Loading Screen
- Displays a visually appealing progress bar during game loading, ensuring smooth transitions.

---

## Controls
- **Drag-and-Release**: Click and drag a bird to aim. Release to launch.
- **Tap Screen Mid-Flight**: Activate the bird's special ability.
- **Menu Navigation**: Use mouse clicks to interact with buttons.

---

## Setup and Installation

1. **Requirements**:
    - Java 8 or higher.
    - libGDX framework.
    - A suitable IDE (e.g., IntelliJ IDEA, Eclipse) or a Java runtime environment.

2. **Clone the Repository**:
    - Clone the project from the provided source or copy the source files.

3. **Run the Game**:
    - Import the project into your IDE and run the `Main` class.

---

## Project Structure

- **Main.java**: Entry point of the game.
- **Screens**:
    - `MainMenuScreen`: Displays the main menu.
    - `LevelScreen`: Level selection screen.
    - `PauseMenu`: Pause menu during gameplay.
    - `WinScreen`: Displayed upon completing a level.
    - `LooseScreen`: Displayed upon failing a level.
- **Levels**:
    - `level_1`, `level_2`, `level_3`: Level-specific setups.
- **Game Objects**:
    - `Bird`: Abstract class for all bird types.
    - `RedBird`, `YellowBird`, `BlueBird`: Specialized bird classes.
    - `Pig`: Abstract class for pigs.
    - `SmallPig`, `MediumPig`, `BigPig`: Specialized pig classes.
    - `Material`: Abstract class for materials.
    - `Glass`, `Wood`, `Rock`: Specialized material classes.
- **Physics**:
    - Powered by the Box2D library for realistic interactions.
- **Catapult**:
    - Handles slingshot rendering and mechanics.

**Enjoy playing!** 🎮🐦

