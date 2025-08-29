@file:OptIn(ExperimentalSerializationApi::class)

package benchmark.reporting.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
}
const val BENCHMARK_COLLECTION_SIZE = 10_000
const val PERFORMANCE_BENCHMARK_OUTPUT_DIR = "build/reports/benchmarks/"
const val PROCESSED_PERFORMANCE_RESULTS_OUTPUT_DIR = "src/main/java/benchmark/reporting/processed"
const val GRAPHS_DIR = "src/main/java/benchmark/reporting/graphs"
const val PERFORMANCE_BENCHMARK_RESULTS_FILE = "results.json"

const val PERFORMANCE_GRAPH_SUFFIX = "p"
const val MEMORY_GRAPH_SUFFIX = "m"

const val DOT = "DOT"
const val COMMA = "COMMA"
const val OPEN_BRACKET = "OBR"
const val CLOSE_BRACKET = "CBR"
const val OPEN_CURLY = "OCR"
const val CLOSE_CURLY = "CCR"
const val GT = "GT"
const val LT = "LT"
