package benchmark.benchmarks

import benchmark.reporting.util.BENCHMARK_COLLECTION_SIZE
import kubit.collections.array.BitArray
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole

@State(Scope.Benchmark)
class BitArrayBenchmark {
    private val size = BENCHMARK_COLLECTION_SIZE
    private var bitArr = BitArray(size)
    private var bitArr2 = BitArray(size)
    private var bitArrLastTrue = BitArray(size).apply {
        this[size - 1] = true
    }
    private var bitArrLastTrue2 = BitArray(size).apply {
        this[size - 1] = true
    }
    private var boolArr = BooleanArray(size)
    private var boolArr2 = BooleanArray(size)
    private var boolArrLastTrue = BooleanArray(size).apply {
        this[size - 1] = true
    }
    private var boolArrLastTrue2 = BooleanArray(size).apply {
        this[size - 1] = true
    }

    private val setIndices = IntArray(size / 4)


    private var seed = 0
    private val random = java.util.Random(0)

    @Setup(Level.Iteration)
    fun prepare() {
        seed++
        for (i in 0 until size) {
            val v = (((i + seed) and 7) xor seed) and 1 == 0
            boolArr[i] = v
            bitArr[i] = v
            boolArr2[i] = v
            bitArr2[i] = v
        }
        for (i in 0 until size / 5) {
            setIndices[i] = random.nextInt(size)
        }
        bitArrLastTrue = BitArray(size).apply {
            this[size - 1] = true
        }
        boolArrLastTrue = BooleanArray(size).apply {
            this[size - 1] = true
        }
        bitArrLastTrue2 = BitArray(size).apply {
            this[size - 1] = true
        }
        boolArrLastTrue2 = BooleanArray(size).apply {
            this[size - 1] = true
        }
    }

    @Benchmark
    fun NOMEM_BooleanArray_forEach(bh: Blackhole) {
        var acc = 0
        boolArr.forEach {
            acc = acc xor if (it) 1 else 0
        }
        bh.consume(acc)
    }

    @Benchmark
    fun kubit_NOMEM_BitArray_forEach(bh: Blackhole) {
        var acc = 0
        bitArr.forEach {
            acc = acc xor if (it) 1 else 0
        }
        bh.consume(acc)
    }

    @Benchmark
    fun NOMEM_BooleanArray_set(): BooleanArray {
        for (i in 0 until setIndices.size) {
            boolArr[setIndices[i]] = true
        }
        return boolArr
    }

    @Benchmark
    fun kubit_NOMEM_BitArray_set(): BitArray {
        for (i in 0 until setIndices.size) {
            bitArr[setIndices[i]] = true
        }
        return bitArr
    }

    @Benchmark
    fun NOMEM_BooleanArray_count(): Int {
        return boolArrLastTrue.count { !it }
    }

    @Benchmark
    fun kubit_NOMEM_BitArray_count(): Int {
        return bitArrLastTrue.count(false)
    }

    @Benchmark
    fun kubit_NOPERF_BitArray_init(): BitArray {
        return BitArray(BENCHMARK_COLLECTION_SIZE)
    }

    @Benchmark
    fun NOPERF_BooleanArray_init(): BooleanArray {
        return BooleanArray(BENCHMARK_COLLECTION_SIZE)
    }

    @Benchmark
    fun NOMEM_BooleanArray_all(): Boolean {
        return boolArrLastTrue.all { !it }
    }

    @Benchmark
    fun kubit_NOMEM_BitArray_all(): Boolean {
        return bitArrLastTrue.allFalse()
    }

    @Benchmark
    fun NOMEM_BooleanArray_fill() {
        boolArr.fill(true)
    }

    @Benchmark
    fun kubit_NOMEM_BitArray_fill() {
        bitArr.fill(true)
    }

    @Benchmark
    fun NOMEM_BooleanArray_contentEquals(): Boolean {
        return boolArr.contentEquals(boolArr2)
    }

    @Benchmark
    fun kubit_NOMEM_BitArray_contentEquals(): Boolean {
        return bitArr.contentEquals(bitArr2)
    }

    @Benchmark
    fun NOMEM_BooleanArray_xor(): BooleanArray {
        return BooleanArray(size) { boolArr[it] xor boolArr2[it] }
    }

    @Benchmark
    fun kubit_NOMEM_BitArray_xor(): BitArray {
        return bitArr xor bitArr2
    }

    @Benchmark
    fun NOMEM_BooleanArray_intersects(): Boolean {
        val a = boolArrLastTrue
        val b = boolArrLastTrue2
        for (i in 0 until a.size) {
            if (a[i] && b[i]) return true
        }
        return false
    }


    @Benchmark
    fun kubit_NOMEM_BitArray_intersects(): Boolean {
        return bitArrLastTrue intersects bitArrLastTrue2
    }
}