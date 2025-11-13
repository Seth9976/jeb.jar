package com.pnfsoftware.jeb.util.interpreter;

public abstract class AbstractCommandInterpreter implements ICommandInterpreter {
   @Override
   public String getDescription() {
      return null;
   }

   @Override
   public String getBanner() {
      return null;
   }

   @Override
   public void prepare() {
   }

   @Override
   public boolean shouldDisplayRawResults() {
      return false;
   }

   @Override
   public AutocompletionResult autoComplete(String var1) {
      return null;
   }
}
