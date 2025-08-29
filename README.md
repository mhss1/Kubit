<h3 align="center">
  <a href="https://github.com/mhss1/kubit">
    <img alt="Kubit" src="assets/logo.png" width="180" />
  </a>
  <br>
  Kubit
</h3>

<p align="center">
  <a href="https://central.sonatype.com/search?namespace=io.github.mhss1">
    <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.mhss1/kubit" />
  </a>
  <a href="http://www.apache.org/licenses/LICENSE-2.0">
    <img alt="GitHub License" src="https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat" />
  </a>
  <img alt="Top language" src="https://img.shields.io/github/languages/top/mhss1/kubit" />
  <img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/mhss1/kubit" />
</p>

---

Kubit delivers micro-optimized Kotlin utilities and data structures that improve performance and memory efficiency. It offers faster, allocation‑conscious alternatives to common operations across platforms.

## Key Features
- ⚡ Fast: tight loops, word-level bit operations, and in-place algorithms. See [Benchmakrs](benchmark.md).
- 🪶 Memory efficient: compact bit storage and unboxed primitive lists; views reuse source data.
- 🧮 Allocation-conscious: pre-sized outputs and fewer temporaries reduce GC pressure.
- 🌐 Multiplatform: Kotlin Multiplatform-ready across JVM, Android, and Native.
- 🧩 Familiar APIs: List-like primitives, array extensions, and serialization support.

Benchmarks: see [benchmark.md](benchmark.md).

Full docs: [Documentation](https://mhss1.github.io/Kubit)

> [!NOTE]
> Kubit is currently in alpha and under active development. Expect bugs, or breaking changes. APIs, modules, and file locations may change, move, or be removed between releases. Feedback and bug reports are welcome — please open an issue on [GitHub](https://github.com/mhss1/kubit/issues).


## Installation
```kotlin
sourceSets {
    commonMain.dependencies {
        implementation("io.github.mhss1:kubit:<version>")
    }
}
```
## Example Usage (selected)
### BitArray
```kotlin

val bits = BitArray(size = 1024) // ~8x less memory than BooleanArray (see benchmarks)

// Set / get single indices
bits[3] = true
println(bits[3])  // true

// Set a range (inclusive)
bits.set(from = 10, to = 25, value = true)

// Bulk checks — much faster than BooleanArray (see benchmarks)
println(bits.count(true)) // number of `true` bits
println(bits.anyTrue())
println(bits.allFalse())

// Bitwise operations (same size)
val mask = BitArray(1024).apply { fill(true) }
val union        = bits or mask
val intersection = bits and mask
val toggled      = bits xor mask
val overlap      = bits intersects mask

// Copy & conversion
val copy = bits.copyOf()
val booleans: BooleanArray = bits.toBooleanArray()
```

### Primitive Lists
`IntList`/`MutableIntList` mirror the familiar `List`/`MutableList` APIs—just specialized for primitives to avoid boxing and reduce allocations. The same API style exists for `LongList`, `FloatList`, and `DoubleList`.

```kotlin
val m = MutableIntList() // faster & significantly less memory than ArrayList<Int>
m.add(10)
m += 20
m.addAll(intArrayOf(30, 40))

// Read-only view and common ops (usual List-like methods)
val l: IntList = m.copy()
println(l.size)
println(l.contains(20))
println(l.filter { it % 2 == 0 })
println(l.take(3))
println(l.dropLast(1))
println(l.sorted())
println(l.sum())
```

kotlinx.serialization ready (deserialize normal JSON arrays into IntList):

```kotlin
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kubit.collections.list.IntList

@Serializable
data class Payload(val ids: IntList)

val json = Json { prettyPrint = false }
val decoded = json.decodeFromString<Payload>("""{"ids":[1,2,3,4]}""")
println(decoded.ids.sum()) // 10

val encoded = json.encodeToString(Payload(IntList(3) { it + 1 }))
println(encoded) // {"ids":[1,2,3]}
```

Seamless conversion from generic lists: mapping to `IntList` without extra steps:

```kotlin
// This map produces an IntList directly
val lengths: IntList = listOf("a", "ab", "abcd").map { it.length }
```

### In‑Place Windows & Sliding Operations
In‑place utilities expose views over the original list—no intermediate allocations.

```kotlin

val data = List(12) { it }

// Materialize windows as lightweight views — dramatically less allocation than standard windowed (see benchmarks)
val windows = data.windowedInPlace(windowSize = 4, step = 2)
println(windows.size) // count of windows

// Iterate windows without allocating new lists
data.forEachWindow(windowSize = 4, step = 2, partialWindows = true) { w ->
    println(w.joinToString())
}
```

## Documentation
- API reference: https://mhss1.github.io/Kubit