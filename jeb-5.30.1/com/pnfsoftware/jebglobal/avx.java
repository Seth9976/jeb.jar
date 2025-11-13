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
public abstract class avx implements awa {
   private static final StructuredLogger JY = aeg.q(avx.class);
   protected avy q;
   protected adz RF;
   protected INativeDecompilerContext xK;
   protected INativeDecompilerExtensionsManager Dw;
   protected IEConverter Uv;
   protected INativeContext oW;
   protected IEGlobalContext gO;
   protected IERoutineContext nf;
   protected INativeMethodItem gP;
   protected IEMasterOptimizer za;
   protected ICMethod lm;
   static boolean zz = false;

   public avx(avy var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
      }
   }

   @Override
   public int q() {
      return this.q.q();
   }

   @Override
   public String RF() {
      return this.q.toString();
   }

   @Override
   public final boolean q(adz var1, boolean var2) {
      var1.LK().verify();

      boolean var4;
      try {
         this.RF = var1;
         this.xK = var1.getDecompiler();
         this.Dw = this.xK.getExtensionsManager();
         this.Uv = var1.getConverter();
         this.oW = this.xK.getNativeContext();
         this.gO = this.xK.getIntermediateContext();
         this.nf = var1.getIRContext();
         this.gP = var1.getMethodItem();
         this.za = var1.getIROptimizer();
         this.za.setTarget(this.nf);
         this.lm = var1.getMethodAST();
         boolean var3 = this.Dw();
         var4 = var3;
      } finally {
         this.RF = null;
         this.xK = null;
         this.Dw = null;
         this.Uv = null;
         this.oW = null;
         this.gO = null;
         this.nf = null;
         this.gP = null;
         this.za = null;
         this.lm = null;
      }

      return var4;
   }

   protected void q(String var1, Object... var2) {
   }

   protected void xK() {
   }

   protected void q(String var1) {
   }

   protected abstract boolean Dw();

   protected int q(StructuredLogger var1, String var2) {
      return aeb.q(this.nf, this.za, null, null, var1, var2);
   }

   protected int q(OptimizerMode var1, StructuredLogger var2, String var3) {
      return aeb.q(this.nf, this.za, var1, null, var2, var3);
   }

   protected void Uv() {
   }

   protected int q(StructuredLogger var1) {
      return this.q(false, var1);
   }

   protected int q(boolean var1, StructuredLogger var2) {
      aoo var3 = new aoo(this.nf, this.xK.getOptions().memoryResolutionPolicy);
      var3.q(this.xK);
      int var4 = aop.q(this.nf, var3, this.za);
      Object[] var10000 = new Object[]{var4};
      this.nf.getCfg();
      var10000 = new Object[0];
      anp var5 = new anp(this.nf);
      int var6 = var5.xK();
      this.nf.getCfg();
      var10000 = new Object[]{var6};
      int var7 = new aps(this.nf).Uv();
      int var8 = new aps(this.nf).oW();
      int var9 = var4 + var6 + var7 + var8;
      if (var9 > 0 || var1) {
         new aps(this.nf).xK();
      }

      if (var9 > 0) {
         this.q(var2, "Running optimizer after typing simplifications");
      }

      return var9;
   }

   protected boolean oW() {
      if (zz) {
         JY.iHHHHH("BLOCKED RECONVERSION! Reconversions are not allowed.");
         return false;
      } else {
         JY.iHHHH("A reconversion is required.");
         if (this.RF.JY()) {
            this.RF.q(DecompilationStatus.NEED_RECONVERSION);
            return true;
         } else {
            JY.iHHHHH("BLOCKED RECONVERSION! Reconversion cannot be initiated, maxRound would not allow it.");
            return false;
         }
      }
   }
}
