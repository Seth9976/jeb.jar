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
public final class adz extends adu implements IDecompiledMethod {
   private static final StructuredLogger Uv = aeg.q(adz.class);
   @SerId(1)
   private long oW;
   @SerId(2)
   private INativeMethodItem gO;
   @SerId(3)
   private IEConverter nf;
   @SerId(4)
   private IERoutineContext gP;
   @SerId(5)
   private ICMethod za;
   @SerId(6)
   private int lm;
   @SerTransient
   private anr zz;
   @SerTransient
   private IEMasterOptimizer JY;
   @SerTransient
   private ICMasterOptimizer HF;
   @SerTransient
   private int LK;
   @SerTransient
   private int io;
   @SerTransient
   private int qa;
   @SerTransient
   private TimedOperationVerifier Hk;
   @SerTransient
   private aeq Me;

   public adz(INativeDecompilerContext var1, INativeMethodItem var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException();
      } else {
         this.gO = var2;
         this.oW = this.gO.getMemoryAddress();
         this.nf = var1.getConverter();
      }
   }

   @Override
   public void q() {
      IEGlobalContext var1 = this.nf.getGlobalContext();
      if (this.gP != null) {
         var1.removeRoutineContext(this.gP, true);
         this.gP = null;
      } else {
         var1.getObjectTracker().releaseAllFromUser(this.gO);
      }

      this.zz = null;
      this.JY = null;
      if (this.za != null) {
         this.za.reset();
      }

      this.HF = null;
      this.RF = DecompilationStatus.IN_PROCESS;
      this.lm = 0;
   }

   public void xK() {
      IEGlobalContext var1 = this.nf.getGlobalContext();
      if (this.gP != null) {
         var1.removeRoutineContext(this.gP, false);
         this.gP = null;
      }

      this.zz = null;
      this.JY = null;
      this.HF = null;
      this.lm = 0;
   }

   public INativeMethodItem Dw() {
      return this.gO;
   }

   public long Uv() {
      return this.oW;
   }

   @Override
   public INativeMethodItem getMethodItem() {
      return this.gO;
   }

   public String oW() {
      return this.gO.getName();
   }

   @Override
   public INativeDecompilerContext getDecompiler() {
      return this.q;
   }

   @Override
   public IEConverter getConverter() {
      return this.nf;
   }

   @Override
   public IEMasterOptimizer getIROptimizer() {
      if (this.JY == null) {
         this.JY = this.q.createIROptimizer(this.gP);
      }

      return this.JY;
   }

   @Override
   public ICMasterOptimizer getASTOptimizer() {
      if (this.HF == null) {
         this.HF = this.q.createASTOptimizer(this.za);
      }

      return this.HF;
   }

   @Override
   public IERoutineContext getIRContext() {
      return this.gP;
   }

   public void q(IERoutineContext var1) {
      this.gP = var1;
   }

   public anr gO() {
      if (this.zz == null) {
         this.zz = new anr(this.gP, this.q);
      }

      return this.zz;
   }

   public void q(anr var1) {
      this.zz = var1;
   }

   @Override
   public NativeDecompilationStage getCompletedStage() {
      try {
         avy var1 = avy.q(this.lm);
         return var1.Dw();
      } catch (JebRuntimeException var2) {
         return NativeDecompilationStage.UNKNOWN;
      }
   }

   public int nf() {
      return this.lm;
   }

   public void q(int var1) {
      this.lm = var1;
   }

   public ICMethod gP() {
      return this.getMethodAST();
   }

   @Override
   public ICMethod getMethodAST() {
      return this.za;
   }

   public void q(ICMethod var1) {
      this.za = var1;
   }

   public int za() {
      return this.LK;
   }

   public void RF(int var1) {
      this.LK = var1;
   }

   public int lm() {
      return this.io;
   }

   public void xK(int var1) {
      this.io = var1;
   }

   public int zz() {
      return this.qa;
   }

   public void Dw(int var1) {
      this.qa = var1;
   }

   public boolean JY() {
      return this.io + 1 < this.qa;
   }

   public void q(aef var1) {
      this.Dw = var1;
   }

   public aef HF() {
      return this.Dw;
   }

   public void q(TimedOperationVerifier var1) {
      this.Hk = var1 == null ? TimedOperationVerifier.DEFAULT : var1;
   }

   public TimedOperationVerifier LK() {
      return this.Hk == null ? TimedOperationVerifier.DEFAULT : this.Hk;
   }

   public aeq io() {
      if (this.Me == null) {
         this.Me = new aeq();
      }

      return this.Me;
   }

   @Override
   public String toString() {
      return Strings.ff("DECOMP:%s,stage=%d,%s", this.gO, this.lm, super.toString());
   }
}
