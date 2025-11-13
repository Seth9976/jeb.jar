package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.math.BigInteger;

public class bwb {
   private static final ILogger Dw = GlobalLog.getLogger(bwb.class);
   IDGlobalContext q;
   IDExpression RF;
   IDVar xK;
   private IDImm Uv;
   private IDImm oW;
   private IDImm gO;
   private IDImm nf;
   private bwp gP;
   private int za;
   private int lm;

   public static boolean q(IDVar var0) {
      IJavaType var1 = var0.getType();
      return var1.isInt() || var1.isLong();
   }

   public bwb(IDGlobalContext var1, IDExpression var2, IDVar var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      IJavaType var5 = var3.getType();
      byte var4;
      if (var5.isInt()) {
         var4 = 32;
      } else {
         if (!var5.isLong()) {
            throw new IllegalArgumentException();
         }

         var4 = 64;
      }

      this.Uv = var1.createImm(0L, var5);
      this.oW = var1.createImm(1L, var5);
      this.gO = this.oW._shl(var4 - 1);
      this.nf = this.gO._sub(this.oW);
   }

   public IDExpression q() {
      return this.RF;
   }

   public IDVar RF() {
      return this.xK;
   }

   public boolean xK() {
      if (this.gP != null) {
         throw new IllegalStateException("Already processed");
      } else {
         this.gP = this.q(this.RF);
         return this.za == 0;
      }
   }

   public bwp Dw() {
      if (this.gP == null) {
         throw new IllegalStateException();
      } else {
         return this.gP;
      }
   }

   public boolean Uv() {
      if (this.gP == null) {
         throw new IllegalStateException();
      } else {
         return this.za == 0;
      }
   }

   public int oW() {
      if (this.gP == null) {
         throw new IllegalStateException();
      } else {
         return this.lm;
      }
   }

   public IDExpression gO() {
      if (this.gP == null) {
         this.xK();
      }

      if (!this.Uv()) {
         return null;
      } else if (this.gP.lm()) {
         return this.q.createBoolean(true);
      } else if (this.gP.gP()) {
         return this.q.createBoolean(false);
      } else {
         int var1 = this.q(this.gP);
         bwp var2 = this.gP.JY();
         int var3 = this.q(var2);
         boolean var4 = var3 < var1;
         bwp var5 = var4 ? var2 : this.gP;
         IDOperation var6 = null;
         if (this.xK.getType().isInt() || this.xK.getType().isLong()) {
            for (bwp.eo var8 : var5.Dw()) {
               IDOperation var9;
               if (var8.q.equals(var5.oW())) {
                  IDImm var10 = this.xK.spawn(var8.RF.longValue());
                  var9 = this.q.createOperation(null, JavaOperatorType.LT, this.xK, var10);
               } else if (var8.RF.equals(var5.gO())) {
                  IDImm var12 = this.xK.spawn(var8.q.longValue());
                  var9 = this.q.createOperation(null, JavaOperatorType.GE, this.xK, var12);
               } else if (var8.q.add(BigInteger.ONE).equals(var8.RF)) {
                  IDImm var13 = this.xK.spawn(var8.q.longValue());
                  var9 = this.q.createOperation(null, JavaOperatorType.EQ, this.xK, var13);
               } else {
                  IDImm var14 = this.xK.spawn(var8.q.longValue());
                  IDImm var11 = this.xK.spawn(var8.RF.longValue());
                  var9 = this.q
                     .createOperation(
                        null,
                        JavaOperatorType.LOG_AND,
                        this.q.createOperation(null, JavaOperatorType.GE, this.xK, var14),
                        this.q.createOperation(null, JavaOperatorType.LT, this.xK, var11)
                     );
               }

               if (var6 == null) {
                  var6 = var9;
               } else {
                  var6 = this.q.createOperation(null, JavaOperatorType.LOG_OR, var6, var9);
               }
            }
         }

         if (var6 == null) {
            return this.RF;
         } else {
            if (var4) {
               var6 = this.q.createOperation(null, JavaOperatorType.LOG_NOT, null, var6);
            }

            return var6;
         }
      }
   }

   private int q(bwp var1) {
      if (!var1.gP() && !var1.lm()) {
         int var2 = 0;

         for (bwp.eo var4 : var1.Dw()) {
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

   private bwp q(IDExpression var1) {
      if (var1 instanceof IDOperation) {
         IDOperation var2 = var1.asOperation();
         JavaOperatorType var3 = var2.getOperatorType();
         if (var3 == JavaOperatorType.LOG_AND) {
            bwp var8 = this.q(var2.getOperand1());
            bwp var9 = this.q(var2.getOperand2());
            return var8.Dw(var9);
         }

         if (var3 == JavaOperatorType.LOG_OR) {
            bwp var7 = this.q(var2.getOperand1());
            bwp var5 = this.q(var2.getOperand2());
            return var7.RF(var5);
         }

         if (var3 == JavaOperatorType.LOG_NOT) {
            bwp var4 = this.q(var2.getOperand1());
            return var4.JY();
         }
      }

      bwp var6 = new bwp(bto.RF(this.xK), false);
      if (!this.q(var1, var6)) {
         this.za++;
      }

      return var6;
   }

   private boolean q(IDExpression var1, bwp var2) {
      this.lm++;
      JavaOperatorType var3 = null;
      IDImm var4 = null;
      if (var1 instanceof IDVar) {
         if (var1 != this.xK) {
            return false;
         }

         var3 = JavaOperatorType.NE;
         var4 = this.Uv;
      } else if (var1 instanceof IDOperation var5) {
         var3 = var5.getOperatorType();
         IDExpression var6 = var5.getOperand1();
         if (q(var3) && var6 instanceof IDVar && var5.getOperand2() instanceof IDImm) {
            if (var6 != this.xK) {
               return false;
            }

            var4 = (IDImm)var5.getOperand2();
         }
      }

      if (var3 != null && var4 != null) {
         switch (var3) {
            case EQ:
               var2.xK(var4);
               break;
            case NE:
               var2.Uv(var4);
               break;
            case LT:
               var2.xK(null, var4);
               break;
            case GE:
               var2.xK(var4, null);
               break;
            case GT:
               if (var4._cmp(this.nf) == 0) {
                  var2.nf();
               } else {
                  IDImm var8 = var4._add(this.oW);
                  var2.xK(var8, null);
               }
               break;
            case LE:
               if (var4._cmp(this.nf) != 0) {
                  IDImm var7 = var4._add(this.oW);
                  var2.xK(null, var7);
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

   private static boolean q(JavaOperatorType var0) {
      if (var0 == null) {
         return false;
      } else {
         switch (var0) {
            case EQ:
            case NE:
            case LT:
            case GE:
            case GT:
            case LE:
               return true;
            default:
               return false;
         }
      }
   }
}
