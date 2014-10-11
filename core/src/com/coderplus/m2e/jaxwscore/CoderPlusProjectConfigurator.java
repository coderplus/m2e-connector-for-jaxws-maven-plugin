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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;
import org.eclipse.m2e.jdt.AbstractJavaProjectConfigurator;
import org.eclipse.m2e.jdt.IClasspathDescriptor;

@SuppressWarnings("deprecation")
public class CoderPlusProjectConfigurator extends AbstractJavaProjectConfigurator {

	private static final String JAVA_NATURE = "org.eclipse.jdt.core.javanature";
	private static final String SOURCE_DEST_DIR = "sourceDestDir";
	private MojoExecution execution = null;

	@Override
	public AbstractBuildParticipant getBuildParticipant(IMavenProjectFacade projectFacade, MojoExecution execution,IPluginExecutionMetadata executionMetadata) {
		this.execution = execution;
		return new CoderPlusBuildParticipant(execution);

	}

	@Override
	public void configureRawClasspath(ProjectConfigurationRequest request,IClasspathDescriptor classpath, IProgressMonitor monitor) throws CoreException {
		IMavenProjectFacade facade = request.getMavenProjectFacade();
		//in case the project is not a java project, make it one.
		addNature(request.getProject(), JAVA_NATURE, monitor);
		//add the output directory as a source directory
		if(execution!= null){
			File outputDirectory = maven.getMojoParameterValue(facade.getMavenProject(), execution, SOURCE_DEST_DIR,File.class, new NullProgressMonitor());
			classpath.addSourceEntry(getFullPath(facade,outputDirectory),facade.getOutputLocation(), true);
		}

	}

}
