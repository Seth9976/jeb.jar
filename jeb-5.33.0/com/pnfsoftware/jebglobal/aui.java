package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aui extends atf {
   private static final StructuredLogger WR = aco.pC(aui.class);

   public aui() {
      super(atg.UO);
   }

   @Override
   protected boolean kS() {
      try {
         WR.beginSection("Structuring...");
         ada var1 = new ada(this.kS.getHighLevelContext(), this.ys);
         var1.pC(this.kS.getOptions().structurerUseVersion);
         this.oT = var1.A();
         this.A.pC(this.oT);
      } finally {
         WR.closeSection();
      }

      return true;
   }
}
