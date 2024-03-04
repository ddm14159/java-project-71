### Hexlet tests, linter status and tests:
[![Actions Status](https://github.com/ddm14159/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ddm14159/java-project-71/actions)
<a href="https://codeclimate.com/github/ddm14159/java-project-71/maintainability"><img src="https://api.codeclimate.com/v1/badges/59ee561851d5f5c108d8/maintainability" /></a>
<a href="https://codeclimate.com/github/ddm14159/java-project-71/test_coverage"><img src="https://api.codeclimate.com/v1/badges/59ee561851d5f5c108d8/test_coverage" /></a>


Difference Calculator is a utility that determines the difference between two data structures. It is a common task with many online services offering similar functionality, such as jsondiff.com. This mechanism is used in testing output or automatically tracking changes in configuration files.

#### Features:
- Supports different input formats: YAML and JSON.
- Generates reports in plain text, stylish, and JSON formats.
### Example Usage:
#### Plain format:

```./app --format plain path/to/file.yml another/path/file.json```
```
Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed
```
#### Stylish format:

```./app filepath1.json filepath2.json```
```
{
  + follow: false
  + numbers: [1, 2, 3]
    setting1: Value 1
  - setting2: 200
  - setting3: true
  + setting3: {key=value}
  + setting4: blah blah
}
```
#### JSON format:

```./app --format json filepath1.yml filepath2.yml```
```
[
  {
    "key": "follow",
    "newValue": false,
    "type": "added",
    "value": null
  },
  {
    "key": "baz",
    "newValue": "bars",
    "type": "changed",
    "value": "bas"
  },
  {
    "key": "group2",
    "type": "removed",
    "value": null
  }
]
```

#### Two JSON files comparison
https://imgur.com/a/dycTSPf

#### Two YML files comparison
https://imgur.com/a/GdqjC3p

#### Nested comparison
https://imgur.com/a/hmvJvCq

#### Plain format
https://imgur.com/a/dDjCxpB

#### Json format
https://imgur.com/a/PiCaJq1
