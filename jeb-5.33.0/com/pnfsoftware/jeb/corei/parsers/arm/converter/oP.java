package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.pY;
import java.util.List;

@Ser
public class oP {
   private static final ILogger ld = GlobalLog.getLogger(oP.class);
   @SerId(21)
   IEVar pC;
   @SerId(22)
   IEVar A;
   @SerId(23)
   IEVar kS;
   @SerId(24)
   IEVar wS;
   @SerId(33)
   IEImm UT;
   @SerId(34)
   IEImm E;
   @SerId(38)
   private IEVar gp;
   @SerId(39)
   private IEVar oT;
   @SerId(40)
   private IEVar fI;
   @SerTransient
   IERoutineContext sY;
   @SerTransient
   boolean ys = true;
   private static oP.Av WR = new oP.Av(null);

   public oP(IEGlobalContext var1) {
      this(var1, true);
   }

   public oP(IEGlobalContext var1, boolean var2) {
      this.pC = var1.getVar(543);
      this.A = var1.getVar(542);
      this.kS = var1.getVar(541);
      this.wS = var1.getVar(540);
      this.gp = var1.createVirtualRegister(66080, "cmp64", 64);
      this.oT = var1.createVirtualRegister(66144, "cmpOp0", 32);
      this.fI = var1.createVirtualRegister(66176, "cmpOp1", 32);
      this.ys = var2;
      this.UT = var1.createImm(0L, 1);
      this.E = var1.createImm(1L, 1);
   }

   void pC(int var1, List var2, IEGeneric var3, List var4, List var5, long var6, boolean var8, IEVar var9) {
      var2.add(this.sY.createJump(var1 + 2 + var4.size(), EUtil.notL(var3)));
      var2.addAll(var4);
      var2.add(this.sY.createJump(var1 + 2 + var4.size() + var5.size(), null));
      var2.addAll(var5);
      IEStatement var10 = (IEStatement)var5.get(var5.size() - 1);
      this.pC(var2, var8, var10, var6, var9);
   }

   void pC(int var1, List var2, IEGeneric var3, List var4, long var5, boolean var7, IEVar var8) {
      var2.add(this.sY.createJump(var1 + 1 + var4.size(), EUtil.notL(var3)));
      var2.addAll(var4);
      IEStatement var9 = (IEStatement)var4.get(var4.size() - 1);
      this.pC(var2, var7, var9, var5, var8);
   }

   private void pC(List var1, boolean var2, IEStatement var3, long var4, IEVar var6) {
      if (var2 && var3 instanceof IEAssign && ((IEAssign)var3).getLeftOperand().equals(var6)) {
         var1.add(this.sY.createBranchAssign(var6, this.sY.createImm(var4, var6.getBitsize()), false));
      }
   }

   public oP.Av pC(HE var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, List var3, long var4, int var6, oP.Av var7) {
      if (var2.wS().kS()) {
         if (var2.UT().sY()) {
            return WR;
         } else if (var2.sY()) {
            return WR;
         } else {
            String var10 = var2.wS().pC();
            IEGeneric var14;
            IEGeneric var15;
            switch (var10) {
               case "CMP":
               case "SUB":
                  var14 = this.pC(var1, var2, 0, var4);
                  var15 = this.A(var1, var2, var14.getBitsize(), var4);
                  break;
               case "RSB":
                  var15 = this.pC(var1, var2, 0, var4);
                  var14 = this.A(var1, var2, var15.getBitsize(), var4);
                  break;
               case "SBC":
                  if (var7 == null || var7.A == null || var7.kS == null) {
                     return WR;
                  }

                  var14 = this.pC(var1, var2, 0, var4);
                  var15 = this.A(var1, var2, var14.getBitsize(), var4);
                  if (var14.getBitsize() != 32 || var7.A.getBitsize() != 32 || var15 == null) {
                     return WR;
                  }

                  var14 = EUtil.compose(this.sY, var7.A, var14);
                  var15 = EUtil.compose(this.sY, var7.kS, var15);
                  break;
               default:
                  Object[] var10000 = new Object[]{var10};
                  return WR;
            }

            if (var14 == null || var15 == null) {
               return WR;
            } else if (var14.getBitsize() == 64) {
               var3.add(0, this.sY.createAssign(this.gp, EUtil.sub(var14, var15)));
               return new oP.Av(this.gp);
            } else {
               var3.add(0, this.sY.createAssign(this.oT, var14));
               var3.add(1, this.sY.createAssign(this.fI, var15));
               var7 = new oP.Av(var6, var4, this.oT, this.fI, var14, var15);
               if (var10.equals("SUB") || var10.equals("RSB")) {
                  var7.pC(var1.pC(var2, (IInstructionOperand)var2.pC(0), var4));
               }

               return var7;
            }
         }
      } else {
         return var7;
      }
   }

   private IEGeneric pC(HE var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, int var3, long var4) {
      return var1.pC(var2, (IInstructionOperand)var2.pC(var2.A().length == 2 ? 0 : 1), var4);
   }

   private IEGeneric A(HE var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, int var3, long var4) {
      int var6 = var2.A().length == 2 ? 1 : 2;
      switch (var2.pC(var6).getOperandType()) {
         case 0:
         case 1:
         case 2:
         case 3:
            return var1.pC(var2, var2.pC(var6), var3, var4);
         default:
            return null;
      }
   }

   public IEGeneric pC(Yg var1, IEGeneric var2) {
      return var1.getOperandType() != 1 && var1.getOperandType() != 6 ? null : this.pC(Integer.valueOf((int)var1.getOperandValue()), var2);
   }

   public IEGeneric pC(Integer var1, IEGeneric var2) {
      if (this.ys && var2 != null) {
         IEGeneric var3 = this.pC(var1 >>> 1, var2);
         if (var3 != null) {
            return (var1 & 1) == 0 ? var3 : EUtil.notL(var3, true);
         }
      }

      return this.pC(var1);
   }

   private IEGeneric pC(int var1, IEGeneric var2) {
      switch (var1) {
         case 0:
            return EUtil.eq(var2, this.sY.createImm(0L, var2.getBitsize()));
         case 1:
         case 2:
         case 3:
         case 4:
            return null;
         case 5:
            return EUtil.geS(var2, this.sY.createImm(0L, var2.getBitsize()));
         case 6:
            return EUtil.gtS(var2, this.sY.createImm(0L, var2.getBitsize()));
         case 7:
            return this.E;
         default:
            return null;
      }
   }

   public IEGeneric pC(Integer var1) {
      IEGeneric var2 = this.pC(var1 >>> 1);
      return (IEGeneric)((var1 & 1) == 0 ? var2 : this.sY.createOperation(OperationType.LOG_NOT, var2));
   }

   private IEGeneric pC(int var1) {
      switch (var1) {
         case 0:
            return this.sY.createOperation(OperationType.LOG_NEQ, this.A, this.UT);
         case 1:
            return this.sY.createOperation(OperationType.LOG_NEQ, this.kS, this.UT);
         case 2:
            return this.sY.createOperation(OperationType.LOG_NEQ, this.pC, this.UT);
         case 3:
            return this.sY.createOperation(OperationType.LOG_NEQ, this.wS, this.UT);
         case 4:
            return this.sY
               .createOperation(
                  OperationType.LOG_AND,
                  this.sY.createOperation(OperationType.LOG_NEQ, this.kS, this.UT),
                  this.sY.createOperation(OperationType.LOG_EQ, this.A, this.UT)
               );
         case 5:
            return this.sY.createOperation(OperationType.LOG_EQ, this.pC, this.wS);
         case 6:
            return this.sY
               .createOperation(
                  OperationType.LOG_AND,
                  this.sY.createOperation(OperationType.LOG_EQ, this.A, this.UT),
                  this.sY.createOperation(OperationType.LOG_EQ, this.pC, this.wS)
               );
         case 7:
            return this.E;
         default:
            return null;
      }
   }

   void pC(IEGeneric var1, List var2) {
      var2.add(this.pC(var1));
      var2.add(this.A(var1));
   }

   private IEAssign pC(IEGeneric var1) {
      IEGeneric var2 = var1.msb();
      return this.sY.createAssign(this.pC, var2);
   }

   private IEAssign A(IEGeneric var1) {
      IECond var2 = this.sY.createCond(var1, this.UT, this.E);
      return this.sY.createAssign(this.A, var2);
   }

   public IEGeneric pC(HE var1, List var2, pY var3, m var4) {
      IEVar var5 = null;
      if (var4 == null) {
         return var5;
      } else {
         if (var3 != null && var4.E()) {
            if (!var3.ld()) {
               throw new IllegalConversionException(Strings.f("Destination register is not a register %s in %s", var3, var4.pC()));
            }

            var5 = var1.E(var3.pC().getBitsize());
            int var6 = var4.pC((IEVar)var3.pC(), var5);
            if (var6 == 0) {
               var5 = null;
            }
         }

         if (var4.A() != null) {
            var2.add(this.sY.createAssign(this.pC, var4.A()));
         }

         if (var4.kS() != null) {
            var2.add(this.sY.createAssign(this.A, var4.kS()));
         }

         if (var4.UT() != null) {
            var2.add(this.sY.createAssign(this.wS, var4.UT()));
         }

         if (var4.wS() != null) {
            var2.add(this.sY.createAssign(this.kS, var4.wS()));
         }

         return var5;
      }
   }

   static class Av {
      private IEVar pC;
      private IEGeneric A;
      private IEGeneric kS;
      private int wS;
      private long UT;
      private IEGeneric E;
      private IEGeneric sY;
      private IEGeneric ys;

      public Av(IEVar var1) {
         this.pC = var1;
      }

      public Av(int var1, long var2, IEGeneric var4, IEGeneric var5, IEGeneric var6, IEGeneric var7) {
         this.wS = var1;
         this.UT = var2;
         this.A = var4;
         this.kS = var5;
         this.E = var6;
         this.sY = var7;
      }

      private void pC(IEGeneric var1) {
         this.ys = var1;
      }

      public IEGeneric pC(BasicBlock var1, int var2, List var3, int var4, boolean var5) {
         if (this.A != null) {
            if (this.wS + 1 == var2) {
               return (IEGeneric)(this.ys != null ? this.ys : EUtil.sub(this.E, this.sY));
            }

            if (var5) {
               for (int var6 = var4; var6 < var3.size(); var6++) {
                  IEStatement var7 = (IEStatement)var3.get(var6);
                  if (var7.getPrimaryLowerLevelAddress() == null || var7.getPrimaryLowerLevelAddress() <= this.UT || !(var7 instanceof IEAssign)) {
                     break;
                  }

                  IEGeneric var8 = ((IEAssign)var7).getLeftOperand();
                  IdRanges var9 = var8.getUsed();
                  if (this.ys != null && var9.hasIntersection(this.ys.getUsed())) {
                     this.ys = null;
                  }

                  if (var9.hasIntersection(this.E.getUsed()) || var9.hasIntersection(this.sY.getUsed())) {
                     break;
                  }
               }
            }
         }

         return (IEGeneric)(this.A != null && this.kS != null ? EUtil.sub(this.A, this.kS) : this.pC);
      }
   }
}
