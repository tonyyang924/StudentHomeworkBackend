# student-homework-ktor

## Usage

### Student

- Get all students:
```
$ curl http://localhost:5001/student
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
- Create or update student
Update entity if input parameter `id` or `number` is equal to the entity in database. Else Create new entity to the database.

- Create new student:
```
$ curl -X PUT 'http://localhost:5001/student' --header 'Content-type: application/json' -d '{ "number":"95279527", "name": "
Tang Bohu"}'
{
  "id" : 101,
  "number" : "95279527",
  "name" : " Tang Bohu"
}
```

- Update existing entity:
```
$ curl -X PUT 'http://localhost:5001/student' --header 'Content-type: application/json' -d '{ "number":"old-earwig-67", "name": "Tony Yang"}'
```

### Homework

- Get all homeworks:
```
$ curl http://localhost:5001/homework
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

- Create or update multiple homeworks
Update entity if input parameter `id` or `title` is equal to the entity in database. Else Create new entity to the database.

- Create new homeworks:
```
$ curl -X PUT 'http://localhost:5001/homework' --header 'Content-type: application/json' --header 'Accept: application/json' -d '[{"id" : 11, "title": "New Homework 11"}, {"id" : 12, "title": "New Homework 12"}]'
[ {
  "id" : 11,
  "title" : "New Homework 11"
}, {
  "id" : 12,
  "title" : "New Homework 12"
} ]
```

- Create and update homeworks:
```
$ curl -X PUT 'http://localhost:5001/homework' --header 'Content-type: application/json' --header 'Accept: application/json' -d '[{"id" : 11, "title": "New Homework 11 - update"}, {"id" : 13, "title": "New Homework 13"}]'
```

- Update existing entitys:
```
$ curl -X PUT 'http://localhost:5001/homework' --header 'Content-type: application/json' --header 'Accept: application/json' -d '[{"id" : 1, "title": "Title 1"}, {"id" : 2, "title": "Title 2"}]'
```

### Record

- Get all records:
```
$ curl http://localhost:5001/record
[ {
  "id" : 1,
  "sid" : 1,
  "hid" : 4,
  "status" : 2
}, {
  "id" : 2,
  "sid" : 2,
  "hid" : 2,
  "status" : 0
}, {
  "id" : 3,
  "sid" : 2,
  "hid" : 10,
  "status" : 0
}, {
  "id" : 4,
  "sid" : 2,
  "hid" : 9,
  "status" : 1
}, {
  "id" : 5,
  "sid" : 2,
  "hid" : 3,
  "status" : 0
}, {
...
}, {
  "id" : 542,
  "sid" : 99,
  "hid" : 5,
  "status" : 0
} ]
```

- Create or update multiple records
Update entity if input parameter `sid` and `hid` is equal to the entity in database. Else Create new entity to the database.

- Create multiple records:
```
$ curl -X PUT 'http://localhost:5001/record' --header 'Content-type: application/json' --header 'Accept: application/json' -d '[{"sid" : 1, "hid" : 4, "status" : 0}, {"sid" : 2, "hid" : 2, "status" : 2}, {"sid" : 99, "hid" : 5, "status" : 1}]'
```
