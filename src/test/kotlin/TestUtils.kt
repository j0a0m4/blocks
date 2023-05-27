import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.function.Executable

internal infix operator fun String.invoke(executable: Executable): DynamicTest =
	DynamicTest.dynamicTest(this, executable)

internal infix fun String.dynamicTest(executable: Executable): DynamicTest =
	DynamicTest.dynamicTest(this, executable)
