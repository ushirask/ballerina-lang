module io.ballerina.cli {
    exports io.ballerina.cli;
    exports io.ballerina.cli.launcher;
    exports io.ballerina.cli.utils;
    exports io.ballerina.cli.cmd;

    requires io.ballerina.runtime;
    requires io.ballerina.lang;
    requires io.ballerina.tools.api;
    requires io.ballerina.testerina.runtime;
    requires io.ballerina.testerina.core;
    requires info.picocli;
    requires org.apache.commons.compress;
    requires com.google.gson;
    requires org.jacoco.core;
    requires io.ballerina.central.client;
    requires io.ballerina.docerina;
    requires jdk.httpserver;
    requires org.slf4j;
    requires io.ballerina.shell.cli;
    requires io.ballerina.toml;
    requires io.ballerina.identifier;
    requires org.objectweb.asm;
}
