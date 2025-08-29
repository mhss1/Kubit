@file:OptIn(ExperimentalSerializationApi::class)

package benchmark.reporting

import benchmark.reporting.model.input.PerformanceBenchmarkEntry
import benchmark.reporting.model.output.PerformanceResult
import benchmark.reporting.util.PERFORMANCE_BENCHMARK_OUTPUT_DIR
import benchmark.reporting.util.PERFORMANCE_BENCHMARK_RESULTS_FILE
import benchmark.reporting.util.PROCESSED_PERFORMANCE_RESULTS_OUTPUT_DIR
import benchmark.reporting.util.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File

fun processBenchmarkResults() {
    val dir = File(PERFORMANCE_BENCHMARK_OUTPUT_DIR)
    val latestFolder = dir.listFiles { file -> file.isDirectory }?.maxByOrNull { it.lastModified() }
    val resultsFile = latestFolder?.resolve(PERFORMANCE_BENCHMARK_RESULTS_FILE)
        ?: throw Exception("No results file found")

    val entries: List<PerformanceBenchmarkEntry> = json.decodeFromStream(resultsFile.inputStream())

    val outputDir = File(PROCESSED_PERFORMANCE_RESULTS_OUTPUT_DIR)

    entries.groupBy { it.benchmark.benchmarkClass }.forEach { (benchmark, entries) ->
        val results = mutableListOf<PerformanceResult>()
        entries.groupBy { it.benchmark.operationName }.forEach { (operation, entries) ->
            val ordered = entries.sortedBy { it.benchmark.isKubit }
            val considerPerformance = entries.first().benchmark.considerPerformanceBenchmark
            val considerMemory = entries.first().benchmark.considerMemoryBenchmark
            val result = PerformanceResult(
                operation = operation,
                variants = ordered.map { it.benchmark.variantName },
                times = if (considerPerformance) ordered.map { it.score } else emptyList(),
                allocatedBytes =
                    if (considerMemory)
                        ordered.map { result -> result.secondaryMetrics.bytesAllocated.score }
                    else
                        emptyList(),
            )
            results.add(result)
        }
        val fileName = benchmark.substringBefore("Benchmark")
        val file = outputDir.resolve("$fileName.json").also {
            if (it.parentFile.exists().not()) it.parentFile.mkdirs()
            if (it.exists().not()) it.createNewFile()
        }
        json.encodeToStream(results, file.outputStream())
    }

    println("✅ Processed benchmark results successfully")
}

val String.benchmarkClass: String
    get() = substringBeforeLast(".").substringAfterLast(".")

val String.operationName: String
    get() = substringAfterLast("_")

val String.variantName: String
    get() = substringAfterLast(".").substringBeforeLast("_").substringAfterLast("_")

val String.isKubit: Boolean
    get() = substringAfterLast(".").contains("kubit", ignoreCase = true)

val String.considerMemoryBenchmark: Boolean
    get() = !substringAfterLast(".").contains("NOMEM", ignoreCase = false)

val String.considerPerformanceBenchmark: Boolean
    get() = !substringAfterLast(".").contains("NOPERF", ignoreCase = false)