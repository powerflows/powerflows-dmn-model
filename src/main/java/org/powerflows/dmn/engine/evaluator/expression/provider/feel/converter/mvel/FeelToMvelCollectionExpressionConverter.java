/*
 * Copyright (c) 2018-present PowerFlows.org - all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.powerflows.dmn.engine.evaluator.expression.provider.feel.converter.mvel;

import org.powerflows.dmn.engine.evaluator.expression.provider.binding.ExpressionEvaluationException;
import org.powerflows.dmn.engine.evaluator.expression.provider.feel.converter.ExpressionConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeelToMvelCollectionExpressionConverter implements ExpressionConverter {

    private final String collectionPattern = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    private final ExpressionConverter feelToMvelUnaryExpressionConverter;

    FeelToMvelCollectionExpressionConverter(ExpressionConverter feelToMvelUnaryExpressionConverter) {
        this.feelToMvelUnaryExpressionConverter = feelToMvelUnaryExpressionConverter;
    }

    @Override
    public String convert(final String feelExpression, final String inputName) {
        final String[] expressions = feelExpression.split(collectionPattern, -1);
        final List<String> mvelExpressions = new ArrayList<>();
        for (String expression : expressions) {
            final String trimmedFeelExpression = expression.trim();
            if (!trimmedFeelExpression.isEmpty()) {
                final String mvelExpression = feelToMvelUnaryExpressionConverter.convert(trimmedFeelExpression, inputName);

                mvelExpressions.add(mvelExpression);
            } else {
                throw new ExpressionEvaluationException("Can not evaluate feel expression '" + feelExpression + "'");
            }
        }

        return mvelExpressions
                .stream()
                .collect(Collectors.joining(" || "));
    }

    @Override
    public boolean isConvertible(final String feelExpression) {
        return feelExpression.split(collectionPattern, -1).length > 1;
    }
}