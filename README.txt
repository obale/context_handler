PLUGIN TEST
===========

To test if your plugin works copy it to the "core" plugins folder:

cp \
        ${plugin_name}/target/${plugin_lib_name}.jar \
        core/src/main/resources/plugins/

After that you maybe have to execute "mvn clean package" in the meta project
(same directory in which this README is stored).
