package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Ser
public class Js implements iD {
   private static final ILogger nf = GlobalLog.getLogger(Js.class);
   @SerId(1)
   int q;
   @SerId(2)
   Integer RF;
   @SerId(3)
   int xK;
   @SerId(4)
   boolean Dw;
   @SerId(5)
   tx Uv;
   @SerId(6)
   tx oW;
   @SerId(7)
   long gO;

   public Js(int var1, Integer var2, int var3, boolean var4, tx var5, tx var6) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
      this.oW = var6;
   }

   public Js(int var1, Integer var2, int var3, boolean var4, tx var5) {
      this(var1, var2, var3, var4, var5, null);
   }

   public Js(Js var1, bR var2, long var3) {
      this.q = var1.q;
      this.RF = var1.RF;
      this.xK = var1.xK;
      this.Dw = var1.Dw;
      this.gO = var3;
      this.Uv = var1.Uv.q(var2, var3);
      this.oW = var1.oW.q(var2, var3);
   }

   public static iD q(INativeCodeAnalyzer var0, bR var1, long var2, int var4) {
      if (var0.getProcessor().getMode() != var4) {
         int var5 = var0.getProcessor().getMode();

         Object var7;
         try {
            var0.getProcessor().setMode(var4);
            return q(var0, var1, var2);
         } catch (ProcessorException var17) {
            nf.catchingSilent(var17);
            var7 = null;
         } finally {
            try {
               var0.getProcessor().setMode(var5);
            } catch (ProcessorException var16) {
               nf.catchingSilent(var16);
            }
         }

         return (iD)var7;
      } else {
         return q(var0, var1, var2);
      }
   }

   static iD q(INativeCodeAnalyzer var0, bR var1, long var2) {
      fA var6 = OC.q(var0, var2);
      if (var6 == null) {
         return null;
      } else if (var6.Dw().q().startsWith("LDR") && var6.RF().length == 2) {
         fV var7 = PG.q(var6);
         if (var7 != null && var7.nf()) {
            CW var8 = var6.RF()[0];
            Integer var9 = q(var2, var0.getProcessor().getMode(), var6.RF()[1]);
            if (var9 == null) {
               return null;
            } else {
               long var4 = var2 + var6.getSize();
               fA var10 = OC.q(var0, var4);
               if (var10 == null) {
                  return null;
               } else if (!var10.Dw().q().equals("CMP")) {
                  return null;
               } else {
                  CW var11;
                  if (var8.equals(var10.RF()[1])) {
                     var11 = var10.RF()[0];
                  } else {
                     if (!var8.equals(var10.RF()[0])) {
                        return null;
                     }

                     var11 = var10.RF()[1];
                  }

                  int var12 = var7.Uv() / 8;
                  fA var13 = var10;
                  ArrayList var14 = new ArrayList();

                  while (!var13.q().isPCUpdated()) {
                     var4 += var13.getSize();
                     var13 = OC.q(var0, var4);
                     if (var13 == null) {
                        return null;
                     }

                     var14.add(var13);
                  }

                  Js.eo var15 = new Js.eo(new Js.nI(var11, var8));
                  tx var16 = new tx(var1.getGlobalContext());
                  return q(var1, var14, var4, var15, var12, var9, var16, true);
               }
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static iD q(bR var0, List var1, long var2, Js.eo var4, int var5, int var6, tx var7, boolean var8) {
      long var9 = var2;
      Boolean var11 = null;
      Ia.nI var12 = new Ia.nI();
      var12.q(var0.getProgramCounter(), null);
      var12.q = var0.oW();

      for (int var13 = var1.size() - 1; var13 >= 0; var9 -= var13 >= 0 ? ((fA)var1.get(var13)).getSize() : 0L) {
         if (var12.q.equals(var0.oW()) && var13 < var1.size() - 1) {
            var12.q = EUtil.imm(((fA)var1.get(var1.size() - 1)).getProcessorMode() == 16 ? 1L : 0L, 1);
         }

         label142: {
            fA var14 = (fA)var1.get(var13);
            if (var14.Uv().gO()) {
               int var15 = var14.Uv().RF();
               if (var11 == null) {
                  var11 = OC.xK(var15) || OC.Dw(var15);
                  var4.nf = var11;
               }

               if ((!var4.nf || !OC.Dw(var15)) && (var4.nf || !OC.q(var15))) {
                  if ((!var4.nf || !OC.RF(var15)) && (var4.nf || !OC.Uv(var15)) && q(var0, var14, var12)) {
                     return null;
                  }

                  if (!var8) {
                     break label142;
                  }
               } else if (var8) {
                  break label142;
               }
            }

            if (!var0.q(var14, var9, var12)) {
               return null;
            }

            if (!var12.xK()) {
               break;
            }

            var12.q();
         }

         var13--;
      }

      if (!var12.xK()) {
         return null;
      } else {
         if (var12.q.equals(var0.oW())) {
            var12.q = EUtil.imm(((fA)var1.get(var1.size() - 1)).getProcessorMode() == 16 ? 1L : 0L, 1);
         }

         boolean var16 = var7.q(var0.RF(var12.q()), var12.q, var0.xK());
         if (!var16) {
            return null;
         } else {
            if (var8) {
               if (var4.gO() == null || !var0.xK(null, var4.gO(), -1L).equals(var7.xK)) {
                  return null;
               }
            } else if (var4.gO() == null || !var0.xK(null, var4.nf(), -1L).equals(var7.xK)) {
               return null;
            }

            if (var11 != null) {
               if (var8) {
                  tx var17 = new tx(var0.getGlobalContext());
                  iD var18 = q(var0, var1, var2, var4, var5, var6, var17, false);
                  if (var18 != null) {
                     return new Js(var5, var6, var4.oW, var11, var7, var17);
                  }
               }

               return new Js(var5, var6, var4.oW, var11, var7);
            } else {
               return null;
            }
         }
      }
   }

   static boolean q(bR var0, fA var1, Ia.nI var2) {
      return tB.q(var0, var1, var2, var0.xK());
   }

   private static void q(fA var0, IEGeneric var1) {
   }

   private static Integer q(long var0, int var2, CW var3) {
      if (var3.getOperandType() == 0) {
         return GC.q(var3, var2) ? 0 : null;
      } else {
         if (var3 instanceof wh var4 && GC.q(var4.RF(), var2)) {
            if (var4.gO() == null) {
               return 0;
            }

            CW var5 = var4.gO();
            if (var5.getOperandType() == 1) {
               return var5.q(var0, var2, null).intValue();
            }
         }

         return null;
      }
   }

   @Override
   public boolean q() {
      return true;
   }

   @Override
   public boolean RF() {
      return true;
   }

   @Override
   public iD q(bR var1, long var2, fA var4, boolean var5) {
      long var6 = var2 + var4.getSize() + (var4.getProcessorMode() == 16 ? 1 : 0);
      return new Js(this, var1, var6);
   }

   @Override
   public BH q(INativeCodeAnalyzer var1, FS var2, bR var3) {
      return new Js.CU(var1.getMemory());
   }

   @SerDisabled
   private class CU implements BH {
      private IVirtualMemory xK;
      int q;

      public CU(IVirtualMemory var2) {
         this.xK = var2;
      }

      @Override
      public boolean q(long var1, long var3, Map var5, Map var6) {
         this.q = VirtualMemoryUtil.readAsLongSafe(this.xK, this.RF(), this.xK()).intValue();
         if (Js.this.Dw) {
            this.q++;
         }

         if (Js.this.oW != null) {
            this.q++;
         }

         return true;
      }

      private tx Dw(int var1) {
         return Js.this.oW != null && var1 + 1 == this.q ? Js.this.oW : Js.this.Uv;
      }

      @Override
      public int q() {
         return this.q;
      }

      @Override
      public long RF() {
         return Js.this.gO + Js.this.RF.intValue();
      }

      @Override
      public int xK() {
         return Js.this.q;
      }

      @Override
      public Long q(int var1) {
         return this.q(0, var1);
      }

      @Override
      public Long q(int var1, int var2) {
         if (var2 >= Js.this.xK && var2 < this.q) {
            if (Js.this.oW != null && var2 + 1 == this.q) {
               var2 = this.q - 1 - (Js.this.Dw ? 1 : 0);
            }

            return this.Dw(var2).q(this.xK, var1, var2);
         } else {
            return null;
         }
      }

      @Override
      public int RF(int var1) {
         return this.Dw(var1).q();
      }

      @Override
      public long xK(int var1) {
         tx var2 = this.Dw(var1);
         return var2.xK(this.xK, var1);
      }

      @Override
      public boolean Dw() {
         return Js.this.Uv.RF();
      }

      @Override
      public int Uv() {
         return 1;
      }

      @Override
      public int RF(int var1, int var2) {
         return this.Dw(var2).q(var1);
      }

      @Override
      public boolean oW() {
         return false;
      }
   }

   public static class eo extends Ia.CU {
      public eo(Js.nI var1) {
         this.q = var1;
      }

      public CW gO() {
         return ((Js.nI)this.q).q;
      }

      public CW nf() {
         return ((Js.nI)this.q).RF;
      }
   }

   static class nI implements Ia.ej {
      CW q;
      CW RF;

      public nI(CW var1, CW var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public int q() {
         return -1;
      }
   }
}
