package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class bsk {
   private IDMethodContext pC;
   private bsv A;
   private Map kS;
   private IdentityHashSet wS;

   public bsk(IDMethodContext var1, bsv var2) {
      if (var1 != null && var2 != null) {
         this.pC = var1;
         this.A = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public IDExpression pC(Map var1) {
      return this.pC(var1, null);
   }

   IDExpression pC(Map var1, Integer var2) {
      if (this.wS != null) {
         throw new IllegalStateException();
      } else {
         this.wS = new IdentityHashSet();
         this.kS = var1;

         IDExpression var3;
         try {
            var3 = this.pC(this.A, var2);
         } finally {
            this.kS = null;
            this.wS.clear();
         }

         return var3;
      }
   }

   private IDExpression pC(bsv var1) {
      return this.pC(var1, null);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private IDExpression pC(bsv var1, Integer var2) {
      if (var1 instanceof bsy var14) {
         bsz var15 = var14.pC;
         if (var15 == null) {
            int var16 = var14.A.pC;
            var15 = (bsz)this.kS.get(var16);
            if (var15 == null) {
               return null;
            }
         }

         switch (bsl.A[var15.ordinal()]) {
            case 1:
               IDExpression var18 = this.pC(var14.kS[0]);
               IDExpression var20 = this.pC(var14.kS[1], var2 == null ? null : var2);
               IDExpression var21 = this.pC(var14.kS[2], bpl.A(var20));
               return this.pC.createConditional(null, var18, var20, var21);
            default:
               JavaOperatorType var17 = var15.kS();
               if (var17 == null) {
                  return null;
               } else {
                  Integer var19 = null;
                  if (var2 != null) {
                     switch (bsl.pC[var17.ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                           var19 = var2;
                     }
                  }

                  IDExpression[] var7 = new IDExpression[3];
                  int var8 = 0;

                  for (bsv var12 : var14.kS) {
                     if (var8 >= var7.length) {
                        return null;
                     }

                     IDExpression var13 = this.pC(var12, var19);
                     if (var13 == null) {
                        return null;
                     }

                     if (var19 == null) {
                        var19 = bpl.A(var13);
                     }

                     var7[var8] = var13;
                     var8++;
                  }

                  return this.pC.createOperation(null, var17, var7[0], var7[1]);
               }
         }
      } else {
         bsx var3 = (bsx)var1;
         if (var3.kS == 16 && var3.pC == -1) {
            if (var3.A != 0) {
               return this.pC(var3.UT, var3.A);
            }

            if (var2 != null) {
               return this.pC(var3.UT, var2.intValue());
            }
         }

         IDExpression var4 = (IDExpression)this.kS.get(var3.pC);
         if (var4 == null) {
            throw new RuntimeException(Strings.ff("Expression not found (leaf_id=%d). Are you using LC() in a substituter? \"Very sad!\" (c) DJT", var3.pC));
         } else {
            if (var3.kS == 16) {
               int var5 = bpl.A(var4);
               if (var3.A != 0 || var5 == 0) {
                  throw new RuntimeException("Illegal bitsize values");
               }

               IDImm var6 = this.pC(var3.UT, var5);
               if (!(var4 instanceof IDImm) || !var4.equalsEx(var6, false)) {
                  return var6;
               }
            }

            if (!this.wS.add(var4)) {
               var4 = var4.duplicate();
            }

            return var4;
         }
      }
   }

   private IDImm pC(long var1, int var3) {
      if (var3 == 32) {
         return this.pC.getGlobalContext().createInt((int)var1);
      } else if (var3 == 64) {
         return this.pC.getGlobalContext().createLong(var1);
      } else {
         throw new RuntimeException();
      }
   }
}
