# Random-Cube
A program that produces a random value from a series of data: numbers, words, ready-made categories

## Special
- The program consists of one `MainActivity`, where there is a `cube`, which displays a random number and a panel with fragments, where you can select a series of data from which random numbers will be selected
- This panel consists of `fragment` and `BottomNavigationView`. On it, you can select one of 3 fragments to set a series of data:
  - **Numbers** - on it you set the minimum and maximum number that can fall
  - **Words** - here you specify a list of words, of which then randomly selected one
  - **Categories** - there are predefined datasets: yes/no, hexagonal cube (1-6) and layer of fate
- After selecting the data, you click on the `cube` and a random element appears on it from that data series
- There is a dark theme and supporting 2 languages
- All rows of data are stored using `DataStore`

## Screanshots
| ![numbers](https://github.com/SviatKuzbyt/Random-Cube/blob/main/screenshots/numbers.jpg) | ![words](https://github.com/SviatKuzbyt/Random-Cube/blob/main/screenshots/words.jpg) | ![categories](https://github.com/SviatKuzbyt/Random-Cube/blob/main/screenshots/categories.jpg)
| - | - | - |

## How to install?
Go to the latest [Release](https://github.com/SviatKuzbyt/Random-Cube/releases/tag/1.0) and download and install the file `app-release.apk`
