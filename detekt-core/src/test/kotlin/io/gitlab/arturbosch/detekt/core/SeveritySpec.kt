package io.gitlab.arturbosch.detekt.core

import io.gitlab.arturbosch.detekt.api.Severity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

class SeveritySpec {

    @ParameterizedTest
    @ValueSource(strings = ["warning", "WARNING", "wArNiNg"])
    fun `get warning severity from string ignoring case`(candidate: String) {
        val actual = parseToSeverity(candidate)

        assertThat(actual).isEqualTo(Severity.Warning)
    }

    @ParameterizedTest
    @EnumSource(Severity::class)
    fun `get all severities by lowercase`(severity: Severity) {
        val actual = parseToSeverity(severity.name.lowercase())

        assertThat(actual).isEqualTo(severity)
    }
}
