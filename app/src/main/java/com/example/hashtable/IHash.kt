import java.io.IOException

interface IHash {
    fun put(value: Any?)
    operator fun get(key: Any): Any?
    override fun toString(): String

    @Throws(IOException::class)
    fun forEach(a: ActionStarter)
}