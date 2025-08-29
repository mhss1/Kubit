package benchmark.reporting.model.input

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PerformanceBenchmarkEntry(
    val benchmark: String,
    val primaryMetric: PerformanceMetric,
    val secondaryMetrics: SecondaryMetric
) {
    val score: Double
        get() = primaryMetric.score
}

@Serializable
data class PerformanceMetric(
    val score: Double
)

@Serializable
data class SecondaryMetric(
    @SerialName("gc.alloc.rate.norm")
    val bytesAllocated: PerformanceMetric
)

