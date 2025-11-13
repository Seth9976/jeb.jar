package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bpw extends AbstractJOptimizer {
   @Override
   public int perform() {
      return this.m == null ? 0 : this.q(this.m.getBody(), new bsb(this.m));
   }

   private int q(IJavaBlock var1, bsb var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaWhile var6
            && var2.q(var6) instanceof IJavaLabel var8
            && (this.q(var1, var4, var6, var8) || this.RF(var1, var4, var6, var8) || this.xK(var1, var4, var6, var8))) {
            var2 = new bsb(this.m);
            var3++;
         }

         if (var5 instanceof IJavaCompound) {
            for (IJavaBlock var10 : ((IJavaCompound)var5).getBlocks()) {
               var3 += this.q(var10, var2);
            }
         }
      }

      return var3;
   }

   private boolean q(IJavaBlock var1, int var2, IJavaWhile var3, IJavaLabel var4) {
      if (!var3.getPredicate().isLitteralTrue()) {
         return false;
      } else {
         IJavaBlock var5 = var3.getBody();
         if (var5.size() == 0) {
            return false;
         } else {
            boolean var6 = false;

            int var7;
            for (var7 = 0; var7 <= 1 && var7 < var5.size(); var7++) {
               IJavaLabel var8 = JUtil.checkIfGoto(var5.get(var7));
               if (var8 != null && var7 + 2 < var5.size() && JUtil.isGotoTo(var5.get(var7 + 1), var4) && var5.get(var7 + 2) == var8) {
                  var6 = true;
                  break;
               }
            }

            if (var6) {
               if (var7 == 0) {
                  IJavaPredicate var18 = ((IJavaIf)var5.get(0)).getBranchPredicate(0);
                  var3.setPredicate(var18);
                  var5.remove(0);
                  var5.remove(0);
                  return true;
               }

               if (var7 == 1 && var5.get(0) instanceof IJavaLabel) {
                  IJavaLabel var17 = (IJavaLabel)var5.remove(0);
                  var1.insert(var2, var17);
                  IJavaPredicate var23 = ((IJavaIf)var5.get(0)).getBranchPredicate(0);
                  var3.setPredicate(var23);
                  var5.remove(0);
                  var5.remove(0);
                  return true;
               }
            }

            if (JUtil.checkIfGoto(var5.get(0)) == var4) {
               IJavaPredicate var16 = ((IJavaIf)var5.get(0)).getBranchPredicate(0);
               var16.reverse(this.of);
               var3.setPredicate(var16);
               var5.remove(0);
               return true;
            } else {
               if (var5.size() >= 2 && var5.get(0) instanceof IJavaLabel) {
                  IJavaLabel var11 = JUtil.checkIfGoto(var5.get(1));
                  if (this.m.getLabelFactory().checkEquivalence(var11, var4)) {
                     IJavaLabel var22 = (IJavaLabel)var5.remove(0);
                     var1.insert(var2, var22);
                     IJavaPredicate var24 = ((IJavaIf)var5.get(0)).getBranchPredicate(0);
                     var24.reverse(this.of);
                     var3.setPredicate(var24);
                     var5.remove(0);
                     return true;
                  }
               }

               if (var5.size() == 2 && var5.get(0) instanceof IJavaLabel) {
                  IJavaLabel var12 = (IJavaLabel)var5.get(0);
                  if (JUtil.isIfElse(var5.get(1))) {
                     IJavaIf var9 = (IJavaIf)var5.get(1);
                     if (var9.getDefaultBlock().size() == 1
                        && JUtil.isGotoTo(var9.getDefaultBlock().getLast(), var4)
                        && var9.getBranchBody(0).size() >= 1
                        && JUtil.isGotoTo(var9.getBranchBody(0).getLast(), var12)) {
                        var5.remove(0);
                        var1.insert(var2, var12);
                        var3.setPredicate(var9.getBranchPredicate(0));
                        var5.remove(0);
                        var9.getBranchBody(0).removeLast();
                        var5.insertAll(0, var9.getBranchBody(0));
                        return true;
                     }
                  }
               }

               if (var5.size() == 3 && var5.get(0) instanceof IJavaLabel) {
                  IJavaLabel var13 = (IJavaLabel)var5.get(0);
                  if (var5.get(1) instanceof IJavaIf) {
                     IJavaIf var19 = (IJavaIf)var5.get(1);
                     if (var19.size() == 1
                        && var19.getBranchBody(0).size() >= 1
                        && JUtil.isGotoTo(var19.getBranchBody(0).getLast(), var13)
                        && JUtil.isGotoTo(var5.get(2), var4)) {
                        var5.remove(0);
                        var1.insert(var2, var13);
                        var3.setPredicate(var19.getBranchPredicate(0));
                        var5.remove(0);
                        var5.remove(0);
                        var19.getBranchBody(0).removeLast();
                        var5.insertAll(0, var19.getBranchBody(0));
                        return true;
                     }
                  }
               }

               if (var5.size() == 3 && var5.get(0) instanceof IJavaLabel) {
                  IJavaLabel var14 = (IJavaLabel)var5.get(0);
                  if (var5.get(1) instanceof IJavaIf) {
                     IJavaIf var20 = (IJavaIf)var5.get(1);
                     if (var20.size() == 1 && JUtil.isGotoTo(var5.get(2), var4)) {
                        var3.setPredicate(var20.getBranchPredicate(0));
                        var5.remove(0);
                        var1.insert(var2, var14);
                        var5.remove(0);
                        var5.insertAll(0, var20.getBranchBody(0));
                        return true;
                     }
                  }
               }

               if (var5.size() >= 2 && var5.get(0) instanceof IJavaLabel) {
                  IJavaLabel var15 = (IJavaLabel)var5.get(0);
                  if (JUtil.isIfElse(var5.getLast())) {
                     IJavaIf var21 = (IJavaIf)var5.getLast();
                     if (var21.getDefaultBlock().size() == 1
                        && JUtil.isGotoTo(var21.getDefaultBlock().getLast(), var4)
                        && var21.getBranchBody(0).size() >= 1
                        && JUtil.isGotoTo(var21.getBranchBody(0).getLast(), var15)) {
                        var21.getBranchPredicate(0).reverse(this.of);
                        IJavaBlock var10 = var21.getBranchBody(0);
                        var21.setBranchBody(0, var21.getDefaultBlock());
                        var21.setDefaultBlock(null);
                        var10.removeLast();
                        var5.insertAll(var5.size(), var10);
                        return true;
                     }
                  }
               }

               return false;
            }
         }
      }
   }

   private boolean RF(IJavaBlock var1, int var2, IJavaWhile var3, IJavaLabel var4) {
      IJavaBlock var5 = var3.getBody();
      if (var5.size() == 0) {
         return false;
      } else if (var2 >= 1 && var1.get(var2 - 1) instanceof IJavaLabel) {
         if (var5.size() >= 2) {
            int var6 = var5.size() - 2;
            if (var5.get(var6) instanceof IJavaIf) {
               IJavaIf var7 = (IJavaIf)var5.get(var6);
               if (var7.size() == 1 && JUtil.isGotoTo(var5.get(var6 + 1), var4)) {
                  var7.getBranchPredicate(0).reverse(this.of);
                  var5.insertAll(var6 + 1, var7.getBranchBody(0));
                  var7.getBranchBody(0).clear();
                  var7.getBranchBody(0).add(this.jctx.createGoto(var4));
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean xK(IJavaBlock var1, int var2, IJavaWhile var3, IJavaLabel var4) {
      if (!var3.getPredicate().isLitteralTrue()) {
         return false;
      } else {
         IJavaBlock var5 = var3.getBody();
         if (var5.size() == 0) {
            return false;
         } else if (var5.get(0) instanceof IJavaDoWhile && ((IJavaDoWhile)var5.get(0)).getPredicate().isLitteralTrue()) {
            IJavaDoWhile var6 = (IJavaDoWhile)var5.get(0);
            if (var6.getBody().size() < 2) {
               return false;
            } else {
               IJavaLabel var7 = null;
               int var8 = 0;
               if (var6.getBody().get(var8) instanceof IJavaLabel) {
                  var7 = (IJavaLabel)var6.getBody().get(var8);
                  var8++;
               }

               bpw.eo var9 = q(var6.getBody(), var8);
               if (var9 == null) {
                  return false;
               } else if (var8 + 2 != var6.getBody().size()) {
                  return false;
               } else if (!(var5.get(1) instanceof IJavaLabel)) {
                  return false;
               } else {
                  IJavaLabel var10 = (IJavaLabel)var5.get(1);
                  if (var10 != var9.RF) {
                     return false;
                  } else {
                     if (var5.getLast() instanceof IJavaGoto) {
                        IJavaLabel var11 = ((IJavaGoto)var5.getLast()).getLabel();
                        if (var11 != var7) {
                           return false;
                        }
                     }

                     if (var4 != var9.xK) {
                        return false;
                     } else {
                        var3.setPredicate(var9.q);
                        var5.remove(0);
                        if (var7 != null) {
                           var1.insert(var2, var7);
                        }

                        var5.insertAll(0, var9.Dw);
                        return false;
                     }
                  }
               }
            }
         } else {
            return false;
         }
      }
   }

   public static bpw.eo q(IJavaBlock var0, int var1) {
      if (var1 + 2 > var0.size()) {
         return null;
      } else if (!(var0.get(var1) instanceof IJavaIf)) {
         return null;
      } else {
         IJavaIf var2 = (IJavaIf)var0.get(var1);
         if (var2.size() != 1) {
            return null;
         } else {
            bpw.eo var3 = new bpw.eo();
            var3.q = var2.getBranchPredicate(0);
            IJavaBlock var4 = var2.getBranchBody(0);
            if (var4.size() != 0 && var4.getLast() instanceof IJavaGoto) {
               var3.RF = ((IJavaGoto)var4.getLast()).getLabel();
               if (!(var0.get(var1 + 1) instanceof IJavaGoto)) {
                  return null;
               } else {
                  var3.xK = ((IJavaGoto)var0.get(var1 + 1)).getLabel();
                  var3.Dw = var4;
                  return var3;
               }
            } else {
               return null;
            }
         }
      }
   }

   static class eo {
      IJavaPredicate q;
      IJavaLabel RF;
      IJavaLabel xK;
      IJavaBlock Dw;
   }
}
