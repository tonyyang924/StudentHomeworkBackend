# student-homework-ktor

## Usage

```
$ curl http://0.0.0.0:5001/student
[ {
  "id" : 1,
  "number" : "swift-dingo-15",
  "name" : "Sheila Parham"
}, {
  "id" : 2,
  "number" : "hot-jellyfish-68",
  "name" : "Raymond Cook"
}, {
  "id" : 3,
  "number" : "warm-goat-65",
  "name" : "Heather Kemper"
}, {
  "id" : 4,
  "number" : "great-warthog-89",
  "name" : "Kenneth Pack"
}, {
  "id" : 5,
  "number" : "happy-panther-35",
  "name" : "Ronald Byrne"
}, {
...
}, {
  "id" : 100,
  "number" : "mighty-chipmunk-24",
  "name" : "Bryant Wright"
} ]%
```

```
$ curl http://0.0.0.0:5001/record
[ {
  "id" : 1,
  "sid" : 1,
  "hid" : 7,
  "status" : 2
}, {
  "id" : 2,
  "sid" : 1,
  "hid" : 10,
  "status" : 0
}, {
  "id" : 3,
  "sid" : 2,
  "hid" : 1,
  "status" : 1
}, {
  "id" : 4,
  "sid" : 2,
  "hid" : 7,
  "status" : 1
}, {
  "id" : 5,
  "sid" : 2,
  "hid" : 8,
  "status" : 0
}, {
...
}, {
  "id" : 437,
  "sid" : 98,
  "hid" : 8,
  "status" : 0
} ]
```
