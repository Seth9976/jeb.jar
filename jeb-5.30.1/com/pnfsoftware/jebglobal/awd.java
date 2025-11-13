package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.util.collect.IntegerSegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

@SerDisabled
public class awd {
   private static final StructuredLogger q = aeg.q(awd.class);
   private static final int RF = avy.KT.q();
   private TreeMap xK = new TreeMap();

   public awb q() {
      TreeMap var1 = new TreeMap();

      for (Entry var3 : this.xK.entrySet()) {
         var1.put((Integer)var3.getKey(), ((awd.eo)var3.getValue()).RF);
      }

      awb var4 = new awb();
      var4.q = var1;
      return var4;
   }

   public awd() {
      this.q(new awg());
      this.q(new awh());
      this.q(new awi());
      this.q(new awj());
      this.q(new awk());
      this.q(new awl());
      this.q(new awm());
      this.q(new awn());
      this.q(new awo());
      this.q(new awp());
      this.q(new awq());
      this.q(new awr());
      this.q(new aws());
      this.q(new awt());
      this.q(new awu());
      this.q(new awv());
      this.q(new aww());
      this.q(new awx());
      this.q(new awy());
      this.q(new axc());
      this.q(new axd());
      this.q(new axe());
   }

   private void q(awa var1) {
      this.xK.put(var1.q(), new awd.eo(var1));
   }

   public List RF() {
      return Collections.unmodifiableList(new ArrayList(this.xK.values()));
   }

   public boolean q(adz var1, avy var2, boolean var3) {
      return this.q(var1, var2.q(), var3);
   }

   public boolean q(adz var1, int var2, boolean var3) {
      return this.RF(var1, var2, var3);
   }

   private boolean RF(adz var1, int var2, boolean var3) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         for (awd.eo var5 : this.xK.values()) {
            aeb.q();
            awa var6 = var5.q;
            if (var6.q() > var1.nf()) {
               if (var6.q() <= var2) {
                  avy var7 = avy.q(var6.q());
                  NativeDecompilationStage var8 = var7.Dw();
                  long var9 = System.nanoTime();
                  q.iHHH("{ round=%d, depth=%d } Stage: %s (routine: %s)", var1.lm(), var1.za(), var6.RF(), var1.oW());
                  DecompilationStatus var11 = this.q(var8, var1);
                  if (var11 != DecompilationStatus.IN_PROCESS) {
                     var1.q(var11);
                     return false;
                  }

                  long var12 = System.nanoTime();
                  boolean var14 = false;

                  try {
                     boolean var15 = this.xK();
                     var14 = var6.q(var1, var15);
                     if (var14 && var15) {
                        avy.q(var6.q());
                        var1.getIRContext();
                     }
                  } catch (Exception var31) {
                     q.debug("Pipeline error: round=%d, depth=%d", var1.lm(), var1.za());
                     q.catchingSilent(var31);
                     var1.q(new aef(var31));
                     var1.q(DecompilationStatus.COMPLETED);
                     if (var3) {
                        throw var31;
                     }
                  } finally {
                     q.iL("End of decompilation Stage: %s (routine: %s)", var6.RF(), var1.oW());
                     q.iSeparator();
                     q.iBreak(2);
                  }

                  if (!var14) {
                     return false;
                  }

                  var1.q(var6.q());
                  long var34 = System.nanoTime();
                  long var17 = System.nanoTime();
                  var11 = this.RF(var8, var1);
                  if (var11 != DecompilationStatus.IN_PROCESS) {
                     var1.q(var11);
                     return false;
                  }

                  long var19 = System.nanoTime();
                  long var21 = (var19 - var9) / 1000000L;
                  long var23 = (var34 - var12) / 1000000L;
                  long var25 = (var12 - var9 + (var17 - var34)) / 1000000L;
                  long var27 = (var19 - var17) / 1000000L;
                  var5.RF.q += var21;
                  var5.RF.RF += var23;
                  var5.RF.xK += var25;
                  var5.RF.Dw += var27;
                  continue;
               }
               break;
            }
         }

         if (var2 < RF) {
            var1.q(DecompilationStatus.IN_PROCESS);
         } else if (var2 == RF) {
            var1.q(DecompilationStatus.COMPLETED);
         }

         var1.q(null);
         return true;
      }
   }

   private DecompilationStatus q(NativeDecompilationStage var1, adz var2) {
      return (DecompilationStatus)var2.getDecompiler().getExtensionsManager().executePrePipelineStage(var1, var2).getResult();
   }

   private DecompilationStatus RF(NativeDecompilationStage var1, adz var2) {
      return (DecompilationStatus)var2.getDecompiler().getExtensionsManager().executePostPipelineStage(var1, var2).getResult();
   }

   boolean xK() {
      return false;
   }

   private void q(avy var1, IERoutineContext var2) {
   }

   protected final void q(avy var1, CFG var2, int... var3) {
      SegmentMap var4 = new SegmentMap();

      for (byte var5 = 0; var5 < var3.length - 1; var5 += 2) {
         int var6 = var3[var5];
         int var7;
         if (var6 >= 0) {
            var7 = var3[var5 + 1] - var6;
         } else {
            var6 = var3[var5 + 1];
            var7 = var3[var5] - var6;
         }

         var4.add(new IntegerSegment(var6, var7));
      }

      for (IEStatement var9 : var2.instructions()) {
         var9.visitDepthPost(new awe(this, var4, var1));
      }
   }

   public static class eo {
      awa q;
      awc RF = new awc();

      eo(awa var1) {
         this.q = var1;
      }
   }
}
