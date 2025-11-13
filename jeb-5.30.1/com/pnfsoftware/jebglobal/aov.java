package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.math.BigInteger;

public class aov {
   IEGeneric q;
   IEVar RF;
   private IEImm xK;
   private IEImm Dw;
   private IEImm Uv;
   private IEImm oW;
   private apw gO;
   private int nf;
   private int gP;

   public aov(IEGeneric var1, IEVar var2) {
      this.q = var1;
      this.RF = var2;
      int var3 = var2.getBitsize();
      this.xK = EUtil.zero(var3);
      this.Dw = EUtil.one(var3);
      this.Uv = this.Dw._shl(var3 - 1);
      this.oW = this.Uv._sub(this.Dw);
   }

   public IEGeneric q() {
      return this.q;
   }

   public IEVar RF() {
      return this.RF;
   }

   public boolean xK() {
      if (this.gO != null) {
         throw new IllegalStateException("Already processed");
      } else {
         this.gO = this.q(this.q);
         return this.nf == 0;
      }
   }

   public apw Dw() {
      if (this.gO == null) {
         throw new IllegalStateException();
      } else {
         return this.gO;
      }
   }

   public boolean Uv() {
      if (this.gO == null) {
         throw new IllegalStateException();
      } else {
         return this.nf == 0;
      }
   }

   public int oW() {
      if (this.gO == null) {
         throw new IllegalStateException();
      } else {
         return this.gP;
      }
   }

   public IEGeneric gO() {
      if (this.gO == null) {
         this.xK();
      }

      if (!this.Uv()) {
         return null;
      } else if (this.gO.lm()) {
         return EUtil.one(1);
      } else if (this.gO.gP()) {
         return EUtil.zero(1);
      } else {
         int var1 = this.q(this.gO);
         apw var2 = this.gO.JY();
         int var3 = this.q(var2);
         boolean var4 = var3 < var1;
         apw var5 = var4 ? var2 : this.gO;
         Object var6 = null;
         if (this.RF.getType() == null || this.RF.getType().isSigned()) {
            for (apw.eo var8 : var5.Dw()) {
               IEOperation var9;
               if (var8.q.equals(var5.oW())) {
                  IEImm var10 = EUtil.imm(var8.RF, this.RF.getBitsize());
                  var9 = EUtil.ltS(this.RF, var10);
               } else if (var8.RF.equals(var5.gO())) {
                  IEImm var12 = EUtil.imm(var8.q, this.RF.getBitsize());
                  var9 = EUtil.geS(this.RF, var12);
               } else if (var8.q.add(BigInteger.ONE).equals(var8.RF)) {
                  IEImm var13 = EUtil.imm(var8.q, this.RF.getBitsize());
                  var9 = EUtil.eq(this.RF, var13);
               } else {
                  IEImm var14 = EUtil.imm(var8.q, this.RF.getBitsize());
                  IEImm var11 = EUtil.imm(var8.RF, this.RF.getBitsize());
                  var9 = EUtil.andL(EUtil.geS(this.RF, var14), EUtil.ltS(this.RF, var11));
               }

               if (var6 == null) {
                  var6 = var9;
               } else {
                  var6 = EUtil.orL((IEGeneric)var6, var9);
               }
            }
         }

         if (var6 == null) {
            return this.q;
         } else {
            if (var4) {
               var6 = EUtil.notL((IEGeneric)var6);
            }

            return (IEGeneric)var6;
         }
      }
   }

   private int q(apw var1) {
      if (!var1.gP() && !var1.lm()) {
         int var2 = 0;

         for (apw.eo var4 : var1.Dw()) {
            if (var4.q.equals(var1.oW())) {
               var2++;
            } else if (var4.RF.equals(var1.gO())) {
               var2++;
            } else if (var4.q.add(BigInteger.ONE).equals(var4.RF)) {
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

   private apw q(IEGeneric var1) {
      if (var1 instanceof IEOperation) {
         IEOperation var2 = var1.asOperation();
         OperationType var3 = var2.getOperationType();
         if (var3 == OperationType.LOG_AND) {
            apw var8 = this.q(var2.getOperand1());
            apw var9 = this.q(var2.getOperand2());
            return var8.Dw(var9);
         }

         if (var3 == OperationType.LOG_OR) {
            apw var7 = this.q(var2.getOperand1());
            apw var5 = this.q(var2.getOperand2());
            return var7.RF(var5);
         }

         if (var3 == OperationType.LOG_NOT) {
            apw var4 = this.q(var2.getOperand1());
            return var4.JY();
         }
      }

      apw var6 = new apw(this.RF.getBitsize(), false);
      if (!this.q(var1, var6)) {
         this.nf++;
      }

      return var6;
   }

   private boolean q(IEGeneric var1, apw var2) {
      this.gP++;
      OperationType var3 = null;
      IEImm var4 = null;
      if (var1 instanceof IEVar) {
         if (var1 != this.RF) {
            return false;
         }

         var3 = OperationType.LOG_NEQ;
         var4 = this.xK;
      } else if (var1 instanceof IEOperation var5) {
         var3 = var5.getOperationType();
         IEGeneric var6 = var5.getOperand1();
         if (q(var3) && var6 instanceof IEVar && var5.getOperand2() instanceof IEImm) {
            if (var6 != this.RF) {
               return false;
            }

            var4 = (IEImm)var5.getOperand2();
         }
      }

      if (var3 != null && var4 != null) {
         switch (var3) {
            case LOG_EQ:
               var2.xK(var4);
               break;
            case LOG_NEQ:
               var2.Uv(var4);
               break;
            case LT_S:
               var2.xK(null, var4);
               break;
            case GE_S:
               var2.xK(var4, null);
               break;
            case GT_S:
               if (var4._cmp(this.oW) == 0) {
                  var2.nf();
               } else {
                  IEImm var12 = var4._add(this.Dw);
                  var2.xK(var12, null);
               }
               break;
            case LE_S:
               if (var4._cmp(this.oW) != 0) {
                  IEImm var11 = var4._add(this.Dw);
                  var2.xK(null, var11);
               }
               break;
            case LT_U:
               int var10 = var4._signum();
               if (var10 == 0) {
                  var2.nf();
               } else if (var10 > 0) {
                  var2.xK(this.xK, var4);
               } else {
                  var2.xK(var2.q().RF(this.xK, null).RF(null, var4));
               }
               break;
            case GE_U:
               int var9 = var4._signum();
               if (var9 != 0) {
                  if (var9 > 0) {
                     var2.xK(var2.q().RF(var4, null).RF(null, this.xK));
                  } else {
                     var2.xK(var4, this.xK);
                  }
               }
               break;
            case GT_U:
               if (var4.isOnes()) {
                  var2.nf();
               } else {
                  int var8 = var4._signum();
                  if (var8 == 0) {
                     var2.Uv(this.xK);
                  } else if (var8 > 0) {
                     IEImm var15 = var4._add(this.Dw);
                     var2.xK(var2.q().RF(var15, null).RF(null, this.xK));
                  } else {
                     IEImm var16 = var4._add(this.Dw);
                     var2.xK(var16, this.xK);
                  }
               }
               break;
            case LE_U:
               if (!var4.isOnes()) {
                  int var7 = var4._signum();
                  if (var7 == 0) {
                     var2.xK(this.xK);
                  } else if (var7 > 0) {
                     IEImm var13 = var4._add(this.Dw);
                     var2.xK(this.xK, var13);
                  } else {
                     IEImm var14 = var4._add(this.Dw);
                     var2.xK(var2.q().RF(this.xK, null).RF(null, var14));
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

   private static boolean q(OperationType var0) {
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
