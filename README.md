# My Study
A student productivity desktop application, with apps such as a to-do list, pomodoro timer and drawboard all accessible from a central dashboard.
## Creators
Created by the University of Auckland SoftEng310 group 5, the Council, for project 1. 
## Features
Key features:
- Central dashboard
- To-do list
- Pomodoro timer
- Drawboard  

Nice to have features:
- Notes
- Calendar integration
- PDF reader and annotator
- Flashcards
- Spotify integration 

## Getting Started
This project was built in Eclipse using Java.
For best performance and support, use the Eclipse Java IDE.
You'll also need to download [JavaFX](https://openjfx.io/openjfx-docs/), and the e(fx)clipse plugin, downloadable from the inbuilt Eclipse marketplace.
Lastly, download the extra .jar dependencies from [here](https://app.mediafire.com/nz54j5hnq1u5n)

Project Setup:
- Once you've opened up the project in Eclipse, go to the Project's Properties -> Java Build Path -> Module -> Add Library -> User Library
- Click on the "User Libraries..." button, and create a new user library called "JavaFX"
- Select "Add External JARs...", and select all of the .jar files in the JavaFX library folder, as well as the extra dependencies.
- Go to the project's Run Configurations -> Arguments, and add "--module-path "\path\to\javafx-sdk-18.0.2\lib" --add-modules javafx.controls,javafx.fxml

Note: Your JavaFX version may be different, make sure to check the library folder and edit the version in the arguments accordingly

## Built With
-	Java
-	JavaFX
-	Scenebuilder
- CalendarFX Api
## Contributing
Check out our [CONTRIBUTING.md](CONTRIBUTING.md)
## License
This project is licensed under the Apache 2.0 License, [LICENSE](LICENSE)
