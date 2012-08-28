# Grails asMap Plugin

This plugin provides a simple dsl to retrieve, as a Map, data parts of a GORM domain.

## Sample use

Given a domain where a User have can many Blogs and a Blog can have many Posts, ...

```groovy
User user = User.findByEmail("tom@test.com")
userMap = user.asMap {
    include "id", "username"
    load("blogs") {
        include "id", "title"
        load("posts") {
            include "id", "title"
            load("category") {
                include "name"
            }
        }
    }
}

println userMap

JSON userJson = userMap as JSON
userJson.prettyPrint = true

println userJson
```

This will give you the following output:

```groovy
[id:1, username:Tom, blogs:[[id:1, title:A blog about Java, posts:[[id:2, title:post1, category:[name:Programming]], [id:1, title:post2, category:[name:Programming]]]]]]
{
  "id": 1,
  "username": "Tom",
  "blogs":
  [
    {
      "id": 1,
      "title": "A blog about Java",
      "posts":
      [
        {
          "id": 2,
          "title": "post1",
          "category": {
            "name": "Programming"
          }
        },
        {
          "id": 1,
          "title": "post2",
          "category": {
            "name": "Programming"
          }
        }
      ]
    }
  ]
}
```