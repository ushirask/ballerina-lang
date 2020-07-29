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

import org.ballerinalang.jvm.util.BLangConstants;
import org.ballerinalang.model.elements.PackageID;
import org.ballerinalang.project.exceptions.BalFileNotFoundException;
import org.ballerinalang.project.exceptions.InvalidBallerinaProjectException;
import org.ballerinalang.project.exceptions.InvalidModuleException;
import org.ballerinalang.project.exceptions.ModuleNotFoundException;
import org.ballerinalang.project.exceptions.NotRegularBalFileException;
import org.ballerinalang.toml.model.LockFile;
import org.wso2.ballerinalang.compiler.util.ProjectDirs;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementation of the Project.
 *
 * @since 2.0.0
 */
public class ProjectImpl implements Project {

     Path sourceRootPath;
     CompilerOptions options;

    public Project loadProject(Path sourceRootPath, CompilerOptions options)
            throws NotRegularBalFileException, BalFileNotFoundException, InvalidBallerinaProjectException,
            ModuleNotFoundException {
        // Validate and decide the source root and the full path of the source.
        sourceRootPath = null != options.sourceRoot ? Paths.get(options.sourceRoot).toAbsolutePath() : sourceRootPath;

        // if ballerina file
        if (options.argList != null && options.argList.size() > 0
                && options.argList.get(0).endsWith(BLangConstants.BLANG_SRC_FILE_SUFFIX)) {
            // get ballerina project root path
            Path findRoot = ProjectDirs.findProjectRoot(sourceRootPath.resolve(options.argList.get(0)));

            // found ballerina root path & valid project structure
            if (isValidProjectStructure(findRoot, sourceRootPath, options.argList.get(0))) {
                return new ModuleProject(findRoot, options);
            }
            // else consider it as single bal file
            return new SingleFileProject(sourceRootPath.resolve(options.argList.get(0)), options);
        }
        // else ballerina project
        return new ModuleProject(sourceRootPath, options);
    }

    @Override
    public boolean isModuleExists(PackageID moduleId) {
        return false;
    }

    @Override
    public Path getBaloPath(PackageID moduleId) throws InvalidModuleException {
        return null;
    }

    @Override
    public boolean isModuleExists(ModuleId moduleId) {
        return false;
    }

    @Override
    public Module getModule(ModuleId moduleId) {
        return null;
    }

    @Override
    public Toml getToml() {
        return null;
    }

    @Override
    public boolean hasLockFile() {
        return false;
    }

    @Override
    public LockFile getLockFile() {
        return null;
    }

    @Override
    public JarResolver getJarResolver() {
        return null;
    }

    @Override
    public void setOptions() {

    }

    @Override
    public Path getSourceRootPath() {
        return sourceRootPath;
    }

    @Override
    public Path getSourcePath() {
        return null;
    }

    private boolean isValidProjectStructure(Path findRoot, Path sourceRootPath, String argsPath) {
        if (findRoot != null) {
            Path modulePath = sourceRootPath.resolve(argsPath).getParent();
            if (modulePath != null) {
                Path projectPath = modulePath.getParent();
                if (projectPath != null) {
                    return projectPath == findRoot;
                }
            }
        }
        return false;
    }
}
