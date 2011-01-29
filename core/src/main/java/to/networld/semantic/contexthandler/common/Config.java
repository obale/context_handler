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

import java.net.URL;

import to.networld.semantic.contexthandler.plugins.PluginManager;

/**
 * @author obale
 */
public abstract class Config {
	public static String getContextTagPrefix() { return "#"; }

	/**
	 * @return Indent Size for the RDF/XML serialization.
	 */
	public static int getRDFIndentSize() { return 8; }
	
	/**
	 * Expanded (value: true): <element></element>
	 * Not expanded (value: false): <element/>
	 * @return Should empty element expanded during RDF/XML serialization.
	 */
	public static boolean getRDFExpandEmptyElements() { return false; }

	/**
	 * @return in which directory the jars plugins are stored.
	 */
	public static String getPluginDirectory() {
		URL url = PluginManager.class.getResource("/plugins/");
		if ( url != null )
			return url.getFile();
		return null;
	}
	
	public static String getTaxonomyNamespace() { return "http://context.networld.to/taxonomy.rdf#"; }

}
