package org.lambda3.indra.core;

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

import org.lambda3.indra.AnalyzedPair;
import org.lambda3.indra.AnalyzedTerm;
import org.lambda3.indra.request.RelatednessRequest;
import org.lambda3.indra.TextPair;
import org.lambda3.indra.composition.VectorComposer;
import org.lambda3.indra.core.vs.VectorSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StandardRelatednessClient extends RelatednessClient {

    protected StandardRelatednessClient(RelatednessRequest request, VectorSpace vectorSpace) {
        super(request, vectorSpace);
    }

    @Override
    protected List<AnalyzedPair> doAnalyzePairs(List<TextPair> pairs) {
        logger.debug("Analyzing {} pairs", pairs.size());

        List<AnalyzedPair> analyzedPairs = new ArrayList<>(pairs.size());
        IndraAnalyzer analyzer = vectorSpace.getAnalyzer();

        for (TextPair pair : pairs) {
            AnalyzedPair analyzedPair = analyzer.analyze(pair, AnalyzedPair.class);
            if (analyzedPair != null) {
                analyzedPairs.add(analyzedPair);
            }
        }

        return analyzedPairs;
    }

    @Override
    protected List<AnalyzedTerm> doAnalyze(String one, List<String> terms) {
        IndraAnalyzer analyzer = vectorSpace.getAnalyzer();

        List<AnalyzedTerm> ats = terms.stream().map(m -> new AnalyzedTerm(m, analyzer.analyze(m))).collect(Collectors.toList());
        if (one != null) {
            ats.add(new AnalyzedTerm(one, analyzer.analyze(one)));
        }

        return ats;
    }

    @Override
    protected Map<? extends AnalyzedPair, VectorPair> getVectors(List<? extends AnalyzedPair> analyzedPairs,
                                                                 VectorComposer termComposer,
                                                                 VectorComposer translationComposer) {
        return vectorSpace.getVectorPairs((List<AnalyzedPair>) analyzedPairs, termComposer);
    }
}
