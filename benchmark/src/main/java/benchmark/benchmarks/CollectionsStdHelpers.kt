package benchmark.benchmarks

inline fun <T, E> List<T>.standardMap(transform: (T) -> E): List<E> = map(transform)
inline fun <T> List<T>.standardFilter(predicate: (T) -> Boolean): List<T> = filter(predicate)

inline fun IntArray.standardMap(transform: (Int) -> Int) = map(transform)
inline fun IntArray.standardFilter(predicate: (Int) -> Boolean) = filter(predicate)
inline fun IntArray.standardTake(n: Int) = take(n)