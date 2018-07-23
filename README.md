# Tests for ok.ru "Change personal data" page.

## How to launch
1. Load project from repository and launch in IDEA;
1. Choose "Import gradle settings" and select "Use default gradle wrapper";
1. Create config file - `src/test/resources/config.properties` - and fill it with your data;
1. Run tests by gradle task `verification`>`test`.

## How to configure
The project is configured by the file `src/test/resources/config.properties`, you need to create it. Use [this example file](https://github.com/MaxCiv/OK-test-change-personal-data/blob/master/src/test/resources/config-example.properties).

Config contains the following options:
* `login` and `password` of the ok.ru user;
* `chromedriver`, `geckodriver`, `iedriver` and `edgedriver` - paths to webdrivers;
* `useBrowser` - the name of the browser to be used (allowed values: _chrome_, _firefox_, _ie_, _edge_);
* `implicitlyWaitTimeSec` - seconds of implicitly waiting.

## Structure
The project consists of the following packages:
* `models` - ValueObjects;
* `pages` - PageObjects of used webpages;
* `steps` - business-logic of tests;
* `tests` - test classes;
* `utils` - utility classes.