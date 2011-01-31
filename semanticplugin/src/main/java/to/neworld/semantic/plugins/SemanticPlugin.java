/**
 * context-handler - org.example.test plugin
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import to.networld.scrawler.foaf.Person;
import to.networld.scrawler.interfaces.IFOAFPerson;
import to.networld.semantic.contexthandler.common.Config;
import to.networld.semantic.contexthandler.common.StringHandler;
import to.networld.semantic.contexthandler.data.ContextTag;
import to.networld.semantic.contexthandler.plugins.Plugin;

/**
 * @author Alex Oberhauser
 */
public class SemanticPlugin implements Plugin {

	private static final String PERSON_FOAF_URL = "http://devnull.networld.to/foaf.rdf";
	private static final int NTHREADS = 50;
	
	public String getPluginName() { return "Semantic Web Plugin"; }
	public String getPluginDescription() { return "Starting from a FOAF file this plugins tries to crawl over RDF files and extract context information."; }
	public String getPluginVersion() { return "v0.01"; }
	
	/**
	 * @see to.networld.semantic.contexthandler.plugins.Plugin#getContextTags()
	 */
	@Override
	public Vector<ContextTag> getContextTags() {
		Vector<ContextTag> retVector = new Vector<ContextTag>();
		try {
			IFOAFPerson person = new Person(new URL(PERSON_FOAF_URL));
			
			Vector<String> publications = person.getPublications();
			ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
			List<Future<Vector<ContextTag>>> futures = new ArrayList<Future<Vector<ContextTag>>>();
			for ( String publication : publications ) {
				Callable<Vector<ContextTag>> worker = new PostFuture(publication);
				Future<Vector<ContextTag>> submit = executor.submit(worker);
				futures.add(submit);
			}
			executor.shutdown();
			
			Vector<String> interests = person.getInterests();
			for ( String interest : interests ) {
				String normalized = StringHandler.normalize(interest);
				ContextTag tag = new ContextTag(normalized);
				tag.setClassification(Config.getTaxonomyNamespace() + "PrivateInterests");
				tag.setPriority(1.0f);
				tag.setCooccurURI(PERSON_FOAF_URL);
				tag.setOrgSpelling(interest);
				retVector.add(tag);
			}
			
			for ( Future<Vector<ContextTag>> entry : futures ) {
				retVector.addAll(entry.get());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVector;
	}

}
