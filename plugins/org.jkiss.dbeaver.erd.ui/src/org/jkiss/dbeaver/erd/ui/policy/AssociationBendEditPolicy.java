/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.erd.ui.policy;

import org.eclipse.draw2dl.geometry.Point;
import org.eclipse.gef3.commands.Command;
import org.eclipse.gef3.editpolicies.BendpointEditPolicy;
import org.eclipse.gef3.requests.BendpointRequest;
import org.jkiss.dbeaver.erd.ui.command.BendpointCreateCommand;
import org.jkiss.dbeaver.erd.ui.command.BendpointDeleteCommand;
import org.jkiss.dbeaver.erd.ui.command.BendpointMoveCommand;
import org.jkiss.dbeaver.erd.ui.part.AssociationPart;

/**
 * EditPolicy to handle deletion of relationships
 * @author Serge Rider
 */
public class AssociationBendEditPolicy extends BendpointEditPolicy
{


    @Override
    protected Command getCreateBendpointCommand(BendpointRequest request) {
        return new BendpointCreateCommand(
            (AssociationPart) getHost(),
            getRelativeLocation(request),
            request.getIndex());
    }

    @Override
    protected Command getDeleteBendpointCommand(BendpointRequest request) {
        return new BendpointDeleteCommand((AssociationPart) getHost(), request.getIndex());
    }

    @Override
    protected Command getMoveBendpointCommand(BendpointRequest request) {
        return new BendpointMoveCommand(
            (AssociationPart) getHost(),
            getRelativeLocation(request),
            request.getIndex());
    }

    private Point getRelativeLocation(BendpointRequest request)
    {
        Point p = request.getLocation();
        getConnection().translateToRelative(p);
        return p;
    }

}