package com.ulticast.domain

import com.ulticast.domain.AuthUser

/**
 * Authority domain class.
 */
class AuthRole {

	static hasMany = [people: AuthUser]

	/** description */
	String description
	/** ROLE String */
	String authority

	static constraints = {
		authority(blank: false, unique: true)
		description()
	}
}
