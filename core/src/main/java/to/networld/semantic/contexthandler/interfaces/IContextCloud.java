/**
 * context-handler - to.networld.semantic.contexthandler.interfaces
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

package to.networld.semantic.contexthandler.interfaces;

import java.util.Vector;

import to.networld.semantic.contexthandler.data.ContextTag;

/**
 * @author Alex Oberhauser
 */
public interface IContextCloud {
	
	/**
	 * @return The identifier of the context cloud.
	 */
	public String getContextCloudID();
	
	/**
	 * 
	 * @param contextTag Adds a context tag entry to the internal data structure.
	 */
	public void addContextTag(ContextTag contextTag);
	
	/**
	 * Stores the contexts tags internally. To write it to the RDF/XML document use flush().
	 * 
	 * @param contextTags Adds a context tags to the internal data structure.
	 */
	public void addContextTags(Vector<ContextTag> contextTags);
	
	/**
	 * Writes the context tags into the context cloud.
	 */
	public void flush();
}
