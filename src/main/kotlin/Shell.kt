import java.io.File
import java.lang.ProcessBuilder.Redirect.PIPE
import java.util.concurrent.TimeUnit.MILLISECONDS
import kotlin.time.Duration
import kotlin.time.DurationUnit.MINUTES
import kotlin.time.toDuration

typealias Command = () -> String

fun shell(
	workingDir: String = ".",
	timeout: Duration = 1.toDuration(MINUTES),
	command: Command
) =
	command.runWith(workingDir.file, timeout)

fun Command.runWith(
	workingDir: File = ".".file,
	timeout: Duration = 1.toDuration(MINUTES)
) =
	ProcessBuilder(parse).runCatching {
		directory(workingDir)
		redirectOutput(PIPE)
		redirectError(PIPE)
		redirectErrorStream(true)
		start().also { it waits timeout }
			.inputStream.bufferedReader().readText()
	}

private val String.file: File
	get() = File(this)

private val Command.parse: List<String>
	get() = "\\s".toRegex().split(invoke())

private infix fun Process.waits(timeout: Duration) =
	waitFor(timeout.inWholeMilliseconds, MILLISECONDS)
