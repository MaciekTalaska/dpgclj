dpgclj - Diceware™ password generator in Clojure
===========================================================

This project is clojure version of [Diceware Password Generator](https://github.com/MaciekTalaska/dpg) I have written some time ago (in Rust).

The aim is to create an application that could be used to generate easy to remember passwords using the Diceware™ method.


## Usage

dpgclj options:

`-l:[language]` - generate password using specified language. By default only pl (Polish) and en (English) are supported. Dpgclj tries to read all files matching `diceware-XY.txt` pattern where XY is code for the language (pl, en, es, etc.) - so this should be pretty easy to use this app to generate password in any language providing only proper list of words

`-w:[number]` - generate password(s) containing specified number of words

`-s:[char]` - separate words using provided separator

`-p:[number]` - number of passwords to be generated at once.

## Building project

`Dpgclj` has been created using [leiningen](https://leiningen.org) - lein becomes the first requirement - install it before proceeding with any further steps.

### Running lein repl

To test dpgclj, or some of its functions, type `lein repl`.

### Executing project

`dpgclj` could be run in very simple way: `lein run`. Parameters could also be added. 

### Building jar...

1. `lein uberar` results in building complete jar

2. `java -jar target/uberjar/dpgclj-0.1.0-SNAPSHOT-standalone.jar`. All options could be passed after the name of the jar file to be executed. 

## Diceware™

Diceware is a trademark of A G Reinhold. More info: http://world.std.com/~reinhold/diceware.html

## Diceware lists

English diceware list taken from: https://www.eff.org/deeplinks/2016/07/new-wordlists-random-passphrases
'
Polish diceware list has been created by me: https://github.com/MaciekTalaska/diceware-pl

Additional lists could be found on Mathiaz Gumm'z repository: https://github.com/mgumz/diceware/tree/master/lists

## License

Copyright © 2018 Maciek Talaska

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
