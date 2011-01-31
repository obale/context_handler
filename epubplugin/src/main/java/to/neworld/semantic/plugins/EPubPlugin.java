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

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Vector;
import java.util.zip.ZipFile;

import org.dom4j.DocumentException;

import to.networld.scrawler.ebooks.epub.EPub;
import to.networld.semantic.contexthandler.common.Config;
import to.networld.semantic.contexthandler.common.StringHandler;
import to.networld.semantic.contexthandler.data.ContextTag;
import to.networld.semantic.contexthandler.plugins.Plugin;

/**
 * Plugin that extracts context information from ePub (ebooks). As context information
 * the subject about the book is used. The cooccure URI is not the path to the ebook, but
 * a combination of Title, Author and special Namespace for ebooks. 
 * 
 * @author Alex Oberhauser
 */
public class EPubPlugin implements Plugin {

	/**
	 * TODO: Write a central configuration file for all plugins and read this value from the config. 
	 */
	private static final String EPUB_DIRECTORY = "/home/obale/FBooks/feedbooks.com/book";
	
	public String getPluginName() { return "EBook (ePub) Plugin"; }
	public String getPluginDescription() { return "Extracts the topics of ebooks that are in epub format."; }
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
		File epubDir = new File(EPUB_DIRECTORY);
		String[] epubPaths = epubDir.list(new EPubFilter());
		for (int count = 0; count < epubPaths.length; count++ ) {
			try {
				String epubStr = epubDir.getAbsolutePath() + "/" + epubPaths[count];
				EPub epub = new EPub(new ZipFile(epubStr));
				Vector<String> subjects = epub.getSubjects();
				for ( String subject : subjects ) {
					ContextTag tag = new ContextTag(StringHandler.normalize(subject));
					if ( config != null )
						tag.setClassification(config.getTaxonomyNamespace() + "EPub");
					tag.setPriority(1.0f);
					tag.setOrgSpelling(subject);
					if ( config != null ) {
						String ebookURI = config.getEbookNamespace() +  URLEncoder.encode(epub.getTitle() + "-" + epub.getAuthor(), "UTF-8");
						tag.setCooccurURI(ebookURI);
					}
					retVector.add(tag);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return retVector;
	}
}
