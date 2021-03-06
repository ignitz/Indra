package org.lambda3.indra.factory;

/*-
 * ==========================License-Start=============================
 * Indra Core Module
 * --------------------------------------------------------------------
 * Copyright (C) 2016 - 2017 Lambda^3
 * --------------------------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * ==========================License-End===============================
 */

import com.google.auto.service.AutoService;
import org.lambda3.indra.relatedness.*;
import org.lambda3.indra.exception.IndraInvalidParameterException;
import org.lambda3.indra.exception.IndraRuntimeException;

@AutoService(IndraFactory.class)
public final class RelatednessFunctionFactory extends CachedIndraFactory<RelatednessFunction> {

    public RelatednessFunctionFactory() {
        super(RelatednessFunction.class);
    }

    @Override
    protected RelatednessFunction create(String name, String... params) throws IndraInvalidParameterException {
        switch (name.toUpperCase()) {
            case "COSINE":
                return new CosineRelatednessFunction();
            case "ABSOLUTECOSINE":
            case "ABSOLUTE-COSINE":
            case "ABSOLUTE_COSINE":
                return new AbsoluteCosineRelatednessFunction();
            case "SPEARMAN":
                return new SpearmanRelatednessFunction();
            case "EUCLIDEAN":
                return new EuclideanRelatednessFunction();
            case "JACCARD":
                return new JaccardRelatednessFunction();
            case "PEARSON":
                return new PearsonRelatednessFunction();
            case "ALPHASKEW":
                return new AlphaSkewRelatednessFunction();
            case "CHEBYSHEV":
                return new ChebyshevRelatednessFunction();
            case "CITYBLOCK":
                return new CityBlockRelatednessFunction();
            case "DICE":
                return new DiceRelatednessFunction();
            case "JACCARD2":
                return new Jaccard2RelatednessFunction();
            case "JENSENSHANNON":
                return new JensenShannonRelatednessFunction();
            default:
                throw new IndraRuntimeException("Unsupported Score Function.");
        }
    }

    @Override
    public RelatednessFunction getDefault() {
        return new CosineRelatednessFunction();
    }

    @Override
    public String getName() {
        return BUILT_IN_FACTORY;
    }
}
