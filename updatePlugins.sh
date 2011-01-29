#!/bin/sh
mvn clean package

cp \
        testplugin/target/contexthandler-testplugin*.jar \
        semanticplugin/target/contexthandler-semanticplugin-*.jar \
        core/src/main/resources/plugins/

mvn clean package
