package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.core.exceptions.JebException;

public class ScriptException extends JebException {
   private static final long serialVersionUID = 1L;

   public ScriptException(String var1) {
      super(var1);
   }

   public ScriptException(Throwable var1) {
      super(var1);
   }

   public ScriptException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
