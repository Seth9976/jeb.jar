package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICNamingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICMasterOptimizer;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class axd extends avx {
   private static final StructuredLogger JY = aeg.q(axd.class);

   public axd() {
      super(avy.xW);
   }

   @Override
   protected boolean Dw() {
      Assert.a(this.lm != null);

      try {
         JY.beginSection("Optimizing the AST");
         ICMasterOptimizer var1 = this.xK.createASTOptimizer(this.lm);
         int var2 = var1.perform();
         Object[] var10000 = new Object[]{var2};
      } finally {
         JY.closeSection();
      }

      aec var6 = null;
      if (this.xK instanceof adw var7) {
         var6 = new aec(var7.oW(), var7);
      }

      ICNamingEngine var8 = this.lm.getIdentifierManager().getNamingEngine();
      if (var8 != null) {
         var8.beautifyIdentifierNames(this.lm, var6);
      }

      if (this.xK.getOptions().astReplaceConstantsByWellKnownLiterals) {
         afd var3 = new afd(this.xK.getNativeContext().getTypeManager(), var6);
         var3.q(this.lm);
      }

      return true;
   }
}
