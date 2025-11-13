package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBreak;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaContinue;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class bqf extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         int var1 = 0;
         Object var2 = this.m.toFlatList();
         HashSet var3 = new HashSet();
         int var4 = 0;

         label190:
         while (true) {
            if (var4++ >= 200) {
               com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("IGF abort"), this.m.getSignature(), this.m.getBody().size(), null, true);
               break;
            }

            bsb var5 = new bsb((List)var2);
            List var6 = var5.xK();
            IJavaLabel var7 = null;

            for (int var8 = 0; var8 < var2.size(); var8++) {
               IJavaStatement var9 = (IJavaStatement)var2.get(var8);
               if (var9 instanceof IJavaGoto) {
                  IJavaLabel var10 = ((IJavaGoto)var9).getLabel();
                  if (!var3.contains(var10)) {
                     var7 = var10;
                     break;
                  }
               }
            }

            if (var7 == null) {
               break;
            }

            ArrayList var25 = new ArrayList();
            int var26 = -1;

            for (int var27 = 0; var27 < var2.size(); var27++) {
               IJavaStatement var11 = (IJavaStatement)var2.get(var27);
               if (var11 == var7) {
                  var26 = var27;
               }

               if (var11 instanceof IJavaGoto && ((IJavaGoto)var11).getLabel() == var7) {
                  var25.add(0, var27);
               }
            }

            if (var26 == -1) {
               break;
            }

            boolean var28 = false;
            List var29 = ((bsb.eo)var6.get(var26)).Dw();

            for (int var13 : var25) {
               List var14 = ((bsb.eo)var6.get(var13)).Dw();
               if (!var14.equals(var29)) {
                  var28 = true;
                  break;
               }
            }

            ArrayList var30 = new ArrayList();
            int var31 = var26;
            int var32 = -1;

            for (int var15 = 0; var31 < var2.size(); var31 = var32) {
               if (var15++ >= 200) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("IGF2 abort"), this.m.getSignature());
                  break label190;
               }

               var32 = var5.Dw(var31);
               if (var32 <= 0) {
                  break;
               }

               int var16 = this.q((List)var2, var32);
               if (var16 == 0) {
                  break;
               }

               if (var16 == 1) {
                  var30.add(var32);
               } else {
                  if (var16 == 2) {
                     var30.add(var32);
                     var32 = var5.Dw(var32);
                     break;
                  }

                  if (var16 != 3) {
                     if (var16 != 4) {
                        throw new RuntimeException();
                     }
                     break;
                  }
               }
            }

            int var33 = var32;
            if (var30.size() >= 1 && var30.size() <= 4) {
               boolean var17 = true;
               if (var28) {
                  for (int var19 : var30) {
                     if (((IJavaStatement)var2.get(var19)).canCauseException()) {
                        var17 = false;
                        break;
                     }
                  }
               }

               if (var17) {
                  if (var32 > 0) {
                     for (int var36 : var25) {
                        IJavaStatement var20 = (IJavaStatement)var2.get((Integer)var30.get(var30.size() - 1));
                        if (var20.equals(var2.get(var36))) {
                           var17 = false;
                           break;
                        }

                        IJavaStatement var21 = (IJavaStatement)var2.set(var36, var20);
                        int var22 = var5.Dw(var36);
                        var2.set(var36, var21);
                        if (var22 != var33) {
                           var17 = false;
                           break;
                        }
                     }
                  }

                  if (var17) {
                     for (IJavaStatement var37 : var2) {
                        if (var37 instanceof IJavaCall && ((IJavaCall)var37).getMethodSignature().startsWith("Ljeb/synthetic/")) {
                           var17 = false;
                           break;
                        }
                     }
                  }

                  if (var17) {
                     ArrayList var38 = new ArrayList((Collection)var2);

                     for (int var40 : var25) {
                        var38.remove(var40);
                        ArrayList var41 = new ArrayList(var30.size());

                        for (int var24 : var30) {
                           var41.add(((IJavaStatement)var2.get(var24)).duplicate());
                        }

                        var38.addAll(var40, var41);
                        var30.size();
                     }

                     var2 = var38;
                     var1++;
                  }
               }
            }

            var3.add(var7);
         }

         if (var1 > 0) {
            this.m.fromFlatList((List)var2);
         }

         return var1;
      }
   }

   private int q(List var1, int var2) {
      IJavaStatement var3 = (IJavaStatement)var1.get(var2);
      if (var3 instanceof IJavaCompound) {
         throw new IllegalArgumentException();
      } else if (var3 instanceof bsq) {
         return 0;
      } else if (var3 instanceof IJavaGoto
         || var3 instanceof IJavaBreak
         || var3 instanceof IJavaContinue
         || var3 instanceof IJavaReturn
         || var3 instanceof IJavaThrow) {
         return 2;
      } else if (var3 instanceof IJavaLabel) {
         return 3;
      } else {
         return JUtil.isDeclarationOrDefinition(var3) ? 4 : 1;
      }
   }
}
