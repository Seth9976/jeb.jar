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

public class cok {
   private static final ILogger RF = GlobalLog.getLogger(cok.class);
   final abg q;
   private final ITypeManager xK;
   private INativeType Dw;
   private INativeType Uv;
   private INativeType oW;
   private INativeType gO;

   public cok(abg var1) {
      this.q = var1;
      this.xK = var1.RF();
      this.RF();
   }

   private void RF() {
      this.Uv = coi.q(this.xK, this.q());
      this.Dw = conn.q(this.xK, this.q());
      this.oW = coh.q(this.xK, this.q());
      com.q(this.xK);
      this.gO = cog.q(this.xK, this.q());
   }

   public cok.CU q(long var1, boolean var3) {
      try {
         coi var5 = coi.q(var1, this);
         if (var5 == null) {
            return null;
         } else {
            long var6 = this.q(var5.RF());
            conn var8 = conn.q(var6, this);
            if (var8 == null) {
               return null;
            } else {
               if (var3) {
                  this.q(var5, var8, true);
               }

               cok.eo var9 = this.q(var5.xK(), var8, var3);
               return new cok.CU(var5, var8, var9);
            }
         }
      } catch (MemoryException var10) {
         return null;
      }
   }

   private cok.eo q(int var1, conn var2, boolean var3) {
      try {
         long var5 = this.q(var1);
         coh var7 = coh.q(var5, this);
         if (var7 == null) {
            return null;
         } else {
            cof var8 = cof.q(var7.Uv(), var7.Dw(), this);
            ArrayList var9 = new ArrayList();

            for (Long var11 : var8.RF()) {
               cog var12 = cog.q(var11, this);
               if (var12 == null) {
                  return null;
               }

               var9.add(var12);
            }

            cok.eo var4 = new cok.eo(var7, var9, var8);
            if (var3) {
               this.q(var4, var2, true);

               for (cog var17 : var9) {
                  if (var1 != var17.gO()) {
                     long var18 = this.q(var17.gO());
                     INativeContinuousItem var14 = this.q.getNativeItemAt(var18);
                     if (var14 == null || !(var14 instanceof INativeDataItem) || ((INativeDataItem)var14).getType() != this.oW) {
                        this.q(var17.gO(), var2, var3);
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

   public cnr q(Map var1) {
      cnr var2 = new cnr(this.q);

      for (cok.CU var4 : var1.values()) {
         var2.q(var4.q());
         List var5 = var4.xK.RF;
         if (!var5.isEmpty()) {
            if (this.q(((cog)var5.get(0)).Dw()) != var4.RF.q()) {
               return null;
            }

            if (((cog)var5.get(0)).oW() != 0) {
               String var6 = ((cog)var5.get(0)).Uv().xK();
               String var7 = var6;
               boolean var8 = true;

               for (int var9 = 1; var9 < var5.size(); var9++) {
                  cog var10 = (cog)var5.get(var9);
                  String var11 = ((cog)var5.get(var9)).Uv().xK();
                  var2.q(var8 ? var7 : var6, var11, var10.RF(), var10.q());
                  var8 = ((cog)var5.get(var9)).oW() != 0;
                  var7 = var11;
               }
            }
         }
      }

      return var2;
   }

   private void q(coi var1, conn var2, boolean var3) {
      this.RF(var1, var2, var3);
      this.q(var2, var3);
   }

   private void q(cok.eo var1, conn var2, boolean var3) {
      this.q(var1.q, var2, var3);
      this.q(var1.RF, var3);
      this.q(var1.xK, var2, var3);
   }

   private void RF(coi var1, conn var2, boolean var3) {
      if (this.q(var1.q()) && var3) {
         String var4 = "??_R4" + var2.xK().substring(4) + "6B@";
         this.q.q(var1.q(), this.Uv, var4, true, true);
      } else {
         this.q.setDataAt(var1.q(), this.Uv, null);
      }
   }

   private void q(conn var1, boolean var2) {
      if (this.q(var1.q()) && var2) {
         String var3 = "??_R0" + var1.xK().substring(1) + "@8";
         this.q.q(var1.q(), this.Dw, var3, true, true);
      } else {
         this.q.setDataAt(var1.q(), this.Dw, null);
      }

      this.q.getCodeModel().getCommentManager().setComment(var1.RF(), "name");
   }

   private void q(coh var1, conn var2, boolean var3) {
      if (this.q(var1.xK()) && var3) {
         String var4 = "??_R3" + var2.xK().substring(4) + "8";
         this.q.q(var1.xK(), this.oW, var4, true, true);
      } else {
         this.q.setDataAt(var1.xK(), this.oW, null);
      }
   }

   private void q(List var1, boolean var2) {
      for (cog var4 : var1) {
         if (this.q(var4.xK()) && var2) {
            String var5 = "??_R1A@?0A@A@" + var4.Uv().xK().substring(4) + "8";
            this.q.q(var4.xK(), this.gO, var5, true, true);
         } else {
            this.q.setDataAt(var4.xK(), this.gO, null);
         }

         this.q(var4.Uv(), var2);
      }
   }

   private void q(cof var1, conn var2, boolean var3) {
      INativeType var4 = var1.q(this.xK, this.q());
      if (this.q(var1.q()) && var3) {
         String var5 = "??_R2" + var2.xK().substring(4) + "8";
         this.q.q(var1.q(), var4, var5, true, true);
      } else {
         this.q.setDataAt(var1.q(), var4, null);
      }
   }

   protected boolean q(long var1) {
      String var3 = this.q.getCodeModel().getLabelManager().getLabel(var1, 0L, AutoLabelPolicy.OFF);
      return var3 == null || var3.startsWith("ptr_") || ((Nx)this.q.getCodeModel().getLabelManager()).q(var3);
   }

   long q(int var1) {
      long var2 = var1 & 4294967295L;
      return this.q() ? var2 + this.q.getVirtualImageBase() : var2;
   }

   boolean q() {
      return this.q.getProcessor().getDefaultMode() == 64;
   }

   public static class CU {
      final coi q;
      final conn RF;
      final cok.eo xK;

      public CU(coi var1, conn var2, cok.eo var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public String q() {
         return this.RF.xK();
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append(this.RF.xK());
         return var1.toString();
      }
   }

   public class eo {
      final coh q;
      final List RF;
      final cof xK;

      public eo(coh var2, List var3, cof var4) {
         this.q = var2;
         this.RF = var3;
         this.xK = var4;
      }
   }
}
