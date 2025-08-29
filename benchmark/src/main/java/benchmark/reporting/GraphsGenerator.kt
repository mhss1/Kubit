package benchmark.reporting

import java.io.File
import benchmark.reporting.model.output.PerformanceResult
import benchmark.reporting.util.BENCHMARK_COLLECTION_SIZE
import benchmark.reporting.util.CLOSE_BRACKET
import benchmark.reporting.util.CLOSE_CURLY
import benchmark.reporting.util.COMMA
import benchmark.reporting.util.DOT
import benchmark.reporting.util.GRAPHS_DIR
import benchmark.reporting.util.GT
import benchmark.reporting.util.LT
import benchmark.reporting.util.MEMORY_GRAPH_SUFFIX
import benchmark.reporting.util.OPEN_BRACKET
import benchmark.reporting.util.OPEN_CURLY
import benchmark.reporting.util.PERFORMANCE_GRAPH_SUFFIX
import benchmark.reporting.util.PROCESSED_PERFORMANCE_RESULTS_OUTPUT_DIR
import benchmark.reporting.util.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromStream
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.scales.Transformation
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.math.abs
import kotlin.text.replace

fun generateBenchmarkGraphs() {
    val dir = File(PROCESSED_PERFORMANCE_RESULTS_OUTPUT_DIR)

    dir.listFiles()?.forEach { file ->
        val results = file.deserialize<List<PerformanceResult>>()
        generatePerformanceGraph(file, results)
        generateMemoryUsageGraph(file, results)
    }

    println("✅ Generated graph images successfully.")
}

@Suppress("DefaultLocale")
private fun generateBarGraph(
    file: File,
    results: List<PerformanceResult>,
    targetValues: (PerformanceResult) -> List<Double>,
    valuesColumnName: String,
    axisName: String,
    filenameSuffix: String,
    baseTitle: String,
    absoluteDiffFloor: Double,
) {
    val rows = results.filter { result ->
        val values = targetValues(result)
        if (values.isEmpty()) return@filter false

        for (i in 0 until values.size) {
            for (j in i + 1 until values.size) {
                val a = values[i]
                val b = values[j]
                val diff = abs(a - b)
                val rel = diff / maxOf(a, b).coerceAtLeast(0.1)
                if (rel >= RELATIVE_DIFF_THRESHOLD && diff >= absoluteDiffFloor) return@filter true
            }
        }

        false
    }.flatMap { result ->
        result.variants.zip(targetValues(result)).mapIndexed { index, (variant, value) ->
            GraphRow(
                operation = result.operation.cleanName,
                variant = variant.cleanName,
                value = value,
                variantIndex = index
            )
        }
    }.ifEmpty { return }

    val df = dataFrameOf(
        "Operation" to rows.map { it.operation },
        "Variant" to rows.map { it.variant },
        valuesColumnName to rows.map { it.value }
    )

    val variantColorMapping = rows
        .distinctBy { it.variant }
        .map { row ->
            val color = when (row.variantIndex) {
                0 -> StandardColor
                1 -> KubitColor
                else -> KubitExtraColor
            }
            row.variant to color
        }

    val yValues = rows.map { it.value }
    val maxVal = yValues.max()
    val minVal = yValues.min()
    val ratio = if (minVal > 0) maxVal / minVal else 1.0
    val (yTransformation, layoutTitle) = when {
        ratio > 50 -> Transformation.SQRT to "$baseTitle (sqrt scale) - ${
            String.format(
                "%,d",
                BENCHMARK_COLLECTION_SIZE
            )
        } items"

        else -> Transformation.IDENTITY to "$baseTitle - ${
            String.format(
                "%,d",
                BENCHMARK_COLLECTION_SIZE
            )
        } items"
    }

    df.plot {
        bars {
            x("Operation") { axis.name = "" }
            y(valuesColumnName) {
                axis.name = axisName
                scale = continuous(transform = yTransformation)
            }
            fillColor("Variant") {
                legend.name = ""
                scale = categorical(*variantColorMapping.toTypedArray())
            }
        }
        layout { title = layoutTitle }
    }.save(
        filename = "${file.nameWithoutExtension}_${filenameSuffix}.svg",
        path = GRAPHS_DIR
    )
}


private fun generatePerformanceGraph(file: File, results: List<PerformanceResult>) {
    generateBarGraph(
        file = file,
        results = results,
        targetValues = { it.times },
        valuesColumnName = "Time",
        axisName = "Time (ns)",
        filenameSuffix = PERFORMANCE_GRAPH_SUFFIX,
        baseTitle = "Time per operation",
        absoluteDiffFloor = ABS_TIME_DIFF_FLOOR_NS
    )
}

private fun generateMemoryUsageGraph(file: File, results: List<PerformanceResult>) {
    generateBarGraph(
        file = file,
        results = results,
        targetValues = { it.allocatedBytes },
        valuesColumnName = "Bytes",
        axisName = "Allocated Memory (bytes)",
        filenameSuffix = MEMORY_GRAPH_SUFFIX,
        baseTitle = "Allocated Memory per operation",
        absoluteDiffFloor = ABS_BYTES_DIFF_FLOOR_NS
    )
}

private val String.cleanName: String
    get() = replace(DOT, ".")
        .replace(COMMA, ",")
        .replace(OPEN_BRACKET, "(")
        .replace(CLOSE_BRACKET, ")")
        .replace(OPEN_CURLY, "{")
        .replace(CLOSE_CURLY, "}")
        .replace(GT, ">")
        .replace(LT, "<")

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> File.deserialize() = json.decodeFromStream<T>(inputStream())

private const val RELATIVE_DIFF_THRESHOLD = 0.10
private const val ABS_TIME_DIFF_FLOOR_NS = 20.0
private const val ABS_BYTES_DIFF_FLOOR_NS = 100.0

private data class GraphRow(
    val operation: String,
    val variant: String,
    val value: Double,
    val variantIndex: Int
)


private val KubitColor = Color.hex("#A313FE")
private val KubitExtraColor = Color.hex("#2C04FE")
private val StandardColor = Color.hex("#333333")
