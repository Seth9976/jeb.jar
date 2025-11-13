package com.pnfsoftware.jeb.util.interpreter;

import java.util.List;

public interface ICommandNode {
   int QUOTES_AS_NORMAL_CHAR = 1;

   String getName();

   String getHelp();

   int getOptions();

   ICommandManager getParent();

   List getChildren();

   ExecutionResult execute(List var1);
}
