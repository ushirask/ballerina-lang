/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.ballerinalang.project;

import org.ballerinalang.project.exceptions.BalFileNotFoundException;
import org.ballerinalang.project.exceptions.NotRegularBalFileException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementation of the single bal file project.
 *
 * @since 2.0.0
 */
public class SingleFileProject extends ProjectImpl {
    private Path sourcePath;

    public SingleFileProject(Path sourceRootPath, CompilerOptions options)
            throws BalFileNotFoundException, NotRegularBalFileException {
        this.sourceRootPath = sourceRootPath;
        this.options = options;
        this.sourcePath = null;

        // check if path given is an absolute path. update source root accordingly.
        if (Paths.get(this.options.argList.get(0)).isAbsolute()) {
            this.sourcePath = Paths.get(this.options.argList.get(0));
        } else {
            this.sourcePath = this.sourceRootPath;
        }
        this.sourceRootPath = this.sourcePath.getParent();

        // check if the given file exists.
        if (!this.sourcePath.toFile().exists()) {
            throw new BalFileNotFoundException("'" + sourceRootPath + "' Ballerina file does not exist.");
        }

        // check if the given file is a regular file and not a symlink.
        if (!Files.isRegularFile(this.sourcePath)) {
            throw new NotRegularBalFileException(
                    "'" + sourceRootPath + "' is not a Ballerina file. Check if it is a symlink or a shortcut.");
        }
    }

    @Override
    public Project loadProject(Path sourceRootPath, CompilerOptions options) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path getSourcePath() {
        return this.sourcePath;
    }
}
