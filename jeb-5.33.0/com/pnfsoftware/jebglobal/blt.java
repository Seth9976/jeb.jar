package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCatchBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMonitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSynchronizedBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import java.util.ArrayList;
import java.util.List;

public class blt extends AbstractJBlockOptimizer {
   public blt() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size() - 1) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaMonitor && ((IJavaMonitor)var5).isEnter()) {
            IJavaExpression var6 = ((IJavaMonitor)var5).getLock();
            if (var6 != null) {
               IJavaCatchBlock var7 = JUtil.isTryCatchall(var1, var4 + 1);
               if (var7 != null) {
                  IJavaBlock var8 = ((IJavaTry)var1.get(var4 + 1)).getTryBody();
                  if (this.pC(var1, var4, var6, var8, var7)) {
                     var3++;
                     continue;
                  }

                  if (this.A(var1, var4, var6, var8, var7)) {
                     var3++;
                     continue;
                  }

                  if (this.kS(var1, var4, var6, var8, var7)) {
                     var3++;
                     continue;
                  }
               }
            }
         }

         var4++;
      }

      return var3;
   }

   private boolean pC(IJavaBlock var1, int var2, IJavaExpression var3, IJavaBlock var4, IJavaCatchBlock var5) {
      if (var4.isEmpty()) {
         return false;
      } else {
         IJavaExpression var6 = JUtil.getMonitorExit(var4.getLast());
         if (var6 != var3) {
            return false;
         } else {
            boolean var7 = false;
            IJavaLabel var8 = null;
            if (var2 + 2 < var1.size()
               && var1.get(var2 + 2) instanceof IJavaThrow
               && var5.getBlock().size() == 1
               && var5.getBlock().get(0) instanceof IJavaGoto) {
               var8 = JUtil.getGotoLabel(var5.getBlock().get(0));
               if (var8 != null && var4.size() >= 4) {
                  int var10 = var4.size() - 4;
                  if (JUtil.isMonitorExit(var4.get(var10), var3)) {
                     boolean var11 = false;
                     IJavaStatement var12 = var4.get(var10 + 1);
                     if (var12 instanceof IJavaReturn) {
                        var11 = true;
                     } else if (var12 instanceof IJavaGoto) {
                        IJavaLabel var9 = ((IJavaGoto)var12).getLabel();
                        var11 = var2 + 3 < var1.size() && var1.get(var2 + 3) == var9;
                     }

                     if (var11 && var4.get(var10 + 2) == var8 && JUtil.isMonitorExit(var4.get(var10 + 3), var3)) {
                        var7 = true;
                     }
                  }
               }
            }

            if (var7) {
               List var14 = this.m.toFlatList();
               if (JUtil.getGotos(var8, var14).size() != 1) {
                  return false;
               } else {
                  IJavaBlock var15 = this.jctx.createBlock();
                  int var16 = var4.size() - 4;
                  var4.remove(var16);
                  var4.remove(var16 + 1);
                  var4.remove(var16 + 1);
                  var15.insertAll(0, var4);
                  IJavaStatement var17 = var1.get(var2);
                  IJavaSynchronizedBlock var13 = this.jctx.createSynchronizedBlock(var3, var15);
                  var13.setPhysicalOffset(var17.getPhysicalOffset());
                  var1.remove(var2);
                  var1.remove(var2);
                  var1.remove(var2);
                  var1.insert(var2, var13);
                  return true;
               }
            } else {
               return false;
            }
         }
      }
   }

   private boolean A(IJavaBlock var1, int var2, IJavaExpression var3, IJavaBlock var4, IJavaCatchBlock var5) {
      boolean var6 = false;
      if (var5.getBlock().size() == 1 && var5.getBlock().get(0) instanceof IJavaGoto) {
         IJavaLabel var7 = JUtil.getGotoLabel(var5.getBlock().get(0));
         int var8 = 1;
         int var9 = var2 + 1;
         ArrayList var10 = new ArrayList();
         IJavaBlock var11 = var4;

         while (true) {
            var10.add(var9);
            if (var11.size() >= 4) {
               int var12 = var11.size() - 4;
               if (JUtil.isMonitorExit(var11.get(var12), var3)) {
                  boolean var13 = false;
                  IJavaStatement var14 = var11.get(var12 + 1);
                  if (var14 instanceof IJavaReturn) {
                     var13 = true;
                  } else if (var14 instanceof IJavaGoto) {
                     var13 = true;
                  }

                  if (var13 && var11.get(var12 + 2) == var7 && JUtil.isMonitorExit(var11.get(var12 + 3), var3)) {
                     var6 = true;
                  }
               }
            }

            if (var6) {
               if (var6) {
                  int var19 = (Integer)var10.get(var10.size() - 1);
                  IJavaStatement var21 = var1.get(var19 + 1);
                  if (!(var21 instanceof IJavaThrow)) {
                     return false;
                  }

                  List var22 = this.m.toFlatList();
                  if (JUtil.getGotos(var7, var22).size() != var8) {
                     return false;
                  }

                  IJavaStatement var15 = var1.get(var2);
                  IJavaBlock var16 = this.jctx.createBlock();
                  var1.remove(var19 + 1);

                  while (var19 >= var2) {
                     if (var10.contains(var19)) {
                        var16.insertAll(0, ((IJavaTry)var1.get(var19)).getTryBody());
                     } else if (var19 > var2) {
                        var16.insert(0, var1.get(var19));
                     }

                     var1.remove(var19);
                     var19--;
                  }

                  IJavaSynchronizedBlock var17 = this.jctx.createSynchronizedBlock(var3, var16);
                  var17.setPhysicalOffset(var15.getPhysicalOffset());
                  var1.insert(var2, var17);
                  JUtil.removeStatementsDeep(var16, new blu(this, var3));
                  return true;
               }

               return false;
            }

            var11 = null;

            for (int var18 = Math.min(++var9 + 10, var1.size()); var9 < var18; var9++) {
               IJavaCatchBlock var20 = JUtil.isTryCatchall(var1, var9);
               if (var20 != null && var20.getBlock().size() == 1 && JUtil.isGotoTo(var20.getBlock().get(0), var7)) {
                  var11 = ((IJavaTry)var1.get(var9)).getTryBody();
                  break;
               }
            }

            if (var11 == null) {
               return false;
            }

            var8++;
         }
      } else {
         return false;
      }
   }

   private boolean kS(IJavaBlock var1, int var2, IJavaExpression var3, IJavaBlock var4, IJavaCatchBlock var5) {
      boolean var6 = false;
      if (var5.getBlock().size() == 2 && JUtil.isMonitorExit(var5.getBlock().get(0), var3) && JUtil.isThrow(var5.getBlock().get(1), var5.getIdentifier())) {
         var6 = true;
      }

      if (var6) {
         IJavaBlock var14 = this.jctx.createBlock();
         IJavaStatement var17 = var1.remove(var2);
         var14.insertAll(0, ((IJavaTry)var1.remove(var2)).getTryBody());
         IJavaSynchronizedBlock var19 = this.jctx.createSynchronizedBlock(var3, var14);
         var19.setPhysicalOffset(var17.getPhysicalOffset());
         var1.insert(var2, var19);
         return true;
      } else {
         if (var5.getBlock().size() == 1 && var5.getBlock().get(0) instanceof IJavaGoto) {
            IJavaLabel var7 = ((IJavaGoto)var5.getBlock().get(0)).getLabel();
            if (var4.size() >= 3) {
               int var8 = var4.size() - 3;
               if (var4.get(var8) == var7 && JUtil.isMonitorExit(var4.get(var8 + 1), var3) && JUtil.isThrow(var4.get(var8 + 2), var5.getIdentifier())) {
                  var6 = true;
               }
            }
         }

         if (var6) {
            IJavaBlock var13 = this.jctx.createBlock();
            IJavaStatement var16 = var1.remove(var2);
            var4.remove(var4.size() - 1);
            var4.remove(var4.size() - 1);
            var13.insertAll(0, ((IJavaTry)var1.remove(var2)).getTryBody());
            IJavaSynchronizedBlock var18 = this.jctx.createSynchronizedBlock(var3, var13);
            var18.setPhysicalOffset(var16.getPhysicalOffset());
            var1.insert(var2, var18);
            return true;
         } else {
            if (var5.getBlock().size() == 1 && var5.getBlock().get(0) instanceof IJavaGoto) {
               IJavaLabel var11 = ((IJavaGoto)var5.getBlock().get(0)).getLabel();
               if (var4.size() >= 2 && var1.size() > var2 + 2) {
                  int var15 = var4.size() - 2;
                  if (var4.get(var15) == var11 && JUtil.isMonitorExit(var4.get(var15 + 1), var3) && JUtil.isThrow(var1.get(var2 + 2), var5.getIdentifier())) {
                     var6 = true;
                  }
               }
            }

            if (var6) {
               IJavaBlock var12 = this.jctx.createBlock();
               var1.remove(var2 + 2);
               IJavaStatement var9 = var1.remove(var2);
               var4.remove(var4.size() - 1);
               var12.insertAll(0, ((IJavaTry)var1.remove(var2)).getTryBody());
               IJavaSynchronizedBlock var10 = this.jctx.createSynchronizedBlock(var3, var12);
               var10.setPhysicalOffset(var9.getPhysicalOffset());
               var1.insert(var2, var10);
               return true;
            } else {
               return false;
            }
         }
      }
   }
}
