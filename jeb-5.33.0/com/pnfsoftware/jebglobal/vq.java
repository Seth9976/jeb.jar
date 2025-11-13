package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
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
public class vq implements QW {
   private static final ILogger ys = GlobalLog.getLogger(vq.class);
   @SerId(1)
   int pC;
   @SerId(2)
   Integer A;
   @SerId(3)
   int kS;
   @SerId(4)
   boolean wS;
   @SerId(5)
   Ei UT;
   @SerId(6)
   Ei E;
   @SerId(7)
   long sY;

   public vq(int var1, Integer var2, int var3, boolean var4, Ei var5, Ei var6) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
      this.E = var6;
   }

   public vq(int var1, Integer var2, int var3, boolean var4, Ei var5) {
      this(var1, var2, var3, var4, var5, null);
   }

   public vq(vq var1, Sp var2, long var3) {
      this.pC = var1.pC;
      this.A = var1.A;
      this.kS = var1.kS;
      this.wS = var1.wS;
      this.sY = var3;
      this.UT = var1.UT.pC(var2, var3);
      this.E = var1.E.pC(var2, var3);
   }

   public static QW pC(INativeCodeAnalyzer var0, Sp var1, long var2, int var4) {
      if (var0.getProcessor().getMode() != var4) {
         int var5 = var0.getProcessor().getMode();

         Object var7;
         try {
            var0.getProcessor().setMode(var4);
            return pC(var0, var1, var2);
         } catch (ProcessorException var17) {
            ys.catchingSilent(var17);
            var7 = null;
         } finally {
            try {
               var0.getProcessor().setMode(var5);
            } catch (ProcessorException var16) {
               ys.catchingSilent(var16);
            }
         }

         return (QW)var7;
      } else {
         return pC(var0, var1, var2);
      }
   }

   static QW pC(INativeCodeAnalyzer var0, Sp var1, long var2) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = PU.pC(var0, var2);
      if (var6 == null) {
         return null;
      } else if (var6.wS().pC().startsWith("LDR") && var6.A().length == 2) {
         mN var7 = MX.pC(var6);
         if (var7 != null && var7.ys()) {
            Yg var8 = var6.A()[0];
            Integer var9 = pC(var2, var0.getProcessor().getMode(), var6.A()[1]);
            if (var9 == null) {
               return null;
            } else {
               long var4 = var2 + var6.getSize();
               com.pnfsoftware.jeb.corei.parsers.arm.rQ var10 = PU.pC(var0, var4);
               if (var10 == null) {
                  return null;
               } else if (!var10.wS().pC().equals("CMP")) {
                  return null;
               } else {
                  Yg var11;
                  if (var8.equals(var10.A()[1])) {
                     var11 = var10.A()[0];
                  } else {
                     if (!var8.equals(var10.A()[0])) {
                        return null;
                     }

                     var11 = var10.A()[1];
                  }

                  int var12 = var7.UT() / 8;
                  com.pnfsoftware.jeb.corei.parsers.arm.rQ var13 = var10;
                  ArrayList var14 = new ArrayList();

                  while (!var13.pC().isPCUpdated()) {
                     var4 += var13.getSize();
                     var13 = PU.pC(var0, var4);
                     if (var13 == null) {
                        return null;
                     }

                     var14.add(var13);
                  }

                  vq.Av var15 = new vq.Av(new vq.K(var11, var8));
                  Ei var16 = new Ei(var1.getGlobalContext());
                  return pC(var1, var14, var4, var15, var12, var9, var16, true);
               }
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static QW pC(Sp var0, List var1, long var2, vq.Av var4, int var5, int var6, Ei var7, boolean var8) {
      long var9 = var2;
      Boolean var11 = null;
      Ro.K var12 = new Ro.K();
      var12.pC(var0.getProgramCounter(), null);
      var12.pC = var0.E();

      for (int var13 = var1.size() - 1; var13 >= 0; var9 -= var13 >= 0 ? ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(var13)).getSize() : 0L) {
         if (var12.pC.equals(var0.E()) && var13 < var1.size() - 1) {
            var12.pC = EUtil.imm(((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(var1.size() - 1)).getProcessorMode() == 16 ? 1L : 0L, 1);
         }

         label142: {
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var14 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(var13);
            if (var14.UT().sY()) {
               int var15 = var14.UT().A();
               if (var11 == null) {
                  var11 = PU.kS(var15) || PU.wS(var15);
                  var4.ys = var11;
               }

               if ((!var4.ys || !PU.wS(var15)) && (var4.ys || !PU.pC(var15))) {
                  if ((!var4.ys || !PU.A(var15)) && (var4.ys || !PU.UT(var15)) && pC(var0, var14, var12)) {
                     return null;
                  }

                  if (!var8) {
                     break label142;
                  }
               } else if (var8) {
                  break label142;
               }
            }

            if (!var0.pC(var14, var9, var12)) {
               return null;
            }

            if (!var12.kS()) {
               break;
            }

            var12.pC();
         }

         var13--;
      }

      if (!var12.kS()) {
         return null;
      } else {
         if (var12.pC.equals(var0.E())) {
            var12.pC = EUtil.imm(((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(var1.size() - 1)).getProcessorMode() == 16 ? 1L : 0L, 1);
         }

         boolean var16 = var7.pC(var0.A(var12.pC()), var12.pC, var0.kS());
         if (!var16) {
            return null;
         } else {
            if (var8) {
               if (var4.E() == null || !var0.kS(null, var4.E(), -1L).equals(var7.kS)) {
                  return null;
               }
            } else if (var4.E() == null || !var0.kS(null, var4.sY(), -1L).equals(var7.kS)) {
               return null;
            }

            if (var11 != null) {
               if (var8) {
                  Ei var17 = new Ei(var0.getGlobalContext());
                  QW var18 = pC(var0, var1, var2, var4, var5, var6, var17, false);
                  if (var18 != null) {
                     return new vq(var5, var6, var4.E, var11, var7, var17);
                  }
               }

               return new vq(var5, var6, var4.E, var11, var7);
            } else {
               return null;
            }
         }
      }
   }

   static boolean pC(Sp var0, com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Ro.K var2) {
      return Td.pC(var0, var1, var2, var0.kS());
   }

   private static Integer pC(long var0, int var2, Yg var3) {
      if (var3.getOperandType() == 0) {
         return LC.pC(var3, var2) ? 0 : null;
      } else {
         if (var3 instanceof KH var4 && LC.pC(var4.A(), var2)) {
            if (var4.sY() == null) {
               return 0;
            }

            Yg var5 = var4.sY();
            if (var5.getOperandType() == 1) {
               return var5.pC(var0, var2, null).intValue();
            }
         }

         return null;
      }
   }

   @Override
   public boolean pC() {
      return true;
   }

   @Override
   public boolean A() {
      return true;
   }

   @Override
   public QW pC(Sp var1, long var2, com.pnfsoftware.jeb.corei.parsers.arm.rQ var4, boolean var5) {
      long var6 = var2 + var4.getSize() + (var4.getProcessorMode() == 16 ? 1 : 0);
      return new vq(this, var1, var6);
   }

   @Override
   public zx pC(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.arm.Av var2, Sp var3) {
      return new vq.Sv(var1.getMemory());
   }

   public static class Av extends Ro.Sv {
      public Av(vq.K var1) {
         this.pC = var1;
      }

      public Yg E() {
         return ((vq.K)this.pC).pC;
      }

      public Yg sY() {
         return ((vq.K)this.pC).A;
      }
   }

   static class K implements Ro.Ws {
      Yg pC;
      Yg A;

      public K(Yg var1, Yg var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public int pC() {
         return -1;
      }
   }

   @SerDisabled
   private class Sv implements zx {
      private IVirtualMemory kS;
      int pC;

      public Sv(IVirtualMemory var2) {
         this.kS = var2;
      }

      @Override
      public boolean pC(long var1, long var3, Map var5, Map var6) {
         this.pC = VirtualMemoryUtil.readAsLongSafe(this.kS, this.A(), this.kS()).intValue();
         if (vq.this.wS) {
            this.pC++;
         }

         if (vq.this.E != null) {
            this.pC++;
         }

         return true;
      }

      private Ei wS(int var1) {
         return vq.this.E != null && var1 + 1 == this.pC ? vq.this.E : vq.this.UT;
      }

      @Override
      public int pC() {
         return this.pC;
      }

      @Override
      public long A() {
         return vq.this.sY + vq.this.A.intValue();
      }

      @Override
      public int kS() {
         return vq.this.pC;
      }

      @Override
      public Long pC(int var1) {
         return this.pC(0, var1);
      }

      @Override
      public Long pC(int var1, int var2) {
         if (var2 >= vq.this.kS && var2 < this.pC) {
            if (vq.this.E != null && var2 + 1 == this.pC) {
               var2 = this.pC - 1 - (vq.this.wS ? 1 : 0);
            }

            return this.wS(var2).pC(this.kS, var1, var2);
         } else {
            return null;
         }
      }

      @Override
      public int A(int var1) {
         return this.wS(var1).pC();
      }

      @Override
      public long kS(int var1) {
         Ei var2 = this.wS(var1);
         return var2.kS(this.kS, var1);
      }

      @Override
      public boolean wS() {
         return vq.this.UT.A();
      }

      @Override
      public int UT() {
         return 1;
      }

      @Override
      public int A(int var1, int var2) {
         return this.wS(var2).pC(var1);
      }
   }
}
