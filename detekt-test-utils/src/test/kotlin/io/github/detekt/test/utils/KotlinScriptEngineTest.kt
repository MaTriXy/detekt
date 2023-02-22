package io.github.detekt.test.utils

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class KotlinScriptEngineTest {

    @Test
    fun `can compile a valid script`() {
        val code = """
            package foo.a
            
            class A
        """.trimIndent()
        KotlinScriptEngine.compile(code)
    }

    @Test
    fun `fails compiling an invalid script`() {
        val invalidCode = """
            package foo.b
            
            val unknownType: UnknownType
        """.trimIndent()
        assertThatThrownBy { KotlinScriptEngine.compile(invalidCode) }
            .isInstanceOf(KotlinScriptException::class.java)
    }

    @RepeatedTest(10)
    fun `can compile the same script repeatedly`() {
        val code = """
            package foo.c
            
            class A
        """.trimIndent()
        KotlinScriptEngine.compile(code)
    }

    @RepeatedTest(10)
    fun `fails repeatedly on invalid script`() {
        val invalidCode = """
            package foo.d
            
            val unknownType: UnknownType
        """.trimIndent()
        assertThatThrownBy { KotlinScriptEngine.compile(invalidCode) }
            .isInstanceOf(KotlinScriptException::class.java)
    }
}
