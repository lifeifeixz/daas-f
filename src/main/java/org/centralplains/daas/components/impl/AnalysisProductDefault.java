package org.centralplains.daas.components.impl;

import org.centralplains.daas.components.Analysis;
import org.jsoup.nodes.Document;

public class AnalysisProductDefault implements Analysis {

    @Override
    public <T> T resolve(Document document, T t) {
        return null;
    }
}
