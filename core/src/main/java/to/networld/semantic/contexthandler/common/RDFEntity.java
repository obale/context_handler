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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Super class for all RDF/XML entities. Encapsulates the most
 * fundamental functionality for serialization.
 * 
 * @author Alex Oberhauser
 */
public class RDFEntity {
	private final SAXReader reader;
	protected final Document document;
	protected final DocumentFactory documentFactory;
	
	protected RDFEntity() throws DocumentException {
		this.reader = new SAXReader();
		this.documentFactory = this.reader.getDocumentFactory();
		this.document = this.documentFactory.createDocument();
	}
	
	public Element createElement(QName _qname) {
		return this.documentFactory.createElement(_qname);
	}
	
	@Override
	public String toString() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setIndentSize(Config.getRDFIndentSize());
			format.setExpandEmptyElements(Config.getRDFExpandEmptyElements());
			XMLWriter writer = new XMLWriter(os, format);
			writer.write(this.document);
			return os.toString();
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * RDF/XML compact serialization. Hard to read for humans, but lower message size.
	 * 
	 * @return The representation of the context tag entry as RDF/XML.
	 */
	public String toCompactRDF() { return this.document.asXML(); }
		
}
