/**
 * context-handler - to.networld.semantic.contexthandler.common
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

package to.networld.semantic.contexthandler.common;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import to.networld.semantic.contexthandler.plugins.PluginManager;

/**
 * @author Alex Oberhauser
 */
public class Config {
	private static Config instance = null;
	private final Properties prop; 
	
	private Config() throws IOException {
		this.prop = new Properties();
		this.prop.load(Config.class.getResourceAsStream("/default.properties"));
	}
	
	public static Config getInstance() throws IOException {
		if ( instance == null )
			instance = new Config();
		return instance;
	}
	
	public String getProperty(String _key) { return this.prop.getProperty(_key); }
	
	public String getContextTagPrefix() { return this.prop.getProperty("core.ContextTagPrefix"); }

	/**
	 * @return Indent Size for the RDF/XML serialization.
	 */
	public int getRDFIndentSize() { return new Integer(this.prop.getProperty("core.RDFIndentSize")); }
	
	/**
	 * Expanded (value: true): <element></element>
	 * Not expanded (value: false): <element/>
	 * @return Should empty element expanded during RDF/XML serialization.
	 */
	public boolean getRDFExpandEmptyElements() { return new Boolean(this.prop.getProperty("core.RDFExpandEmptyElements")); }

	/**
	 * @return in which directory the jars plugins are stored.
	 */
	public String getPluginDirectory() {
		boolean relative = new Boolean(this.prop.getProperty("core.pluginDirRelative"));
		String pluginDir = this.prop.getProperty("core.pluginDir");
		if ( relative ) {
			URL url = PluginManager.class.getResource(pluginDir);
			if ( url != null )
				return url.getFile();
			return null;
		} else {
			return pluginDir;
		}
	}
	
	public String getTaxonomyNamespace() { return this.prop.getProperty("core.TaxonomyNamespace"); }
	
	public String getEbookNamespace() { return this.prop.getProperty("core.EbookNamespace"); }

}
