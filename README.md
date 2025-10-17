Co-Owned by: Bosco Zhang, Nihal Sidhu

# ☄️ Projectile Motion

A 2-dimensional projectile motion simulator built with Java swing. This project utilizes core object-oriented design concepts 
like the observer design pattern to develop an interactive graphical user interface (GUI).

## 🚀 How to Run (Setup)

1. **Java Installation:**
   First and foremost, ensure that you have Java installed. You can check this by running the following:
   ```
   java -version
   ```
   
   If Java isn't installed, you'll receive an error, to which you will run the following:
   
   ```
   brew install openjdk
   ```

3. **Clone the repository:**
   ```
   git clone https://github.com/BoscoZhangers/ProjectileMotion.git
   ```

3. **Enter the repo:**
   ```
   cd ProjectileMotion
   ```

4. **Run: In `ProjectileMotion/` run:**
   ```
   java -jar ProjectileMotion.jar
   ```
### ${}$


## 🧠 Design Notes

This simulator makes use of the observer design pattern by defining an AnimationPanel object with Java Swing interactable components (ex. buttons, sliders, etc...). 
The AnimationPanel acts as a listener to these subjects which will emit an event when the user attempts to change the state (settings of the cannon) through the GUI components. 

By incorporating a solid design pattern that makes use of object definitions, effective modularity and high encapsulation, our project is both maintainable and extendable
to easily support future changes, improvements or additions.

We made an active effort to design our program as defensively as possible with a consistent use of exception handling to address hypothetical shortcomings or errors (such as graphical elements not being found, invalid user input, etc...).
