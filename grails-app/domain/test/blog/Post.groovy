package test.blog

class Post {

    String title
    String content

    Category category

    static belongsTo = [blog: Blog]
    static hasMany = [comments: Comment, tags: Tag]

    static mapping = {
        content type: 'text'
    }
    
    static constraints = {
        category nullable: true
    }
}
