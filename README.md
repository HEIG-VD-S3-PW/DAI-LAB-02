# File Encryption CLI

### Version 1.0.0

A command-line utility to encrypt and decrypt files securely using AES or DES encryption algorithms. The application allows you to easily encrypt files with a passphrase and choose between AES or DES for encryption.

## Table of Contents
- [Features](#features)
- [Requirements for using the application](#requirements-for-using-the-application)
- [Installation](#installation)
  - [Download the JAR File](#download-the-jar-file)
  - [Clone the Repository](#clone-the-repository)
- [Usage](#usage)
  - [Available Commands](#available-commands)
  - [Options](#options)
  - [Help Command](#help-command)
- [Examples](#examples)
  - [Encrypting a File](#encrypting-a-file)
  - [Decrypting a File](#decrypting-a-file)
- [Command Summary](#command-summary)
- [Contributing](#contributing)
  - [Add a new encryption/decryption algorithm](#add-a-new-encryptiondecryption-algorithm)
- [Credits](#credits)

---

## Features
- **AES and DES Encryption**: Choose between AES and DES encryption algorithms.
- **Passphrase Support**: Provide your own passphrase or let the application generate a random one.
- **File Encryption/Decryption**: Securely encrypt and decrypt files.
- **User-friendly CLI**: Easy to use with clear options and commands.

---

## Requirements for using the application
- **Java**: You need to have Java 21 installed.

---

## Installation

There are two ways to install the application: download the JAR file or clone the repository and build the project from source.

### Download the JAR File

You can download the JAR file from the releases page. The JAR file contains all the dependencies and can be run on any system with Java installed.

### Clone the Repository

To get started, you can clone the project repository to your local machine:

```bash
git clone https://github.com/HEIG-VD-S3-PW/DAI-LAB-02

cd DAI-LAB-02
```

Build the Project with Maven

Once you have cloned the project, use Maven to build it:

```bash
mvn clean install
```

After a successful build, the jar file will be created in the target/ directory:

```bash
target/DAI-LAB-02-1.0-SNAPSHOT.jar
```

## Usage

The application is a CLI tool that can encrypt and decrypt files. You can choose between AES or DES encryption algorithms and provide a passphrase or let the application generate one for you.
Available Commands

    encrypt: Encrypts a file using AES or DES.
    decrypt: Decrypts a previously encrypted file.

Options

    -a, --algorithm: The encryption algorithm to use (AES or DES). Defaults to AES.
    -p, --passphrase: Passphrase for encryption/decryption. If not provided, a random passphrase will be generated.
    -o, --output: The path of the output file. (Default: same directory as the input file).

Help Command

You can use the help command to get information about the available options and commands:

```bash
java -jar target/DAI-LAB-02-1.0-SNAPSHOT.jar --help
```

Output:

```vbnet

======= File Encryption CLI =======

Encrypt and decrypt files securely using AES or DES.

Usage: pw-2-1.0-SNAPSHOT.jar [-hV] [-a=<algorithm>] [-p=<passphrase>]
                             <filename> [COMMAND]

Description:
A command-line utility to encrypt and decrypt files using AES or DES encryption
algorithms.

Arguments:
      <filename>   The name of the file.

Options:
  -a, --algorithm=<algorithm>
                   The algorithm to use (Possible values: DES, AES (Default:
                     AES).
  -h, --help       Show this help message and exit.
  -o, --output=<outputPath>
                   The path of the output file. (Default: same directory as the
                     input file).
  -p, --passphrase=<passphrase>
                   The passphrase to use (Will be randomly generated if left
                     empty).
  -V, --version    Print version information and exit.

Commands:
  encrypt, enc, e  Encrypt a file with the specified algorithm and passphrase.
  decrypt, dec, d  Decrypt a file using the specified algorithm and passphrase.

Credits: Tristan Baud and Mathieu Emery

```

## Examples
### Encrypting a File

To encrypt a file, specify the algorithm (AES or DES), the file name, and an optional passphrase:

```bash
java -jar target/DAI-LAB-02-1.0-SNAPSHOT.jar -a=DES -p=mysecretpassphrase test.txt encrypt
```

Output:

```arduino
File encrypted successfully using DES.
```

If no passphrase is provided, a random one will be generated:

```bash
java -jar target/DAI-LAB-02-1.0-SNAPSHOT.jar -a=DES test.txt encrypt
```

If you want to specify an output folder for the decrypted file, you can use the -o or --output option:

```bash
java -jar target/DAI-LAB-02-1.0-SNAPSHOT.jar -a=DES -o=outputs test.txt encrypt
```


Output:

```arduino
The randomly generated passphrase has been copied to your clipboard.
File encrypted successfully using DES.
```

### Decrypting a File

To decrypt an encrypted file, specify just the file name and the passphrase used for encryption (if the passphrase is not specified, the application will ask for it):

```bash
java -jar target/DAI-LAB-02-1.0-SNAPSHOT.jar -p=mysecretpassphrase test.txt.des decrypt
```

If you want to specify an output folder for the decrypted file, you can use the -o or --output option:

```bash
java -jar target/DAI-LAB-02-1.0-SNAPSHOT.jar -p=mysecretpassphrase -o=outputs test.txt.des decrypt
```

Output:

```arduino
File decrypted successfully using DES.
```

---

## Command Summary
| Command          | 	Description                                                  |
|------------------|---------------------------------------------------------------|
| encrypt          | 	Encrypt a file with the specified algorithm and passphrase.  |
| decrypt          | 	Decrypt a file using the specified algorithm and passphrase. |
| -a, --algorithm  | 	The encryption algorithm to use (AES or DES).                |
| -p, --passphrase | 	Passphrase for encryption/decryption.                        |
| -o, --output     | 	Output folder path.                                          |
| -h, --help       | 	Show help message and exit.                                  |
| -V, --version    | 	Print version information and exit.                          |

---

## Contributing

If you want to contribute or modify the application, follow these steps:

1) Declare what you want to do in an issue.
2) Discuss it with the maintainers.
3) Fork the repository.
4) Make your changes.
5) Build the project with Maven.
6) Test the application.
7) Create a pull request with your changes.
8) Wait for the pull request to be reviewed and merged.
9) Celebrate your contribution 🎉


### Add a new encryption/decryption algorithm

1) Create a new class that extends the `Algorithm` class.
2) Implement the `encrypt` and `decrypt` methods. (_They return a byte array_)
3) Add the algorithm name in the CLI help message.
4) Compile the project and test the new algorithm.

Example:

```java
public class MyAlgorithm extends Algorithm {

    public MyAlgorithm() {
      super("MyAlgorithm", "MyAlgorithm is an encryption algorithm.");
    }
  
    @Override
    public byte[] encrypt(byte[] data, String passphrase) {
        // Implement the encryption logic
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] data, String passphrase) {
        // Implement the decryption logic
        return new byte[0];
    }
}
```

### **Additional Tips:**

- **Stay Updated:** Regularly sync your fork with the main repository to incorporate the latest changes.
  ```bash
  git remote add upstream https://github.com/original-owner/repository-name.git
  git fetch upstream
  git checkout main
  git merge upstream/main

---

## Credits

This project was developed by Tristan Baud ([NATSIIRT](https://github.com/NATSIIRT)) and Mathieu Emery ([mathieuemery](https://github.com/mathieuemery)) as part of a DAI (Development of internet applications) lab project.


---