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

public class brr {
   private static final ILogger wS = GlobalLog.getLogger(brr.class);
   IDGlobalContext pC;
   IDExpression A;
   IDVar kS;
   private IDImm UT;
   private IDImm E;
   private IDImm sY;
   private IDImm ys;
   private bsd ld;
   private int gp;
   private int oT;

   public static boolean pC(IDVar var0) {
      IJavaType var1 = var0.getType();
      return var1.isInt() || var1.isLong();
   }

   public brr(IDGlobalContext var1, IDExpression var2, IDVar var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
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

      this.UT = var1.createImm(0L, var5);
      this.E = var1.createImm(1L, var5);
      this.sY = this.E._shl(var4 - 1);
      this.ys = this.sY._sub(this.E);
   }

   public boolean pC() {
      if (this.ld != null) {
         throw new IllegalStateException("Already processed");
      } else {
         this.ld = this.pC(this.A);
         return this.gp == 0;
      }
   }

   public bsd A() {
      if (this.ld == null) {
         throw new IllegalStateException();
      } else {
         return this.ld;
      }
   }

   private bsd pC(IDExpression var1) {
      if (var1 instanceof IDOperation) {
         IDOperation var2 = var1.asOperation();
         JavaOperatorType var3 = var2.getOperatorType();
         if (var3 == JavaOperatorType.LOG_AND) {
            bsd var8 = this.pC(var2.getOperand1());
            bsd var9 = this.pC(var2.getOperand2());
            return var8.wS(var9);
         }

         if (var3 == JavaOperatorType.LOG_OR) {
            bsd var7 = this.pC(var2.getOperand1());
            bsd var5 = this.pC(var2.getOperand2());
            return var7.A(var5);
         }

         if (var3 == JavaOperatorType.LOG_NOT) {
            bsd var4 = this.pC(var2.getOperand1());
            return var4.sY();
         }
      }

      bsd var6 = new bsd(bpl.A(this.kS), false);
      if (!this.pC(var1, var6)) {
         this.gp++;
      }

      return var6;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean pC(IDExpression var1, bsd var2) {
      this.oT++;
      JavaOperatorType var3 = null;
      IDImm var4 = null;
      if (var1 instanceof IDVar) {
         if (var1 != this.kS) {
            return false;
         }

         var3 = JavaOperatorType.NE;
         var4 = this.UT;
      } else if (var1 instanceof IDOperation var5) {
         var3 = var5.getOperatorType();
         IDExpression var6 = var5.getOperand1();
         if (pC(var3) && var6 instanceof IDVar && var5.getOperand2() instanceof IDImm) {
            if (var6 != this.kS) {
               return false;
            }

            var4 = (IDImm)var5.getOperand2();
         }
      }

      if (var3 != null && var4 != null) {
         switch (var3) {
            case EQ:
               var2.A(var4);
               break;
            case NE:
               var2.wS(var4);
               break;
            case LT:
               var2.pC(null, var4);
               break;
            case GE:
               var2.pC(var4, null);
               break;
            case GT:
               if (var4._cmp(this.ys) == 0) {
                  var2.wS();
               } else {
                  IDImm var8 = var4._add(this.E);
                  var2.pC(var8, null);
               }
               break;
            case LE:
               if (var4._cmp(this.ys) != 0) {
                  IDImm var7 = var4._add(this.E);
                  var2.pC(null, var7);
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

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static boolean pC(JavaOperatorType var0) {
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
