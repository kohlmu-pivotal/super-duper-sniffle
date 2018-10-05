package io.sniffle.serialization.impl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.Serializable
import java.util.stream.Stream

class FSTSerializationServiceImplTest {

    private val serializationService = FSTSerializationServiceImpl()

    @ParameterizedTest(name = "{index} type={0}")
    @MethodSource("createStringValue", "createLongValue", "createIntValue", "createShortValue",
            "createByteValue", "createEnumValue", "createComplexDataObject")
    fun testSerialization(type: String, valueToSerialize: Any) {
        val serializedValue = serializationService.serialize(valueToSerialize)
        val deserializedObject = serializationService.deserialize(serializedValue)
        Assertions.assertEquals(valueToSerialize, deserializedObject)
    }

    companion object {
        @JvmStatic
        private fun createStringValue(): Stream<Arguments> = Stream.of(Arguments.of("String", "This is a String for testing"))

        @JvmStatic
        private fun createLongValue(): Stream<Arguments> = Stream.of(Arguments.of("Long", 1.toLong()))

        @JvmStatic
        private fun createIntValue(): Stream<Arguments> = Stream.of(Arguments.of("Int", 1.toInt()))

        @JvmStatic
        private fun createShortValue(): Stream<Arguments> = Stream.of(Arguments.of("Short", 1.toShort()))

        @JvmStatic
        private fun createByteValue(): Stream<Arguments> = Stream.of(Arguments.of("Byte", 1.toByte()))

        @JvmStatic
        private fun createEnumValue(): Stream<Arguments> = Stream.of(Arguments.of("Enum", TestEnum.ONE))

        @JvmStatic
        private fun createComplexDataObject(): Stream<Arguments> = Stream.of(Arguments.of("UserObject", User()))
    }

    private enum class TestEnum {
        ONE,
        TWO
    }

    private data class User(val name: String = "First", val surname: String = "Last", val address: Address = Address("line1", "line2", "line3", "City", 12345, "IcePark")) : Serializable
    private data class Address(val line1: String, val line2: String, val line3: String, val city: String, val postCode: Int, val country: String) : Serializable
}


