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

import java.util.Vector;

import to.networld.semantic.contexthandler.data.ContextTag;

/**
 * @author Alex Oberhauser
 */
public interface Plugin {
	
	public String getPluginName();
	public String getPluginDescription();
	public String getPluginVersion();
	
	/**
	 * The Plugin that implements against this interface should try
	 * to gather context information from different sources and return
	 * this information to the core system.
	 * 
	 * @return A Vector with all found context tags.
	 */
	public Vector<ContextTag> getContextTags();
}
