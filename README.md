# Gibberish-Detector

Based on Python gibberish detector written by Rob Renaud. Ported into Java by Shir Fiszman.

You can find the original here: https://github.com/rrenaud/Gibberish-Detector

This gibberish detector is not limmited to a certain language, and can be trained on files by the user's choice.

# How to use this library?
Use GibberishDetectorFactory in order to create a new instance of the gibberish detector.
You should supply it the following inputs:
- A big text file in the language of your choice for training.
- A small text file with correct sentences.
- A small text file with gibberish sentences.
- A string containing the alphaBet of that language plus a space character.
You can pass the files' data as lists of string, java file objects, paths of the files, or the names of local files.
The factory instantiates a new GibberishDetector object that is trained by this input.
Use the 'isGibberish' method in order to determine if a sentence is gibberish or not.

If you wish to select your own heuristic for setting the thrshold to classify sentences, you can override the method 'getThreshold'
and implement it yourself. The factory can create instances of detectors that extend GibberishDetector.

# Content
java files:
- GibberishDetector - implements the gibberish detector algorythm used in the python open source gibberish detector.
- GibberishDetectorExtended - an example of a detector the extends GibberishDetector and overrides the 'isGibberish' method. 
- GibberishDetectorFactory - the factory for creating an instance of a gibberish detector by the user's input.

text files:
- bigEnglish.txt, goodEnglish.txt, badEnglish.txt - text files used as inputs in the test for english gibberish detector. same files used
in the python open source gibberish detector.
- bigHebrew.txt, goodHebrew.txt, badHebrew.txt - text files used as inputs in the test for hebrew gibberish detector.

tests:
- EnglishGibbeirishDetectorTest - test for english gibberish detector. Demonstrates the usage of the factory with the GibberishDetectorExtended.
- HebrewGibberishDetectorTest - test for hebrew gibberish detector. Demonstrates the usage of the factory on a language different than english.

# License

Gibberish-Detector-Java is available under the MIT License.  See [LICENSE.txt](LICENSE.txt).
