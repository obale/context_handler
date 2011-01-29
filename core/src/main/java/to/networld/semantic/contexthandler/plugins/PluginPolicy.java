/**
 * context-handler - to.networld.semantic.contexthandler.plugins
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

package to.networld.semantic.contexthandler.plugins;

import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;

/**
 * @author Alex Oberhauser
 */
public class PluginPolicy extends Policy {
	
	/**
	 * XXX: Permit all permssion to all plugins.
	 * TODO: Implement here a satisfactorily check if the plugins is not evil. 
	 * 
	 * @see java.security.Policy#getPermissions(java.security.CodeSource)
	 */
	@Override
	public PermissionCollection getPermissions(CodeSource codeSource) {
		Permissions permission = new Permissions();
		permission.add(new AllPermission());
		return permission;
	}
	
	public void refresh() {}
}
