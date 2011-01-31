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
import to.networld.semantic.contexthandler.common.StringHandler;
import to.networld.semantic.contexthandler.data.ContextTag;
import to.networld.semantic.contexthandler.plugins.Plugin;

/**
 * @author Alex Oberhauser
 */
public class EPubPlugin implements Plugin {

	private static final String EPUB_DIRECTORY = "/home/obale/FBooks/feedbooks.com/book";
	
	public String getPluginName() { return "EBook (ePub) Plugin"; }
	public String getPluginDescription() { return "Extracts the topics of ebooks that are in epub format."; }
	public String getPluginVersion() { return "v0.01"; }
	
	/**
	 * @see to.networld.semantic.contexthandler.plugins.Plugin#getContextTags()
	 */
	@Override
	public Vector<ContextTag> getContextTags() {
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
					tag.setClassification("http://example.org/taxonomy.rdf#EPub");
					tag.setPriority(1.0f);
					tag.setOrgSpelling(subject);
					String ebookURI = "http://example.org/ebook/" +  URLEncoder.encode(epub.getTitle() + "-" + epub.getAuthor(), "UTF-8");
					tag.setCooccurURI(ebookURI);
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
