/**
 * context-handler - to.networld.semantic.contexthandler.data
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

package to.networld.semantic.contexthandler.data;

import java.io.IOException;
import java.util.LinkedList;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;

import to.networld.semantic.contexthandler.common.Config;
import to.networld.semantic.contexthandler.common.Ontologies;
import to.networld.semantic.contexthandler.common.RDFEntity;
import to.networld.semantic.contexthandler.interfaces.IContextTag;

/**
 * Implementation of the {@link IContextTag} interface that encapsulates one entry
 * in the context cloud.
 * 
 * @author Alex Oberhauser
 */
public class ContextTag extends RDFEntity implements IContextTag {

	private String normalizedTag = null;
	private int absoluteFrequency = 0;
	private float priority = 0.0f;
	
	private LinkedList<String> orginalSpellingVector = new LinkedList<String>();
	private LinkedList<String> classificationVector = new LinkedList<String>();
	private LinkedList<String> cooccurVector = new LinkedList<String>();
	
	private final Element tagNode;
	
	/**
	 * @throws DocumentException
	 */
	public ContextTag(String _normalizedTag) throws DocumentException {
		super();
		this.normalizedTag = _normalizedTag;
		
		this.tagNode = this.document.addElement(new QName("Tag", Ontologies.SCOT));
		Config config;
		try {
			config = Config.getInstance();
			tagNode.addAttribute(new QName("about", Ontologies.RDF), config.getContextTagPrefix() + this.normalizedTag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.tagNode.addElement(new QName("name", Ontologies.SCOT)).addText(this.normalizedTag);
		
		this.setAbsoluteFrequency(1);
	}
	
	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#setOrgSpelling(java.lang.String)
	 */
	@Override
	public void setOrgSpelling(String _tagOrgName) {
		if ( !this.orginalSpellingVector.contains(_tagOrgName) ) {
			this.orginalSpellingVector.add(_tagOrgName);
			this.tagNode.addElement(new QName("spelling_variant", Ontologies.SCOT)).addText(_tagOrgName);
		}
	}

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#setClassification(java.lang.String)
	 */
	@Override
	public void setClassification(String _classificationURIorString) {
		if ( !this.classificationVector.contains(_classificationURIorString) ) {
			this.classificationVector.add(_classificationURIorString);
			this.tagNode.addElement(new QName("object", Ontologies.RDF)).addText(_classificationURIorString);
		}
	}

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#setAbsoluteFrequency(int)
	 */
	@Override
	public void setAbsoluteFrequency(int _absoluteFrequency) {
		this.absoluteFrequency = _absoluteFrequency;
	}

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#setPriority(float)
	 */
	@Override
	public void setPriority(float _priority) {
		this.priority = _priority;
	}

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#setCooccurURI(java.lang.String)
	 */
	@Override
	public void setCooccurURI(String _uri) {
		if ( !this.cooccurVector.contains(_uri) ) {
			this.cooccurVector.add(_uri);
			Element element = this.tagNode.addElement(new QName("cooccurs_in", Ontologies.SCOT));
			element.addAttribute(new QName("resource", Ontologies.RDF), _uri);
		}
	}

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#incrementFrequency()
	 */
	@Override
	public void incrementFrequency() {
		this.absoluteFrequency++;
	}

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#getNormalizedTag()
	 */
	@Override
	public String getNormalizedTag() { return this.normalizedTag; }

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#getClassification()
	 */
	@Override
	public LinkedList<String> getClassification() { return this.classificationVector; }

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#getAbsoluteFrequency()
	 */
	@Override
	public int getAbsoluteFrequency() { return this.absoluteFrequency; }

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#getPriority()
	 */
	@Override
	public float getPriority() { return this.priority; }

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#getCooccurURI()
	 */
	@Override
	public LinkedList<String> getCooccurURI() { return this.cooccurVector; }

	public Element getTagNode() { return this.tagNode; }
	
	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextTag#getOrgSpelling()
	 */
	@Override
	public LinkedList<String> getOrgSpelling() { return this.orginalSpellingVector; }
}
