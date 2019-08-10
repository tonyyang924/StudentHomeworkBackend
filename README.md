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
} ]
```

```
$ curl http://0.0.0.0:5001/homework
[ {
  "id" : 1,
  "title" : "Two Sum"
}, {
  "id" : 2,
  "title" : "Add Two Numbers"
}, {
  "id" : 3,
  "title" : "Longest Substring Without Repeating Characters"
}, {
  "id" : 4,
  "title" : "Median of Two Sorted Arrays"
}, {
  "id" : 5,
  "title" : "Longest Palindromic Substring"
}, {
  "id" : 6,
  "title" : "ZigZag Conversion"
}, {
  "id" : 7,
  "title" : "Reverse Integer"
}, {
  "id" : 8,
  "title" : "String to Integer (atoi)"
}, {
  "id" : 9,
  "title" : "Palindrome Number"
}, {
  "id" : 10,
  "title" : "Regular Expression Matching"
} ]
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
