package com.pnfsoftware.jeb.core.exceptions;

import com.pnfsoftware.jeb.util.format.Strings;

public class ToDoException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public ToDoException() {
   }

   public ToDoException(String var1) {
      super(var1);
   }

   public ToDoException(String var1, Object... var2) {
      super(Strings.ff(var1, var2));
   }
}
