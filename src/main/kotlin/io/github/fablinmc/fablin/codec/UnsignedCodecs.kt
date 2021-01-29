package io.github.fablinmc.fablin.codec

import com.mojang.serialization.Codec

/**
 * Codecs for unsigned numeric types. Note that as of Kotlin 1.4,
 * unsigned types are considered experimental.
 */
@ExperimentalUnsignedTypes
object UnsignedCodecs {
    val UBYTE: Codec<UByte> = Codec.BYTE.xmap(Byte::toUByte, UByte::toByte)
    val USHORT: Codec<UShort> = Codec.SHORT.xmap(Short::toUShort, UShort::toShort)
    val UINT: Codec<UInt> = Codec.INT.xmap(Int::toUInt, UInt::toInt)
    val ULONG: Codec<ULong> = Codec.LONG.xmap(Long::toULong, ULong::toLong)
}