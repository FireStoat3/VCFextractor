## VCFextractor
**A simple program to extract data from Virtual Contact Files (vCard Files) and save them to a simple text file**

## How to compile?

> [!IMPORTANT]
> VCFextractor has been written using Java 21. Please be sure to install a Java 21 JDK before continuing with the compilation.
> The OpenJDK JDK is recommended: you can obtain it from the [official website](https://openjdk.org/).

**1. Compile the .java files**
- Open a terminal window and move to the root of the project
- From the root of the project execute the following command to compile the files:
  
   ~~~
   
   javac ./src/com/github/FireStoat3/VCFextractor/*.java ./src/com/github/FireStoat3/VCFextractor/gui/*.java -d ./bin/
   
   ~~~
**2. Create the .jar file**
- Open a terminal window and move to the root of the project
- Execute the following command:
  
  ~~~

  jar -c -f VCFextractor.jar -m ./META_INF/MANIFEST.MF -C ./bin ./com/github/FireStoat3/

  ~~~

## How to run the program?
> [!IMPORTANT]
> As written in the "How to compile?" section, this program has been written with Java 21

> [!NOTE]
> VCFextractor.jar is the name of the jar created by following instructions in the "How to compile?" section

There are two ways for running the program:
1. Simple double click on VCFextractor.jar (view the "How to compile?" section for more information)
2. Open a terminal window and move where the .jar file is, then run the following command:
   
   ~~~

   java -jar VCFextractor.jar

   ~~~

## How to use the program?
> [!CAUTION]
> During the use of the program a window with a progress bar could open and close automatically.
> If the vCard file has very few contacts the window will open and close very quickly
 
Using the program is very simple, you just need to do five things:

1. Open the program
2. Read the welcome screen and press "OK"
3. Press "Select VCF file" button and select your vCard file
4. Read the file by pressing the "Read File" button
5. Save the data in a text file by pressing the "Save" button

A text file with the name you chose will be created in the directory you selected in the save dialog.
> [!NOTE]
> The program does not add the file extension (.txt) automatically
