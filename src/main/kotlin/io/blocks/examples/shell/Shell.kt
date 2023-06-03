package io.blocks.examples.shell

import java.io.File
import java.lang.ProcessBuilder.Redirect.PIPE
import java.util.concurrent.TimeUnit.MILLISECONDS
import kotlin.time.Duration
import kotlin.time.DurationUnit.MINUTES
import kotlin.time.toDuration

typealias ShellCommand = () -> String

fun shell(
	workingDir: String = ".",
	timeout: Duration = 1.toDuration(MINUTES),
	shellCommand: ShellCommand
) =
	shellCommand.runWith(workingDir.file, timeout)

fun ShellCommand.runWith(
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

private val ShellCommand.parse: List<String>
	get() = "\\s".toRegex().split(invoke())

private infix fun Process.waits(timeout: Duration) =
	waitFor(timeout.inWholeMilliseconds, MILLISECONDS)
