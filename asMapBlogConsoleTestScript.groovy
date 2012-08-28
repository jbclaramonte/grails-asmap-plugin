import test.blog.*


Category catScience = new Category(name: "Science")
catScience.save(flush: true, failOnError: true)
Category catSport = new Category(name: "Sport")
catSport.save(flush: true, failOnError: true)
Category catProgramming = new Category(name: "Programming")
catProgramming.save(flush: true, failOnError: true)
Category catFun = new Category(name: "Fun")
catFun.save(flush: true, failOnError: true)

Tag tagSoccer = new Tag(name: "Soccer")
tagSoccer.save(flush: true, failOnError: true)

Tag tagGroovy = new Tag(name: "Groovy")
tagGroovy.save(flush: true, failOnError: true)

Tag tagJava = new Tag(name: "Java")
tagJava.save(flush: true, failOnError: true)

User user1 = new User(username: "Tom", email: "tom@test.com")
user1.save(flush: true, failOnError: true)

Blog blog1User1 = new Blog(title: "A blog about Java", summary: "Post about Java and related")
user1.addToBlogs(blog1User1)
user1.save(flush: true, failOnError: true)


Post post
post = new Post(title: "post1", content: "Great content Post 1")
post.category = catProgramming
post.addToTags(tagJava)
post.addToTags(tagGroovy)
blog1User1.addToPosts(post)

post = new Post(title: "post2", content: "Great content Post 2")
post.category = catProgramming
post.addToTags(tagGroovy)
blog1User1.addToPosts(post)

blog1User1.save(flush: true, failOnError: true)



user1.asMap {
    include "username"
    load("blogs") {
        include "title"
        load("posts") {
            include "title"
            load("category") {
                include "name"
            }
        }
    }
}