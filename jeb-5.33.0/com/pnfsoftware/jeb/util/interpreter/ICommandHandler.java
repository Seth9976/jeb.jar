package com.pnfsoftware.jeb.util.interpreter;

import java.util.List;

public interface ICommandHandler extends ICommandNode {
   List getParameters();

   String getHelpDetails();
}
