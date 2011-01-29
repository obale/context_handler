/**
 * contexthandler-core - to.networld.semantic.contexthandler.common
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

/**
 * @author Alex Oberhauser
 */
public abstract class StringHandler {
	
	/**
	 * Normalizes an input String. Weakens the fact that semantically the same term with
	 * slightly different spelling do not match.
	 * 
	 * @param _string
	 * @return The normalized String
	 */
	public static String normalize(String _string) {
		return _string.toLowerCase()
			.replace(" ", "")
			.replace("-", "")
			.replace("_", "");
	}
}
