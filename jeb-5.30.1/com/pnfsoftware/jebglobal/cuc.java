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

public class cuc {
   private boolean Dw;
   private int Uv;
   private int oW;
   private long gO;
   private int nf;
   private long gP;
   private int za;
   private long lm;
   List q = new ArrayList();
   List RF = new ArrayList();
   List xK = new ArrayList();

   public static cuc q(cum var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.q.getMemory();
      boolean var9 = var0.q.getProcessor().getMode() == 64;
      cuc var10 = new cuc();
      var10.Dw = var9;
      var10.Uv = var6.readInt(var1);
      long var7 = var1 + 4L;
      if (var10.Uv != 429065504 && var10.Uv != 429065505 && var10.Uv != 429065506) {
         return null;
      } else {
         var10.oW = var6.readInt(var7);
         var7 += 4L;
         var10.gO = var6.readInt(var7) & 4294967295L;
         if (var10.gO != 0L && var9) {
            var10.gO += var3;
         }

         var7 += 4L;
         var10.nf = var6.readInt(var7);
         var7 += 4L;
         var10.gP = var6.readInt(var7) & 4294967295L;
         if (var10.gP != 0L && var9) {
            var10.gP += var3;
         }

         var7 += 4L;
         var10.za = var6.readInt(var7);
         var7 += 4L;
         var10.lm = var6.readInt(var7) & 4294967295L;
         if (var10.lm != 0L && var9) {
            var10.lm += var3;
         }

         var7 = var10.gO;

         for (int var11 = 0; var11 < var10.oW; var11++) {
            cul var12 = cul.q(var0, var7, var3, var5);
            if (var12 == null) {
               return null;
            }

            var10.q.add(var12);
            var7 += var12.xK;
         }

         var7 = var10.gP;

         for (int var21 = 0; var21 < var10.nf; var21++) {
            cuh var23 = cuh.q(var0, var7, var3, var5);
            if (var23 == null) {
               return null;
            }

            var10.RF.add(var23);
            var7 += var23.RF;
         }

         var7 = var10.lm;

         for (int var22 = 0; var22 < var10.za; var22++) {
            cue var24 = cue.q(var0, var7, var3, var5);
            if (var24 == null) {
               return null;
            }

            var10.xK.add(var24);
            var7 += var24.xK;
         }

         if (var5 && var0.gO != null) {
            var0.q.defineData(var1, var0.gO);
         }

         return var10;
      }
   }

   public void q(INativeCodeAnalyzer var1) {
      for (cul var3 : this.q) {
         if (var3.RF != 0L) {
            long var4 = var3.RF;
            if (cub.Dw(var1, var4)) {
               cub.q(var1, var4);
            }
         }
      }

      for (cuh var7 : this.RF) {
         for (cud var5 : var7.xK) {
            if (cub.Dw(var1, var5.RF)) {
               cub.q(var1, var5.RF);
            }
         }
      }
   }

   public adq q(INativeCodeAnalyzer var1, Map var2) {
      adq var3 = new adq(this.Dw ? adi.Dw : adi.RF);
      int var4 = 0;
      ArrayList var5 = new ArrayList();

      for (cul var7 : this.q) {
         ArrayList var8 = new ArrayList();
         if (var7.RF != 0L) {
            var8.add(ado.q(var7.RF));
            var5.add(var4);
         } else {
            for (cuh var10 : this.RF) {
               if (var10.q == var4) {
                  for (cud var12 : var10.xK) {
                     if (var12.q == 0L) {
                        var8.add(ado.q(var12.RF, cua.RF));
                     } else {
                        INativeContinuousItem var13 = var1.getModel().getItemAt(var12.q);
                        boolean var14 = false;
                        if (var13 != null) {
                           String var15 = var13.getName(true);
                           if (var15.endsWith("`RTTI Type Descriptor'")) {
                              String var16 = Strings.substring(var15, 0, -23);
                              if (var16 != null) {
                                 var8.add(ado.q(var12.RF, new cua(var16)));
                                 var14 = true;
                              }
                           }
                        }

                        if (!var14) {
                           var8.add(ado.q(var12.RF, cua.q));
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
                  var17.add(adp.q((Long)var18.getFirst(), var21, var8));
               }

               for (adp var22 : var17) {
                  var3.q(var22);
               }

               var4++;
            }
         }
      }

      return var3;
   }
}
