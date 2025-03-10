/*
 *  Copyright (c) 2023, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package io.ballerina.projects.internal;

import io.ballerina.projects.BalToolsManifest;
import io.ballerina.projects.BalToolsToml;
import io.ballerina.projects.PackageVersion;
import io.ballerina.projects.ProjectException;
import io.ballerina.projects.TomlDocument;
import io.ballerina.projects.util.FileUtils;
import io.ballerina.toml.semantic.TomlType;
import io.ballerina.toml.semantic.ast.TomlTableArrayNode;
import io.ballerina.toml.semantic.ast.TomlTableNode;
import io.ballerina.toml.semantic.ast.TopLevelNode;
import io.ballerina.toml.validator.TomlValidator;
import io.ballerina.toml.validator.schema.Schema;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.ballerina.projects.internal.ManifestUtils.getStringFromTomlTableNode;

/**
 * {@code BalToolsManifestBuilder} processes the bal-tools toml file parsed
 * and populate a {@link BalToolsManifest}.
 *
 * @since 2201.6.0
 */
public class BalToolsManifestBuilder {
    private final Optional<TomlDocument> balToolsToml;

    private final BalToolsManifest balToolsManifest;

    private BalToolsManifestBuilder(TomlDocument balToolsToml) {
        this.balToolsToml = Optional.ofNullable(balToolsToml);
        this.balToolsManifest = parseAsBalToolsManifest();
    }

    public static BalToolsManifestBuilder from(TomlDocument balToolsToml) {
        return new BalToolsManifestBuilder(balToolsToml);
    }

    public static BalToolsManifestBuilder from(BalToolsToml balToolsToml) {
        return new BalToolsManifestBuilder(balToolsToml.tomlDocument());
    }

    public BalToolsManifest getBalToolsManifest() {
        return balToolsManifest;
    }

    public BalToolsManifestBuilder addTool(String id, String org, String name, String version) {
        balToolsManifest.addTool(id, org, name, version);
        return this;
    }

    public BalToolsManifestBuilder removeTool(String id) {
        balToolsManifest.removeTool(id);
        return this;
    }

    private BalToolsManifest parseAsBalToolsManifest() {
        if (balToolsToml.isEmpty() || balToolsToml.get().toml().rootNode().entries().isEmpty()) {
            return BalToolsManifest.from();
        }
        validateBalToolsTomlAgainstSchema();
        Map<String, BalToolsManifest.Tool> tools = getTools();
        return BalToolsManifest.from(tools);
    }

    private void validateBalToolsTomlAgainstSchema() {
        if (balToolsToml.isEmpty()) {
            return;
        }
        TomlValidator balToolsTomlValidator;
        try {
            balToolsTomlValidator = new TomlValidator(
                    Schema.from(FileUtils.readFileAsString("bal-tools-toml-schema.json")));
        } catch (IOException e) {
            throw new ProjectException("Failed to read the bal-tools.toml validator schema file.");
        }
        balToolsTomlValidator.validate(balToolsToml.get().toml());
    }

    private Map<String, BalToolsManifest.Tool> getTools() {
        if (balToolsToml.isEmpty()) {
            return new HashMap<>();
        }

        TomlTableNode tomlTableNode = balToolsToml.get().toml().rootNode();

        if (tomlTableNode.entries().isEmpty()) {
            return new HashMap<>();
        }

        TopLevelNode toolEntries = tomlTableNode.entries().get("tool");
        if (toolEntries == null || toolEntries.kind() == TomlType.NONE) {
            return new HashMap<>();
        }

        Map<String, BalToolsManifest.Tool> tools = new HashMap<>();
        if (toolEntries.kind() == TomlType.TABLE_ARRAY) {
            TomlTableArrayNode toolTableArray = (TomlTableArrayNode) toolEntries;

            for (TomlTableNode toolNode : toolTableArray.children()) {
                String id = getStringValueFromToolNode(toolNode, "id");
                String org = getStringValueFromToolNode(toolNode, "org");
                String name = getStringValueFromToolNode(toolNode, "name");
                String version = getStringValueFromToolNode(toolNode, "version");

                // If id, org or name, one of the value is null, ignore tool record
                if (id == null || org == null || name == null) {
                    continue;
                }

                if (version != null) {
                    try {
                        PackageVersion.from(version);
                    } catch (ProjectException ignore) {
                        continue;
                    }
                }
                tools.put(id, new BalToolsManifest.Tool(id, org, name, version));
            }
        }
        return tools;
    }

    private String getStringValueFromToolNode(TomlTableNode pkgNode, String key) {
        TopLevelNode topLevelNode = pkgNode.entries().get(key);
        if (topLevelNode == null) {
            return null;
        }
        return getStringFromTomlTableNode(topLevelNode);
    }

    public BalToolsManifest build() {
        return this.balToolsManifest;
    }
}
