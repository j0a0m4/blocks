import java.io.File
import java.lang.ProcessBuilder.Redirect.PIPE
import java.util.concurrent.TimeUnit.MILLISECONDS
import kotlin.time.Duration
import kotlin.time.DurationUnit.MINUTES
import kotlin.time.toDuration

typealias Command = () -> String

fun shell(
	workingDir: String = ".",
	timeout: Duration = 60.toDuration(MINUTES),
	command: Command
) =
	command.runWith(workingDir.file, timeout)

private val String.file: File
	get() = File(this)

val Command.parse: List<String>
	get() = "\\s".toRegex().split(invoke())

fun Command.runWith(
	workingDir: File = File("."),
	timeout: Duration = 60.toDuration(MINUTES)
) =
	ProcessBuilder(parse).runCatching {
		directory(workingDir)
		redirectOutput(PIPE)
		redirectError(PIPE)
		redirectErrorStream(true)
		start().also { it waits timeout }
			.inputStream.bufferedReader().readText()
	}

private infix fun Process.waits(timeout: Duration) =
	waitFor(timeout.inWholeMilliseconds, MILLISECONDS)
