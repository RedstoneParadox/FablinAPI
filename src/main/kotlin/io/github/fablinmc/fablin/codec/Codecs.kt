package io.github.fablinmc.fablin.codec

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

object Codecs {
    @ExperimentalUnsignedTypes
    val UBYTE: Codec<UByte> = Codec.BYTE.xmap(Byte::toUByte, UByte::toByte)
    @ExperimentalUnsignedTypes
    val USHORT: Codec<UShort> = Codec.SHORT.xmap(Short::toUShort, UShort::toShort)
    @ExperimentalUnsignedTypes
    val UINT: Codec<UInt> = Codec.INT.xmap(Int::toUInt, UInt::toInt)
    @ExperimentalUnsignedTypes
    val ULONG: Codec<ULong> = Codec.LONG.xmap(Long::toULong, ULong::toLong)

    val INT_RANGE: Codec<IntRange> = rangeCodec(Codec.INT, ::IntRange)
    val LONG_RANGE: Codec<LongRange> = rangeCodec(Codec.LONG, ::LongRange)

    private inline fun <reified T, reified U: ClosedRange<T>> rangeCodec(valueCodec: Codec<T>, noinline factory: (T, T) -> U): Codec<U> {
        return RecordCodecBuilder.create { instance ->
            instance.group(
                    valueCodec.fieldOf("start").forGetter { it.start },
                    valueCodec.fieldOf("endInclusive").forGetter { it.endInclusive }
            ).apply(instance, factory)
        }
    }
}