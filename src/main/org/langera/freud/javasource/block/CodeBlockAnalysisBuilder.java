package org.langera.freud.javasource.block;

import org.langera.freud.AbstractAnalysisBuilder;
import org.langera.freud.Builder;
import org.langera.freud.dsl.BooleanOperatorDsl;
import org.langera.freud.dsl.NumericOperatorDsl;
import org.langera.freud.javasource.block.assertion.CodeBlockNumberOfLinesCalculation;
import org.langera.freud.javasource.block.assertion.HasMethodCallAssertion;
import org.langera.freud.javasource.block.assertion.MethodDeclAssertionDelegator;
import org.langera.freud.javasource.methoddecl.MethodDeclaration;
import org.langera.freud.javasource.methoddecl.MethodDeclarationAnalysisBuilder;

/**
 * This file is part of "Freud".
 * <p/>
 * Freud is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Freud is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Amir Langer  langera_at_gmail_dot_com
 */

public final class CodeBlockAnalysisBuilder extends AbstractAnalysisBuilder<CodeBlockAnalysisBuilder, CodeBlock>
        implements CodeBlockDsl
{
    public BooleanOperatorDsl<CodeBlockAnalysisBuilder> codeBlock()
    {
        return trueAssertion();
    }

    public BooleanOperatorDsl<CodeBlockAnalysisBuilder> hasMethodCall(String methodCall)
    {
        setAssertion(new HasMethodCallAssertion(methodCall));
        return this;
    }

    @SuppressWarnings("unchecked")
    public BooleanOperatorDsl<CodeBlockAnalysisBuilder> method(BooleanOperatorDsl<MethodDeclarationAnalysisBuilder> methodDeclarationDsl)
    {
        setAssertion(new MethodDeclAssertionDelegator(
                ((Builder<MethodDeclaration>) methodDeclarationDsl).buildAssertion()));
        return this;
    }

    public NumericOperatorDsl<CodeBlockAnalysisBuilder> codeBlockNumberOfLines()
    {
        setCalculation(CodeBlockNumberOfLinesCalculation.getInstance());
        return this;
    }

    public Class<CodeBlock> getType()
    {
        return CodeBlock.class;
    }
}
