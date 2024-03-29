# Remote-Desktop-Application
## Application
This is a simple remote desktop application. It has some the same functions as Teamviewer.
>**This project is inspired from this [project][inspired project]**

## Features
* Control the computer (**Main function**)
* Send file 
* The chat function will add on in the future.

## Requirements
* Operating System
    * Windows
    * Linux
    * MacOS
* Java
    * Oracle JDK (recommended)
    * others

## Start the Project

* **Step 1**: Go to the project using terminal (use cd command)
* **Step 2**: Compile project `javac -sourcepath SOURCE_FOLDER -d COMPILED_FOLDER *.java`
    * SOURCE_PATH: the folder contains all source code of project. In this case, it is `.`
    * COMPILED_FOLDER: the folder contains all compile files (*.class) of project. In this case, I recommend use CompiledFolder in this project
    * So the command will be `javac -sourcepath . -d CompiledFolder *.java`
* **Step 3**: Run the project using `java` command in Compiled Folder. Example:
    * `cd` to CompiledFolder
    * Run command `java Main`

**This is a result:**
* Remote Control Function
![][remote control function] 
* FTP Server Function
![][ftp server function]

## Demo
This is [demo video][demo link]

## Discussion
>This is the first long Java project and I coded it by myself. So there are many shortcomings.

* This project lacks many functions such as: chat, prevent computer shutdown, register the account for user, etc...
* Beside It has many bugs such as: can't catch some keys: Win key,...; some function will be locked when system app is started, to reduce these bugs you must lauch app as `Administrator`
* FTP Server function has a bug which can't update file list by itself. FTP Server only can send 1 file at a time, so it is very inconvenient for those who want to send many files at once 

In the future, I will add chat function to this project. It will allow 2 persons send and receive the message to each other

## Suggestions
Thanks for your interest in my project. If your have any questions, please feel free to contact me at any time



[//]:<> (===================================================================)
[//]: <> (This is a variable definition area)

[remote control function]: ./Assets/remote_control.png
[ftp server function]: ./Assets/ftp_server.png
[inspired  project]: https://github.com/rajpushkar/Remote-Desktop-Control
[demo link]: https://www.youtube.com/watch?v=WdxRDTKv8M0