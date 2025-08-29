package benchmark.benchmarks

import benchmark.reporting.util.BENCHMARK_COLLECTION_SIZE
import kubit.collections.list.IntList
import kubit.collections.list.MutableIntList
import kubit.collections.list.ext.*
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import kotlin.random.Random


@State(Scope.Benchmark)
class PrimitiveListsBenchmark {

    private val listSize = BENCHMARK_COLLECTION_SIZE

    private lateinit var intList: MutableIntList
    private lateinit var list: MutableList<Int>

    private var seed = 0
    private var random = Random(seed)

    @Setup(Level.Iteration)
    fun prepare() {
        random = Random(seed++)
        intList = MutableIntList(listSize)
        list = ArrayList(listSize)

        repeat(listSize) {
            val v = random.nextInt()
            intList.add(v)
            list.add(v)
        }
    }

    @Benchmark
    fun kubit_IntList_init() = IntList(listSize) { it + seed}

    @Benchmark
    fun ArrayListLTIntGT_init() = List(listSize) { it + seed }

    @Benchmark
    fun IntList_add(bh: Blackhole): IntList {
        val list = MutableIntList(16)
        repeat(listSize) { list.add(it) }
        bh.consume(list.size)
        return list
    }

    @Benchmark
    fun ArrayListLTIntGT_add(bh: Blackhole): List<Int> {
        val list = ArrayList<Int>(16)
        repeat(listSize) { list.add(it) }
        bh.consume(list.size)
        return list
    }

    @Benchmark
    fun kubit_NOMEM_IntList_forEach(bh: Blackhole) {
        var acc = 0
        val s = seed
        intList.forEach {
            acc = acc xor (it + s)
        }
        bh.consume(acc)
    }

    @Benchmark
    fun NOMEM_ArrayListLTIntGT_forEach(bh: Blackhole) {
        var acc = 0
        val s = seed
        list.forEach {
            acc = acc xor (it + s)
        }
        bh.consume(acc)
    }

    @Benchmark
    fun kubit_IntList_map(bh: Blackhole): IntList {
        val s = seed
        val res = intList.map { it + s }
        bh.consume(res)
        return res
    }

    @Benchmark
    fun ArrayListLTIntGT_map(bh: Blackhole): List<Int> {
        val s = seed
        val res = list.standardMap { it + s }
        bh.consume(res)
        return res
    }

    @Benchmark
    fun kubit_NOMEM_IntList_filter() = intList.filter { it % 2 == 0 }

    @Benchmark
    fun NOMEM_ArrayListLTIntGT_filter() = list.standardFilter { it % 2 == 0 }

    @Benchmark
    fun kubit_NOMEM_IntList_chunkedOBR30CBR() = intList.chunked(30)

    @Benchmark
    fun NOMEM_ArrayListLTIntGT_chunkedOBR30CBR() = list.chunked(30)

    @Benchmark
    fun kubit_NOMEM_IntList_takeOBR4000CBR() = intList.take(4000)

    @Benchmark
    fun NOMEM_ArrayListLTIntGT_takeOBR4000CBR() = list.take(4000)
}

