package io.gitlab.arturbosch.detekt.rules.style

import io.gitlab.arturbosch.detekt.api.ActiveByDefault
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Finding
import io.gitlab.arturbosch.detekt.api.RequiresFullAnalysis
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.rules.isCallingWithNonNullCheckArgument
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtCallExpression

/**
 * Turn on this rule to flag `require` calls for not-null check that can be replaced with a `requireNotNull` call.
 *
 * <noncompliant>
 * require(x != null)
 * </noncompliant>
 *
 * <compliant>
 * requireNotNull(x)
 * </compliant>
 */
@ActiveByDefault(since = "1.21.0")
class UseRequireNotNull(config: Config) :
    Rule(
        config,
        "Use requireNotNull() instead of require() for checking not-null."
    ),
    RequiresFullAnalysis {

    override fun visitCallExpression(expression: KtCallExpression) {
        super.visitCallExpression(expression)
        if (expression.isCallingWithNonNullCheckArgument(requireFunctionFqName, bindingContext)) {
            report(Finding(Entity.from(expression), description))
        }
    }

    companion object {
        private val requireFunctionFqName = FqName("kotlin.require")
    }
}
