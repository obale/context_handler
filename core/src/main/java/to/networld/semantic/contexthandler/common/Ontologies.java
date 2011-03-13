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

import org.dom4j.Namespace;

/**
 * Stores the Namespaces with related prefixes in a central and
 * consistent way. If you serialize a class to RDF/XML file.
 * 
 * @author Alex Oberhauser
 */
public abstract class Ontologies {
	public static Namespace RDF = new Namespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
	public static Namespace RDFS = new Namespace("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
	public static Namespace SCOT = new Namespace("scot", "http://scot-project.org/scot/ns#");
	public static Namespace SKOS = new Namespace("skos", "http://www.w3.org/2004/02/skos/core#");
	public static Namespace LIST = new Namespace("list", "http://crschmidt.net/ns/list#");
}
