package benchmark.benchmarks

import benchmark.reporting.util.BENCHMARK_COLLECTION_SIZE
import kubit.collections.array.filter
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import kubit.collections.array.map
import kubit.collections.array.take
import kubit.collections.list.IntList
import kotlin.random.Random

@State(Scope.Benchmark)
class ArrayExtBenchmark {
    private val size = BENCHMARK_COLLECTION_SIZE
    private var array = IntArray(size) { it }

    private var seed = 0
    private var random = Random(seed)

    @Setup(Level.Iteration)
    fun prepare() {
        random = Random(seed++)
        array.shuffle(random)
    }

    @Benchmark
    fun kubit_Kubit_map(): IntList = array.map { it * 9 }

    @Benchmark
    fun standard_map(): List<Int> = array.standardMap { it * 9 }

    @Benchmark
    fun kubit_Kubit_filter(): IntList = array.filter { it % 2 == 0 }

    @Benchmark
    fun standard_filter(): List<Int> = array.standardFilter { it % 2 == 0 }

    @Benchmark
    fun kubit_Kubit_take(): IntList = array.take(size / 4)

    @Benchmark
    fun standard_take(): List<Int> = array.standardTake( size / 4)

}