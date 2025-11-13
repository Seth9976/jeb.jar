package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class cjp {
   private boolean wS;
   private int UT;
   private int E;
   private long sY;
   private int ys;
   private long ld;
   private int gp;
   private long oT;
   List pC = new ArrayList();
   List A = new ArrayList();
   List kS = new ArrayList();

   public static cjp pC(cjz var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.pC.getMemory();
      boolean var9 = var0.pC.getProcessor().getMode() == 64;
      cjp var10 = new cjp();
      var10.wS = var9;
      var10.UT = var6.readInt(var1);
      long var7 = var1 + 4L;
      if (var10.UT != 429065504 && var10.UT != 429065505 && var10.UT != 429065506) {
         return null;
      } else {
         var10.E = var6.readInt(var7);
         var7 += 4L;
         var10.sY = var6.readInt(var7) & 4294967295L;
         if (var10.sY != 0L && var9) {
            var10.sY += var3;
         }

         var7 += 4L;
         var10.ys = var6.readInt(var7);
         var7 += 4L;
         var10.ld = var6.readInt(var7) & 4294967295L;
         if (var10.ld != 0L && var9) {
            var10.ld += var3;
         }

         var7 += 4L;
         var10.gp = var6.readInt(var7);
         var7 += 4L;
         var10.oT = var6.readInt(var7) & 4294967295L;
         if (var10.oT != 0L && var9) {
            var10.oT += var3;
         }

         var7 = var10.sY;

         for (int var11 = 0; var11 < var10.E; var11++) {
            cjy var12 = cjy.pC(var0, var7, var3, var5);
            if (var12 == null) {
               return null;
            }

            var10.pC.add(var12);
            var7 += var12.kS;
         }

         var7 = var10.ld;

         for (int var21 = 0; var21 < var10.ys; var21++) {
            cju var23 = cju.pC(var0, var7, var3, var5);
            if (var23 == null) {
               return null;
            }

            var10.A.add(var23);
            var7 += var23.A;
         }

         var7 = var10.oT;

         for (int var22 = 0; var22 < var10.gp; var22++) {
            cjr var24 = cjr.pC(var0, var7, var3, var5);
            if (var24 == null) {
               return null;
            }

            var10.kS.add(var24);
            var7 += var24.kS;
         }

         if (var5 && var0.sY != null) {
            var0.pC.defineData(var1, var0.sY);
         }

         return var10;
      }
   }

   public void pC(INativeCodeAnalyzer var1) {
      for (cjy var3 : this.pC) {
         if (var3.A != 0L) {
            long var4 = var3.A;
            if (cjo.wS(var1, var4)) {
               cjo.pC(var1, var4);
            }
         }
      }

      for (cju var7 : this.A) {
         for (cjq var5 : var7.kS) {
            if (cjo.wS(var1, var5.A)) {
               cjo.pC(var1, var5.A);
            }
         }
      }
   }

   public aby pC(INativeCodeAnalyzer var1, Map var2) {
      aby var3 = new aby(this.wS ? abq.wS : abq.A);
      int var4 = 0;
      ArrayList var5 = new ArrayList();

      for (cjy var7 : this.pC) {
         ArrayList var8 = new ArrayList();
         if (var7.A != 0L) {
            var8.add(abw.pC(var7.A));
            var5.add(var4);
         } else {
            for (cju var10 : this.A) {
               if (var10.pC == var4) {
                  for (cjq var12 : var10.kS) {
                     if (var12.pC == 0L) {
                        var8.add(abw.pC(var12.A, cjn.A));
                     } else {
                        INativeContinuousItem var13 = var1.getModel().getItemAt(var12.pC);
                        boolean var14 = false;
                        if (var13 != null) {
                           String var15 = var13.getName(true);
                           if (var15.endsWith("`RTTI Type Descriptor'")) {
                              String var16 = Strings.substring(var15, 0, -23);
                              if (var16 != null) {
                                 var8.add(abw.pC(var12.A, new cjn(var16)));
                                 var14 = true;
                              }
                           }
                        }

                        if (!var14) {
                           var8.add(abw.pC(var12.A, cjn.pC));
                        }
                     }
                  }
               }
            }
         }

         if (var8.isEmpty()) {
            var4++;
         } else {
            ArrayList var17 = new ArrayList();
            Couple var18 = (Couple)var2.get(var4);
            if (var18 != null && var18.getFirst() != null && var18.getSecond() != null && !((List)var18.getSecond()).isEmpty()) {
               for (long var21 : (List)var18.getSecond()) {
                  var17.add(abx.pC((Long)var18.getFirst(), var21, var8));
               }

               for (abx var22 : var17) {
                  var3.pC(var22);
               }

               var4++;
            }
         }
      }

      return var3;
   }
}
