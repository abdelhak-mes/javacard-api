# Java Card API

This repository contains an implementation of the Java Card API v.3.0.5 and
Global Platform specification v2.3 for education and research purposes.

## Copyright and license

Copyright (C) 2020

This software is licensed under both MIT and Oracle OTN licenses: 

* The Global Platform API and the Java Card Starter is under MIT licence. See
[LICENSE](MIT-LICENSE.txt) file at the root folder of the project.
* The Java Card technology is Copyright by Oracle (C). More information about
Oracle license upon their [OTN
License](https://www.oracle.com/a/tech/docs/javacard-otn-license-1.4.19.html).
This API must be used regarding this license.

## Author

  * Guillaume BOUFFARD (<mailto:guillaume.bouffard@ssi.gouv.fr>)
  * Vincent GIRAUD (<mailto:vincent@giraud.eu>)

## Description

#### java.[...], javacard[...], javacardx[...], com[...]

These packages contain standard libraries. Among other things, they provide
tools to interact with the Java Card Virtual Machine (JCVM), or utilities
functions. As they are standard, they should be able to work with an application
programmed for another Java Card environment.

#### fr.gouv.ssi.starter

This is the first package launched when starting the platform. Create a
GlobalPlatform instance if there is not one already existing. Then, execute it.

#### org.globalplatform

This is an implementation of the GlobalPlatform specification, which dictates
the different roles assured by a daemon on the platform : managing the
applications on the platform, controlling all communications (the internal ones,
but also communications reaching external entities), enforcing permissions,
managing all the life cycle state (for the card and for each application,
executable module, and executable load file), ...

This whole implementation of the GlobalPlatform specification can work without
using the `int` type; with the exception of the `process_command_APDU()`
function in `PlateformeGlobale.java`. If it is ever needed to run it on a
platform non-supportive of the `int` type; the only necessary tweak would be a
modification of the `process_command_APDU()` function, so that support for
ISO7816 extended fields in APDUs is completely dropped.

## Building the API

### Dependencies

The Java Card API depends on:

* [ant](https://ant.apache.org/): to build the project. To install ant, read
this [page](https://ant.apache.org/manual/install.html) or check on you Linux
packages repository.
* [Oracle JavaCard Classic
  SDK](https://github.com/martinpaljak/oracle_javacard_sdks): this dependence is
  include as a git module. It aims a building the Java Card API.

### Fetching this repository

To clone the repository and its dependency, you should execute the following
command:

```
git clone --recursive https://github.com/choupi-project/javacard-api 
```

### How to compile

Change your current directory to the root of this project and then execute
```
ant
```

This should compile all the libraries described in [Description](./README.md#Description).

If needed, you can clean the built files with

```
ant clean
```

### How to generate documentation for the GlobalPlatform and Java Card API implementation

To generate documentation for API, change your current directory to the root of
this project and then execute:

```
ant doc
```

The generation should print warnings due to the official interfaces which
miss any comments. These errors will not actually prevent the process from
finishing, and can be ignored. You will then find the built javadoc
documentation:
 * for GlobalPlatform implementation
    * in the ```./documentation/GlobalPlatform``` folder. ```index.html``` is the entry point.
 * for Java Card API
    * in the ```./documentation/JavaCardAPI``` folder. ```index.html``` is the entry point.

## TODO list

This section list the next features to implement.

#### General

* Implementation of all cryptography, secure channel, signatures and tokens
  verifications, ...
* Actual loading and storing of new application through the operating system
* Creation of an actual pathway through the hardware, OS, and Java Card VM for incoming and outcoming APDUs
* Optional : Logical channels

#### PlateformeGlobale.java

* `process_command_APDU()`: Extended fields support is unfinished : APDUs can
  contain unsigned 16 bits numbers, where ```0x0000``` can correspond to 2^16. As
  these cannot be stored and processed with java card's `short` type, ```int```
  must be used, however this prevents the use of ```Util.arrayCopy(...)```
* `process_command_APDU()`: Once the APDU has been parsed, verify and apply the
  requested actions
* `process_command_APDU()`: Return a response APDU at the end of the function,
  or find another way of responding to a request APDU

#### globalServiceProvider.java

* `getServiceInterface()`: Verify that the parameter `baBuffer` is a global
  array prior to using it
* `getServiceInterface()`: If the `GLOBAL_REGISTER` privilege is owned, it
  should be verified that the requesting application is not usurping another one
  with the same privilege.

#### Starter.java

* This class should run GlobalPlatform after obtaining an instance of it.
  GlobalPlatform should be waiting for an APDU to come once its initialization
  is over; thus a function of this kind from it should be called.
