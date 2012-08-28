package test.blog

import test.blog.*

/**
 * Created by IntelliJ IDEA.
 * User: jb
 * Date: 27/08/12
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
class AsMapTests extends GroovyTestCase {

    final static USER1_USERNAME = 'Tom'
    final static USER1_EMAIL = 'tom@test.com'
    final static BLOG_TITLE = 'A blog about Java'

    protected void setUp() {

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

        User user1 = new User(username: USER1_USERNAME, email: USER1_EMAIL)
        user1.save(flush: true, failOnError: true)

        Blog blog1User1 = new Blog(title: BLOG_TITLE, summary: "Post about Java and related")
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

    }

    void testBasicInclude() {

        User user1 = User.findByEmail(USER1_EMAIL)
        
        Map map = user1.asMap {
            include 'username', 'email'
        }
        
        assertEquals(USER1_USERNAME, map.username)
        assertEquals(USER1_EMAIL, map.email)

    }


    void testOneToMany() {

        User user1 = User.findByEmail("tom@test.com")

        Map map = user1.asMap {
            include 'id', 'username'
            load("blogs") {
                include 'id', 'title'
            }
        }

        assertEquals(BLOG_TITLE, map.blogs[0].title)

    }


    void testNestedOneToMany() {

        User user1 = User.findByEmail(USER1_EMAIL)

        Map map = user1.asMap {
            include 'username', 'email'
            load("blogs") {
                include "title"
                load("posts") {
                    include "title"
                }
            }
        }

        assertEquals(BLOG_TITLE, map.blogs[0].title)
        assertEquals(2, map.blogs[0].posts.size())

    }

}
