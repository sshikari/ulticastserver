package com.ulticast.controller

import org.codehaus.groovy.grails.plugins.springsecurity.Secured

class SecureTestController {
   @Secured(['ROLE_ADMIN'])
   def index = {
      render 'Secure access only'
   }
}