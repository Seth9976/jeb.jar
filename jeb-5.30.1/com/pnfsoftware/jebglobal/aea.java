package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.DemoLimitationException;
import com.pnfsoftware.jeb.core.exceptions.InterruptionException;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.DecompilationOptions;
import com.pnfsoftware.jeb.core.units.code.DecompilationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledItem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.DecompilerException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.EMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.concurrent.TimedOperationVerifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.IdentityHashMap;

@Ser
public class aea implements INativeItemListener {
   private static final StructuredLogger q = aeg.q(aea.class);
   @SerId(1)
   private INativeDecompilerContext RF;
   @SerId(2)
   private IdentityHashMap xK = new IdentityHashMap();
   @SerTransient
   private boolean Dw;
   @SerTransient
   private Deque Uv;
   @SerTransient
   private Deque oW;

   public aea(INativeDecompilerContext var1) {
      this.RF = var1;
   }

   public INativeDecompilerContext q() {
      return this.RF;
   }

   public Collection RF() {
      return this.xK.values();
   }

   public IDecompiledItem q(INativeItem var1) {
      return (IDecompiledItem)this.xK.get(var1);
   }

   public IDecompiledMethod q(INativeMethodItem var1) {
      return (adz)this.xK.get(var1);
   }

   public IDecompiledField q(INativeFieldItem var1) {
      return (ady)this.xK.get(var1);
   }

   public IDecompiledClass q(INativeClassItem var1) {
      return (adx)this.xK.get(var1);
   }

   public IDecompiledItem q(INativeItem var1, DecompilationContext var2) {
      if (var1 instanceof INativeMethodItem) {
         return this.q((INativeMethodItem)var1, var2);
      } else if (var1 instanceof INativeFieldItem) {
         return this.q((INativeFieldItem)var1, var2);
      } else {
         return var1 instanceof INativeClassItem ? this.q((INativeClassItem)var1, var2) : null;
      }
   }

   public IDecompiledClass q(INativeClassItem var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return null;
      } else {
         IProgressCallback var3 = var2.getCallback();
         adx var4 = (adx)this.xK.get(var1);
         if (var4 == null) {
            var4 = new adx(this.RF, var1);
            var4.setFlags(var2.getFlags());
            this.xK.put(var1, var4);
            var1.addListener(this);
         }

         if (var4 != null && var4.getStatus() == DecompilationStatus.COMPLETED) {
            return var4;
         } else {
            if (var3 != null && !var3.isInitialized()) {
               var3.setTotal(var1.getMethods().size());
            }

            ArrayList var5 = new ArrayList();

            for (INativeMethodItem var7 : var1.getMethods()) {
               IDecompiledMethod var8 = this.q(var7, var2);
               if (var8 != null && var8.getStatus() == DecompilationStatus.COMPLETED) {
                  var5.add(var8.getMethodAST());
               }
            }

            ArrayList var11 = new ArrayList();

            for (INativeFieldItem var14 : var1.getFields()) {
               IDecompiledField var9 = this.q(var14, var2);
               if (var9 != null && var9.getStatus() == DecompilationStatus.COMPLETED) {
                  var11.add(var9.getFieldAST());
               }
            }

            ICClass var13 = this.RF.getHighLevelContext().getClassFactory().create(var1, false);
            var5.forEach(var1x -> var13.addMethod(var1x));
            var11.forEach(var1x -> var13.addField(var1x));
            var4.q(var13);
            var4.q(DecompilationStatus.COMPLETED);
            return var4;
         }
      }
   }

   public IDecompiledField q(INativeFieldItem var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return null;
      } else {
         ady var3 = (ady)this.xK.get(var1);
         if (var3 == null) {
            var3 = new ady(this.RF, var1);
            var3.setFlags(var2.getFlags());
            this.xK.put(var1, var3);
            var1.addListener(this);
         }

         if (var3 != null && var3.getStatus() == DecompilationStatus.COMPLETED) {
            return var3;
         } else {
            ICField var4 = this.RF.getHighLevelContext().getFieldFactory().create(var1, false);
            var3.q(var4);
            var3.q(DecompilationStatus.COMPLETED);
            return var3;
         }
      }
   }

   private IDecompiledMethod q(INativeMethodItem var1, DecompilationContext var2) {
      return this.q(var1, var2, avy.KT, 0, false, false);
   }

   public IDecompiledMethod q(INativeMethodItem var1, DecompilationContext var2, avy var3, int var4, boolean var5, boolean var6) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return null;
      } else {
         IProgressCallback var7 = var2.getCallback();
         int var8 = var2.getFlags();
         boolean var9 = (var8 & 128) != 0;
         if (!var9 && this.RF(var1)) {
            return null;
         } else if (var1 == null) {
            return null;
         } else {
            String var10 = var1.getAddress(false);
            if (var10 == null) {
               return null;
            } else if (var1.getMemoryAddress() == null) {
               q.error(S.L("The routine %s is not internal"), var10);
               return null;
            } else {
               long var11 = var1.getMemoryAddress();
               boolean var13 = (var8 & 8) != 0;
               if (var13) {
                  q.warn("GENDEC does not support FLAG_NO_METHOD_AST_GENERATION at the moment");
               }

               boolean var14 = (var8 & 16) != 0;
               if (var14) {
                  q.warn("GENDEC does not support FLAG_TEMP_FORCED_REDECOMPILATIONS at the moment");
               }

               int var15 = var1.getData().getCFG().getInstructionCount();
               int var16 = var1.getData().getCFG().getEffectiveSize();
               boolean var17 = this.Uv() == null;
               DecompilationResult var18 = new DecompilationResult();
               long var20 = 0L;
               adz var22 = null;

               try {
                  q.debug("Decompiling routine: %s", var10);
                  if (var7 != null && var17) {
                     var7.message(0, "Method: " + var10);
                  }

                  if (var4 <= 0) {
                     var4 = this.q().getOptions().reconversionMaxCount;
                     if (var4 <= 0) {
                        var4 = 10;
                     }
                  }

                  if (var3 == null) {
                     var3 = avy.xK();
                  }

                  if (var9) {
                     var4 = 1;
                     var3 = avy.xK;
                  }

                  var20 = System.currentTimeMillis();

                  int var23;
                  for (var23 = 0; var23 < var4; var23++) {
                     var1 = this.RF.getNativeContext().getRoutine(var11);
                     if (var1 == null) {
                        if (var23 == 0) {
                           q.error(S.L("The routine at 0x%X does not exist!"), var11);
                        } else {
                           q.error(S.L("The routine at 0x%X no longer exists!"), var11);
                        }

                        return null;
                     }

                     var22 = null;
                     if (!var9) {
                        var22 = (adz)this.xK.get(var1);
                        if (var22 != null) {
                           boolean var54 = (var8 & 64) != 0;
                           boolean var58 = (var22.getFlags() & 64) != 0;
                           if (var54 && !var58) {
                              this.RF((INativeItem)var1);
                           }

                           if (var22.getStatus() == DecompilationStatus.COMPLETED) {
                              if (var3 == avy.KT) {
                                 return var22;
                              }
                           } else if (var22.getStatus() == DecompilationStatus.IN_PROCESS && var3.q() < var22.nf()) {
                              this.RF((INativeItem)var1);
                           }
                        }
                     }

                     var22 = this.q(var1, var2, var3, var23, var4);
                     DecompilationStatus var55 = var22.getStatus();
                     if (this.Dw) {
                        var55 = DecompilationStatus.NEED_RECONVERSION;
                     }

                     if (var55 != DecompilationStatus.NEED_RECONVERSION) {
                        return var22;
                     }

                     if (!(this.RF.getNativeContext() instanceof INativeCodeUnit)) {
                        throw new RuntimeException("Re-analysis is currently not supported for pseudo native contexts");
                     }

                     INativeCodeUnit var59 = (INativeCodeUnit)this.RF.getNativeContext();
                     INativeCodeAnalyzer var62 = var59.getCodeAnalyzer();
                     if (var62.needsAnalysis()) {
                        var62.analyze(true, true);
                     }

                     var22.q();
                  }

                  if (var23 >= var4) {
                     q.warn(S.L("Exceeded maxRoundCount (%d) for routine: %Xh"), var4, var11);
                  }

                  return null;
               } catch (Exception var51) {
                  boolean var24 = true;
                  Throwable var25 = Throwables.getRootCause(var51);
                  String var26 = Throwables.formatStacktrace(var25);
                  String var27 = Strings.safe(var25.getMessage(), "Unspecified error");
                  byte var28 = 2;
                  if (var25 instanceof DemoLimitationException) {
                     var28 = 3;
                     var24 = false;
                  }

                  if (!var9) {
                     var22 = (adz)this.xK.get(var1);
                  }

                  if (var22 != null) {
                     ICMethod var29 = var22.gP();
                     if (var29 == null) {
                        var29 = this.RF.getHighLevelContext().getMethodFactory().create(var10);
                        var22.q(var29);
                     }

                     var29.setStatus(var28, Licensing.isReleaseBuild() ? var27 : var26);
                  }

                  var2.recordError(var10, var26);
                  var18.error = var26;
                  if (var24) {
                     aeb.q(var51, this.q().getNativeContext(), var10);
                  }

                  if (var5) {
                     throw new DecompilerException(Strings.ff("Failed to decompile routine @ 0x%X", var11), var51);
                  } else {
                     return null;
                  }
               } finally {
                  if (!var9) {
                     var22 = (adz)this.xK.get(var1);
                  }

                  if ((
                        Licensing.isReleaseBuild()
                           || JebCoreService.getExistingInstance() != null && JebCoreService.getExistingInstance().getOptions().isUIClient()
                     )
                     && var17
                     && var22 != null
                     && var22.getIRContext() != null
                     && var22.getIROptimizer() != null) {
                     IERoutineContext var40 = var22.getIRContext();
                     int var41 = var40.getCfg().getInstructionCount();
                     int var42 = var22.getIROptimizer().getTotalOptimizationCount();
                     int var43 = var22.getIROptimizer().getOptimizationCount(true);
                     if (var43 > 0) {
                        String var45 = null;

                        for (String var47 : var40.getNotes()) {
                           if (var47.startsWith("Deobfuscation score:")) {
                              var45 = var47;
                              break;
                           }
                        }

                        if (var45 != null) {
                           var40.removeNote(var45);
                        }

                        int var69 = (var43 * 20 + (var42 - var43)) / var41;
                        var40.addNote(Strings.ff("%s %d [EXPERIMENTAL]", "Deobfuscation score:", var69));
                        if (var69 >= 200 && JebCoreService.getExistingInstance().getOptions().isUIClient()) {
                           RuntimeException var48 = new RuntimeException("TBI: OBF routine, high-score: inform user");
                           aeb.q(var48, this.RF.getNativeContext(), var1.getAddress(false));
                        }
                     }
                  }

                  if (var22 != null && var22.getIRContext() != null && var22.getMethodAST() != null) {
                     IERoutineContext var64 = var22.getIRContext();
                     ICMethod var66 = var22.getMethodAST();
                     var64.getNotes().forEach(var1x -> var66.addDecompilationNote(var1x));
                  }

                  if (var22 != null && !var6) {
                     this.q(var22);
                  }

                  if (var17) {
                     var18.nsize = var15;
                     var18.bsize = var16;
                     long var65 = System.currentTimeMillis() - var20;
                     var18.time1 = var65;
                     var2.recordResult(var10, var18);
                     if (var22 != null) {
                        aeq var67 = var22.io();
                        var67.q = var65;
                        var67.RF = var15;
                        var67.xK = var16;
                        var67.q(((EMasterOptimizer)var22.getIROptimizer()).retrievePerformanceCounters());
                     }

                     if (var7 != null) {
                        long var68 = var7.increment();
                        String var44 = Strings.ff("%d/%d: %s (DONE:%dms)", var68, var7.getTotal(), var10, System.currentTimeMillis() - var20);
                        var7.message(0, var44);
                     }
                  }
               }
            }
         }
      }
   }

   public void q(IDecompiledMethod var1) {
      adz var2 = (adz)var1;
      if (var2.getIRContext() != null) {
         CFG var3 = var2.getIRContext().getCfg();
         if (var3 != null) {
            var3.invalidateDataFlowAnalysis();
         }
      }

      if ((var2.getFlags() & 64) == 0) {
         var2.xK();
      }
   }

   private adz q(INativeMethodItem var1, DecompilationContext var2, avy var3, int var4, int var5) {
      Assert.a(var1.getData() != null, "Need an internal routine");
      q.iH("Decompile single-round: %s (to=%s, round=%d)", var1, var3, var4);
      var2 = DecompilationContext.safe(var2);
      if (this.Uv == null) {
         this.Uv = new ArrayDeque();
      }

      if (this.oW == null) {
         this.oW = new ArrayDeque();
      }

      int var7 = this.Uv.size();
      this.Dw = false;
      this.Uv.push(var1);
      this.oW.push(var2);

      adz var10;
      try {
         if (var4 < 0) {
            throw new IllegalArgumentException("Round must be >= 0");
         }

         if (var3 == null) {
            var3 = avy.xK();
         }

         boolean var8 = (var2.getFlags() & 128) != 0;
         adz var6;
         if (var8) {
            var6 = new adz(this.RF, var1);
         } else {
            var6 = (adz)this.xK.get(var1);
            if (var6 == null) {
               var6 = new adz(this.RF, var1);
               this.xK.put(var1, var6);
               var1.addListener(this);
               var1.getData().addListener(this);
            }
         }

         var6.setFlags(var2.getFlags());
         var6.RF(var7);
         var6.Dw(var5);
         var6.xK(var4);
         var6.RF();
         if (var4 == 0) {
            Long var9 = var2.getMaxTimePerMethod();
            if (var9 == null) {
               var9 = this.q().getOptions().methodDecompTimeout;
            }

            var6.q(new TimedOperationVerifier(var9));
         }

         awd var17 = new awd();
         if (!var17.q(var6, var3, true)) {
            q.iHH("Pipeline: perform() returned false - status= %s", var6.getStatus());
         }

         var6.io().q(var17.q());
         var10 = var6;
      } catch (InterruptionException var14) {
         this.xK.remove(var1);
         throw var14;
      } finally {
         this.Uv.pop();
         this.oW.pop();
      }

      return var10;
   }

   public DecompilationContext xK() {
      return this.oW != null && !this.oW.isEmpty() ? (DecompilationContext)this.oW.peek() : null;
   }

   public DecompilationOptions Dw() {
      return this.oW != null && !this.oW.isEmpty() ? ((DecompilationContext)this.oW.peek()).getOptions() : null;
   }

   public INativeMethodItem Uv() {
      return this.Uv != null && !this.Uv.isEmpty() ? (INativeMethodItem)this.Uv.peek() : null;
   }

   public boolean RF(INativeMethodItem var1) {
      return this.Uv != null && this.Uv.contains(var1);
   }

   public void q(boolean var1) {
      this.Dw = var1;
   }

   public boolean oW() {
      return this.Dw;
   }

   public void RF(INativeItem var1) {
      this.q(var1, 0);
   }

   public void xK(INativeItem var1) {
      this.q(var1, 2);
   }

   private void q(INativeItem var1, int var2) {
      this.q(var1, var2, true);
   }

   private void q(INativeItem var1, int var2, boolean var3) {
      adu var4 = (adu)this.xK.get(var1);
      if (var4 != null) {
         var4.q();
         if (var2 != 1) {
            if (var2 == 2) {
               String var5 = var1.getAddress(false);
               this.xK.remove(var1);
               var1.removeListener(this);
               if (var1 instanceof INativeMethodDataItem) {
                  ((INativeMethodItem)var1).getData().removeListener(this);
               }

               if (var1 instanceof INativeMethodItem) {
                  afb var6 = (afb)this.RF.getHighLevelContext().getMethodFactory();
                  var6.RF(var5);
               } else if (var1 instanceof INativeFieldItem) {
                  aey var7 = (aey)this.RF.getHighLevelContext().getFieldFactory();
                  var7.RF(var5);
               } else if (var1 instanceof INativeClassItem) {
                  aeu var8 = (aeu)this.RF.getHighLevelContext().getClassFactory();
                  var8.RF(var5);
               }
            } else {
               Assert.a(var2 == 0);
            }
         }

         if (var3) {
            this.RF.onEngineNotification(new Couple(var4, var2));
         }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      NativeItemEventType var2 = var1.getType();
      if (var2 == NativeItemEventType.DISPOSED || var2 == NativeItemEventType.MODIFIED) {
         boolean var3 = var2 == NativeItemEventType.DISPOSED;
         INativeItem var4 = var1.getItem();
         if (var4 instanceof INativeMethodDataItem) {
            for (INativeMethodItem var6 : ((INativeMethodDataItem)var4).getMethodReferences()) {
               this.q(var6, var3);
            }
         } else {
            this.q(var4, var3);
         }
      }
   }

   private void q(INativeItem var1, boolean var2) {
      IDecompiledItem var3 = (IDecompiledItem)this.xK.get(var1);
      if (var3 != null) {
         Assert.a(var1 == var3.getNativeItem());
         if (var2) {
            this.q(var1, 2);
         } else if (var1 != this.Uv()) {
            this.RF(var1);
         }
      }
   }

   public IEMasterOptimizer q(IEMasterOptimizer var1) {
      if (this.RF.getNativeContext().getProcessor().isRISC()) {
         OptimizerEntry var2 = var1.getOptimizer(atk.class);
         if (var2 != null) {
            var1.unregisterOptimizer(var2);
         }

         var1.registerOptimizer(new avb());
         var1.registerOptimizer(new ava());
      }

      return var1;
   }

   public void gO() {
      this.xK.clear();
      this.RF = null;
   }
}
