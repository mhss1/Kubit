package kubit.collections.inplace

internal fun throwIndexOutOfBoundsException(index: Int, size: Int) {
    throw IndexOutOfBoundsException("index: $index, size: $size")
}