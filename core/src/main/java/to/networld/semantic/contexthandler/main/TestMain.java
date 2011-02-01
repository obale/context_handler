/**
 * context-handler - to.networld.semantic.contexthandler.main
 *
 * Copyright (C) 2011 by Networld Project
 * Written by Alex Oberhauser <oberhauseralex@networld.to>
 * All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see <http://www.gnu.org/licenses/>
 */

package to.networld.semantic.contexthandler.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Vector;

import org.apache.log4j.Logger;

import to.networld.semantic.contexthandler.data.ContextCloud;
import to.networld.semantic.contexthandler.plugins.Plugin;
import to.networld.semantic.contexthandler.plugins.PluginManager;

/**
 * @author Alex Oberhauser
 */
public class TestMain {
	
	public static void main(String[] args) throws Exception {
		ContextCloud contextCloud = new ContextCloud("#myContext");
		PluginManager pluginManager = new PluginManager();
		Vector<Plugin> plugins = pluginManager.getPluginList();
		for ( Plugin plugin : plugins ) {
			try {
				Logger.getLogger(TestMain.class).info("[*] Execute Plugin -> " + plugin.getPluginName() + " " + plugin.getPluginVersion() + " - " + plugin.getPluginDescription());	
				contextCloud.addContextTags(plugin.getContextTags());
			} catch (Exception e) {
				Logger.getLogger(TestMain.class).error("[!!] Not able to gather context information from plugin '" + 
						plugin.getPluginName() + "' Reason: " + e.getLocalizedMessage());
			}
		}
		System.out.flush();
		contextCloud.flush();
		
		System.err.println(contextCloud);
		
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("/tmp/context_cloud.rdf")));
		out.write(contextCloud.toString().getBytes());
	}
}
