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

class Ro {
   private static final ILogger pC = GlobalLog.getLogger(Ro.class);

   public static class Av {
      Yg pC;
      private com.pnfsoftware.jeb.corei.parsers.arm.rQ A;

      public void pC() {
         this.A = null;
      }

      public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Ro.Sv var2) {
         if (var2.wS() && !var1.wS().pC().startsWith("IT")) {
            Object[] var10000 = new Object[]{var1};
         } else {
            this.A = var1;
         }
      }

      public com.pnfsoftware.jeb.corei.parsers.arm.rQ A() {
         return this.A;
      }
   }

   public static class DH implements Ro.Ws {
      Yg pC;
      Yg A;
      IEGeneric kS = null;
      IEGeneric wS = null;

      public DH(Yg var1, Yg var2, IEGeneric var3, IEGeneric var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      }

      @Override
      public int pC() {
         return -1;
      }

      @Override
      public String toString() {
         return "NonResolvedState [cmpIre1=" + this.kS + ", cmpIre2=" + this.wS + "]";
      }
   }

   public static class K {
      private IEGeneric A;
      public IEGeneric pC;

      public void pC(IEGeneric var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2) {
         this.A = var1;
      }

      public IEGeneric pC() {
         return this.A;
      }

      public IEGeneric A() {
         return this.A;
      }

      public boolean kS() {
         return this.A != null;
      }

      @Override
      public String toString() {
         return this.A.toString() + "\t[[CPSRT: " + this.pC + "]]";
      }
   }

   public static class Sv {
      Ro.Ws pC = null;
      IEGeneric A = null;
      Ro.bO kS = null;
      IEGeneric wS = null;
      int UT = -1;
      int E = 0;
      boolean sY = false;
      boolean ys = false;
      boolean ld = false;
      boolean gp = false;
      private boolean WR = false;
      public List oT = new ArrayList();
      public List fI = new ArrayList();

      public boolean pC() {
         return this.pC == null && !this.gp;
      }

      public int A() {
         int var1 = this.pC == null ? -1 : this.pC.pC();
         return var1 == -1 && this.UT != -1 ? this.UT : var1;
      }

      int pC(int var1) {
         return this.ys ? var1 + 1 : var1;
      }

      public boolean kS() {
         return this.pC != null;
      }

      public boolean wS() {
         return this.pC instanceof Ro.bO;
      }

      public Yg UT() {
         if (this.pC instanceof Ro.bO) {
            return ((Ro.bO)this.pC).A();
         } else {
            return this.pC instanceof Ro.cq ? ((Ro.cq)this.pC).A() : null;
         }
      }

      public Boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, String var2, long var3, Sp var5, Ro.Av var6, List var7, int var8) {
         if (this.kS() || this.gp) {
            return true;
         } else if (var2.equals("CMP")) {
            this.pC(var1, var1.A()[0], var1.A()[1], var3, var5, !this.sY, var6, var7, var8);
            return true;
         } else if (var5.getStateProcessorMode(null) == 64 && var2.equals("CSET")) {
            if (this.kS != null && this.kS.A == -1 && Td.pC(this.kS.pC, var1.A()[0])) {
               var6.pC(var1, this);
               this.sY = true;
               this.kS.A = 2;
               this.wS = var5.kS(var1, var1.A()[0], var3);
               this.pC = this.kS;
               this.A = this.wS;
            }

            return false;
         } else if (!var1.wS().A()) {
            return true;
         } else {
            int var9 = var1.getCountOfOperands() - 1;
            if (var2.equals("SUB") || var2.equals("RSB")) {
               this.pC(var1, var1.A()[var9 - 1], var1.A()[var9], var3, var5, !this.sY, var6, var7, var8);
               this.WR = true;
               return true;
            } else if (!this.sY) {
               return true;
            } else {
               this.gp = true;
               return false;
            }
         }
      }

      private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, Yg var3, long var4, Sp var6, boolean var7, Ro.Av var8, List var9, int var10) {
         if (var8.A() == null && this.pC(var8, var9, var10)) {
            var7 = false;
         }

         if (var3.isImmediate()) {
            if (var7) {
               if (this.kS != null && this.kS.A == -1 && Td.pC(this.kS.pC, var2)) {
                  this.kS.A = this.pC((int)var3.getOperandValue(var4));
                  this.wS = var6.kS(var1, var2, var4);
                  this.pC = this.kS;
                  this.A = this.wS;
               }
            } else {
               this.pC = new Ro.bO(var2, this.pC((int)var3.getOperandValue(var4)));
               this.A = var6.kS(var1, var2, var4);
            }
         } else {
            IEGeneric var11 = var6.kS(var1, var2, var4);
            IEGeneric var12 = var6.kS(var1, var3, var4);
            if (!var7) {
               this.pC = new Ro.DH(var2, var3, var11, var12);
            }
         }
      }

      public boolean pC(
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var1,
         long var2,
         Ro.K var4,
         Sp var5,
         IEGlobalContext var6,
         IVirtualMemory var7,
         Ro.Av var8,
         List var9,
         int var10
      ) {
         if (this.WR) {
            this.WR = false;
            return true;
         } else {
            boolean var11 = false;
            if (this.pC instanceof Ro.DH var12) {
               var12.kS = var5.A(var1, var2, var12.kS);
               var12.wS = var5.A(var1, var2, var12.wS);
               List var13 = Td.pC(var6, var12.kS);
               List var14 = Td.pC(var6, var12.wS);
               if (var13.size() == 0 && var14.size() == 0) {
                  return false;
               }

               if (var14.size() == 0) {
                  this.pC(var1, var12.pC, var12.wS, var6, var7);
                  this.A = var12.wS;
                  var11 = true;
               } else if (var13.size() == 0) {
                  this.pC(var1, var12.A, var12.kS, var6, var7);
                  this.A = var12.kS;
                  var11 = true;
               }
            }

            if (this.pC instanceof Ro.cq var16) {
               var16.A = var5.A(var1, var2, var16.A);
               if (var16.A == null) {
                  return false;
               }

               this.pC(var1, var16.pC, var16.A, var6, var7);
            }

            if (this.pC instanceof Ro.DH var17) {
               List var18 = Td.pC(var6, var4);
               boolean var19 = var18.contains(var17.kS);
               boolean var15 = var18.contains(var17.wS);
               if (var19 && var15) {
                  return false;
               }

               if (var19) {
                  this.pC = new Ro.cq(var17.pC, var17.wS);
                  this.A = var17.kS;
               } else if (var15) {
                  this.pC = new Ro.cq(var17.A, var17.kS);
                  this.A = var17.wS;
               }
            }

            if (this.A != null && (this.pC instanceof Ro.cq || this.pC instanceof Ro.bO)) {
               if (!var11) {
                  this.A = var5.A(var1, var2, this.A);
               }

               this.pC(var1, var4, var5, var6);
            }

            return true;
         }
      }

      private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Ro.K var2, Sp var3, IEGlobalContext var4) {
         IEGeneric var5 = this.A(this.A);
         if (var5 != this.A && var5.getBitsize() <= this.A.getBitsize()) {
            this.A = var5.zeroExtend(this.A.getBitsize());
         }

         List var6 = Td.pC(var4, var2);
         List var7 = Td.pC(var4, this.A);
         if (this.A.isVar() && var7.size() == 1 && var6.contains(var7.get(0))) {
            long var15 = var3.getNativeRegisterIdFromRegisterVariable((IEVar)var7.get(0), true);
            Yg var18 = LC.A(var15, var3.getStateProcessorMode(null));
            if (this.pC instanceof Ro.cq) {
               ((Ro.cq)this.pC).pC = var18;
            } else {
               ((Ro.bO)this.pC).pC = var18;
            }
         } else {
            IEVar var8 = var3.pC();
            EVisitResults var9 = new EVisitResults();
            IEGeneric var10 = EUtil.replaceSubExpressionRecursive(var2.pC(), this.A, var8, var9);
            var2.pC(var10, var1);
            var2.pC = EUtil.replaceSubExpressionRecursive(var2.pC, this.A, var8, null);
            if (!var9.isVisitedSuccessfully()) {
               if (this.pC instanceof Ro.cq) {
                  ((Ro.cq)this.pC).pC = LC.ys(var1.getProcessorMode());
               } else {
                  ((Ro.bO)this.pC).pC = LC.ys(var1.getProcessorMode());
               }
            } else if (this.pC(this.A).isVar()) {
               var6 = Td.pC(var4, var2);
               var7 = Td.pC(var4, this.A);
               if (var7.size() == 1 && var6.contains(var7.get(0))) {
                  IEImm var11 = null;
                  OperationType var12 = null;
                  if (EUtil.isOperation(this.A, OperationType.ADD)) {
                     if (this.A.asOperation().getOperand2().isImm()) {
                        var11 = this.A.asOperation().getOperand2().asImm();
                        this.A = this.A.asOperation().getOperand1();
                        var12 = OperationType.SUB;
                     }
                  } else if (EUtil.isOperation(this.A, OperationType.SUB) && this.A.asOperation().getOperand2().isImm()) {
                     var11 = this.A.asOperation().getOperand2().asImm();
                     this.A = this.A.asOperation().getOperand1();
                     var12 = OperationType.ADD;
                  }

                  if (var11 != null) {
                     var9 = new EVisitResults();
                     var10 = EUtil.replaceSubExpressionRecursive(var2.pC(), (IEGeneric)var7.get(0), EUtil.op(var12, (IEGeneric)var7.get(0), var11), var9);
                     var2.pC(var10, var1);
                     var2.pC = EUtil.replaceSubExpressionRecursive(var2.pC, (IEGeneric)var7.get(0), EUtil.op(var12, (IEGeneric)var7.get(0), var11), null);
                     this.pC(var1, var2, var3, var4);
                  }
               }
            }
         }
      }

      private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, IEGeneric var3, IEGlobalContext var4, IVirtualMemory var5) {
         List var6 = Td.pC(var4, var3);
         if (var6.isEmpty()) {
            try {
               EState var7 = new EState(var5.getStandardEndianess());
               var7.setMemory(var5);
               int var8 = (int)var3.evaluateUnsignedLong(var7);
               this.pC = new Ro.bO(var2, this.pC(var8));
            } catch (Exception var9) {
               Ro.pC.catchingSilent(var9);
            }
         }
      }

      public IEGeneric pC(IEGeneric var1) {
         if (EUtil.isZeroExtend(var1)) {
            return this.pC(var1.asCompose().getLowPart());
         } else if (var1.isOperation(OperationType.AND, OperationType.ADD, OperationType.SUB) && var1.asOperation().getOperand2().isImm()) {
            return this.pC(var1.asOperation().getOperand1());
         } else {
            return var1.isSlice() ? var1.asSlice().getWholeExpression() : var1;
         }
      }

      private IEGeneric A(IEGeneric var1) {
         IEGeneric var2 = var1;
         if (EUtil.isZeroExtend(var1)) {
            return this.A(var1.asCompose().getLowPart());
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
               if (this.UT == -1) {
                  this.UT = (int)var3 + 1;
               } else {
                  this.UT = Math.min((int)var3 + 1, this.UT);
               }
            }

            return var1 != var2 ? this.A(var2) : var2;
         }
      }

      public void pC(Ro.K var1, IEGlobalContext var2, Sp var3) {
         if (this.kS == null) {
            List var4 = Td.pC(var2, var1);
            if (var4.size() == 1) {
               long var5 = var3.getNativeRegisterIdFromRegisterVariable((IEVar)var4.get(0), true);
               this.kS = new Ro.bO(LC.A(var5, var3.getStateProcessorMode(null)), -1);
               this.wS = (IEGeneric)var4.get(0);
            }
         }
      }

      private boolean pC(Ro.Av var1, List var2, int var3) {
         Integer var4 = null;
         int var5 = var3 + 1;

         while (true) {
            label40: {
               if (var5 < var2.size()) {
                  com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var2.get(var5);
                  if (var6.fI()) {
                     var4 = (int)var6.A()[0].getOperandValue();
                  }

                  if (var6.UT().sY()) {
                     var4 = var6.UT().A();
                  }

                  if (var4 != null) {
                     if (!PU.pC(var4) && !PU.A(var4)) {
                        break label40;
                     }

                     this.ys = PU.kS(var4) || PU.wS(var4);
                     var1.pC(var6, this);
                     this.sY = true;
                     return true;
                  }

                  if (!var6.wS().kS()) {
                     break label40;
                  }
               }

               return false;
            }

            var5++;
         }
      }
   }

   public interface Ws {
      int pC();
   }

   public static class bO implements Ro.Ws {
      Yg pC;
      int A = -1;

      public bO(Yg var1, int var2) {
         this.pC = var1;
         this.A = var2;
      }

      public Yg A() {
         return this.pC;
      }

      @Override
      public int pC() {
         return this.A;
      }

      @Override
      public String toString() {
         return "FullResolvedState [cmpReg=" + this.pC + ", highRange=" + this.A + "]";
      }
   }

   public static class cq implements Ro.Ws {
      Yg pC;
      IEGeneric A = null;

      public cq(Yg var1, IEGeneric var2) {
         this.pC = var1;
         this.A = var2;
      }

      public Yg A() {
         return this.pC;
      }

      @Override
      public int pC() {
         return -1;
      }

      @Override
      public String toString() {
         return "IntermediateResolvedState [cmpReg=" + this.pC + ", cmpHighRangeIre=" + this.A + "]";
      }
   }
}
