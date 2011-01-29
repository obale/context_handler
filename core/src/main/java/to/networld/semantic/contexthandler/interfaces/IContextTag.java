/**
 * context-handler - to.networld.semantic.contexthandler
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

/**
 * Interface for a single entry in the context cloud. Encapsulates all relevant
 * information that are later mapped to RDF/XML or also to other formal representation
 * that allows easy reasoning over the data set.
 * 
 * @author Alex Oberhauser
 */
public interface IContextTag {

	/**
	 * Setter method of the original spelling (before normalization).
	 * 
	 * @param tagOrgName The tag name as received from the source.
	 */
	public abstract void setOrgSpelling(String tagOrgName);

	/**
	 * Classifies this tag. Useful to use here a link to concept in a taxonomy,
	 * to simplify the later reasoning.
	 * 
	 * @param classificationURIorString Link to classification URI/URL or a simple String.
	 */
	public abstract void setClassification(String classificationURIorString);
	
	/**
	 * Setter method for the absolute frequency.
	 * 
	 * @param absoluteFrequency The absolute value how often the tag occurs in the context.
	 */
	public abstract void setAbsoluteFrequency(int absoluteFrequency);

	/**
	 * Setter method for the priority. The priority is in percent from 0 to 1.
	 * 
	 * @param _priority The priority that the tag has for this context.
	 */
	public abstract void setPriority(float priority);

	/**
	 * @param uri The uri from which source the context tag was read out.
	 */
	public abstract void setCooccurURI(String uri);

	/**
	 * Increment the frequency by one. Useful if the tag was found in another source.
	 */
	public abstract void incrementFrequency();

	public abstract String getNormalizedTag();
	public abstract Vector<String> getClassification();
	public abstract int getAbsoluteFrequency();
	public abstract float getPriority();
	public abstract Vector<String> getCooccurURI();
	public abstract Vector<String> getOrgSpelling();
}