#!/bin/sh
mvn clean package

cp \
        testplugin/target/contexthandler-testplugin*.jar \
        semanticplugin/target/contexthandler-semanticplugin-*.jar \
        epubplugin/target/contexthandler-epubplugin-*.jar \
        core/src/main/resources/plugins/

mvn clean package
