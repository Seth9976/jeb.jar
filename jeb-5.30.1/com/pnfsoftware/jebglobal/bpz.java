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

public class bpz extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         int var1 = 0;
         var1 += new bpz.CU().q(this.m);
         var1 += this.q(this.m.getBody());
         return var1 + this.RF(this.m.getBody());
      }
   }

   int q(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         if (var4 instanceof IJavaTry && !((IJavaTry)var4).hasFinallyBlock() && this.q(var1, var3, (IJavaTry)var4)) {
            var2++;
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.q(var6);
            }
         }
      }

      return var2;
   }

   private boolean q(IJavaBlock var1, int var2, IJavaTry var3) {
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
            var5 = q(var8, var6, false);
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
            bpz.eo var13 = new bpz.eo(var5, var1, var2);
            if (!var13.q()) {
               return false;
            } else {
               Assert.a(var13.Dw.q == var13.Uv.q);
               Assert.a(var13.Dw.RF < var13.Uv.RF);
               if (var13.Dw.q == var4 && var13.Uv.RF <= var7) {
                  return false;
               } else {
                  IJavaBlock var9 = this.jctx.createBlock();
                  var3.setFinallyBlock(var9);
                  IJavaBlock var10 = var13.Dw.q;
                  var10.remove(var13.Uv.RF);
                  JUtil.moveStatements(var10, var13.Dw.RF + 1, var13.Uv.RF, var9, 0);
                  var4.remove(var7);

                  for (brf var12 : var13.oW) {
                     var12.q.remove(var12.xK);
                  }

                  if (var6[0]) {
                     q(this.m.getBody(), var5);
                  }

                  return true;
               }
            }
         }
      }
   }

   int RF(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         IJavaIdentifier var5 = q(var4, null, true);
         if (var5 != null && this.q(var1, var3, var5)) {
            var2++;
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var7 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.RF(var7);
            }
         }
      }

      return var2;
   }

   private boolean q(IJavaBlock var1, int var2, IJavaIdentifier var3) {
      bpz.eo var4 = new bpz.eo(var3, var1, var2 + 1);
      if (!var4.q()) {
         return false;
      } else {
         Assert.a(var4.Dw.q == var4.Uv.q);
         Assert.a(var4.Dw.RF < var4.Uv.RF);
         if (var1 != var4.gO.q) {
            return false;
         } else {
            IJavaBlock var5 = this.jctx.createBlock();
            IJavaBlock var6 = this.jctx.createBlock();
            IJavaTry var7 = this.jctx.createTry(var5);
            var7.setFinallyBlock(var6);
            IJavaBlock var8 = var4.Dw.q;
            var8.remove(var4.Uv.RF);
            JUtil.moveStatements(var8, var4.Dw.RF + 1, var4.Uv.RF, var6, 0);
            int var9 = var4.gO.RF;
            if (var1 == var8 && var4.gO.RF >= var4.Uv.RF) {
               var9 -= var4.Uv.RF - var4.Dw.RF;
            }

            JUtil.moveStatements(var1, var2 + 1, var9, var5, 0);
            var1.remove(var2);
            var1.insert(var2, var7);
            var1.remove(var2 + 1);

            for (brf var11 : var4.oW) {
               if (var11.q == var1) {
                  var11.q = var5;
               }

               var11.q.remove(var11.xK);
            }

            return true;
         }
      }
   }

   private static IJavaIdentifier q(IJavaStatement var0, boolean[] var1, boolean var2) {
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

   private static IJavaExpression q(IJavaStatement var0) {
      return var0 instanceof IJavaCall && ((IJavaCall)var0).getMethodSignature().equals("Ljeb/synthetic/FIN;->finallyCodeBegin$NT(I)V")
         ? ((IJavaCall)var0).getArgument(0)
         : null;
   }

   private static boolean q(IJavaStatement var0, IJavaIdentifier var1) {
      return q(var0) == var1;
   }

   private static IJavaExpression RF(IJavaStatement var0) {
      return var0 instanceof IJavaCall && ((IJavaCall)var0).getMethodSignature().equals("Ljeb/synthetic/FIN;->finallyCodeEnd$NT(I)V")
         ? ((IJavaCall)var0).getArgument(0)
         : null;
   }

   private static boolean RF(IJavaStatement var0, IJavaIdentifier var1) {
      return RF(var0) == var1;
   }

   private static IJavaExpression xK(IJavaStatement var0) {
      return var0 instanceof IJavaCall && ((IJavaCall)var0).getMethodSignature().equals("Ljeb/synthetic/FIN;->finallyExec$NT(I)V")
         ? ((IJavaCall)var0).getArgument(0)
         : null;
   }

   private static boolean xK(IJavaStatement var0, IJavaIdentifier var1) {
      return xK(var0) == var1;
   }

   public static boolean q(IJavaBlock var0, IJavaIdentifier var1) {
      for (int var2 = 0; var2 < var0.size(); var2++) {
         IJavaStatement var3 = var0.get(var2);
         if (var3 instanceof IJavaDefinition && ((IJavaDefinition)var3).getIdentifier() == var1) {
            var0.remove(var2);
            return true;
         }

         if (var3 instanceof IJavaCompound) {
            for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
               if (q(var5, var1)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   static class CU {
      private List q;
      private List RF;

      int q(IJavaMethod var1) {
         bsb var2 = new bsb(var1);
         List var3 = var2.RF();
         boolean var4 = false;

         for (int var5 = 0; var5 < var3.size(); var5++) {
            IJavaStatement var6 = (IJavaStatement)var3.get(var5);
            if (bpz.q(var6) != null) {
               var4 = true;
               break;
            }
         }

         if (!var4) {
            return 0;
         } else {
            this.q = var3;
            int var7 = this.q();
            if (var7 > 0) {
               var1.fromFlatList(var3);
            }

            return var7;
         }
      }

      private int q() {
         int var1 = 0;

         while (this.RF()) {
            var1++;
         }

         return var1;
      }

      private boolean RF() {
         this.RF = new ArrayList(this.q.size());
         LinkedHashMap var1 = new LinkedHashMap();
         ArrayDeque var2 = new ArrayDeque();

         for (int var3 = 0; var3 < this.q.size(); var3++) {
            IJavaStatement var4 = (IJavaStatement)this.q.get(var3);
            this.RF.add(new bpz.CU.CU(var3, var4, var2));
            if (var4 instanceof brp) {
               var2.push(var3);
            } else if (var4 instanceof brq) {
               var2.pop();
            } else {
               IJavaExpression var5 = bpz.q(var4);
               boolean var6 = true;
               if (var5 == null) {
                  var5 = bpz.RF(var4);
                  if (var5 == null) {
                     continue;
                  }

                  var6 = false;
               }

               bpz.CU.eo var7 = (bpz.CU.eo)var1.get(var5);
               if (var7 == null) {
                  var7 = new bpz.CU.eo(var5);
                  var1.put(var5, var7);
               }

               if (var6) {
                  var7.RF = var3;
               } else {
                  var7.xK = var3;
               }
            }
         }

         for (bpz.CU.eo var18 : var1.values()) {
            if (var18.RF >= 0 && var18.xK >= 0 && var18.RF < var18.xK) {
               List var19 = ((bpz.CU.CU)this.RF.get(var18.RF)).xK;
               List var20 = ((bpz.CU.CU)this.RF.get(var18.xK)).xK;
               if (!var19.equals(var20)) {
                  List var21 = this.q(var18.RF);
                  var21.add(0, var18.RF);
                  List var8 = this.q(var18.xK);
                  var8.add(0, var18.xK);
                  int var9 = -1;
                  int var10 = -1;

                  label79:
                  for (int var12 : var21) {
                     List var13 = ((bpz.CU.CU)this.RF.get(var12)).xK;

                     for (int var15 : var8) {
                        List var16 = ((bpz.CU.CU)this.RF.get(var15)).xK;
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
                        this.q(this.q, var18.RF, var9);
                        this.q(this.q, var18.xK, var10);
                        return true;
                     }
                  }
               }
            }
         }

         return false;
      }

      private void q(List var1, int var2, int var3) {
         if (var2 != var3) {
            IJavaStatement var4 = (IJavaStatement)var1.remove(var2);
            if (var3 < var2) {
               var1.add(var3, var4);
            } else {
               var1.add(var3 - 1, var4);
            }
         }
      }

      private List q(int var1) {
         ArrayList var2 = new ArrayList();

         for (int var3 = var1 - 1; var3 >= 0; var3 -= 2) {
            IJavaStatement var4 = (IJavaStatement)this.q.get(var3);
            if (!(var4 instanceof brp) || var3 - 1 < 0) {
               break;
            }

            IJavaStatement var5 = (IJavaStatement)this.q.get(var3 - 1);
            if (!(var5 instanceof bsj) || ((bsj)var5).Dw()) {
               break;
            }

            var2.add(var3 - 1);
         }

         int var6 = var1 + 1;

         while (var6 < this.q.size()) {
            IJavaStatement var7 = (IJavaStatement)this.q.get(var6);
            if (var7 instanceof bsj && !((bsj)var7).Dw() && var6 + 1 < this.q.size()) {
               IJavaStatement var8 = (IJavaStatement)this.q.get(var6 + 1);
               if (var8 instanceof brp) {
                  var2.add(var6 + 2);
                  var6 += 2;
                  continue;
               }
            }

            if (!(var7 instanceof brq) || var6 + 1 >= this.q.size()) {
               break;
            }

            IJavaStatement var9 = (IJavaStatement)this.q.get(var6 + 1);
            if (!(var9 instanceof bsl) && !(var9 instanceof bsa)) {
               break;
            }

            var2.add(var6 + 2);
            var6 += 2;
         }

         return var2;
      }

      static class CU {
         int q;
         IJavaStatement RF;
         List xK;

         CU(int var1, IJavaStatement var2, Deque var3) {
            this.q = var1;
            this.RF = var2;
            this.xK = new ArrayList(var3);
         }
      }

      static class eo {
         IJavaExpression q;
         int RF = -1;
         int xK = -1;

         eo(IJavaExpression var1) {
            this.q = var1;
         }
      }
   }

   static class eo {
      IJavaIdentifier q;
      IJavaBlock RF;
      int xK;
      brf Dw;
      brf Uv;
      List oW = new ArrayList();
      brf gO;

      eo(IJavaIdentifier var1, IJavaBlock var2, int var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      boolean q() {
         if (!this.q(this.RF, this.xK)) {
            return false;
         } else if (this.oW.isEmpty()) {
            return false;
         } else if (this.Dw != null && this.Uv != null && this.Dw.q == this.Uv.q && this.Dw.RF < this.Uv.RF) {
            for (brf var2 : this.oW) {
               if (var2.q == this.RF && (this.gO == null || var2.RF > this.gO.RF)) {
                  this.gO = var2;
               }
            }

            if (this.gO == null) {
               this.gO = (brf)this.oW.get(this.oW.size() - 1);
            }

            return this.gO != null;
         } else {
            return false;
         }
      }

      private boolean q(IJavaBlock var1, int var2) {
         while (var2 < var1.size()) {
            IJavaStatement var3 = var1.get(var2);
            if (bpz.q(var3, this.q)) {
               if (this.Dw != null) {
                  return false;
               }

               this.Dw = new brf(var1, var2, var3);
               this.oW.add(new brf(var1, var2, var3));
            } else if (bpz.RF(var3, this.q)) {
               if (this.Uv != null || this.Dw == null || this.Dw.q != var1) {
                  return false;
               }

               this.Uv = new brf(var1, var2, var3);
            } else if (bpz.xK(var3, this.q)) {
               this.oW.add(new brf(var1, var2, var3));
            }

            if (var3 instanceof IJavaCompound) {
               for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
                  if (!this.q(var5, 0)) {
                     return false;
                  }
               }
            }

            var2++;
         }

         return true;
      }
   }
}
