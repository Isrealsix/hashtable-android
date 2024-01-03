interface ObjectBuilder {
    fun typeName(): String?
    fun create(): Any?
    fun toString(value: Any?): String?
}