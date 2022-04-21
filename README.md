## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Su vscode : 

* 1) Installare le seguenti estensioni :
- `Extension Pack for Java`
- `Gradle for Java`
- `Scene Builder(impostare il path per l'eseguibile)`

* 2) Creare la cartella .vscode (se non già presente)
>>>creare due file all'interno della cartella:

# 1
- - "launch.json"  deve avere la seguente struttra
#  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - #
    {
        // Use IntelliSense to learn about possible attributes.
        // Hover to view descriptions of existing attributes.
        // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
        "version": "0.2.0",
        "configurations": [
            {
                "type": "java",
                "name": "Launch Current File",
                "request": "launch",
                "mainClass": "main.java.App",
    <windows/>"vmArgs": "--module-path "\path\to\javafx-sdk-17.0.1\lib" --add-modules javafx.controls,javafx.fxml", 
    <mac/linux/>"vmArgs": "--module-path /path/to/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml",
            }
        ]
    }
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - #
* Sostituire /path/to/javafx-sdk-17.0.1/lib con il path corrispondente! (Va bene anche fx-sdk 18)

# 2
- - "settings.json"  deve avere la seguente struttra
#  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - #
    {
        "java.project.sourcePaths": ["src"],
        "java.project.outputPath": "bin",
        "java.project.referencedLibraries": [
            "lib/**/*.jar"
        ]
    }
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - #
`Fine`