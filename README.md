# File Encryption CLI

### Version 1.0.0

A command-line utility to encrypt and decrypt files securely using AES or DES encryption algorithms. The application allows you to easily encrypt files with a passphrase and choose between AES or DES for encryption.

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
    - [Examples](#examples)
    - [Command Summary](#command-summary)
- [Building from Source](#building-from-source)
- [Credits](#credits)

---

## Features
- **AES and DES Encryption**: Choose between AES and DES encryption algorithms.
- **Passphrase Support**: Provide your own passphrase or let the application generate a random one.
- **File Encryption/Decryption**: Securely encrypt and decrypt files.
- **User-friendly CLI**: Easy to use with clear options and commands.

---

## Requirements
- **Java**: You need to have Java 21 installed.
- **Maven**: Ensure you have Maven installed to manage dependencies and build the project.

---

## Installation

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
target/pw-2-1.0-SNAPSHOT.jar
```

## Usage

The application is a CLI tool that can encrypt and decrypt files. You can choose between AES or DES encryption algorithms and provide a passphrase or let the application generate one for you.
Available Commands

    encrypt: Encrypts a file using AES or DES.
    decrypt: Decrypts a previously encrypted file.

Options

    -a, --algorithm: The encryption algorithm to use (AES or DES). Defaults to AES.
    -p, --passphrase: Passphrase for encryption/decryption. If not provided, a random passphrase will be generated.

Help Command

You can use the help command to get information about the available options and commands:

```bash
java -jar target/pw-2-1.0-SNAPSHOT.jar --help
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
                   The algorithm to use (possible values: , default value: AES).
  -h, --help       Show this help message and exit.
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
java -jar target/pw-2-1.0-SNAPSHOT.jar -a=AES -p=mysecretpassphrase test.txt encrypt
```

Output:

```arduino
File encrypted successfully using AES.
```

If no passphrase is provided, a random one will be generated:

```bash
java -jar target/pw-2-1.0-SNAPSHOT.jar -a=DES test.txt encrypt
```

Output:

```arduino
The randomly generated passphrase has been copied to your clipboard.
File encrypted successfully using DES.
```

### Decrypting a File

To decrypt an encrypted file, specify the algorithm and passphrase used during encryption:

```bash
java -jar target/pw-2-1.0-SNAPSHOT.jar -a=AES -p=mysecretpassphrase test.txt.encrypted decrypt
```

Output:

```arduino
File decrypted successfully using AES.
```

---

## Command Summary
| Command          | 	Description                                                  |
|------------------|---------------------------------------------------------------|
| encrypt          | 	Encrypt a file with the specified algorithm and passphrase.  |
| decrypt          | 	Decrypt a file using the specified algorithm and passphrase. |
| -a, --algorithm  | 	The encryption algorithm to use (AES or DES).                |
| -p, --passphrase | 	Passphrase for encryption/decryption.                        |
| -h, --help       | 	Show help message and exit.                                  |
| -V, --version    | 	Print version information and exit.                          |

---

## Building from Source

If you want to contribute or modify the application, follow these steps to build the project from the source.

Clone the repository:

```bash
git clone https://github.com/HEIG-VD-S3-PW/DAI-LAB-02

cd DAI-LAB-02
```

Build the project: Use Maven to build the project and generate the JAR file.

```bash
mvn clean install
```

Run the application: Once built, you can run the application using:

```bash
java -jar target/pw-2-1.0-SNAPSHOT.jar
```

Testing the application: You can also run the tests using Maven:

```bash
mvn test
```

---

## Credits

This project was developed by Tristan Baud ([NATSIIRT](https://github.com/NATSIIRT)) and Mathieu Emery ([mathieuemery](https://github.com/mathieuemery))as part of a DAI (Development of internet applications) lab project.


---

### Key Points Covered in This README:

1. **Purpose and Features**: Explained clearly what the application does and its main features.
2. **Usage Instructions**: Provided detailed instructions on how to use the application with examples and outputs to make it easy for new users to understand without running it locally.
3. **Build and Setup**: Clear instructions for cloning the repo, building the project with Maven, and running the application.
4. **Command Summary**: Summarized the key commands and options for easy reference.

This README should make it straightforward for new users or developers to understand, use, and contribute to your project.

