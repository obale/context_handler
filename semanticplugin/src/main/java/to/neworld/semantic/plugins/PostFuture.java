/**
 * contexthandler-semanticplugin - to.neworld.semantic.plugins
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
import java.net.URL;
import java.util.Vector;
import java.util.concurrent.Callable;

import to.networld.scrawler.sioc.Post;
import to.networld.semantic.contexthandler.common.Config;
import to.networld.semantic.contexthandler.common.StringHandler;
import to.networld.semantic.contexthandler.data.ContextTag;

/**
 * @author Alex Oberhauser
 */
public class PostFuture implements Callable<Vector<ContextTag>> {
	private final String url;
	
	public PostFuture(String _url) {
		this.url = _url;
	}
	
	/**
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Vector<ContextTag> call() throws Exception {
		Config config = null;
		try {
			config = Config.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Vector<ContextTag> retVector = new Vector<ContextTag>();
		Post post = new Post(new URL(this.url));
		Vector<String> postTopics = post.getTopics();
		for ( String topic : postTopics ) {
			String normalized = StringHandler.normalize(topic);
			ContextTag tag = new ContextTag(normalized);
			if (config != null )
				tag.setClassification(config.getTaxonomyNamespace() + "PublicationTopic");
			tag.setPriority(1.0f);
			tag.setCooccurURI(this.url);
			tag.setOrgSpelling(topic);
			retVector.add(tag);
		}
		return retVector;
	}

}
