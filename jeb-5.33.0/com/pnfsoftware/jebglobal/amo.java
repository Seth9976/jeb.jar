package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.math.BigInteger;

public class amo {
   IEGeneric pC;
   IEVar A;
   private IEImm kS;
   private IEImm wS;
   private IEImm UT;
   private IEImm E;
   private anm sY;
   private int ys;
   private int ld;

   public amo(IEGeneric var1, IEVar var2) {
      this.pC = var1;
      this.A = var2;
      int var3 = var2.getBitsize();
      this.kS = EUtil.zero(var3);
      this.wS = EUtil.one(var3);
      this.UT = this.wS._shl(var3 - 1);
      this.E = this.UT._sub(this.wS);
   }

   public boolean pC() {
      if (this.sY != null) {
         throw new IllegalStateException("Already processed");
      } else {
         this.sY = this.pC(this.pC);
         return this.ys == 0;
      }
   }

   public anm A() {
      if (this.sY == null) {
         throw new IllegalStateException();
      } else {
         return this.sY;
      }
   }

   public boolean kS() {
      if (this.sY == null) {
         throw new IllegalStateException();
      } else {
         return this.ys == 0;
      }
   }

   public IEGeneric wS() {
      if (this.sY == null) {
         this.pC();
      }

      if (!this.kS()) {
         return null;
      } else if (this.sY.gp()) {
         return EUtil.one(1);
      } else if (this.sY.ys()) {
         return EUtil.zero(1);
      } else {
         int var1 = this.pC(this.sY);
         anm var2 = this.sY.oT();
         int var3 = this.pC(var2);
         boolean var4 = var3 < var1;
         anm var5 = var4 ? var2 : this.sY;
         Object var6 = null;
         if (this.A.getType() == null || this.A.getType().isSigned()) {
            for (anm.Av var8 : var5.kS()) {
               IEOperation var9;
               if (var8.pC.equals(var5.UT())) {
                  IEImm var10 = EUtil.imm(var8.A, this.A.getBitsize());
                  var9 = EUtil.ltS(this.A, var10);
               } else if (var8.A.equals(var5.E())) {
                  IEImm var12 = EUtil.imm(var8.pC, this.A.getBitsize());
                  var9 = EUtil.geS(this.A, var12);
               } else if (var8.pC.add(BigInteger.ONE).equals(var8.A)) {
                  IEImm var13 = EUtil.imm(var8.pC, this.A.getBitsize());
                  var9 = EUtil.eq(this.A, var13);
               } else {
                  IEImm var14 = EUtil.imm(var8.pC, this.A.getBitsize());
                  IEImm var11 = EUtil.imm(var8.A, this.A.getBitsize());
                  var9 = EUtil.andL(EUtil.geS(this.A, var14), EUtil.ltS(this.A, var11));
               }

               if (var6 == null) {
                  var6 = var9;
               } else {
                  var6 = EUtil.orL((IEGeneric)var6, var9);
               }
            }
         }

         if (var6 == null) {
            return this.pC;
         } else {
            if (var4) {
               var6 = EUtil.notL((IEGeneric)var6);
            }

            return (IEGeneric)var6;
         }
      }
   }

   private int pC(anm var1) {
      if (!var1.ys() && !var1.gp()) {
         int var2 = 0;

         for (anm.Av var4 : var1.kS()) {
            if (var4.pC.equals(var1.UT())) {
               var2++;
            } else if (var4.A.equals(var1.E())) {
               var2++;
            } else if (var4.pC.add(BigInteger.ONE).equals(var4.A)) {
               var2++;
            } else {
               var2 += 2;
            }
         }

         return var2;
      } else {
         return 0;
      }
   }

   private anm pC(IEGeneric var1) {
      if (var1 instanceof IEOperation) {
         IEOperation var2 = var1.asOperation();
         OperationType var3 = var2.getOperationType();
         if (var3 == OperationType.LOG_AND) {
            anm var8 = this.pC(var2.getOperand1());
            anm var9 = this.pC(var2.getOperand2());
            return var8.wS(var9);
         }

         if (var3 == OperationType.LOG_OR) {
            anm var7 = this.pC(var2.getOperand1());
            anm var5 = this.pC(var2.getOperand2());
            return var7.A(var5);
         }

         if (var3 == OperationType.LOG_NOT) {
            anm var4 = this.pC(var2.getOperand1());
            return var4.oT();
         }
      }

      anm var6 = new anm(this.A.getBitsize(), false);
      if (!this.pC(var1, var6)) {
         this.ys++;
      }

      return var6;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean pC(IEGeneric var1, anm var2) {
      this.ld++;
      OperationType var3 = null;
      IEImm var4 = null;
      if (var1 instanceof IEVar) {
         if (var1 != this.A) {
            return false;
         }

         var3 = OperationType.LOG_NEQ;
         var4 = this.kS;
      } else if (var1 instanceof IEOperation var5) {
         var3 = var5.getOperationType();
         IEGeneric var6 = var5.getOperand1();
         if (pC(var3) && var6 instanceof IEVar && var5.getOperand2() instanceof IEImm) {
            if (var6 != this.A) {
               return false;
            }

            var4 = (IEImm)var5.getOperand2();
         }
      }

      if (var3 != null && var4 != null) {
         switch (var3) {
            case LOG_EQ:
               var2.A(var4);
               break;
            case LOG_NEQ:
               var2.wS(var4);
               break;
            case LT_S:
               var2.kS(null, var4);
               break;
            case GE_S:
               var2.kS(var4, null);
               break;
            case GT_S:
               if (var4._cmp(this.E) == 0) {
                  var2.sY();
               } else {
                  IEImm var12 = var4._add(this.wS);
                  var2.kS(var12, null);
               }
               break;
            case LE_S:
               if (var4._cmp(this.E) != 0) {
                  IEImm var11 = var4._add(this.wS);
                  var2.kS(null, var11);
               }
               break;
            case LT_U:
               int var10 = var4._signum();
               if (var10 == 0) {
                  var2.sY();
               } else if (var10 > 0) {
                  var2.kS(this.kS, var4);
               } else {
                  var2.kS(var2.pC().A(this.kS, null).A(null, var4));
               }
               break;
            case GE_U:
               int var9 = var4._signum();
               if (var9 != 0) {
                  if (var9 > 0) {
                     var2.kS(var2.pC().A(var4, null).A(null, this.kS));
                  } else {
                     var2.kS(var4, this.kS);
                  }
               }
               break;
            case GT_U:
               if (var4.isOnes()) {
                  var2.sY();
               } else {
                  int var8 = var4._signum();
                  if (var8 == 0) {
                     var2.wS(this.kS);
                  } else if (var8 > 0) {
                     IEImm var15 = var4._add(this.wS);
                     var2.kS(var2.pC().A(var15, null).A(null, this.kS));
                  } else {
                     IEImm var16 = var4._add(this.wS);
                     var2.kS(var16, this.kS);
                  }
               }
               break;
            case LE_U:
               if (!var4.isOnes()) {
                  int var7 = var4._signum();
                  if (var7 == 0) {
                     var2.A(this.kS);
                  } else if (var7 > 0) {
                     IEImm var13 = var4._add(this.wS);
                     var2.kS(this.kS, var13);
                  } else {
                     IEImm var14 = var4._add(this.wS);
                     var2.kS(var2.pC().A(this.kS, null).A(null, var14));
                  }
               }
               break;
            default:
               throw new RuntimeException("TBI: Operator " + var3);
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean pC(OperationType var0) {
      if (var0 == null) {
         return false;
      } else {
         switch (var0) {
            case LOG_EQ:
            case LOG_NEQ:
            case LT_S:
            case GE_S:
            case GT_S:
            case LE_S:
            case LT_U:
            case GE_U:
            case GT_U:
            case LE_U:
               return true;
            default:
               return false;
         }
      }
   }
}
