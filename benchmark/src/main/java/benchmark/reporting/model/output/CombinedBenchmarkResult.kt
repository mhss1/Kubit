package benchmark.reporting.model.output

import kotlinx.serialization.Serializable


@Serializable
data class PerformanceResult(
    val operation: String,
    val variants: List<String>,
    val times: List<Double>,
    val allocatedBytes: List<Double>,
)