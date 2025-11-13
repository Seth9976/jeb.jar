package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.DemoLimitationException;
import com.pnfsoftware.jeb.core.exceptions.InterruptionException;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
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
public class aci implements INativeItemListener {
   private static final StructuredLogger pC = aco.pC(aci.class);
   @SerId(1)
   private INativeDecompilerContext A;
   @SerId(2)
   private IdentityHashMap kS = new IdentityHashMap();
   @SerTransient
   private boolean wS;
   @SerTransient
   private Deque UT;
   @SerTransient
   private Deque E;

   public aci(INativeDecompilerContext var1) {
      this.A = var1;
   }

   public INativeDecompilerContext pC() {
      return this.A;
   }

   public Collection A() {
      return this.kS.values();
   }

   public IDecompiledItem pC(INativeItem var1) {
      return (IDecompiledItem)this.kS.get(var1);
   }

   public IDecompiledMethod pC(INativeMethodItem var1) {
      return (ach)this.kS.get(var1);
   }

   public IDecompiledField pC(INativeFieldItem var1) {
      return (acg)this.kS.get(var1);
   }

   public IDecompiledClass pC(INativeClassItem var1) {
      return (acf)this.kS.get(var1);
   }

   public IDecompiledItem pC(INativeItem var1, DecompilationContext var2) {
      if (var1 instanceof INativeMethodItem) {
         return this.pC((INativeMethodItem)var1, var2);
      } else if (var1 instanceof INativeFieldItem) {
         return this.pC((INativeFieldItem)var1, var2);
      } else {
         return var1 instanceof INativeClassItem ? this.pC((INativeClassItem)var1, var2) : null;
      }
   }

   public IDecompiledClass pC(INativeClassItem var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return null;
      } else {
         IProgressCallback var3 = var2.getCallback();
         acf var4 = (acf)this.kS.get(var1);
         if (var4 == null) {
            var4 = new acf(this.A, var1);
            var4.setFlags(var2.getFlags());
            this.kS.put(var1, var4);
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
               IDecompiledMethod var8 = this.pC(var7, var2);
               if (var8 != null && var8.getStatus() == DecompilationStatus.COMPLETED) {
                  var5.add(var8.getMethodAST());
               }
            }

            ArrayList var11 = new ArrayList();

            for (INativeFieldItem var14 : var1.getFields()) {
               IDecompiledField var9 = this.pC(var14, var2);
               if (var9 != null && var9.getStatus() == DecompilationStatus.COMPLETED) {
                  var11.add(var9.getFieldAST());
               }
            }

            ICClass var13 = this.A.getHighLevelContext().getClassFactory().create(var1, false);
            var5.forEach(var1x -> var13.addMethod(var1x));
            var11.forEach(var1x -> var13.addField(var1x));
            var4.pC(var13);
            var4.pC(DecompilationStatus.COMPLETED);
            return var4;
         }
      }
   }

   public IDecompiledField pC(INativeFieldItem var1, DecompilationContext var2) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return null;
      } else {
         acg var3 = (acg)this.kS.get(var1);
         if (var3 == null) {
            var3 = new acg(this.A, var1);
            var3.setFlags(var2.getFlags());
            this.kS.put(var1, var3);
            var1.addListener(this);
         }

         if (var3 != null && var3.getStatus() == DecompilationStatus.COMPLETED) {
            return var3;
         } else {
            ICField var4 = this.A.getHighLevelContext().getFieldFactory().create(var1, false);
            var3.pC(var4);
            var3.pC(DecompilationStatus.COMPLETED);
            return var3;
         }
      }
   }

   private IDecompiledMethod pC(INativeMethodItem var1, DecompilationContext var2) {
      return this.pC(var1, var2, atg.rl, 0, false, false);
   }

   public IDecompiledMethod pC(INativeMethodItem var1, DecompilationContext var2, atg var3, int var4, boolean var5, boolean var6) {
      var2 = DecompilationContext.safe(var2);
      if (var2.isInterruptionRequested()) {
         return null;
      } else {
         IProgressCallback var7 = var2.getCallback();
         int var8 = var2.getFlags();
         boolean var9 = (var8 & 128) != 0;
         if (!var9 && this.A(var1)) {
            return null;
         } else if (var1 == null) {
            return null;
         } else {
            String var10 = var1.getAddress(false);
            if (var10 == null) {
               return null;
            } else if (var1.getMemoryAddress() == null) {
               pC.error(S.L("The routine %s is not internal"), var10);
               return null;
            } else {
               long var11 = var1.getMemoryAddress();
               boolean var13 = (var8 & 8) != 0;
               if (var13) {
                  pC.warn("GENDEC does not support FLAG_NO_METHOD_AST_GENERATION at the moment");
               }

               boolean var14 = (var8 & 16) != 0;
               if (var14) {
                  pC.warn("GENDEC does not support FLAG_TEMP_FORCED_REDECOMPILATIONS at the moment");
               }

               int var15 = var1.getData().getCFG().getInstructionCount();
               int var16 = var1.getData().getCFG().getEffectiveSize();
               boolean var17 = this.wS() == null;
               DecompilationResult var18 = new DecompilationResult();
               long var20 = 0L;
               ach var22 = null;

               try {
                  pC.debug("Decompiling routine: %s", var10);
                  if (var7 != null && var17) {
                     var7.message(0, "Method: " + var10);
                  }

                  if (var4 <= 0) {
                     var4 = this.pC().getOptions().reconversionMaxCount;
                     if (var4 <= 0) {
                        var4 = 10;
                     }
                  }

                  if (var3 == null) {
                     var3 = atg.A();
                  }

                  if (var9) {
                     var4 = 1;
                     var3 = atg.kS;
                  }

                  var20 = System.currentTimeMillis();

                  int var23;
                  for (var23 = 0; var23 < var4; var23++) {
                     var1 = this.A.getNativeContext().getRoutine(var11);
                     if (var1 == null) {
                        if (var23 == 0) {
                           pC.error(S.L("The routine at 0x%X does not exist!"), var11);
                        } else {
                           pC.error(S.L("The routine at 0x%X no longer exists!"), var11);
                        }

                        return null;
                     }

                     var22 = null;
                     if (!var9) {
                        var22 = (ach)this.kS.get(var1);
                        if (var22 != null) {
                           boolean var54 = (var8 & 64) != 0;
                           boolean var58 = (var22.getFlags() & 64) != 0;
                           if (var54 && !var58) {
                              this.A((INativeItem)var1);
                           }

                           if (var22.getStatus() == DecompilationStatus.COMPLETED) {
                              if (var3 == atg.rl) {
                                 return var22;
                              }
                           } else if (var22.getStatus() == DecompilationStatus.IN_PROCESS && var3.pC() < var22.sY()) {
                              this.A((INativeItem)var1);
                           }
                        }
                     }

                     var22 = this.pC(var1, var2, var3, var23, var4);
                     DecompilationStatus var55 = var22.getStatus();
                     if (this.wS) {
                        var55 = DecompilationStatus.NEED_RECONVERSION;
                     }

                     if (var55 != DecompilationStatus.NEED_RECONVERSION) {
                        return var22;
                     }

                     if (!(this.A.getNativeContext() instanceof INativeCodeUnit)) {
                        throw new RuntimeException("Re-analysis is currently not supported for pseudo native contexts");
                     }

                     INativeCodeUnit var59 = (INativeCodeUnit)this.A.getNativeContext();
                     INativeCodeAnalyzer var62 = var59.getCodeAnalyzer();
                     if (var62.needsAnalysis()) {
                        var62.analyze(true, true);
                     }

                     var22.pC();
                  }

                  if (var23 >= var4) {
                     pC.warn(S.L("Exceeded maxRoundCount (%d) for routine: %Xh"), var4, var11);
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
                     var22 = (ach)this.kS.get(var1);
                  }

                  if (var22 != null) {
                     ICMethod var29 = var22.ys();
                     if (var29 == null) {
                        var29 = this.A.getHighLevelContext().getMethodFactory().create(var10);
                        var22.pC(var29);
                     }

                     var29.setStatus(var28, Licensing.isReleaseBuild() ? var27 : var26);
                  }

                  var2.recordError(var10, var26);
                  var18.error = var26;
                  if (var24) {
                     acj.pC(var51, this.pC().getNativeContext(), var10);
                  }

                  if (var5) {
                     throw new DecompilerException(Strings.ff("Failed to decompile routine @ 0x%X", var11), var51);
                  } else {
                     return null;
                  }
               } finally {
                  if (!var9) {
                     var22 = (ach)this.kS.get(var1);
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
                           acj.pC(var48, this.A.getNativeContext(), var1.getAddress(false));
                        }
                     }
                  }

                  if (var22 != null && var22.getIRContext() != null && var22.getMethodAST() != null) {
                     IERoutineContext var64 = var22.getIRContext();
                     ICMethod var66 = var22.getMethodAST();
                     var64.getNotes().forEach(var1x -> var66.addDecompilationNote(var1x));
                  }

                  if (var22 != null && !var6) {
                     this.pC(var22);
                  }

                  if (var17) {
                     var18.nsize = var15;
                     var18.bsize = var16;
                     long var65 = System.currentTimeMillis() - var20;
                     var18.time1 = var65;
                     var2.recordResult(var10, var18);
                     if (var22 != null) {
                        acy var67 = var22.NS();
                        var67.pC = var65;
                        var67.A = var15;
                        var67.kS = var16;
                        var67.pC(((EMasterOptimizer)var22.getIROptimizer()).retrievePerformanceCounters());
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

   public void pC(IDecompiledMethod var1) {
      ach var2 = (ach)var1;
      if (var2.getIRContext() != null) {
         CFG var3 = var2.getIRContext().getCfg();
         if (var3 != null) {
            var3.invalidateDataFlowAnalysis();
         }
      }

      if ((var2.getFlags() & 64) == 0) {
         var2.kS();
      }
   }

   private ach pC(INativeMethodItem var1, DecompilationContext var2, atg var3, int var4, int var5) {
      Assert.a(var1.getData() != null, "Need an internal routine");
      pC.iH("Decompile single-round: %s (to=%s, round=%d)", var1, var3, var4);
      var2 = DecompilationContext.safe(var2);
      if (this.UT == null) {
         this.UT = new ArrayDeque();
      }

      if (this.E == null) {
         this.E = new ArrayDeque();
      }

      int var7 = this.UT.size();
      this.wS = false;
      this.UT.push(var1);
      this.E.push(var2);

      ach var10;
      try {
         if (var4 < 0) {
            throw new IllegalArgumentException("Round must be >= 0");
         }

         if (var3 == null) {
            var3 = atg.A();
         }

         boolean var8 = (var2.getFlags() & 128) != 0;
         ach var6;
         if (var8) {
            var6 = new ach(this.A, var1);
         } else {
            var6 = (ach)this.kS.get(var1);
            if (var6 == null) {
               var6 = new ach(this.A, var1);
               this.kS.put(var1, var6);
               var1.addListener(this);
               var1.getData().addListener(this);
            }
         }

         var6.setFlags(var2.getFlags());
         var6.A(var7);
         var6.wS(var5);
         var6.kS(var4);
         var6.A();
         if (var4 == 0) {
            Long var9 = var2.getMaxTimePerMethod();
            if (var9 == null) {
               var9 = this.pC().getOptions().methodDecompTimeout;
            }

            var6.pC(new TimedOperationVerifier(var9));
         }

         atl var17 = new atl();
         if (!var17.pC(var6, var3, true)) {
            pC.iHH("Pipeline: perform() returned false - status= %s", var6.getStatus());
         }

         var6.NS().pC(var17.pC());
         var10 = var6;
      } catch (InterruptionException var14) {
         this.kS.remove(var1);
         throw var14;
      } finally {
         this.UT.pop();
         this.E.pop();
      }

      return var10;
   }

   public DecompilationContext kS() {
      return this.E != null && !this.E.isEmpty() ? (DecompilationContext)this.E.peek() : null;
   }

   public INativeMethodItem wS() {
      return this.UT != null && !this.UT.isEmpty() ? (INativeMethodItem)this.UT.peek() : null;
   }

   public boolean A(INativeMethodItem var1) {
      return this.UT != null && this.UT.contains(var1);
   }

   public void A(INativeItem var1) {
      this.pC(var1, 0);
   }

   public void kS(INativeItem var1) {
      this.pC(var1, 2);
   }

   private void pC(INativeItem var1, int var2) {
      this.pC(var1, var2, true);
   }

   private void pC(INativeItem var1, int var2, boolean var3) {
      acc var4 = (acc)this.kS.get(var1);
      if (var4 != null) {
         var4.pC();
         if (var2 != 1) {
            if (var2 == 2) {
               String var5 = var1.getAddress(false);
               this.kS.remove(var1);
               var1.removeListener(this);
               if (var1 instanceof INativeMethodDataItem) {
                  ((INativeMethodItem)var1).getData().removeListener(this);
               }

               if (var1 instanceof INativeMethodItem) {
                  adi var6 = (adi)this.A.getHighLevelContext().getMethodFactory();
                  var6.A(var5);
               } else if (var1 instanceof INativeFieldItem) {
                  adf var7 = (adf)this.A.getHighLevelContext().getFieldFactory();
                  var7.A(var5);
               } else if (var1 instanceof INativeClassItem) {
                  adb var8 = (adb)this.A.getHighLevelContext().getClassFactory();
                  var8.A(var5);
               }
            } else {
               Assert.a(var2 == 0);
            }
         }

         if (var3) {
            this.A.onEngineNotification(new Couple(var4, var2));
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
               this.pC(var6, var3);
            }
         } else {
            this.pC(var4, var3);
         }
      }
   }

   private void pC(INativeItem var1, boolean var2) {
      IDecompiledItem var3 = (IDecompiledItem)this.kS.get(var1);
      if (var3 != null) {
         Assert.a(var1 == var3.getNativeItem());
         if (var2) {
            this.pC(var1, 2);
         } else if (var1 != this.wS()) {
            this.A(var1);
         }
      }
   }

   public IEMasterOptimizer pC(IEMasterOptimizer var1) {
      if (this.A.getNativeContext().getProcessor().isRISC()) {
         OptimizerEntry var2 = var1.getOptimizer(aqv.class);
         if (var2 != null) {
            var1.unregisterOptimizer(var2);
         }

         var1.registerOptimizer(new ask());
         var1.registerOptimizer(new asj());
      }

      return var1;
   }

   public void UT() {
      this.kS.clear();
      this.A = null;
   }
}
