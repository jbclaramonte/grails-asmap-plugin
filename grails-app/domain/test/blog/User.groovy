package test.blog

class User {

    String username
    String email

    static hasMany = [blogs: Blog]
    
    static constraints = {
    }
}
