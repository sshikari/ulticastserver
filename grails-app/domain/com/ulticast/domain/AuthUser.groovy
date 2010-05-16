package com.ulticast.domain

import com.ulticast.domain.AuthRole

/**
 * User domain class.
 */
class AuthUser {
	static transients = ['pass']
	static hasMany = [authorities: AuthRole]
	static belongsTo = AuthRole

	/** Username */
	String username
	/** User Real Name*/
	String userRealName
	/** MD5 Password */
	String passwd
	/** enabled */
	boolean enabled

	String email
	boolean emailShow

	/** description */
	String description = ''

	/** plain password to create a MD5 password */
	String pass = '[secret]'

	Date dateCreated
	Date lastUpdated
	
	static mapping = {
		autoTimestamp true
	}
	static constraints = {
		username(blank: false, unique: true)
		userRealName(blank: false)
		passwd(blank: false)
		enabled()
	}
}
