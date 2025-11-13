package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICNamingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICMasterOptimizer;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class auj extends atf {
   private static final StructuredLogger WR = aco.pC(auj.class);

   public auj() {
      super(atg.Ab);
   }

   @Override
   protected boolean kS() {
      Assert.a(this.oT != null);

      try {
         WR.beginSection("Optimizing the AST");
         ICMasterOptimizer var1 = this.kS.createASTOptimizer(this.oT);
         int var2 = var1.perform();
         Object[] var10000 = new Object[]{var2};
      } finally {
         WR.closeSection();
      }

      ack var6 = null;
      if (this.kS instanceof ace var7) {
         var6 = new ack(var7.UT(), var7);
      }

      ICNamingEngine var8 = this.oT.getIdentifierManager().getNamingEngine();
      if (var8 != null) {
         var8.beautifyIdentifierNames(this.oT, var6);
      }

      if (this.kS.getOptions().astReplaceConstantsByWellKnownLiterals) {
         adk var9 = new adk(this.kS.getNativeContext().getTypeManager(), var6);
         var9.pC(this.oT);
      }

      return true;
   }
}
