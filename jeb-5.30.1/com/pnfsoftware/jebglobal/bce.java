package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import org.antlr.v4.runtime.tree.ParseTree;

public class bce extends JebRuntimeException {
   private static final long serialVersionUID = 1L;
   ParseTree q;

   public bce(ParseTree var1) {
      this.q = var1;
   }

   @Override
   public String getMessage() {
      return this.q.getClass().getSimpleName();
   }
}
