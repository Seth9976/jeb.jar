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

public class bsm {
   private static final ILogger A = GlobalLog.getLogger(bsm.class);
   public static boolean pC = false;
   private static int kS = 2;
   private static int wS = 50;
   private IDMethodContext UT;
   private bsv E;
   private Map sY;
   private int ys;
   private int ld;
   private boolean gp = false;
   private Map oT;

   public bsm(IDMethodContext var1, bsv var2, Map var3) {
      if (var1 != null && var2 != null) {
         this.UT = var1;
         this.E = var2;
         if (var2 instanceof bsy var4 && var4.wS < 0) {
            btb.pC(var2);
         }

         if (var3 == null) {
            var3 = new HashMap();
         }

         this.sY = (Map)var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   private bsm() {
   }

   private boolean A() {
      return this.ld < wS;
   }

   private bsm pC(bsm var1) {
      bsm var2 = new bsm();
      var2.UT = this.UT;
      var2.E = this.E;
      var2.sY = new HashMap(this.sY);
      var2.ys = this.ys;
      var2.ld = this.ld + 1;
      var2.gp = this.gp;
      return var2;
   }

   public void pC(boolean var1) {
      this.gp = var1;
   }

   public void pC(Map var1) {
      this.oT = var1;
   }

   public void A(boolean var1) {
      if (!var1) {
         this.sY.clear();
      }

      this.ys = 0;
      this.ld = 0;
   }

   public void pC() {
      this.A(false);
   }

   public boolean pC(IDExpression var1) {
      if (this.ys != 0) {
         throw new IllegalStateException();
      } else {
         this.ys = 1;
         return this.pC(var1, this.E, 0);
      }
   }

   private static boolean kS() {
      return false;
   }

   private static boolean pC(bsz var0, IDExpression var1) {
      return false;
   }

   private static boolean pC(String var0) {
      return false;
   }

   private boolean pC(IDExpression var1, bsv var2, int var3) {
      IJavaType var4 = var1.getType();
      if (var4 == null || var4.isObject() || var4.isFloatingPointType()) {
         return false;
      } else {
         if (var2 instanceof bsy var5) {
            if (this.oT != null) {
               Integer var15 = (Integer)this.oT.get(var1);
               if (var15 != null && var5.wS > var15) {
                  return false;
               }
            }

            bsz var16 = var5.pC;
            if (var16 == null) {
               if (!(var1 instanceof IDOperation var17)) {
                  return pC("Expected an EOperation");
               }

               JavaOperatorType var26 = var17.getOperatorType();

               for (bsz var12 : var5.A.A) {
                  if (var12.kS() == var26) {
                     var16 = var12;
                     int var13 = var5.A.pC;
                     Object var14 = this.sY.get(var13);
                     if (var14 != null) {
                        if (var14 != var26) {
                           return pC("Leaf mismatched");
                        }
                     } else {
                        this.sY.put(var13, var26);
                     }
                     break;
                  }
               }

               if (var16 == null) {
                  return pC("Null operator");
               }
            }

            if (var5.kS.length >= 3) {
               switch (var16) {
                  case wS:
                     if (!var1.isOperation(JavaOperatorType.ADD, JavaOperatorType.SUB)) {
                        return pC(var16, var1);
                     }

                     List var23 = this.pC((IDOperation)var1);
                     if (var23.size() != var5.kS.length) {
                        return pC(var16, var1);
                     }

                     if (!this.pC(var23, var5.kS, 0, var3)) {
                        return pC(var16, var1);
                     }

                     return true;
                  case E:
                     if (!var1.isOperation(JavaOperatorType.MUL)) {
                        return pC(var16, var1);
                     }

                     List var22 = this.A((IDOperation)var1);
                     if (var22.size() != var5.kS.length) {
                        return pC(var16, var1);
                     }

                     if (!this.pC(var22, var5.kS, 0, var3)) {
                        return pC(var16, var1);
                     }

                     return true;
                  case oT:
                  case fI:
                  case WR:
                     if (!var1.isOperation(JavaOperatorType.AND, JavaOperatorType.OR, JavaOperatorType.XOR)) {
                        return pC(var16, var1);
                     }

                     List var21 = this.kS((IDOperation)var1);
                     if (var21.size() != var5.kS.length) {
                        return pC(var16, var1);
                     }

                     if (!this.pC(var21, var5.kS, 0, var3)) {
                        return pC(var16, var1);
                     }

                     return true;
               }
            }

            switch (var16) {
               case pC:
                  if (var1 instanceof IDOperation var20 && var20.getOperatorType() == JavaOperatorType.COND_EXP) {
                     return this.pC(var20.getCondPredicate(), var5.kS[0], var3 + 1)
                        && this.pC(var20.getCondTrueExpression(), var5.kS[1], var3 + 1)
                        && this.pC(var20.getCondFalseExpression(), var5.kS[2], var3 + 1);
                  }

                  return pC(var16, var1);
               default:
                  if (var1 instanceof IDImm var18 && var16 == bsz.gp) {
                     IDImm var28 = var18._not();
                     return this.pC(var28, var5.kS[0], var3 + 1);
                  }

                  if (var1 instanceof IDOperation var19) {
                     JavaOperatorType var27 = var19.getOperatorType();
                     boolean var30 = pC(var27, var16);
                     if (!var30) {
                        if (var19.getOperand2() instanceof IDImm var31) {
                           if (var27 == JavaOperatorType.ADD && var16 == bsz.UT || var27 == JavaOperatorType.SUB && var16 == bsz.wS) {
                              List var39 = var19.getSubExpressions();
                              var39.set(1, ((IDExpression)var39.get(1)).asImm()._neg());
                              return this.pC((IDExpression)var39.get(0), var5.kS[0], var3 + 1) && this.pC((IDExpression)var39.get(1), var5.kS[1], var3 + 1);
                           }

                           if (var27 == JavaOperatorType.SHL && var16 == bsz.E) {
                              if (var31.canReadAsLong()) {
                                 int var38 = (int)var31.getValueAsLong();
                                 int var43 = var19.getType().isSingleSlot() ? 31 : 63;
                                 IDImm var45 = var19.spawn(2L)._pow(var38 & var43);
                                 return this.pC(var19.getOperand1(), var5.kS[0], var3 + 1) && this.pC(var45, var5.kS[1], var3 + 1);
                              }
                           } else if (var16 == bsz.rl && var27 == JavaOperatorType.MUL) {
                              Integer var35 = var31._log2();
                              if (var35 != null) {
                                 IDImm var40 = var19.spawn(var35.intValue());
                                 return this.pC(var19.getOperand1(), var5.kS[0], var3 + 1) && this.pC(var40, var5.kS[1], var3 + 1);
                              }
                           }
                        }
                        if (switch (var16) {
                           case eP -> var27 == JavaOperatorType.LE;
                           case ah -> var27 == JavaOperatorType.LT;
                           case Ab -> var27 == JavaOperatorType.GE;
                           case UO -> var27 == JavaOperatorType.GT;
                           default -> false;
                        }) {
                           return this.pC(var19.getOperand2(), var5.kS[0], var3 + 1) && this.pC(var19.getOperand1(), var5.kS[1], var3 + 1);
                        }
                     }

                     if (var30) {
                        List var33 = var19.getSubExpressions();
                        Boolean var36 = this.pC(var33, var5, var16, var3);
                        if (var36 != null) {
                           if (!var36) {
                              Boolean var42 = this.pC(var19, var5, var16, var3);
                              if (var42 != null) {
                                 return var42;
                              }
                           }

                           return var36;
                        }

                        var36 = this.pC(var19, var5, var16, var3);
                        if (var36 != null) {
                           return var36;
                        }

                        int var41 = 0;

                        for (IDExpression var46 : var33) {
                           if (!this.pC(var46, var5.kS[var41], var3 + 1)) {
                              return pC("Operand mismatch: " + var46);
                           }

                           var41++;
                        }

                        return true;
                     }
                  }
            }
         } else {
            bsx var6 = (bsx)var2;
            if (var6.A != 0 && var6.A != bpl.A(var1)) {
               return pC("leaf: unexpected bitsize");
            }

            boolean var7 = var1 instanceof IDVar || var1 instanceof IDImm;
            if ((var6.kS & 7) == 7
               || var1 instanceof IDVar && (var6.kS & 2) == 2
               || var1 instanceof IDImm && (var6.kS & 1) == 1
               || var7 && (var6.kS & 3) == 3
               || !var7 && (var6.kS & 4) == 4) {
               if (var6.wS != null) {
                  var1 = var6.wS.pC(var6, var1);
                  if (var1 == null) {
                     return pC("leaf: process() returned null");
                  }
               }

               IDExpression var25 = (IDExpression)this.sY.get(var6.pC);
               if (var25 != null) {
                  return this.pC(var25, var1, var6, true);
               }

               this.sY.put(var6.pC, var1);
               return true;
            }

            if (var1 instanceof IDImm && (var6.kS & 16) != 0) {
               if (var6.pC >= 0) {
                  IDExpression var8 = (IDExpression)this.sY.get(var6.pC);
                  if (var8 != null) {
                     return this.pC(var8, var1, var6, true);
                  }

                  this.sY.put(var6.pC, var1);
               }

               Long var24 = var6.UT;
               if (var24 != null) {
                  long var9 = ((IDImm)var1).getRawValue();
                  if (var24 == var9) {
                     return true;
                  }
               }
            } else if (var1 instanceof IDImm && (var6.kS & 32) != 0) {
               throw new RuntimeException();
            }
         }

         return kS();
      }
   }

   private boolean pC(IDExpression var1, IDExpression var2, bsx var3, boolean var4) {
      if (var1.equals(var2)) {
         return true;
      } else if (var1.equalsEx(var2, false)) {
         if (var2.getType() == null) {
            return true;
         } else if (var1.getType() == null) {
            if (var3.pC >= 0 && var4) {
               this.sY.put(var3.pC, var2);
            }

            return true;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   static boolean pC(JavaOperatorType var0, bsz var1) {
      switch (var1) {
         case A:
            return var0 == JavaOperatorType.ADD || var0 == JavaOperatorType.XOR || var0 == JavaOperatorType.OR;
         case kS:
            return var0 == JavaOperatorType.SUB || var0 == JavaOperatorType.XOR;
         default:
            return var1.kS() == var0;
      }
   }

   List pC(IDOperation var1) {
      ArrayList var2 = new ArrayList();
      this.pC(var1, false, var2);
      return var2;
   }

   private void pC(IDExpression var1, boolean var2, List var3) {
      if (var1 instanceof IDOperation var4) {
         JavaOperatorType var5 = var4.getOperatorType();
         if (var5 == JavaOperatorType.ADD) {
            this.pC(var4.getOperand1(), var2, var3);
            this.pC(var4.getOperand2(), var2, var3);
         } else if (var5 == JavaOperatorType.SUB) {
            this.pC(var4.getOperand1(), var2, var3);
            this.pC(var4.getOperand2(), !var2, var3);
         } else {
            var3.add(new bsm.Av(var1, var2));
         }
      } else if (!(var1 instanceof IDImm var6 && var6.isZero())) {
         var3.add(new bsm.Av(var1, var2));
      }
   }

   List A(IDOperation var1) {
      ArrayList var2 = new ArrayList();
      this.A(var1, false, var2);
      return var2;
   }

   private void A(IDExpression var1, boolean var2, List var3) {
      if (var1 instanceof IDOperation var4) {
         JavaOperatorType var5 = var4.getOperatorType();
         if (var5 == JavaOperatorType.MUL) {
            this.A(var4.getOperand1(), var2, var3);
            this.A(var4.getOperand2(), var2, var3);
         } else if (var5 == JavaOperatorType.SUB && var4.getOperand1() instanceof IDImm var6 && var6.isZero()) {
            this.A(var4.getOperand2(), !var2, var3);
         } else {
            var3.add(new bsm.Av(var1, var2));
         }
      } else {
         var3.add(new bsm.Av(var1, var2));
      }
   }

   List kS(IDOperation var1) {
      ArrayList var2 = new ArrayList();
      this.pC(var1, var1.getOperatorType(), var2);
      return var2;
   }

   private void pC(IDExpression var1, JavaOperatorType var2, List var3) {
      if (var1 instanceof IDOperation var4) {
         JavaOperatorType var5 = var4.getOperatorType();
         if (var5 == var2) {
            this.pC(var4.getOperand1(), var2, var3);
            this.pC(var4.getOperand2(), var2, var3);
         } else {
            var3.add(new bsm.Av(var1));
         }
      } else {
         var3.add(new bsm.Av(var1));
      }
   }

   boolean pC(List var1, bsv[] var2, int var3, int var4) {
      if (var1.isEmpty()) {
         return true;
      } else {
         bsv var5 = var2[var3];

         for (int var6 = 0; var6 < var1.size(); var6++) {
            bsm.Av var7 = (bsm.Av)var1.get(var6);
            IDExpression var8 = var7.pC;
            if (var7.A) {
               if (!(var5 instanceof bsy var9) || var9.pC != bsz.ld) {
                  continue;
               }

               var5 = var9.kS[0];
            }

            if (!this.A()) {
               return false;
            }

            bsm var12 = this.pC(this);
            boolean var10 = var12.pC(var8, var5, var4 + 1);
            this.ld = var12.ld;
            if (var10) {
               ArrayList var11 = new ArrayList(var1);
               var11.remove(var6);
               var10 = var12.pC(var11, var2, var3 + 1, var4 + 1);
               this.ld = var12.ld;
               if (var10) {
                  this.sY.putAll(var12.sY);
                  return true;
               }
            }
         }

         return false;
      }
   }

   private Boolean pC(List var1, bsy var2, bsz var3, int var4) {
      if (kS > 0 && var3.pC()) {
         if (var2.kS[0] instanceof bsx var5 && var2.kS[1] instanceof bsx var6) {
            boolean var14 = false;
            int var16 = var5.pC;
            int var19 = var6.pC;
            IDExpression var21 = (IDExpression)this.sY.get(var16);
            IDExpression var22 = (IDExpression)this.sY.get(var19);
            if (var21 != null && var22 == null) {
               if (var21.equalsEx(var1.get(1), false)) {
                  var14 = true;
               }
            } else if (var21 == null && var22 != null) {
               if (var22.equalsEx(var1.get(0), false)) {
                  var14 = true;
               }
            } else if (var21 != null && var22 != null && (!var21.equalsEx(var1.get(0), false) || !var22.equalsEx(var1.get(1), false))) {
               var14 = true;
            }

            if (var14) {
               return this.pC((IDExpression)var1.get(0), var2.kS[1], var4 + 1) && this.pC((IDExpression)var1.get(1), var2.kS[0], var4 + 1);
            }

            return this.pC((IDExpression)var1.get(0), var2.kS[0], var4 + 1) && this.pC((IDExpression)var1.get(1), var2.kS[1], var4 + 1);
         }

         if (kS >= 2 && this.A()) {
            int var13 = var2.kS[0] instanceof bsy var8 ? var8.UT : 1;
            int var15 = var2.kS[1] instanceof bsy var17 ? var17.UT : 1;
            boolean var20 = false;
            bsm var11 = this.pC(this);
            boolean var18;
            if (var15 < var13) {
               var18 = var11.pC((IDExpression)var1.get(1), var2.kS[1], var4 + 1);
               this.ld = var11.ld;
               if (var18) {
                  var20 = var11.pC((IDExpression)var1.get(0), var2.kS[0], var4 + 1);
                  this.ld = var11.ld;
               }
            } else {
               var18 = var11.pC((IDExpression)var1.get(0), var2.kS[0], var4 + 1);
               this.ld = var11.ld;
               if (var18) {
                  var20 = var11.pC((IDExpression)var1.get(1), var2.kS[1], var4 + 1);
                  this.ld = var11.ld;
               }
            }

            if (!var20) {
               var11 = this.pC(this);
               if (var15 < var13) {
                  var18 = var11.pC((IDExpression)var1.get(0), var2.kS[1], var4 + 1);
                  this.ld = var11.ld;
                  if (var18) {
                     var20 = var11.pC((IDExpression)var1.get(1), var2.kS[0], var4 + 1);
                     this.ld = var11.ld;
                  }
               } else {
                  var18 = var11.pC((IDExpression)var1.get(1), var2.kS[0], var4 + 1);
                  this.ld = var11.ld;
                  if (var18) {
                     var20 = var11.pC((IDExpression)var1.get(0), var2.kS[1], var4 + 1);
                     this.ld = var11.ld;
                  }
               }
            }

            if (var18 && var20) {
               this.sY.putAll(var11.sY);
               return true;
            }

            return pC("Operand mismatch: " + var1.get(1));
         }
      }

      return null;
   }

   private Boolean pC(IDOperation var1, bsy var2, bsz var3, int var4) {
      if (this.gp && var3.A() && this.A()) {
         List var5 = switch (var3.kS()) {
            case ADD -> this.pC(var1);
            case MUL -> this.A(var1);
            default -> this.kS(var1);
         };
         if (var5 != null && var5.size() > 2 && var5.size() >= var2.kS.length) {
            bsm var6 = this.pC(this);

            for (IDOperation var9 : this.pC(var5, var3)) {
               ArrayList var10 = new ArrayList();
               var10.add(new bsm.Av(var9.getOperand1(), false));
               var10.add(new bsm.Av(var9.getOperand2(), false));
               if (var6.pC(var10, var2.kS, 0, var4)) {
                  this.sY.putAll(var6.sY);
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

   private List pC(List var1, bsz var2) {
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList(var1);
      ArrayList var5 = new ArrayList();
      var5.add(0, (bsm.Av)var4.remove(0));
      IDExpression var6 = this.A(var5, var2);
      IDExpression var7 = this.A(var4, var2);
      var3.add(this.UT.createOperation(null, var2.kS(), var6, var7));

      for (int var8 = 1; var8 < var1.size(); var8++) {
         for (int var9 = var8; var8 == 1 && var9 < var1.size() - 1 || var8 != 1 && var9 < var1.size(); var9++) {
            var4 = new ArrayList(var1);
            var5 = new ArrayList();

            for (int var10 = var9; var10 >= var8; var10--) {
               var5.add(0, (bsm.Av)var4.remove(var10));
            }

            var5.add(0, (bsm.Av)var4.remove(0));
            var6 = this.A(var5, var2);
            var7 = this.A(var4, var2);
            var3.add(this.UT.createOperation(null, var2.kS(), var6, var7));
         }
      }

      return var3;
   }

   private IDExpression A(List var1, bsz var2) {
      if (var1.isEmpty()) {
         return null;
      } else {
         bsm.Av var4 = (bsm.Av)var1.get(0);
         Object var3;
         if (var4.A) {
            var3 = this.UT.createOperation(null, var2.kS(), var4.pC.spawn(0L), var4.pC);
         } else {
            var3 = var4.pC;
         }

         for (int var5 = 1; var5 < var1.size(); var5++) {
            bsm.Av var6 = (bsm.Av)var1.get(var5);
            if (var6.A) {
               if (var2.kS() == JavaOperatorType.ADD) {
                  var3 = this.UT.createOperation(null, JavaOperatorType.SUB, (IDExpression)var3, var6.pC);
               } else {
                  var3 = this.UT.createOperation(null, var2.kS(), (IDExpression)var3, this.UT.createOperation(null, var2.kS(), var6.pC.spawn(0L), var6.pC));
               }
            } else {
               var3 = this.UT.createOperation(null, var2.kS(), (IDExpression)var3, var6.pC);
            }
         }

         return (IDExpression)var3;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("IR Matcher: '%s' [leafmap: %s] [fork: %d]", this.E, this.sY, this.ld);
   }

   static class Av {
      IDExpression pC;
      boolean A;

      Av(IDExpression var1) {
         this.pC = var1;
      }

      Av(IDExpression var1, boolean var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public String toString() {
         return this.A ? "-" + this.pC : this.pC.toString();
      }
   }
}
