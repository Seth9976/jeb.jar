package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;

public class bma extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         int var1 = 0;
         var1 += new bma.Sv().pC(this.m);
         var1 += this.pC(this.m.getBody());
         return var1 + this.A(this.m.getBody());
      }
   }

   int pC(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         if (var4 instanceof IJavaTry && !((IJavaTry)var4).hasFinallyBlock() && this.pC(var1, var3, (IJavaTry)var4)) {
            var2++;
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.pC(var6);
            }
         }
      }

      return var2;
   }

   private boolean pC(IJavaBlock var1, int var2, IJavaTry var3) {
      Assert.a(var1.get(var2) == var3);
      Assert.a(!var3.hasFinallyBlock());
      IJavaBlock var4 = var3.getTryBody();
      if (var4.isEmpty()) {
         return false;
      } else {
         IJavaIdentifier var5 = null;
         boolean[] var6 = new boolean[1];
         int var7 = 0;

         while (var7 < var4.size()) {
            IJavaStatement var8 = var4.get(var7);
            var5 = pC(var8, var6, false);
            if (var5 == null) {
               if (!(var8 instanceof IJavaCompound) && !JUtil.canThrow(var8)) {
                  var7++;
                  continue;
               }

               return false;
            }
            break;
         }

         if (var5 == null) {
            return false;
         } else {
            bma.Av var13 = new bma.Av(var5, var1, var2);
            if (!var13.pC()) {
               return false;
            } else {
               Assert.a(var13.wS.pC == var13.UT.pC);
               Assert.a(var13.wS.A < var13.UT.A);
               if (var13.wS.pC == var4 && var13.UT.A <= var7) {
                  return false;
               } else {
                  IJavaBlock var9 = this.jctx.createBlock();
                  var3.setFinallyBlock(var9);
                  IJavaBlock var10 = var13.wS.pC;
                  var10.remove(var13.UT.A);
                  JUtil.moveStatements(var10, var13.wS.A + 1, var13.UT.A, var9, 0);
                  var4.remove(var7);

                  for (bne var12 : var13.E) {
                     var12.pC.remove(var12.kS);
                  }

                  if (var6[0]) {
                     pC(this.m.getBody(), var5);
                  }

                  return true;
               }
            }
         }
      }
   }

   int A(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         IJavaIdentifier var5 = pC(var4, null, true);
         if (var5 != null && this.pC(var1, var3, var5)) {
            var2++;
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var7 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.A(var7);
            }
         }
      }

      return var2;
   }

   private boolean pC(IJavaBlock var1, int var2, IJavaIdentifier var3) {
      bma.Av var4 = new bma.Av(var3, var1, var2 + 1);
      if (!var4.pC()) {
         return false;
      } else {
         Assert.a(var4.wS.pC == var4.UT.pC);
         Assert.a(var4.wS.A < var4.UT.A);
         if (var1 != var4.sY.pC) {
            return false;
         } else {
            IJavaBlock var5 = this.jctx.createBlock();
            IJavaBlock var6 = this.jctx.createBlock();
            IJavaTry var7 = this.jctx.createTry(var5);
            var7.setFinallyBlock(var6);
            IJavaBlock var8 = var4.wS.pC;
            var8.remove(var4.UT.A);
            JUtil.moveStatements(var8, var4.wS.A + 1, var4.UT.A, var6, 0);
            int var9 = var4.sY.A;
            if (var1 == var8 && var4.sY.A >= var4.UT.A) {
               var9 -= var4.UT.A - var4.wS.A;
            }

            JUtil.moveStatements(var1, var2 + 1, var9, var5, 0);
            var1.remove(var2);
            var1.insert(var2, var7);
            var1.remove(var2 + 1);

            for (bne var11 : var4.E) {
               if (var11.pC == var1) {
                  var11.pC = var5;
               }

               var11.pC.remove(var11.kS);
            }

            return true;
         }
      }
   }

   private static IJavaIdentifier pC(IJavaStatement var0, boolean[] var1, boolean var2) {
      if (var0 instanceof IJavaAssignment && ((IJavaAssignment)var0).isSimpleAssignment()) {
         IJavaExpression var3 = ((IJavaAssignment)var0).getRight();
         if (var3 instanceof IJavaCall && ((IJavaCall)var3).getMethodSignature().equals("Ljeb/synthetic/FIN;->finallyOpen$NT()I")) {
            IJavaLeftExpression var4 = ((IJavaAssignment)var0).getLeft();
            if (var4 instanceof IJavaIdentifier) {
               if (var2) {
                  return null;
               }

               if (var1 != null) {
                  var1[0] = true;
               }

               return (IJavaIdentifier)var4;
            }

            if (var4 instanceof IJavaDefinition) {
               if (var1 != null) {
                  var1[0] = false;
               }

               return ((IJavaDefinition)var4).getIdentifier();
            }
         }
      }

      return null;
   }

   private static IJavaExpression pC(IJavaStatement var0) {
      return var0 instanceof IJavaCall && ((IJavaCall)var0).getMethodSignature().equals("Ljeb/synthetic/FIN;->finallyCodeBegin$NT(I)V")
         ? ((IJavaCall)var0).getArgument(0)
         : null;
   }

   private static boolean pC(IJavaStatement var0, IJavaIdentifier var1) {
      return pC(var0) == var1;
   }

   private static IJavaExpression A(IJavaStatement var0) {
      return var0 instanceof IJavaCall && ((IJavaCall)var0).getMethodSignature().equals("Ljeb/synthetic/FIN;->finallyCodeEnd$NT(I)V")
         ? ((IJavaCall)var0).getArgument(0)
         : null;
   }

   private static boolean A(IJavaStatement var0, IJavaIdentifier var1) {
      return A(var0) == var1;
   }

   private static IJavaExpression kS(IJavaStatement var0) {
      return var0 instanceof IJavaCall && ((IJavaCall)var0).getMethodSignature().equals("Ljeb/synthetic/FIN;->finallyExec$NT(I)V")
         ? ((IJavaCall)var0).getArgument(0)
         : null;
   }

   private static boolean kS(IJavaStatement var0, IJavaIdentifier var1) {
      return kS(var0) == var1;
   }

   public static boolean pC(IJavaBlock var0, IJavaIdentifier var1) {
      for (int var2 = 0; var2 < var0.size(); var2++) {
         IJavaStatement var3 = var0.get(var2);
         if (var3 instanceof IJavaDefinition && ((IJavaDefinition)var3).getIdentifier() == var1) {
            var0.remove(var2);
            return true;
         }

         if (var3 instanceof IJavaCompound) {
            for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
               if (pC(var5, var1)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   static class Av {
      IJavaIdentifier pC;
      IJavaBlock A;
      int kS;
      bne wS;
      bne UT;
      List E = new ArrayList();
      bne sY;

      Av(IJavaIdentifier var1, IJavaBlock var2, int var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      boolean pC() {
         if (!this.pC(this.A, this.kS)) {
            return false;
         } else if (this.E.isEmpty()) {
            return false;
         } else if (this.wS != null && this.UT != null && this.wS.pC == this.UT.pC && this.wS.A < this.UT.A) {
            for (bne var2 : this.E) {
               if (var2.pC == this.A && (this.sY == null || var2.A > this.sY.A)) {
                  this.sY = var2;
               }
            }

            if (this.sY == null) {
               this.sY = (bne)this.E.get(this.E.size() - 1);
            }

            return this.sY != null;
         } else {
            return false;
         }
      }

      private boolean pC(IJavaBlock var1, int var2) {
         while (var2 < var1.size()) {
            IJavaStatement var3 = var1.get(var2);
            if (bma.pC(var3, this.pC)) {
               if (this.wS != null) {
                  return false;
               }

               this.wS = new bne(var1, var2, var3);
               this.E.add(new bne(var1, var2, var3));
            } else if (bma.A(var3, this.pC)) {
               if (this.UT != null || this.wS == null || this.wS.pC != var1) {
                  return false;
               }

               this.UT = new bne(var1, var2, var3);
            } else if (bma.kS(var3, this.pC)) {
               this.E.add(new bne(var1, var2, var3));
            }

            if (var3 instanceof IJavaCompound) {
               for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
                  if (!this.pC(var5, 0)) {
                     return false;
                  }
               }
            }

            var2++;
         }

         return true;
      }
   }

   static class Sv {
      private List pC;
      private List A;

      int pC(IJavaMethod var1) {
         boa var2 = new boa(var1);
         List var3 = var2.A();
         boolean var4 = false;

         for (int var5 = 0; var5 < var3.size(); var5++) {
            IJavaStatement var6 = (IJavaStatement)var3.get(var5);
            if (bma.pC(var6) != null) {
               var4 = true;
               break;
            }
         }

         if (!var4) {
            return 0;
         } else {
            this.pC = var3;
            int var7 = this.pC();
            if (var7 > 0) {
               var1.fromFlatList(var3);
            }

            return var7;
         }
      }

      private int pC() {
         int var1 = 0;

         while (this.A()) {
            var1++;
         }

         return var1;
      }

      private boolean A() {
         this.A = new ArrayList(this.pC.size());
         LinkedHashMap var1 = new LinkedHashMap();
         ArrayDeque var2 = new ArrayDeque();

         for (int var3 = 0; var3 < this.pC.size(); var3++) {
            IJavaStatement var4 = (IJavaStatement)this.pC.get(var3);
            this.A.add(new bma.Sv.Sv(var3, var4, var2));
            if (var4 instanceof bno) {
               var2.push(var3);
            } else if (var4 instanceof bnp) {
               var2.pop();
            } else {
               IJavaExpression var5 = bma.pC(var4);
               boolean var6 = true;
               if (var5 == null) {
                  var5 = bma.A(var4);
                  if (var5 == null) {
                     continue;
                  }

                  var6 = false;
               }

               bma.Sv.Av var7 = (bma.Sv.Av)var1.get(var5);
               if (var7 == null) {
                  var7 = new bma.Sv.Av(var5);
                  var1.put(var5, var7);
               }

               if (var6) {
                  var7.A = var3;
               } else {
                  var7.kS = var3;
               }
            }
         }

         for (bma.Sv.Av var18 : var1.values()) {
            if (var18.A >= 0 && var18.kS >= 0 && var18.A < var18.kS) {
               List var19 = ((bma.Sv.Sv)this.A.get(var18.A)).kS;
               List var20 = ((bma.Sv.Sv)this.A.get(var18.kS)).kS;
               if (!var19.equals(var20)) {
                  List var21 = this.pC(var18.A);
                  var21.add(0, var18.A);
                  List var8 = this.pC(var18.kS);
                  var8.add(0, var18.kS);
                  int var9 = -1;
                  int var10 = -1;

                  label79:
                  for (int var12 : var21) {
                     List var13 = ((bma.Sv.Sv)this.A.get(var12)).kS;

                     for (int var15 : var8) {
                        List var16 = ((bma.Sv.Sv)this.A.get(var15)).kS;
                        if (var13.equals(var16)) {
                           var9 = var12;
                           var10 = var15;
                           break label79;
                        }
                     }
                  }

                  if (var9 >= 0) {
                     Assert.a(var10 >= 0);
                     Object[] var10000 = new Object[]{var9, var10};
                     if (var9 < var10) {
                        this.pC(this.pC, var18.A, var9);
                        this.pC(this.pC, var18.kS, var10);
                        return true;
                     }
                  }
               }
            }
         }

         return false;
      }

      private void pC(List var1, int var2, int var3) {
         if (var2 != var3) {
            IJavaStatement var4 = (IJavaStatement)var1.remove(var2);
            if (var3 < var2) {
               var1.add(var3, var4);
            } else {
               var1.add(var3 - 1, var4);
            }
         }
      }

      private List pC(int var1) {
         ArrayList var2 = new ArrayList();

         for (int var3 = var1 - 1; var3 >= 0; var3 -= 2) {
            IJavaStatement var4 = (IJavaStatement)this.pC.get(var3);
            if (!(var4 instanceof bno) || var3 - 1 < 0) {
               break;
            }

            IJavaStatement var5 = (IJavaStatement)this.pC.get(var3 - 1);
            if (!(var5 instanceof boi) || ((boi)var5).A()) {
               break;
            }

            var2.add(var3 - 1);
         }

         int var6 = var1 + 1;

         while (var6 < this.pC.size()) {
            IJavaStatement var7 = (IJavaStatement)this.pC.get(var6);
            if (var7 instanceof boi && !((boi)var7).A() && var6 + 1 < this.pC.size()) {
               IJavaStatement var8 = (IJavaStatement)this.pC.get(var6 + 1);
               if (var8 instanceof bno) {
                  var2.add(var6 + 2);
                  var6 += 2;
                  continue;
               }
            }

            if (!(var7 instanceof bnp) || var6 + 1 >= this.pC.size()) {
               break;
            }

            IJavaStatement var9 = (IJavaStatement)this.pC.get(var6 + 1);
            if (!(var9 instanceof bok) && !(var9 instanceof bnz)) {
               break;
            }

            var2.add(var6 + 2);
            var6 += 2;
         }

         return var2;
      }

      static class Av {
         IJavaExpression pC;
         int A = -1;
         int kS = -1;

         Av(IJavaExpression var1) {
            this.pC = var1;
         }
      }

      static class Sv {
         int pC;
         IJavaStatement A;
         List kS;

         Sv(int var1, IJavaStatement var2, Deque var3) {
            this.pC = var1;
            this.A = var2;
            this.kS = new ArrayList(var3);
         }
      }
   }
}
