package io.github.fablinmc.fablin.codec

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import java.util.*

fun <O: Any, A> Codec<A>.field(name: String, getter: O.() -> A): RecordCodecBuilder<O, A> {
    return this.fieldOf(name).forGetter { it.getter() }
}

fun <O: Any, A> Codec<A>.optionalField(name: String, getter: O.() -> Optional<A>): RecordCodecBuilder<O, Optional<A>> {
    return this.optionalFieldOf(name).forGetter { it.getter() }
}

// TODO: Delete this, I only need it to "bookmark" dfu stuff.
fun foo() {
    val foo: Codec<Bar> = RecordCodecBuilder.create { instance ->
        instance
                .group(
                        Codec.INT.field("foo", Bar::foo)
                )
                .apply(instance, ::Bar)

    }
}

class Bar(val foo: Int)