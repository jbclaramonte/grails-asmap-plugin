package test.blog

class Blog {

    String title
    String summary

    static belongsTo = [user: User]
    static hasMany = [posts: Post]

    static mapping = {
        summary type: 'text'
    }

    static constraints = {
        summary nullable: true
    }
}
