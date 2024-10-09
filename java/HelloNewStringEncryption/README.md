
# HelloNewStringEncryption

**HelloNewStringEncryption** is a sample Java project created to demonstrate a new string encryption solution. This project uses Apache Ant as the build tool to compile, package, and run the application.

## Project Structure

```
HelloNewStringEncryption
│
├── src                 # Source files directory
├── build               # Compiled .class files directory
├── dist                # Directory for the JAR file output
└── build.xml           # Ant build file
```

## Build and Run Instructions

### Prerequisites

- **Java 11** or higher must be installed and configured.
- **Apache Ant** must be installed and added to your system’s PATH.

### Setting Up the Environment

1. Ensure that the `JAVA_HOME` environment variable is set to your JDK 11 installation.
2. Add `$JAVA_HOME/bin` to your system’s PATH if it’s not already included.

### Using Ant Targets

The `build.xml` file provides the following targets:

- **clean**: Cleans the `build` and `dist` directories, removing compiled classes and the generated JAR file.
- **compile**: Compiles the Java source files from the `src` directory and outputs the `.class` files into the `build` directory.
- **jar**: Packages the compiled `.class` files into a JAR file named `HNewStringE.jar` in the `dist` directory, with the main class set to `org.example.Main`.
- **run**: Compiles the source files and runs the main class (`org.example.Main`) directly from the `build` directory.

### How to Build and Run

1. **Clean the project**:
   ```bash
   ant clean
   ```

2. **Compile the project**:
   ```bash
   ant compile
   ```

3. **Create the JAR file**:
   ```bash
   ant jar
   ```

4. **Run the application**:
   ```bash
   ant run
   ```

5. **Run the JAR file** (after creating it with `ant jar`):
   ```bash
   java -jar dist/HNewStringE.jar
   ```

## Description

This project is a sample application demonstrating a new approach to string encryption. It includes sample classes and a main method designed to illustrate the new encryption solution's functionality.

## Notes

- **Main Class**: The main entry point is `org.example.Main`, as specified in the manifest.
- **Java Version**: Make sure to use Java 11 for compatibility.
