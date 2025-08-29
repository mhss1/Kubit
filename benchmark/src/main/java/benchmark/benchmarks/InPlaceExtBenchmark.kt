package benchmark.benchmarks

import benchmark.reporting.util.BENCHMARK_COLLECTION_SIZE
import kubit.collections.inplace.chunkedInPlace
import kubit.collections.inplace.dropInPlace
import kubit.collections.inplace.forEachWindow
import kubit.collections.inplace.takeInPlace
import kubit.collections.inplace.windowedInPlace
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
class InPlaceExtBenchmark {

    private val list = List(10_000) { it }

    @Benchmark
    fun kubit_inPlace_windowedOBR100COMMA50CBR() = list.windowedInPlace(100, 50)

    @Benchmark
    fun standard_windowedOBR100COMMA50CBR() = list.windowed(100, 50)

    @Benchmark
    fun kubit_inPlace_chunkedOBR100CBR() = list.chunkedInPlace(100)

    @Benchmark
    fun standard_chunkedOBR100CBR() = list.chunked(100)

    @Benchmark
    fun kubit_inPlace_forEachWindowOBR100COMMA50CBR(): Int {
        var acc = 0
        list.forEachWindow(100, 50) { acc += it.size }
        return acc
    }

    @Benchmark
    fun standard_forEachWindowOBR100COMMA50CBR(): Int {
        var acc = 0
        list.windowed(100, 50).forEach { acc += it.size }
        return acc
    }

    @Benchmark
    fun kubit_inPlace_takeOBR1000CBR() = list.takeInPlace(BENCHMARK_COLLECTION_SIZE / 10)

    @Benchmark
    fun standard_takeOBR1000CBR() = list.take(BENCHMARK_COLLECTION_SIZE / 10)

    @Benchmark
    fun kubit_inPlace_dropOBR1000CBR() = list.dropInPlace(BENCHMARK_COLLECTION_SIZE / 10)

    @Benchmark
    fun standard_dropOBR1000CBR() = list.drop(BENCHMARK_COLLECTION_SIZE / 10)
}

