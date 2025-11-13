package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.concurrent.TimedOperationVerifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;

@Ser
public final class ach extends acc implements IDecompiledMethod {
   private static final StructuredLogger UT = aco.pC(ach.class);
   @SerId(1)
   private long E;
   @SerId(2)
   private INativeMethodItem sY;
   @SerId(3)
   private IEConverter ys;
   @SerId(4)
   private IERoutineContext ld;
   @SerId(5)
   private ICMethod gp;
   @SerId(6)
   private int oT;
   @SerTransient
   private alm fI;
   @SerTransient
   private IEMasterOptimizer WR;
   @SerTransient
   private ICMasterOptimizer NS;
   @SerTransient
   private int vP;
   @SerTransient
   private int xC;
   @SerTransient
   private int ED;
   @SerTransient
   private TimedOperationVerifier Sc;
   @SerTransient
   private acy ah;

   public ach(INativeDecompilerContext var1, INativeMethodItem var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException();
      } else {
         this.sY = var2;
         this.E = this.sY.getMemoryAddress();
         this.ys = var1.getConverter();
      }
   }

   @Override
   public void pC() {
      IEGlobalContext var1 = this.ys.getGlobalContext();
      if (this.ld != null) {
         var1.removeRoutineContext(this.ld, true);
         this.ld = null;
      } else {
         var1.getObjectTracker().releaseAllFromUser(this.sY);
      }

      this.fI = null;
      this.WR = null;
      if (this.gp != null) {
         this.gp.reset();
      }

      this.NS = null;
      this.A = DecompilationStatus.IN_PROCESS;
      this.oT = 0;
   }

   public void kS() {
      IEGlobalContext var1 = this.ys.getGlobalContext();
      if (this.ld != null) {
         var1.removeRoutineContext(this.ld, false);
         this.ld = null;
      }

      this.fI = null;
      this.WR = null;
      this.NS = null;
      this.oT = 0;
   }

   public INativeMethodItem wS() {
      return this.sY;
   }

   @Override
   public INativeMethodItem getMethodItem() {
      return this.sY;
   }

   public String UT() {
      return this.sY.getName();
   }

   @Override
   public INativeDecompilerContext getDecompiler() {
      return this.pC;
   }

   @Override
   public IEConverter getConverter() {
      return this.ys;
   }

   @Override
   public IEMasterOptimizer getIROptimizer() {
      if (this.WR == null) {
         this.WR = this.pC.createIROptimizer(this.ld);
      }

      return this.WR;
   }

   @Override
   public ICMasterOptimizer getASTOptimizer() {
      if (this.NS == null) {
         this.NS = this.pC.createASTOptimizer(this.gp);
      }

      return this.NS;
   }

   @Override
   public IERoutineContext getIRContext() {
      return this.ld;
   }

   public void pC(IERoutineContext var1) {
      this.ld = var1;
   }

   public alm E() {
      if (this.fI == null) {
         this.fI = new alm(this.ld, this.pC);
      }

      return this.fI;
   }

   @Override
   public NativeDecompilationStage getCompletedStage() {
      try {
         atg var1 = atg.pC(this.oT);
         return var1.kS();
      } catch (JebRuntimeException var2) {
         return NativeDecompilationStage.UNKNOWN;
      }
   }

   public int sY() {
      return this.oT;
   }

   public void pC(int var1) {
      this.oT = var1;
   }

   public ICMethod ys() {
      return this.getMethodAST();
   }

   @Override
   public ICMethod getMethodAST() {
      return this.gp;
   }

   public void pC(ICMethod var1) {
      this.gp = var1;
   }

   public int ld() {
      return this.vP;
   }

   public void A(int var1) {
      this.vP = var1;
   }

   public int gp() {
      return this.xC;
   }

   public void kS(int var1) {
      this.xC = var1;
   }

   public void wS(int var1) {
      this.ED = var1;
   }

   public boolean oT() {
      return this.xC + 1 < this.ED;
   }

   public void pC(acn var1) {
      this.wS = var1;
   }

   public acn fI() {
      return this.wS;
   }

   public void pC(TimedOperationVerifier var1) {
      this.Sc = var1 == null ? TimedOperationVerifier.DEFAULT : var1;
   }

   public TimedOperationVerifier WR() {
      return this.Sc == null ? TimedOperationVerifier.DEFAULT : this.Sc;
   }

   public acy NS() {
      if (this.ah == null) {
         this.ah = new acy();
      }

      return this.ah;
   }

   @Override
   public String toString() {
      return Strings.ff("DECOMP:%s,stage=%d,%s", this.sY, this.oT, super.toString());
   }
}
