package com.gitlab.lamapizama.notee.note.note

import spock.lang.Specification

class CommentContentSpec extends Specification {

    def "Comment content should not be null"() {
        when:
            new CommentContent(null)
        then:
            thrown(NullPointerException)
    }

    def "Comment content should not be empty"() {
        when:
            new CommentContent('')
        then:
            thrown(IllegalArgumentException)
    }
}
