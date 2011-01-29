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

import java.util.Vector;

import org.dom4j.DocumentException;

import to.networld.semantic.contexthandler.data.ContextCloud;
import to.networld.semantic.contexthandler.plugins.Plugin;
import to.networld.semantic.contexthandler.plugins.PluginManager;

/**
 * @author Alex Oberhauser
 */
public class TestMain {
	
	public static void main(String[] args) throws DocumentException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		ContextCloud contextCloud = new ContextCloud("#myContext");
		PluginManager pluginManager = new PluginManager();
		Vector<Plugin> plugins = pluginManager.getPluginList();
		for ( Plugin plugin : plugins ) {
			System.out.println(plugin.getPluginName() + " - " + plugin.getPluginVersion() + " := " + plugin.getPluginDescription());
			contextCloud.addContextTags(plugin.getContextTags());
		}
		contextCloud.flush();
		System.out.println(contextCloud);
	}
}
