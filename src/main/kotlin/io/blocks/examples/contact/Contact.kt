package io.blocks.examples.contact

import io.blocks.core.Delegates.notBlankVal
import io.blocks.core.interfaces.Block

data class Contact(val name: String, val phone: String) {
	interface Builder {
		var name: String
		var phone: String
	}

	// OO Approach
//	companion object : io.blocks.core.interfaces.Block<Builder, Contact> by ContactDsl()

	// Functional Approach
	companion object : Block<Builder, Contact> by contactDsl
}

// Functional Approach
val contactDsl = Block<Contact.Builder, Contact> { block ->
	object : Contact.Builder {
		override var name by notBlankVal
		override var phone by notBlankVal
	}.run {
		block()
		Contact(name, phone)
	}
}
