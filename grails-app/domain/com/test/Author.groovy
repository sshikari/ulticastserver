package com.test

class Author {
    static hasMany = [books : com.test.Book]
    String firstName
    String lastName
  
    static constraints = {
    }
}
