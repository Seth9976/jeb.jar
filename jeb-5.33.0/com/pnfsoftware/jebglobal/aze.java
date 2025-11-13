package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import org.antlr.v4.runtime.tree.ParseTree;

public class aze extends JebRuntimeException {
   private static final long serialVersionUID = 1L;
   ParseTree pC;

   public aze(ParseTree var1) {
      this.pC = var1;
   }

   @Override
   public String getMessage() {
      return this.pC.getClass().getSimpleName();
   }
}
