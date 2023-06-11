package io.blocks.examples.contact

import Delegates
import io.blocks.core.interfaces.Block

// OO Approach
class ContactDsl : Block<Contact.Builder, Contact>, Contact.Builder {
	override var name by Delegates.notBlankVal
	override var phone by Delegates.notBlankVal

	override fun invoke(block: Contact.Builder.() -> Unit) =
		apply { block() }
			.run { Contact(name, phone) }
}
