package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

class Ia {
   private static final ILogger q = GlobalLog.getLogger(Ia.class);

   public static class CU {
      Ia.ej q = null;
      IEGeneric RF = null;
      Ia.oM xK = null;
      IEGeneric Dw = null;
      int Uv = -1;
      int oW = 0;
      boolean gO = false;
      boolean nf = false;
      boolean gP = false;
      boolean za = false;
      private boolean JY = false;
      public List lm = new ArrayList();
      public List zz = new ArrayList();

      public boolean q() {
         return this.q == null && !this.za;
      }

      public int RF() {
         return this.oW;
      }

      public int xK() {
         int var1 = this.q == null ? -1 : this.q.q();
         return var1 == -1 && this.Uv != -1 ? this.Uv : var1;
      }

      int q(int var1) {
         return this.nf ? var1 + 1 : var1;
      }

      public boolean Dw() {
         return this.q != null;
      }

      public boolean Uv() {
         return this.q instanceof Ia.oM;
      }

      public CW oW() {
         if (this.q instanceof Ia.oM) {
            return ((Ia.oM)this.q).RF();
         } else {
            return this.q instanceof Ia.Nt ? ((Ia.Nt)this.q).RF() : null;
         }
      }

      public Boolean q(fA var1, String var2, long var3, bR var5, Ia.eo var6, List var7, int var8) {
         if (this.Dw() || this.za) {
            return true;
         } else if (var2.equals("CMP")) {
            this.q(var1, var1.RF()[0], var1.RF()[1], var3, var5, !this.gO, var6, var7, var8);
            return true;
         } else if (var5.getStateProcessorMode(null) == 64 && var2.equals("CSET")) {
            if (this.xK != null && this.xK.RF == -1 && tB.q(this.xK.q, var1.RF()[0])) {
               var6.q(var1, this);
               this.gO = true;
               this.xK.RF = 2;
               this.Dw = var5.xK(var1, var1.RF()[0], var3);
               this.q = this.xK;
               this.RF = this.Dw;
            }

            return false;
         } else if (!var1.Dw().RF()) {
            return true;
         } else {
            int var9 = var1.getCountOfOperands() - 1;
            if (var2.equals("SUB") || var2.equals("RSB")) {
               this.q(var1, var1.RF()[var9 - 1], var1.RF()[var9], var3, var5, !this.gO, var6, var7, var8);
               this.JY = true;
               return true;
            } else if (!this.gO) {
               return true;
            } else {
               this.za = true;
               return false;
            }
         }
      }

      private void q(fA var1, CW var2, CW var3, long var4, bR var6, boolean var7, Ia.eo var8, List var9, int var10) {
         if (var8.RF() == null && this.q(var8, var9, var10)) {
            var7 = false;
         }

         if (var3.isImmediate()) {
            if (var7) {
               if (this.xK != null && this.xK.RF == -1 && tB.q(this.xK.q, var2)) {
                  this.xK.RF = this.q((int)var3.getOperandValue(var4));
                  this.Dw = var6.xK(var1, var2, var4);
                  this.q = this.xK;
                  this.RF = this.Dw;
               }
            } else {
               this.q = new Ia.oM(var2, this.q((int)var3.getOperandValue(var4)));
               this.RF = var6.xK(var1, var2, var4);
            }
         } else {
            IEGeneric var11 = var6.xK(var1, var2, var4);
            IEGeneric var12 = var6.xK(var1, var3, var4);
            if (!var7) {
               this.q = new Ia.iZ(var2, var3, var11, var12);
            }
         }
      }

      public boolean q(fA var1, long var2, Ia.nI var4, bR var5, IEGlobalContext var6, IVirtualMemory var7, Ia.eo var8, List var9, int var10) {
         if (this.JY) {
            this.JY = false;
            return true;
         } else {
            boolean var11 = false;
            if (this.q instanceof Ia.iZ var12) {
               var12.xK = var5.RF(var1, var2, var12.xK);
               var12.Dw = var5.RF(var1, var2, var12.Dw);
               List var13 = tB.q(var6, var12.xK);
               List var14 = tB.q(var6, var12.Dw);
               if (var13.size() == 0 && var14.size() == 0) {
                  return false;
               }

               if (var14.size() == 0) {
                  this.q(var1, var12.q, var12.Dw, var6, var7);
                  this.RF = var12.Dw;
                  var11 = true;
               } else if (var13.size() == 0) {
                  this.q(var1, var12.RF, var12.xK, var6, var7);
                  this.RF = var12.xK;
                  var11 = true;
               }
            }

            if (this.q instanceof Ia.Nt var16) {
               var16.RF = var5.RF(var1, var2, var16.RF);
               if (var16.RF == null) {
                  return false;
               }

               this.q(var1, var16.q, var16.RF, var6, var7);
            }

            if (this.q instanceof Ia.iZ var17) {
               List var18 = tB.q(var6, var4);
               boolean var19 = var18.contains(var17.xK);
               boolean var15 = var18.contains(var17.Dw);
               if (var19 && var15) {
                  return false;
               }

               if (var19) {
                  this.q = new Ia.Nt(var17.q, var17.Dw);
                  this.RF = var17.xK;
               } else if (var15) {
                  this.q = new Ia.Nt(var17.RF, var17.xK);
                  this.RF = var17.Dw;
               }
            }

            if (this.RF != null && (this.q instanceof Ia.Nt || this.q instanceof Ia.oM)) {
               if (!var11) {
                  this.RF = var5.RF(var1, var2, this.RF);
               }

               this.q(var1, var4, var5, var6);
            }

            return true;
         }
      }

      private void q(fA var1, Ia.nI var2, bR var3, IEGlobalContext var4) {
         IEGeneric var5 = this.RF(this.RF);
         if (var5 != this.RF && var5.getBitsize() <= this.RF.getBitsize()) {
            this.RF = var5.zeroExtend(this.RF.getBitsize());
         }

         List var6 = tB.q(var4, var2);
         List var7 = tB.q(var4, this.RF);
         if (this.RF.isVar() && var7.size() == 1 && var6.contains(var7.get(0))) {
            long var15 = var3.getNativeRegisterIdFromRegisterVariable((IEVar)var7.get(0), true);
            CW var18 = GC.RF(var15, var3.getStateProcessorMode(null));
            if (this.q instanceof Ia.Nt) {
               ((Ia.Nt)this.q).q = var18;
            } else {
               ((Ia.oM)this.q).q = var18;
            }
         } else {
            IEVar var8 = var3.q();
            EVisitResults var9 = new EVisitResults();
            IEGeneric var10 = EUtil.replaceSubExpressionRecursive(var2.q(), this.RF, var8, var9);
            var2.q(var10, var1);
            var2.q = EUtil.replaceSubExpressionRecursive(var2.q, this.RF, var8, null);
            if (!var9.isVisitedSuccessfully()) {
               if (this.q instanceof Ia.Nt) {
                  ((Ia.Nt)this.q).q = GC.gO(var1.getProcessorMode());
               } else {
                  ((Ia.oM)this.q).q = GC.gO(var1.getProcessorMode());
               }
            } else if (this.q(this.RF).isVar()) {
               var6 = tB.q(var4, var2);
               var7 = tB.q(var4, this.RF);
               if (var7.size() == 1 && var6.contains(var7.get(0))) {
                  IEImm var11 = null;
                  OperationType var12 = null;
                  if (EUtil.isOperation(this.RF, OperationType.ADD)) {
                     if (this.RF.asOperation().getOperand2().isImm()) {
                        var11 = this.RF.asOperation().getOperand2().asImm();
                        this.RF = this.RF.asOperation().getOperand1();
                        var12 = OperationType.SUB;
                     }
                  } else if (EUtil.isOperation(this.RF, OperationType.SUB) && this.RF.asOperation().getOperand2().isImm()) {
                     var11 = this.RF.asOperation().getOperand2().asImm();
                     this.RF = this.RF.asOperation().getOperand1();
                     var12 = OperationType.ADD;
                  }

                  if (var11 != null) {
                     var9 = new EVisitResults();
                     var10 = EUtil.replaceSubExpressionRecursive(var2.q(), (IEGeneric)var7.get(0), EUtil.op(var12, (IEGeneric)var7.get(0), var11), var9);
                     var2.q(var10, var1);
                     var2.q = EUtil.replaceSubExpressionRecursive(var2.q, (IEGeneric)var7.get(0), EUtil.op(var12, (IEGeneric)var7.get(0), var11), null);
                     this.q(var1, var2, var3, var4);
                  }
               }
            }
         }
      }

      private void q(fA var1, CW var2, IEGeneric var3, IEGlobalContext var4, IVirtualMemory var5) {
         List var6 = tB.q(var4, var3);
         if (var6.isEmpty()) {
            try {
               EState var7 = new EState(var5.getStandardEndianess());
               var7.setMemory(var5);
               int var8 = (int)var3.evaluateUnsignedLong(var7);
               this.q = new Ia.oM(var2, this.q(var8));
            } catch (Exception var9) {
               Ia.q.catchingSilent(var9);
            }
         }
      }

      public IEGeneric q(IEGeneric var1) {
         if (EUtil.isZeroExtend(var1)) {
            return this.q(var1.asCompose().getLowPart());
         } else if (var1.isOperation(OperationType.AND, OperationType.ADD, OperationType.SUB) && var1.asOperation().getOperand2().isImm()) {
            return this.q(var1.asOperation().getOperand1());
         } else {
            return var1.isSlice() ? var1.asSlice().getWholeExpression() : var1;
         }
      }

      private IEGeneric RF(IEGeneric var1) {
         IEGeneric var2 = var1;
         if (EUtil.isZeroExtend(var1)) {
            return this.RF(var1.asCompose().getLowPart());
         } else {
            long var3 = -1L;
            if (var1.isOperation(OperationType.AND) && var1.asOperation().getOperand2().isImm()) {
               var3 = var1.asOperation().getOperand2().asImm().getValueAsLong();
               var2 = var1.asOperation().getOperand1();
               if (var3 <= 0L) {
                  return var1;
               }

               for (long var9 = var3; var9 != 0L; var9 >>>= 1) {
                  long var7 = var9 >>> 1 << 1;
                  if (var7 != 0L && var9 == var7) {
                     return var1;
                  }
               }
            } else if (var1.isSlice()) {
               int var5 = var1.asSlice().getBitsize();
               if (var5 <= 16) {
                  var3 = 1 << var5;
               }

               var2 = var1.asSlice().getWholeExpression();
            }

            if (var3 >= 0L && var3 < 256L) {
               if (this.Uv == -1) {
                  this.Uv = (int)var3 + 1;
               } else {
                  this.Uv = Math.min((int)var3 + 1, this.Uv);
               }
            }

            return var1 != var2 ? this.RF(var2) : var2;
         }
      }

      public void q(Ia.nI var1, IEGlobalContext var2, bR var3) {
         if (this.xK == null) {
            List var4 = tB.q(var2, var1);
            if (var4.size() == 1) {
               long var5 = var3.getNativeRegisterIdFromRegisterVariable((IEVar)var4.get(0), true);
               this.xK = new Ia.oM(GC.RF(var5, var3.getStateProcessorMode(null)), -1);
               this.Dw = (IEGeneric)var4.get(0);
            }
         }
      }

      private boolean q(Ia.eo var1, List var2, int var3) {
         Integer var4 = null;
         int var5 = var3 + 1;

         while (true) {
            label40: {
               if (var5 < var2.size()) {
                  fA var6 = (fA)var2.get(var5);
                  if (var6.zz()) {
                     var4 = (int)var6.RF()[0].getOperandValue();
                  }

                  if (var6.Uv().gO()) {
                     var4 = var6.Uv().RF();
                  }

                  if (var4 != null) {
                     if (!OC.q(var4) && !OC.RF(var4)) {
                        break label40;
                     }

                     this.nf = OC.xK(var4) || OC.Dw(var4);
                     var1.q(var6, this);
                     this.gO = true;
                     return true;
                  }

                  if (!var6.Dw().xK()) {
                     break label40;
                  }
               }

               return false;
            }

            var5++;
         }
      }
   }

   public static class Nt implements Ia.ej {
      CW q;
      IEGeneric RF = null;

      public Nt(CW var1, IEGeneric var2) {
         this.q = var1;
         this.RF = var2;
      }

      public CW RF() {
         return this.q;
      }

      @Override
      public int q() {
         return -1;
      }

      @Override
      public String toString() {
         return "IntermediateResolvedState [cmpReg=" + this.q + ", cmpHighRangeIre=" + this.RF + "]";
      }
   }

   public interface ej {
      int q();
   }

   public static class eo {
      CW q;
      private fA RF;

      public void q() {
         this.RF = null;
      }

      public void q(fA var1, Ia.CU var2) {
         if (var2.Uv() && !var1.Dw().q().startsWith("IT")) {
            Object[] var10000 = new Object[]{var1};
         } else {
            this.RF = var1;
         }
      }

      public fA RF() {
         return this.RF;
      }
   }

   public static class iZ implements Ia.ej {
      CW q;
      CW RF;
      IEGeneric xK = null;
      IEGeneric Dw = null;

      public iZ(CW var1, CW var2, IEGeneric var3, IEGeneric var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      }

      @Override
      public int q() {
         return -1;
      }

      @Override
      public String toString() {
         return "NonResolvedState [cmpIre1=" + this.xK + ", cmpIre2=" + this.Dw + "]";
      }
   }

   public static class nI {
      private IEGeneric RF;
      public IEGeneric q;

      public void q(IEGeneric var1, fA var2) {
         this.RF = var1;
      }

      public IEGeneric q() {
         return this.RF;
      }

      public IEGeneric RF() {
         return this.RF;
      }

      public boolean xK() {
         return this.RF != null;
      }

      @Override
      public String toString() {
         return this.RF.toString() + "\t[[CPSRT: " + this.q + "]]";
      }
   }

   public static class oM implements Ia.ej {
      CW q;
      int RF = -1;

      public oM(CW var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }

      public CW RF() {
         return this.q;
      }

      @Override
      public int q() {
         return this.RF;
      }

      @Override
      public String toString() {
         return "FullResolvedState [cmpReg=" + this.q + ", highRange=" + this.RF + "]";
      }
   }
}
