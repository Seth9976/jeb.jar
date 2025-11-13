package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class bwy {
   private IDMethodContext q;
   private bxj RF;
   private Map xK;
   private IdentityHashSet Dw;

   public bwy(IDMethodContext var1, bxj var2) {
      if (var1 != null && var2 != null) {
         this.q = var1;
         this.RF = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public IDExpression q(Map var1) {
      return this.q(var1, null);
   }

   IDExpression q(Map var1, Integer var2) {
      if (this.Dw != null) {
         throw new IllegalStateException();
      } else {
         this.Dw = new IdentityHashSet();
         this.xK = var1;

         IDExpression var3;
         try {
            var3 = this.q(this.RF, var2);
         } finally {
            this.xK = null;
            this.Dw.clear();
         }

         return var3;
      }
   }

   private IDExpression q(bxj var1) {
      return this.q(var1, null);
   }

   private IDExpression q(bxj var1, Integer var2) {
      if (var1 instanceof bxm var14) {
         bxn var15 = var14.q;
         if (var15 == null) {
            int var16 = var14.RF.q;
            var15 = (bxn)this.xK.get(var16);
            if (var15 == null) {
               return null;
            }
         }

         switch (var15) {
            case q:
               IDExpression var18 = this.q(var14.xK[0]);
               IDExpression var20 = this.q(var14.xK[1], var2 == null ? null : var2);
               IDExpression var21 = this.q(var14.xK[2], bto.RF(var20));
               return this.q.createConditional(null, var18, var20, var21);
            default:
               JavaOperatorType var17 = var15.xK();
               if (var17 == null) {
                  return null;
               } else {
                  Integer var19 = null;
                  if (var2 != null) {
                     switch (var17) {
                        case ADD:
                        case SUB:
                        case MUL:
                        case DIV:
                        case REM:
                        case NEG:
                        case AND:
                        case OR:
                        case XOR:
                        case SHR:
                        case SHL:
                        case USHR:
                        case NOT:
                           var19 = var2;
                     }
                  }

                  IDExpression[] var7 = new IDExpression[3];
                  int var8 = 0;

                  for (bxj var12 : var14.xK) {
                     if (var8 >= var7.length) {
                        return null;
                     }

                     IDExpression var13 = this.q(var12, var19);
                     if (var13 == null) {
                        return null;
                     }

                     if (var19 == null) {
                        var19 = bto.RF(var13);
                     }

                     var7[var8] = var13;
                     var8++;
                  }

                  return this.q.createOperation(null, var17, var7[0], var7[1]);
               }
         }
      } else {
         bxl var3 = (bxl)var1;
         if (var3.za == 16 && var3.nf == -1) {
            if (var3.gP != 0) {
               return this.q(var3.zz, var3.gP);
            }

            if (var2 != null) {
               return this.q(var3.zz, var2.intValue());
            }
         }

         IDExpression var4 = (IDExpression)this.xK.get(var3.nf);
         if (var4 == null) {
            throw new RuntimeException(Strings.ff("Expression not found (leaf_id=%d). Are you using LC() in a substituter? \"Very sad!\" (c) DJT", var3.nf));
         } else {
            if (var3.za == 16) {
               int var5 = bto.RF(var4);
               if (var3.gP != 0 || var5 == 0) {
                  throw new RuntimeException("Illegal bitsize values");
               }

               IDImm var6 = this.q(var3.zz, var5);
               if (!(var4 instanceof IDImm) || !var4.equalsEx(var6, false)) {
                  return var6;
               }
            }

            if (!this.Dw.add(var4)) {
               var4 = var4.duplicate();
            }

            return var4;
         }
      }
   }

   private IDImm q(long var1, int var3) {
      if (var3 == 32) {
         return this.q.getGlobalContext().createInt((int)var1);
      } else if (var3 == 64) {
         return this.q.getGlobalContext().createLong(var1);
      } else {
         throw new RuntimeException();
      }
   }
}
