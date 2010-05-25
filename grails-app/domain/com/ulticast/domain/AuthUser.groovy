package com.ulticast.domain

import com.ulticast.domain.AuthRole

/**
 * User domain class.
 */
class AuthUser {
	static transients = ['pass']
	static hasMany = [authorities: AuthRole]
	static belongsTo = AuthRole

	String username
	String firstName
	
	String lastName
	
	/** MD5 Password */
	String passwd
	boolean enabled

	String email
	boolean emailShow

	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	Date dateCreated
	Date lastUpdated
	
	static mapping = {
		autoTimestamp true
	}
	static constraints = {
		username(blank: false,unique: true)
		firstName(nullable: false,blank: false)
		lastName(nullable:false,blank:false)
		passwd(blank:false)
		enabled()
		email(email:true,nullable:false)
	}
	
	
}
