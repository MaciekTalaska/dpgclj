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



## Diceware™

Diceware is a trademark of A G Reinhold.

## License

Copyright © 2018 Maciek Talaska

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
