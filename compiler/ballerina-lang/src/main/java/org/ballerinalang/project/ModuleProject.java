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

import org.ballerinalang.project.exceptions.InvalidBallerinaProjectException;
import org.ballerinalang.project.exceptions.ModuleNotFoundException;
import org.ballerinalang.toml.model.LockFile;
import org.ballerinalang.toml.model.Manifest;
import org.wso2.ballerinalang.compiler.util.ProjectDirConstants;
import org.wso2.ballerinalang.compiler.util.ProjectDirs;
import org.wso2.ballerinalang.util.RepoUtils;

import java.nio.file.Path;

import static org.wso2.ballerinalang.compiler.util.ProjectDirConstants.SOURCE_DIR_NAME;

/**
 * Implementation of the module project.
 *
 * @since 2.0.0
 */
public class ModuleProject extends ProjectImpl {

    Manifest manifest;
    LockFile lockFile;

    public ModuleProject(Path sourceRootPath, CompilerOptions options)
            throws InvalidBallerinaProjectException, ModuleNotFoundException {
        this.sourceRootPath = sourceRootPath;
        this.options = options;

        // Project structure validation

        //// If module build, check module directory exists.
        if (!options.buildAll) {
            Path modulePath = this.sourceRootPath.resolve(ProjectDirConstants.SOURCE_DIR_NAME)
                    .resolve(this.options.argList.get(0));
            if (!(modulePath.toFile().exists() && modulePath.toFile().isDirectory())) {
                throw new ModuleNotFoundException();
            }
        }

        //// Validate and set the path of the source root.
        if (!ProjectDirs.isProject(this.sourceRootPath)) {
            Path findRoot = ProjectDirs.findProjectRoot(this.sourceRootPath);
            if (null == findRoot) {
                throw new InvalidBallerinaProjectException("you are trying to build/compile a Ballerina project that "
                        + "does not have a Ballerina.toml file.");
            }
            this.sourceRootPath = findRoot;
        }

        //// Check if command executed from project root.
        if (!RepoUtils.isBallerinaProject(this.sourceRootPath)) {
            throw new InvalidBallerinaProjectException(
                    "you are trying to build/compile a module that is not inside a project.");
        }
    }

    @Override
    public Project loadProject(Path sourceRootPath, CompilerOptions options) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isModuleExists(ModuleId moduleId) {
        String projectOrgName = this.manifest.getProject().getOrgName();
        if (projectOrgName.equals(moduleId.getOrgName())) {
            Path modulePath = this.sourceRootPath.resolve(SOURCE_DIR_NAME).resolve(moduleId.getModuleName());
            return modulePath.toFile().exists();
        }
        return false;
    }

    @Override
    public boolean hasLockFile() {
        return this.lockFile != null;
    }

    @Override
    public LockFile getLockFile() {
        return this.lockFile;
    }

    @Override
    public Path getSourcePath() {
        throw new UnsupportedOperationException();
    }
}
