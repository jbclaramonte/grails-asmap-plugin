package test.blog

class Comment {

    String username
    String content
    
    static belongsTo = [post: Post]

    static mapping = {
        content type: 'text'
    }
    
    static constraints = {
    }
}
