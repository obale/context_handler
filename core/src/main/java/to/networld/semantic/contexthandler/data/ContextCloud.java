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

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;

import to.networld.semantic.contexthandler.common.Ontologies;
import to.networld.semantic.contexthandler.common.RDFEntity;
import to.networld.semantic.contexthandler.interfaces.IContextCloud;

/**
 * @author Alex Oberhauser
 */
public class ContextCloud extends RDFEntity implements IContextCloud {
	private final String contextCloudID;
	private final Element contextTagRootNode;
	
	private HashMap<String, ContextTag> internalContextCloud = new HashMap<String, ContextTag>();
	
	public ContextCloud(String _contextCloudID) throws DocumentException {
		super();
		this.contextCloudID = _contextCloudID;
		
		Element rdfRootNode = this.document.addElement(new QName("RDF", Ontologies.RDF));
		rdfRootNode.add(Ontologies.RDF);
		rdfRootNode.add(Ontologies.RDFS);
		rdfRootNode.add(Ontologies.SCOT);
		rdfRootNode.add(Ontologies.LIST);
		
		this.contextTagRootNode = rdfRootNode.addElement(new QName("TagCloud", Ontologies.SCOT));
		this.contextTagRootNode.addAttribute(new QName("about", Ontologies.RDF), _contextCloudID);
	}
	
	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextCloud#getContextCloudID()
	 */
	@Override
	public String getContextCloudID() { return this.contextCloudID; }

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextCloud#addContextTag(to.networld.semantic.contexthandler.data.ContextTag)
	 */
	@Override
	public void addContextTag(ContextTag contextTag) {
		String normalized = contextTag.getNormalizedTag();
		if ( this.internalContextCloud.containsKey(normalized) ) {
			ContextTag oldTag = this.internalContextCloud.get(normalized);
			oldTag.incrementFrequency();
			LinkedList<String> classifications = contextTag.getClassification();
			for ( String entry : classifications )
				oldTag.setClassification(entry);
			LinkedList<String> cooccur = contextTag.getCooccurURI();
			for ( String entry : cooccur )
				oldTag.setCooccurURI(entry);
			LinkedList<String> orgspelling = contextTag.getOrgSpelling();
			for ( String entry : orgspelling )
				oldTag.setOrgSpelling(entry);
			float priority = contextTag.getPriority();
			if ( priority > oldTag.getPriority() )
				oldTag.setPriority(priority);
		} else {
			this.internalContextCloud.put(contextTag.getNormalizedTag(), contextTag);
		}
	}
	
	@Override
	public void addContextTags(Vector<ContextTag> _tags) {
		for ( ContextTag entry : _tags ) {
			this.addContextTag(entry);
		}
	}

	/**
	 * @see to.networld.semantic.contexthandler.interfaces.IContextCloud#flush()
	 */
	@Override
	public void flush() {
		Collection<ContextTag> tags = this.internalContextCloud.values();
		for ( ContextTag entry : tags ) {
			Element tag = entry.getTagNode();
			tag.detach();
		
			Element cooccureElement = this.contextTagRootNode.addElement(new QName("contains", Ontologies.SCOT));
			cooccureElement.add(tag);
			
			tag.addElement(new QName("own_afrequency", Ontologies.SCOT)).addText(entry.getAbsoluteFrequency() + "");
			tag.addElement(new QName("priority", Ontologies.LIST)).addText(entry.getPriority() + "");
		}
	}

}
