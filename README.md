# Blocks
Building blocks to write DSLs in Kotlin

This project aims to provide friendly APIs to help design DSLs in Kotlin

## DateTimePicker
With the DateTimePicker you're able to write dates in a natural way

```kotlin
lateinit var date: LocalDate

// You can implement the functional interface as a lambda
val started = LocalDatePicker { date = it }

// Emit this date to the started LocalDatePicker closure as 'it'
started on 26 March 2005

// Assert the values are equal
date shouldBe LocalDate.of(2005, MARCH, 26)
```