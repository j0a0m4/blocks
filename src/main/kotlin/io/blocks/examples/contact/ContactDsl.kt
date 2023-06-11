package io.blocks.examples.contact

import io.blocks.core.Delegates.notBlankVal
import io.blocks.core.interfaces.Block

// OO Approach
class ContactDsl : Block<Contact.Builder, Contact>, Contact.Builder {
	override var name by notBlankVal
	override var phone by notBlankVal

	override fun invoke(block: Contact.Builder.() -> Unit) =
		apply { block() }
			.run { Contact(name, phone) }
}
