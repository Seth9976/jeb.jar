package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bxa {
   private static final ILogger RF = GlobalLog.getLogger(bxa.class);
   public static boolean q = false;
   private static int xK = 2;
   private static int Dw = 50;
   private IDMethodContext Uv;
   private bxj oW;
   private Map gO;
   private int nf;
   private int gP;
   private boolean za = false;
   private Map lm;

   public bxa(IDMethodContext var1, bxj var2, Map var3) {
      if (var1 != null && var2 != null) {
         this.Uv = var1;
         this.oW = var2;
         if (var2 instanceof bxm var4 && var4.Dw < 0) {
            bxq.q(var2);
         }

         if (var3 == null) {
            var3 = new HashMap();
         }

         this.gO = (Map)var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public bxa(IDMethodContext var1, bxj var2) {
      this(var1, var2, null);
   }

   private bxa() {
   }

   private boolean Uv() {
      return this.gP < Dw;
   }

   private bxa q(bxa var1) {
      bxa var2 = new bxa();
      var2.Uv = this.Uv;
      var2.oW = this.oW;
      var2.gO = new HashMap(this.gO);
      var2.nf = this.nf;
      var2.gP = this.gP + 1;
      var2.za = this.za;
      return var2;
   }

   public void q(boolean var1) {
      this.za = var1;
   }

   public boolean q() {
      return this.za;
   }

   public void q(Map var1) {
      this.lm = var1;
   }

   public Map RF() {
      return this.lm;
   }

   public Map xK() {
      return this.gO;
   }

   public void RF(boolean var1) {
      if (!var1) {
         this.gO.clear();
      }

      this.nf = 0;
      this.gP = 0;
   }

   public void Dw() {
      this.RF(false);
   }

   public boolean q(IDExpression var1) {
      if (this.nf != 0) {
         throw new IllegalStateException();
      } else {
         this.nf = 1;
         return this.q(var1, this.oW, 0);
      }
   }

   private static boolean oW() {
      return false;
   }

   private static boolean q(bxn var0, IDExpression var1) {
      return false;
   }

   private static boolean q(String var0) {
      return false;
   }

   private boolean q(IDExpression var1, bxj var2, int var3) {
      IJavaType var4 = var1.getType();
      if (var4 == null || var4.isObject() || var4.isFloatingPointType()) {
         return false;
      } else {
         if (var2 instanceof bxm var5) {
            if (this.lm != null) {
               Integer var15 = (Integer)this.lm.get(var1);
               if (var15 != null && var5.Dw > var15) {
                  return false;
               }
            }

            bxn var16 = var5.q;
            if (var16 == null) {
               if (!(var1 instanceof IDOperation var17)) {
                  return q("Expected an EOperation");
               }

               JavaOperatorType var26 = var17.getOperatorType();

               for (bxn var12 : var5.RF.RF) {
                  if (var12.xK() == var26) {
                     var16 = var12;
                     int var13 = var5.RF.q;
                     Object var14 = this.gO.get(var13);
                     if (var14 != null) {
                        if (var14 != var26) {
                           return q("Leaf mismatched");
                        }
                     } else {
                        this.gO.put(var13, var26);
                     }
                     break;
                  }
               }

               if (var16 == null) {
                  return q("Null operator");
               }
            }

            if (var5.xK.length >= 3) {
               switch (var16) {
                  case Dw:
                     if (!var1.isOperation(JavaOperatorType.ADD, JavaOperatorType.SUB)) {
                        return q(var16, var1);
                     }

                     List var23 = this.q((IDOperation)var1);
                     if (var23.size() != var5.xK.length) {
                        return q(var16, var1);
                     }

                     if (!this.q(var23, var5.xK, 0, var3)) {
                        return q(var16, var1);
                     }

                     return true;
                  case oW:
                     if (!var1.isOperation(JavaOperatorType.MUL)) {
                        return q(var16, var1);
                     }

                     List var22 = this.RF((IDOperation)var1);
                     if (var22.size() != var5.xK.length) {
                        return q(var16, var1);
                     }

                     if (!this.q(var22, var5.xK, 0, var3)) {
                        return q(var16, var1);
                     }

                     return true;
                  case lm:
                  case zz:
                  case JY:
                     if (!var1.isOperation(JavaOperatorType.AND, JavaOperatorType.OR, JavaOperatorType.XOR)) {
                        return q(var16, var1);
                     }

                     List var21 = this.xK((IDOperation)var1);
                     if (var21.size() != var5.xK.length) {
                        return q(var16, var1);
                     }

                     if (!this.q(var21, var5.xK, 0, var3)) {
                        return q(var16, var1);
                     }

                     return true;
               }
            }

            switch (var16) {
               case q:
                  if (var1 instanceof IDOperation var20 && var20.getOperatorType() == JavaOperatorType.COND_EXP) {
                     return this.q(var20.getCondPredicate(), var5.xK[0], var3 + 1)
                        && this.q(var20.getCondTrueExpression(), var5.xK[1], var3 + 1)
                        && this.q(var20.getCondFalseExpression(), var5.xK[2], var3 + 1);
                  }

                  return q(var16, var1);
               default:
                  if (var1 instanceof IDImm var18 && var16 == bxn.za) {
                     IDImm var28 = var18._not();
                     return this.q(var28, var5.xK[0], var3 + 1);
                  }

                  if (var1 instanceof IDOperation var19) {
                     JavaOperatorType var27 = var19.getOperatorType();
                     boolean var30 = q(var27, var16);
                     if (!var30) {
                        if (var19.getOperand2() instanceof IDImm var31) {
                           if (var27 == JavaOperatorType.ADD && var16 == bxn.Uv || var27 == JavaOperatorType.SUB && var16 == bxn.Dw) {
                              List var39 = var19.getSubExpressions();
                              var39.set(1, ((IDExpression)var39.get(1)).asImm()._neg());
                              return this.q((IDExpression)var39.get(0), var5.xK[0], var3 + 1) && this.q((IDExpression)var39.get(1), var5.xK[1], var3 + 1);
                           }

                           if (var27 == JavaOperatorType.SHL && var16 == bxn.oW) {
                              if (var31.canReadAsLong()) {
                                 int var38 = (int)var31.getValueAsLong();
                                 int var43 = var19.getType().isSingleSlot() ? 31 : 63;
                                 IDImm var45 = var19.spawn(2L)._pow(var38 & var43);
                                 return this.q(var19.getOperand1(), var5.xK[0], var3 + 1) && this.q(var45, var5.xK[1], var3 + 1);
                              }
                           } else if (var16 == bxn.KT && var27 == JavaOperatorType.MUL) {
                              Integer var35 = var31._log2();
                              if (var35 != null) {
                                 IDImm var40 = var19.spawn(var35.intValue());
                                 return this.q(var19.getOperand1(), var5.xK[0], var3 + 1) && this.q(var40, var5.xK[1], var3 + 1);
                              }
                           }
                        }
                        if (switch (var16) {
                           case PV -> var27 == JavaOperatorType.LE;
                           case Me -> var27 == JavaOperatorType.LT;
                           case xW -> var27 == JavaOperatorType.GE;
                           case oQ -> var27 == JavaOperatorType.GT;
                           default -> false;
                        }) {
                           return this.q(var19.getOperand2(), var5.xK[0], var3 + 1) && this.q(var19.getOperand1(), var5.xK[1], var3 + 1);
                        }
                     }

                     if (var30) {
                        List var33 = var19.getSubExpressions();
                        Boolean var36 = this.q(var33, var5, var16, var3);
                        if (var36 != null) {
                           if (!var36) {
                              Boolean var42 = this.q(var19, var5, var16, var3);
                              if (var42 != null) {
                                 return var42;
                              }
                           }

                           return var36;
                        }

                        var36 = this.q(var19, var5, var16, var3);
                        if (var36 != null) {
                           return var36;
                        }

                        int var41 = 0;

                        for (IDExpression var46 : var33) {
                           if (!this.q(var46, var5.xK[var41], var3 + 1)) {
                              return q("Operand mismatch: " + var46);
                           }

                           var41++;
                        }

                        return true;
                     }
                  }
            }
         } else {
            bxl var6 = (bxl)var2;
            if (var6.gP != 0 && var6.gP != bto.RF(var1)) {
               return q("leaf: unexpected bitsize");
            }

            boolean var7 = var1 instanceof IDVar || var1 instanceof IDImm;
            if ((var6.za & 7) == 7
               || var1 instanceof IDVar && (var6.za & 2) == 2
               || var1 instanceof IDImm && (var6.za & 1) == 1
               || var7 && (var6.za & 3) == 3
               || !var7 && (var6.za & 4) == 4) {
               if (var6.lm != null) {
                  var1 = var6.lm.q(var6, var1);
                  if (var1 == null) {
                     return q("leaf: process() returned null");
                  }
               }

               IDExpression var25 = (IDExpression)this.gO.get(var6.nf);
               if (var25 != null) {
                  return this.q(var25, var1, var6, true);
               }

               this.gO.put(var6.nf, var1);
               return true;
            }

            if (var1 instanceof IDImm && (var6.za & 16) != 0) {
               if (var6.nf >= 0) {
                  IDExpression var8 = (IDExpression)this.gO.get(var6.nf);
                  if (var8 != null) {
                     return this.q(var8, var1, var6, true);
                  }

                  this.gO.put(var6.nf, var1);
               }

               Long var24 = var6.zz;
               if (var24 != null) {
                  long var9 = ((IDImm)var1).getRawValue();
                  if (var24 == var9) {
                     return true;
                  }
               }
            } else if (var1 instanceof IDImm && (var6.za & 32) != 0) {
               throw new RuntimeException();
            }
         }

         return oW();
      }
   }

   private boolean q(IDExpression var1, IDExpression var2, bxl var3, boolean var4) {
      if (var1.equals(var2)) {
         return true;
      } else if (var1.equalsEx(var2, false)) {
         if (var2.getType() == null) {
            return true;
         } else if (var1.getType() == null) {
            if (var3.nf >= 0 && var4) {
               this.gO.put(var3.nf, var2);
            }

            return true;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   static boolean q(JavaOperatorType var0, bxn var1) {
      switch (var1) {
         case RF:
            return var0 == JavaOperatorType.ADD || var0 == JavaOperatorType.XOR || var0 == JavaOperatorType.OR;
         case xK:
            return var0 == JavaOperatorType.SUB || var0 == JavaOperatorType.XOR;
         default:
            return var1.xK() == var0;
      }
   }

   List q(IDOperation var1) {
      ArrayList var2 = new ArrayList();
      this.q(var1, false, var2);
      return var2;
   }

   private void q(IDExpression var1, boolean var2, List var3) {
      if (var1 instanceof IDOperation var4) {
         JavaOperatorType var5 = var4.getOperatorType();
         if (var5 == JavaOperatorType.ADD) {
            this.q(var4.getOperand1(), var2, var3);
            this.q(var4.getOperand2(), var2, var3);
         } else if (var5 == JavaOperatorType.SUB) {
            this.q(var4.getOperand1(), var2, var3);
            this.q(var4.getOperand2(), !var2, var3);
         } else {
            var3.add(new bxa.eo(var1, var2));
         }
      } else if (!(var1 instanceof IDImm var6 && var6.isZero())) {
         var3.add(new bxa.eo(var1, var2));
      }
   }

   List RF(IDOperation var1) {
      ArrayList var2 = new ArrayList();
      this.RF(var1, false, var2);
      return var2;
   }

   private void RF(IDExpression var1, boolean var2, List var3) {
      if (var1 instanceof IDOperation var4) {
         JavaOperatorType var5 = var4.getOperatorType();
         if (var5 == JavaOperatorType.MUL) {
            this.RF(var4.getOperand1(), var2, var3);
            this.RF(var4.getOperand2(), var2, var3);
         } else if (var5 == JavaOperatorType.SUB && var4.getOperand1() instanceof IDImm var6 && var6.isZero()) {
            this.RF(var4.getOperand2(), !var2, var3);
         } else {
            var3.add(new bxa.eo(var1, var2));
         }
      } else {
         var3.add(new bxa.eo(var1, var2));
      }
   }

   List xK(IDOperation var1) {
      ArrayList var2 = new ArrayList();
      this.q(var1, var1.getOperatorType(), var2);
      return var2;
   }

   private void q(IDExpression var1, JavaOperatorType var2, List var3) {
      if (var1 instanceof IDOperation var4) {
         JavaOperatorType var5 = var4.getOperatorType();
         if (var5 == var2) {
            this.q(var4.getOperand1(), var2, var3);
            this.q(var4.getOperand2(), var2, var3);
         } else {
            var3.add(new bxa.eo(var1));
         }
      } else {
         var3.add(new bxa.eo(var1));
      }
   }

   boolean q(List var1, bxj[] var2, int var3, int var4) {
      if (var1.isEmpty()) {
         return true;
      } else {
         bxj var5 = var2[var3];

         for (int var6 = 0; var6 < var1.size(); var6++) {
            bxa.eo var7 = (bxa.eo)var1.get(var6);
            IDExpression var8 = var7.q;
            if (var7.RF) {
               if (!(var5 instanceof bxm var9) || var9.q != bxn.gP) {
                  continue;
               }

               var5 = var9.xK[0];
            }

            if (!this.Uv()) {
               return false;
            }

            bxa var12 = this.q(this);
            boolean var10 = var12.q(var8, var5, var4 + 1);
            this.gP = var12.gP;
            if (var10) {
               ArrayList var11 = new ArrayList(var1);
               var11.remove(var6);
               var10 = var12.q(var11, var2, var3 + 1, var4 + 1);
               this.gP = var12.gP;
               if (var10) {
                  this.gO.putAll(var12.gO);
                  return true;
               }
            }
         }

         return false;
      }
   }

   private Boolean q(List var1, bxm var2, bxn var3, int var4) {
      if (xK > 0 && var3.q()) {
         if (var2.xK[0] instanceof bxl var5 && var2.xK[1] instanceof bxl var6) {
            boolean var14 = false;
            int var15 = var5.nf;
            int var16 = var6.nf;
            IDExpression var10 = (IDExpression)this.gO.get(var15);
            IDExpression var11 = (IDExpression)this.gO.get(var16);
            if (var10 != null && var11 == null) {
               if (var10.equalsEx(var1.get(1), false)) {
                  var14 = true;
               }
            } else if (var10 == null && var11 != null) {
               if (var11.equalsEx(var1.get(0), false)) {
                  var14 = true;
               }
            } else if (var10 != null && var11 != null && (!var10.equalsEx(var1.get(0), false) || !var11.equalsEx(var1.get(1), false))) {
               var14 = true;
            }

            if (var14) {
               return this.q((IDExpression)var1.get(0), var2.xK[1], var4 + 1) && this.q((IDExpression)var1.get(1), var2.xK[0], var4 + 1);
            }

            return this.q((IDExpression)var1.get(0), var2.xK[0], var4 + 1) && this.q((IDExpression)var1.get(1), var2.xK[1], var4 + 1);
         }

         if (xK >= 2 && this.Uv()) {
            bxa var13 = this.q(this);
            boolean var9 = false;
            boolean var8 = var13.q((IDExpression)var1.get(0), var2.xK[0], var4 + 1);
            this.gP = var13.gP;
            if (var8) {
               var9 = var13.q((IDExpression)var1.get(1), var2.xK[1], var4 + 1);
               this.gP = var13.gP;
            }

            if (!var9) {
               var13 = this.q(this);
               var8 = var13.q((IDExpression)var1.get(0), var2.xK[1], var4 + 1);
               this.gP = var13.gP;
               if (var8) {
                  var9 = var13.q((IDExpression)var1.get(1), var2.xK[0], var4 + 1);
                  this.gP = var13.gP;
               }
            }

            if (var8 && var9) {
               this.gO.putAll(var13.gO);
               return true;
            }

            return q("Operand mismatch: " + var1.get(1));
         }
      }

      return null;
   }

   private Boolean q(IDOperation var1, bxm var2, bxn var3, int var4) {
      if (this.za && var3.RF() && this.Uv()) {
         List var5 = switch (var3.xK()) {
            case ADD -> this.q(var1);
            case MUL -> this.RF(var1);
            default -> this.xK(var1);
         };
         if (var5 != null && var5.size() > 2 && var5.size() >= var2.xK.length) {
            bxa var6 = this.q(this);

            for (IDOperation var9 : this.q(var5, var3)) {
               ArrayList var10 = new ArrayList();
               var10.add(new bxa.eo(var9.getOperand1(), false));
               var10.add(new bxa.eo(var9.getOperand2(), false));
               if (var6.q(var10, var2.xK, 0, var4)) {
                  this.gO.putAll(var6.gO);
                  return true;
               }
            }

            return null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private List q(List var1, bxn var2) {
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList(var1);
      ArrayList var5 = new ArrayList();
      var5.add(0, (bxa.eo)var4.remove(0));
      IDExpression var6 = this.RF(var5, var2);
      IDExpression var7 = this.RF(var4, var2);
      var3.add(this.Uv.createOperation(null, var2.xK(), var6, var7));

      for (int var8 = 1; var8 < var1.size(); var8++) {
         for (int var9 = var8; var8 == 1 && var9 < var1.size() - 1 || var8 != 1 && var9 < var1.size(); var9++) {
            var4 = new ArrayList(var1);
            var5 = new ArrayList();

            for (int var10 = var9; var10 >= var8; var10--) {
               var5.add(0, (bxa.eo)var4.remove(var10));
            }

            var5.add(0, (bxa.eo)var4.remove(0));
            var6 = this.RF(var5, var2);
            var7 = this.RF(var4, var2);
            var3.add(this.Uv.createOperation(null, var2.xK(), var6, var7));
         }
      }

      return var3;
   }

   private IDExpression RF(List var1, bxn var2) {
      if (var1.isEmpty()) {
         return null;
      } else {
         bxa.eo var4 = (bxa.eo)var1.get(0);
         Object var3;
         if (var4.RF) {
            var3 = this.Uv.createOperation(null, var2.xK(), var4.q.spawn(0L), var4.q);
         } else {
            var3 = var4.q;
         }

         for (int var5 = 1; var5 < var1.size(); var5++) {
            bxa.eo var6 = (bxa.eo)var1.get(var5);
            if (var6.RF) {
               if (var2.xK() == JavaOperatorType.ADD) {
                  var3 = this.Uv.createOperation(null, JavaOperatorType.SUB, (IDExpression)var3, var6.q);
               } else {
                  var3 = this.Uv.createOperation(null, var2.xK(), (IDExpression)var3, this.Uv.createOperation(null, var2.xK(), var6.q.spawn(0L), var6.q));
               }
            } else {
               var3 = this.Uv.createOperation(null, var2.xK(), (IDExpression)var3, var6.q);
            }
         }

         return (IDExpression)var3;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("IR Matcher: '%s' [leafmap: %s] [fork: %d]", this.oW, this.gO, this.gP);
   }

   static class eo {
      IDExpression q;
      boolean RF;

      eo(IDExpression var1) {
         this.q = var1;
      }

      eo(IDExpression var1, boolean var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public String toString() {
         return this.RF ? "-" + this.q : this.q.toString();
      }
   }
}
