/**
 * context-handler - to.networld.semantic.contexthandler.plugins
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

package to.networld.semantic.contexthandler.plugins;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.Policy;
import java.util.Vector;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import to.networld.semantic.contexthandler.common.Config;

/**
 * @author Alex Oberhauser
 */
public class PluginManager {
	
	public String getPluginEntryClass(InputStream _profileXMLConfig) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(_profileXMLConfig);
		Node node = doc.selectSingleNode("/plugin");
		if (node != null )
			return node.valueOf("@class");
		return null;
	}
	
	public Plugin loadPlugin(URL pluginURL) {
		ClassLoader pluginLoader = URLClassLoader.newInstance(new URL[] { pluginURL });
		try {
			JarFile jarfile = new JarFile(pluginURL.getFile());
			ZipEntry pluginConfig = jarfile.getEntry("plugin.xml");
			
			if ( pluginConfig != null ) {
				String pluginPath = getPluginEntryClass(jarfile.getInputStream(pluginConfig));

				if ( pluginPath != null )
					return (Plugin) pluginLoader.loadClass(pluginPath).newInstance();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PluginManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException, DocumentException {
		Policy.setPolicy(new PluginPolicy());
		System.setSecurityManager(new SecurityManager());
	}
	
	public Vector<Plugin> getPluginList() {
		Vector<Plugin> retVector = new Vector<Plugin>();
		String pluginDirectory = Config.getPluginDirectory();
		if ( pluginDirectory != null ) {
			File pluginDirectoryFile =  new File(pluginDirectory);
			String [] jarFiles = pluginDirectoryFile.list(new JarFilter());
			for ( int count = 0; count < jarFiles.length; count++ ) {
				try {
					Plugin plugin = loadPlugin(new URL("file://" + pluginDirectory + jarFiles[count]));
					if ( plugin != null )
						retVector.add(plugin);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		return retVector;
	}
}
