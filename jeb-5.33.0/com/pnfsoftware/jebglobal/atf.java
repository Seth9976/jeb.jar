package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public abstract class atf implements ati {
   private static final StructuredLogger WR = aco.pC(atf.class);
   protected atg pC;
   protected ach A;
   protected INativeDecompilerContext kS;
   protected INativeDecompilerExtensionsManager wS;
   protected IEConverter UT;
   protected INativeContext E;
   protected IEGlobalContext sY;
   protected IERoutineContext ys;
   protected INativeMethodItem ld;
   protected IEMasterOptimizer gp;
   protected ICMethod oT;
   static boolean fI = false;

   public atf(atg var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
      }
   }

   @Override
   public int pC() {
      return this.pC.pC();
   }

   @Override
   public String A() {
      return this.pC.toString();
   }

   @Override
   public final boolean pC(ach var1, boolean var2) {
      var1.WR().verify();

      boolean var4;
      try {
         this.A = var1;
         this.kS = var1.getDecompiler();
         this.wS = this.kS.getExtensionsManager();
         this.UT = var1.getConverter();
         this.E = this.kS.getNativeContext();
         this.sY = this.kS.getIntermediateContext();
         this.ys = var1.getIRContext();
         this.ld = var1.getMethodItem();
         this.gp = var1.getIROptimizer();
         this.gp.setTarget(this.ys);
         this.oT = var1.getMethodAST();
         boolean var3 = this.kS();
         var4 = var3;
      } finally {
         this.A = null;
         this.kS = null;
         this.wS = null;
         this.UT = null;
         this.E = null;
         this.sY = null;
         this.ys = null;
         this.ld = null;
         this.gp = null;
         this.oT = null;
      }

      return var4;
   }

   protected abstract boolean kS();

   protected int pC(StructuredLogger var1, String var2) {
      return acj.pC(this.ys, this.gp, null, null, var1, var2);
   }

   protected int pC(OptimizerMode var1, StructuredLogger var2, String var3) {
      return acj.pC(this.ys, this.gp, var1, null, var2, var3);
   }

   protected int pC(StructuredLogger var1) {
      return this.pC(false, var1);
   }

   protected int pC(boolean var1, StructuredLogger var2) {
      amh var3 = new amh(this.ys, this.kS.getOptions().memoryResolutionPolicy);
      var3.pC(this.kS);
      int var4 = ami.pC(this.ys, var3, this.gp);
      Object[] var10000 = new Object[]{var4};
      this.ys.getCfg();
      var10000 = new Object[0];
      alk var5 = new alk(this.ys);
      int var6 = var5.pC();
      this.ys.getCfg();
      var10000 = new Object[]{var6};
      int var7 = new ani(this.ys).UT();
      int var8 = new ani(this.ys).E();
      int var9 = var4 + var6 + var7 + var8;
      if (var9 > 0 || var1) {
         new ani(this.ys).kS();
      }

      if (var9 > 0) {
         this.pC(var2, "Running optimizer after typing simplifications");
      }

      return var9;
   }

   protected boolean wS() {
      if (fI) {
         WR.iHHHHH("BLOCKED RECONVERSION! Reconversions are not allowed.");
         return false;
      } else {
         WR.iHHHH("A reconversion is required.");
         if (this.A.oT()) {
            this.A.pC(DecompilationStatus.NEED_RECONVERSION);
            return true;
         } else {
            WR.iHHHHH("BLOCKED RECONVERSION! Reconversion cannot be initiated, maxRound would not allow it.");
            return false;
         }
      }
   }
}
