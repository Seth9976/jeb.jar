package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class chg {
   private static final ILogger A = GlobalLog.getLogger(chg.class);
   final C pC;
   private final ITypeManager kS;
   private INativeType wS;
   private INativeType UT;
   private INativeType E;
   private INativeType sY;

   public chg(C var1) {
      this.pC = var1;
      this.kS = var1.A();
      this.A();
   }

   private void A() {
      this.UT = che.pC(this.kS, this.pC());
      this.wS = chj.pC(this.kS, this.pC());
      this.E = chd.pC(this.kS, this.pC());
      chi.pC(this.kS);
      this.sY = chc.pC(this.kS, this.pC());
   }

   public chg.Sv pC(long var1, boolean var3) {
      try {
         che var5 = che.pC(var1, this);
         if (var5 == null) {
            return null;
         } else {
            long var6 = this.pC(var5.A());
            chj var8 = chj.pC(var6, this);
            if (var8 == null) {
               return null;
            } else {
               if (var3) {
                  this.pC(var5, var8, true);
               }

               chg.Av var9 = this.pC(var5.kS(), var8, var3);
               return new chg.Sv(var5, var8, var9);
            }
         }
      } catch (MemoryException var10) {
         return null;
      }
   }

   private chg.Av pC(int var1, chj var2, boolean var3) {
      try {
         long var5 = this.pC(var1);
         chd var7 = chd.pC(var5, this);
         if (var7 == null) {
            return null;
         } else {
            chb var8 = chb.pC(var7.kS(), var7.A(), this);
            ArrayList var9 = new ArrayList();

            for (Long var11 : var8.A()) {
               chc var12 = chc.pC(var11, this);
               if (var12 == null) {
                  return null;
               }

               var9.add(var12);
            }

            chg.Av var4 = new chg.Av(var7, var9, var8);
            if (var3) {
               this.pC(var4, var2, true);

               for (chc var17 : var9) {
                  if (var1 != var17.sY()) {
                     long var18 = this.pC(var17.sY());
                     INativeContinuousItem var14 = this.pC.getNativeItemAt(var18);
                     if (var14 == null || !(var14 instanceof INativeDataItem) || ((INativeDataItem)var14).getType() != this.E) {
                        this.pC(var17.sY(), var2, var3);
                     }
                  }
               }
            }

            return var4;
         }
      } catch (MemoryException var15) {
         return null;
      }
   }

   public cgn pC(Map var1) {
      cgn var2 = new cgn(this.pC);

      for (chg.Sv var4 : var1.values()) {
         var2.pC(var4.pC());
         List var5 = var4.kS.A;
         if (!var5.isEmpty()) {
            if (this.pC(((chc)var5.get(0)).wS()) != var4.A.pC()) {
               return null;
            }

            if (((chc)var5.get(0)).E() != 0) {
               String var6 = ((chc)var5.get(0)).UT().kS();
               String var7 = var6;
               boolean var8 = true;

               for (int var9 = 1; var9 < var5.size(); var9++) {
                  chc var10 = (chc)var5.get(var9);
                  String var11 = ((chc)var5.get(var9)).UT().kS();
                  var2.pC(var8 ? var7 : var6, var11, var10.A(), var10.pC());
                  var8 = ((chc)var5.get(var9)).E() != 0;
                  var7 = var11;
               }
            }
         }
      }

      return var2;
   }

   private void pC(che var1, chj var2, boolean var3) {
      this.A(var1, var2, var3);
      this.pC(var2, var3);
   }

   private void pC(chg.Av var1, chj var2, boolean var3) {
      this.pC(var1.pC, var2, var3);
      this.pC(var1.A, var3);
      this.pC(var1.kS, var2, var3);
   }

   private void A(che var1, chj var2, boolean var3) {
      if (this.pC(var1.pC()) && var3) {
         String var4 = "??_R4" + var2.kS().substring(4) + "6B@";
         this.pC.pC(var1.pC(), this.UT, var4, true, true);
      } else {
         this.pC.setDataAt(var1.pC(), this.UT, null);
      }
   }

   private void pC(chj var1, boolean var2) {
      if (this.pC(var1.pC()) && var2) {
         String var3 = "??_R0" + var1.kS().substring(1) + "@8";
         this.pC.pC(var1.pC(), this.wS, var3, true, true);
      } else {
         this.pC.setDataAt(var1.pC(), this.wS, null);
      }

      this.pC.getCodeModel().getCommentManager().setComment(var1.A(), "name");
   }

   private void pC(chd var1, chj var2, boolean var3) {
      if (this.pC(var1.pC()) && var3) {
         String var4 = "??_R3" + var2.kS().substring(4) + "8";
         this.pC.pC(var1.pC(), this.E, var4, true, true);
      } else {
         this.pC.setDataAt(var1.pC(), this.E, null);
      }
   }

   private void pC(List var1, boolean var2) {
      for (chc var4 : var1) {
         if (this.pC(var4.kS()) && var2) {
            String var5 = "??_R1A@?0A@A@" + var4.UT().kS().substring(4) + "8";
            this.pC.pC(var4.kS(), this.sY, var5, true, true);
         } else {
            this.pC.setDataAt(var4.kS(), this.sY, null);
         }

         this.pC(var4.UT(), var2);
      }
   }

   private void pC(chb var1, chj var2, boolean var3) {
      INativeType var4 = var1.pC(this.kS, this.pC());
      if (this.pC(var1.pC()) && var3) {
         String var5 = "??_R2" + var2.kS().substring(4) + "8";
         this.pC.pC(var1.pC(), var4, var5, true, true);
      } else {
         this.pC.setDataAt(var1.pC(), var4, null);
      }
   }

   protected boolean pC(long var1) {
      String var3 = this.pC.getCodeModel().getLabelManager().getLabel(var1, 0L, AutoLabelPolicy.OFF);
      return var3 == null || var3.startsWith("ptr_") || ((HM)this.pC.getCodeModel().getLabelManager()).pC(var3);
   }

   long pC(int var1) {
      long var2 = var1 & 4294967295L;
      return this.pC() ? var2 + this.pC.getVirtualImageBase() : var2;
   }

   boolean pC() {
      return this.pC.getProcessor().getDefaultMode() == 64;
   }

   public class Av {
      final chd pC;
      final List A;
      final chb kS;

      public Av(chd var2, List var3, chb var4) {
         this.pC = var2;
         this.A = var3;
         this.kS = var4;
      }
   }

   public static class Sv {
      final che pC;
      final chj A;
      final chg.Av kS;

      public Sv(che var1, chj var2, chg.Av var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      public String pC() {
         return this.A.kS();
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append(this.A.kS());
         return var1.toString();
      }
   }
}
