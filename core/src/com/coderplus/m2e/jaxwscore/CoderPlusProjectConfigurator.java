/*******************************************************************************
 * Copyright (c) 2014 Aneesh Joseph
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Aneesh Joseph(coderplus.com)
 *******************************************************************************/
package com.coderplus.m2e.jaxwscore;

import java.io.File;

import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.project.MavenProject;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.MavenProjectUtils;
import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;
import org.eclipse.m2e.jdt.AbstractSourcesGenerationProjectConfigurator;
import org.eclipse.m2e.jdt.IClasspathDescriptor;

public class CoderPlusProjectConfigurator extends AbstractSourcesGenerationProjectConfigurator {

	private static final String OUTPUT_DIRECTORY = "sourceDestDir";
	private static final String WSIMPORT_TEST = "wsimport-test";
	private static final String WSGEN_TEST = "wsgen-test";
	private static final String XNOCOMPILE = "xnocompile";
	private static final String KEEP = "keep";

	@Override
	public AbstractBuildParticipant getBuildParticipant(IMavenProjectFacade projectFacade, MojoExecution execution,IPluginExecutionMetadata executionMetadata) {
		return new CoderPlusBuildParticipant(execution);
	}


	@Override
	public void configureRawClasspath(ProjectConfigurationRequest request,IClasspathDescriptor classpath, IProgressMonitor monitor)
			throws CoreException {
		IMavenProjectFacade facade = request.getMavenProjectFacade();
		MavenProject mavenProject = facade.getMavenProject();
		IProject project = facade.getProject();
		addNature(request.getProject(), "org.eclipse.jdt.core.javanature",monitor);
		for (MojoExecution execution : getMojoExecutions(request, monitor)) {
			boolean xnocompile = Boolean.TRUE.equals(maven.getMojoParameterValue(mavenProject, execution, XNOCOMPILE,Boolean.class, new NullProgressMonitor()));
			boolean keep = Boolean.TRUE.equals(maven.getMojoParameterValue(mavenProject, execution, KEEP,Boolean.class, new NullProgressMonitor()));
			File outputDirectory = maven.getMojoParameterValue(mavenProject, execution, OUTPUT_DIRECTORY,File.class, new NullProgressMonitor());
			if((keep || xnocompile) && outputDirectory != null){
				if(WSIMPORT_TEST.equals(execution.getGoal()) || WSGEN_TEST.equals(execution.getGoal())){
					IPath relativeSourcePath = MavenProjectUtils.getProjectRelativePath(project,outputDirectory.getAbsolutePath());
					classpath.addSourceEntry(project.getFullPath().append(relativeSourcePath),facade.getTestOutputLocation(), true);

				} else {
					IPath relativeSourcePath = MavenProjectUtils.getProjectRelativePath(project,outputDirectory.getAbsolutePath());
					classpath.addSourceEntry(project.getFullPath().append(relativeSourcePath),facade.getOutputLocation(), true);
				}
			}

		}
	}	 

}
