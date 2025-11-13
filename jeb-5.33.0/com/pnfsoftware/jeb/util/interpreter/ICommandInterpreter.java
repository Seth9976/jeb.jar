package com.pnfsoftware.jeb.util.interpreter;

public interface ICommandInterpreter {
   String getName();

   String getDescription();

   String getBanner();

   void prepare();

   ExecutionResult executeCommand(String var1);

   boolean shouldDisplayRawResults();

   AutocompletionResult autoComplete(String var1);
}
