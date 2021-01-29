package io.github.fablinmc.fablin.codec

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

/**
 * Codecs for implementors of [ClosedRange].
 */
object ClosedRangeCodecs {
    val INT_RANGE: Codec<IntRange> = rangeCodec(Codec.INT, ::IntRange)
    val LONG_RANGE: Codec<LongRange> = rangeCodec(Codec.LONG, ::LongRange)

    inline fun <reified T, reified U: ClosedRange<T>> rangeCodec(valueCodec: Codec<T>, noinline factory: (T, T) -> U): Codec<U> {
        return RecordCodecBuilder.create { instance ->
            instance.group(
                    valueCodec.fieldOf("start").forGetter { it.start },
                    valueCodec.fieldOf("endInclusive").forGetter { it.endInclusive }
            ).apply(instance, factory)
        }
    }
}