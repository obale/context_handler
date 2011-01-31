/**
 * context-handler - org.example.testplugin
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

package to.neworld.semantic.plugins;

import java.io.IOException;
import java.util.Vector;

import org.dom4j.DocumentException;

import to.networld.semantic.contexthandler.common.Config;
import to.networld.semantic.contexthandler.data.ContextTag;
import to.networld.semantic.contexthandler.plugins.Plugin;

/**
 * @author Alex Oberhauser
 */
public class TestPlugin implements Plugin {

	public String getPluginName() { return "Test Plugin"; }
	public String getPluginDescription() { return "Plugin with hardcoded tags. Only for testing purpose to demonstrate the plugin mechanism."; }
	public String getPluginVersion() { return "v0.01"; }
	
	/**
	 * @see to.networld.semantic.contexthandler.plugins.Plugin#getContextTags()
	 */
	@Override
	public Vector<ContextTag> getContextTags() {
		Config config = null;
		try {
			config = Config.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Vector<ContextTag> retVector = new Vector<ContextTag>();
		try {
			ContextTag exampleTag = new ContextTag("computerscience");
			exampleTag.setOrgSpelling("Computer Science");
			exampleTag.setOrgSpelling("computer science");
			exampleTag.incrementFrequency();
			exampleTag.setOrgSpelling("computerScience");
			exampleTag.incrementFrequency();
			exampleTag.setPriority(0.80f);
			if ( config != null ) {
				exampleTag.setClassification(config.getTaxonomyNamespace() + "Interests");
				exampleTag.setClassification(config.getTaxonomyNamespace() + "Work");
			}
			exampleTag.setCooccurURI("http://example.org/work/foaf.rdf");
			exampleTag.setCooccurURI("http://example.org/private/foaf.rdf");
			retVector.add(exampleTag);
			
			ContextTag exampleTag2 = new ContextTag("artificialintelligence");
			exampleTag2.setOrgSpelling("AI");
			exampleTag2.setOrgSpelling("Artificial Intelligence");
			exampleTag2.incrementFrequency();
			exampleTag2.setOrgSpelling("ai");
			exampleTag2.incrementFrequency();
			exampleTag2.setPriority(1.0f);
			if ( config != null ) {
				exampleTag2.setClassification(config.getTaxonomyNamespace() + "Private");
				exampleTag2.setClassification(config.getTaxonomyNamespace() + "Interests");
			}
			exampleTag2.setCooccurURI("http://example.org/private/foaf.rdf");
			retVector.add(exampleTag2);
			
			ContextTag exampleTag3 = new ContextTag("semanticweb");
			exampleTag3.setOrgSpelling("semweb");
			exampleTag3.setOrgSpelling("Semantic Web");
			exampleTag3.incrementFrequency();
			exampleTag3.setOrgSpelling("semantic web");
			exampleTag3.incrementFrequency();
			exampleTag3.setPriority(1.0f);
			if ( config != null ) {
				exampleTag3.setClassification(config.getTaxonomyNamespace() + "Work");
				exampleTag3.setClassification(config.getTaxonomyNamespace() + "Private");
			}
			exampleTag3.setCooccurURI("http://example.org/work/foaf.rdf");
			exampleTag3.setCooccurURI("http://example.org/private/foaf.rdf");
			retVector.add(exampleTag3);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return retVector;
	}

}
