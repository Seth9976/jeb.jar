package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.TreeMap;
import java.util.Map.Entry;

@SerDisabled
public class atl {
   private static final StructuredLogger pC = aco.pC(atl.class);
   private static final int A = atg.rl.pC();
   private TreeMap kS = new TreeMap();

   public atj pC() {
      TreeMap var1 = new TreeMap();

      for (Entry var3 : this.kS.entrySet()) {
         var1.put((Integer)var3.getKey(), ((atl.Av)var3.getValue()).A);
      }

      atj var4 = new atj();
      var4.pC = var1;
      return var4;
   }

   public atl() {
      this.pC(new atm());
      this.pC(new atn());
      this.pC(new ato());
      this.pC(new atp());
      this.pC(new atq());
      this.pC(new atr());
      this.pC(new ats());
      this.pC(new att());
      this.pC(new atu());
      this.pC(new atv());
      this.pC(new atw());
      this.pC(new atx());
      this.pC(new aty());
      this.pC(new atz());
      this.pC(new aua());
      this.pC(new aub());
      this.pC(new auc());
      this.pC(new aud());
      this.pC(new aue());
      this.pC(new aui());
      this.pC(new auj());
      this.pC(new auk());
   }

   private void pC(ati var1) {
      this.kS.put(var1.pC(), new atl.Av(var1));
   }

   public boolean pC(ach var1, atg var2, boolean var3) {
      return this.pC(var1, var2.pC(), var3);
   }

   public boolean pC(ach var1, int var2, boolean var3) {
      return this.A(var1, var2, var3);
   }

   private boolean A(ach var1, int var2, boolean var3) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         for (atl.Av var5 : this.kS.values()) {
            acj.pC();
            ati var6 = var5.pC;
            if (var6.pC() > var1.sY()) {
               if (var6.pC() <= var2) {
                  atg var7 = atg.pC(var6.pC());
                  NativeDecompilationStage var8 = var7.kS();
                  long var9 = System.nanoTime();
                  pC.iHHH("{ round=%d, depth=%d } Stage: %s (routine: %s)", var1.gp(), var1.ld(), var6.A(), var1.UT());
                  DecompilationStatus var11 = this.pC(var8, var1);
                  if (var11 == DecompilationStatus.COMPLETED) {
                     continue;
                  }

                  if (var11 != DecompilationStatus.IN_PROCESS) {
                     var1.pC(var11);
                     return false;
                  }

                  long var12 = System.nanoTime();
                  boolean var14 = false;

                  try {
                     boolean var15 = this.A();
                     var14 = var6.pC(var1, var15);
                     if (var14 && var15) {
                        atg.pC(var6.pC());
                        var1.getIRContext();
                     }
                  } catch (Exception var27) {
                     pC.debug("Pipeline error: round=%d, depth=%d", var1.gp(), var1.ld());
                     pC.catchingSilent(var27);
                     var1.pC(new acn(var27));
                     var1.pC(DecompilationStatus.COMPLETED);
                     if (var3) {
                        throw var27;
                     }
                  } finally {
                     pC.iL("End of decompilation Stage: %s (routine: %s)", var6.A(), var1.UT());
                     pC.iSeparator();
                     pC.iBreak(2);
                  }

                  if (!var14) {
                     return false;
                  }

                  var1.pC(var6.pC());
                  long var30 = System.nanoTime();
                  var11 = this.A(var8, var1);
                  if (var11 != DecompilationStatus.IN_PROCESS) {
                     var1.pC(var11);
                     return false;
                  }

                  long var17 = System.nanoTime();
                  long var19 = (var17 - var9) / 1000000L;
                  long var21 = (var30 - var12) / 1000000L;
                  long var23 = (var17 - var30) / 1000000L;
                  var5.A.pC += var19;
                  var5.A.A += var21;
                  var5.A.kS += var23;
                  continue;
               }
               break;
            }
         }

         if (var2 < A) {
            var1.pC(DecompilationStatus.IN_PROCESS);
         } else if (var2 == A) {
            var1.pC(DecompilationStatus.COMPLETED);
         }

         var1.pC(null);
         return true;
      }
   }

   private DecompilationStatus pC(NativeDecompilationStage var1, ach var2) {
      return (DecompilationStatus)var2.getDecompiler().getExtensionsManager().executePrePipelineStage(var1, var2).getResult();
   }

   private DecompilationStatus A(NativeDecompilationStage var1, ach var2) {
      return (DecompilationStatus)var2.getDecompiler().getExtensionsManager().executePostPipelineStage(var1, var2).getResult();
   }

   boolean A() {
      return false;
   }

   public static class Av {
      ati pC;
      atk A = new atk();

      Av(ati var1) {
         this.pC = var1;
      }
   }
}
