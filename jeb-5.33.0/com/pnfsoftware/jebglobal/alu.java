package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.NativeCommentManager;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraph;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.DecompilerException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESimulationResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.PreRoutineInvocationDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SimulationPointInformation;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemoryShim;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.collections4.CollectionUtils;

public class alu implements IESimulationResults {
   private static final StructuredLogger pC = aco.pC(alu.class);
   private IERoutineContext A;
   private INativeContext kS;
   private CFG wS;
   private INativeDecompilerContext UT;
   private int E = 1;
   private boolean sY = true;
   private boolean ys = true;
   private Map ld = new HashMap();
   private Map gp = new HashMap();
   private Set oT = new LinkedHashSet();
   private MultiMap fI = new MultiMap();
   private final long WR;
   private long NS;
   private alu.Av vP;

   public alu(IERoutineContext var1, INativeDecompilerContext var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
         this.wS = var1.getCfg();
         this.UT = var2;
         this.kS = var1.getNativeContext();
         this.WR = 2113929216L;
      }
   }

   @Override
   public boolean recordComment(long var1, String var3) {
      if (Strings.isBlank(var3)) {
         return false;
      } else {
         this.ld.put(var1, var3);
         return true;
      }
   }

   @Override
   public boolean recordSupportRoutineCall(long var1, INativeMethodItem var3) {
      this.gp.put(var1, var3);
      return true;
   }

   public int pC() {
      IVirtualMemory var1 = this.A.getGlobalContext().getNativeMemory();
      IVirtualMemoryShim var2 = VirtualMemoryUtil.getCopyOnWriteShim(var1);

      try {
         return this.pC(var2);
      } catch (Exception var4) {
         Assert.debugFail(var4);
         return 0;
      }
   }

   private int pC(IVirtualMemoryShim var1) {
      if (this.A.usesCopyVars()) {
         throw new IllegalStateException();
      } else {
         IEVar var2 = this.A.getStackPointer();
         int var3 = var2.getId();
         int var4 = this.A.getProgramCounterId();
         IdentityHashMap var5 = new IdentityHashMap(this.wS.size());

         for (BasicBlock var7 : this.wS.getBlocks()) {
            amm var8 = new amm(this.wS, var7);
            var5.put(var7, var8);
         }

         BasicBlock var48 = this.wS.getEntryBlock();
         Assert.a(var48.getFirstAddress() == 0L);
         EState var50 = this.A.buildEmptyState();
         var50.setExecuteSubRoutines(true);
         var50.setMemory(var1);
         long var52 = this.A.getRoutine().getMemoryAddress();
         var50.setValue(var4, var52);
         if (this.E == 0) {
            this.A.getConverter().initializeStateRegisters(var50, var52);
         } else {
            this.A.getConverter().customInitStateRegisters(var50, var52);
         }

         long var10 = this.WR;
         var50.setValue(var2, var10);
         long var12 = (var10 & -4096L) - 65536L;
         int var14 = 131072;
         long var15 = var12 + var14;
         VirtualMemoryUtil.allocateFillGaps(var1, var12, var14, 3);
         var50.setNativeStackStart(var12);
         var50.setNativeStackEnd(var15);
         if (this.E == 1) {
            var50.setSoftFailMode(true);
            var50.writeMemoryBad(var12, var14 * 8, 2);
         }

         int var17 = var1.getSpaceBits() / 8;
         this.NS = -3819410105738241352L / var17 * var17;
         this.NS = this.A.getConverter().sanitizeNativeAddress(-3819410105738241352L);
         this.vP = new alu.Av(this.A);
         if (this.vP.pC()) {
            this.vP.pC(var50, this.NS);
         }

         var50.enableMemoryWriteHitmap(this.ys, true);
         ((amm)var5.get(var48)).kS = var50;
         ArrayDeque var18 = new ArrayDeque();
         var18.add(var48);
         IdentityHashMap var19 = new IdentityHashMap();
         int var20 = 30;
         SimulationPointInformation var21 = new SimulationPointInformation();
         var21.cfg = this.wS;

         while (!var18.isEmpty()) {
            var48 = (BasicBlock)var18.remove();
            var21.bb = var48;
            amm var23 = (amm)var5.get(var48);
            if (var23.kS == null) {
               throw new RuntimeException("No input tracker for block: " + var48);
            }

            var50 = new EState(var23.kS, false);
            var1 = (IVirtualMemoryShim)var50.getMemory();
            Object var22 = var50.getVariables();
            ArrayList var24 = new ArrayList();
            ArrayList var25 = new ArrayList();
            long var26 = var48.getFirstAddress();

            for (int var28 = 0; var28 < var48.size(); var28++) {
               IEStatement var29 = (IEStatement)var48.get(var28);
               var21.instructionIndex = var28;
               var21.blkPreExecRegVals = var24;
               var21.blkPostExecRegVals = var25;
               var24.add(var22);
               Couple var32 = (Couple)var19.get(var29);
               List var30;
               if (var32 != null) {
                  var30 = (List)var32.getFirst();
                  List var10000 = (List)var32.getSecond();
               } else {
                  EDefUseInfo var33 = new EDefUseInfo();
                  var29.getDefUse(var33);
                  var30 = var33.getDefinedVarIds();
                  List var31 = var33.getUsedVarIds();
                  var19.put(var29, new Couple(var30, var31));
               }

               Set var56 = var22.keySet();
               byte var34 = 0;
               if (this.UT != null) {
                  this.UT.getExtensionsManager().augmentSimulationContext(this.UT, this.A, this, var26, var29, var50);
               }

               int var35 = this.pC(var26, var29, var50, this.oT, this.fI);
               if (var35 == -1) {
                  Object[] var69 = new Object[0];
                  var34 = 2;
               }

               if (var34 == 0) {
                  if (EUtil.isPCAssign(var29)) {
                     int var36 = this.pC(var48, (IEAssign)var29, var26, var50);
                     if (var36 == 2) {
                        return 2;
                     }
                  }

                  if (var29 instanceof IECall || var29 instanceof IEAssign && ((IEAssign)var29).isRoutineCall()) {
                     var22 = new HashMap((Map)var22);
                     var50.setVariables((Map)var22);
                     boolean var57 = false;
                     if (!(var29 instanceof IECall)) {
                        if (this.ys) {
                           IEBranchDetails var37 = ((IEAssign)var29).getBranchDetails(true);
                           var37.setPreInvocationDetails(new PreRoutineInvocationDetails(this.A, var50));
                        }

                        try {
                           if (var29.evaluate(var50) == null) {
                              var57 = true;
                           }
                        } catch (Exception var46) {
                           var57 = true;
                        }

                        var50.setRoutineContext(this.A);
                     }

                     Integer var59;
                     if (var29 instanceof IECall) {
                        var59 = ((IECall)var29).getStackPointerDeltaAfterExecution();
                     } else {
                        Integer var38 = null;

                        try {
                           var21.state = var50;
                           var21.instructionEmulationFailed = var57;
                           var38 = this.A.getConverter().determineStackPointerDeltaFromSimulation(var21);
                        } catch (Exception var45) {
                        }

                        if (var38 != null) {
                           Object[] var70 = new Object[]{var38};
                           var59 = var38;
                           IEBranchDetails var39 = ((IEAssign)var29).getBranchDetails(true);
                           var39.getStackPointerDeltaDeterminer().add(var59, var20, 10);
                        } else {
                           Integer var63 = null;
                           amj var40 = new amj(this.A);
                           if (var40.pC(var48, true)) {
                              IWildcardPrototype var41 = var40.pC();
                              if (var41 != null) {
                                 var63 = this.A.getConverter().determineStackPointerDeltaAfterIRCall(var41, null);
                              }
                           }

                           if (var63 != null) {
                              var59 = var63;
                              var20 = 30;
                           } else {
                              IEBranchDetails var67 = ((IEAssign)var29).getBranchDetails();
                              if (var67 == null) {
                                 var59 = this.A.getConverter().getDefaultBranchToRoutineSideEffects(null).getStackPointerDeltaValue();
                                 var20 = 0;
                              } else {
                                 SPDC var42 = var67.getStackPointerDeltaDeterminer().getBestCandidate();
                                 var59 = var42.getValue();
                                 var20 = Math.min(var20, var42.getGuarantee());
                              }
                           }
                        }
                     }

                     Iterator var61 = var22.keySet().iterator();

                     while (var61.hasNext()) {
                        int var64 = (Integer)var61.next();
                        if (var64 >= 0 && (var64 != var3 || var59 == null)) {
                           IEVar var66 = this.A.getGlobalContext().getVar(var64);

                           try {
                              long var68 = this.A.getConverter().getNativeRegisterIdFromRegisterVariable(var66);
                              if (((AbstractConverter)this.A.getConverter()).isPossibleSpoiledRegistersForProcessorCallingConventions(var68)) {
                                 var61.remove();
                              }
                           } catch (DecompilerException var44) {
                           }
                        }
                     }

                     if (var59 != null) {
                        int var62 = var59;
                        Object[] var71 = new Object[]{var62};
                        if (var62 != 0 && var50.hasValue(var3)) {
                           IEImm var65 = var50.getValue(var3);
                           var50.setValue(var2, var65._add(EUtil.imm(var62, var65.getBitsize())));
                        }
                     }
                  } else {
                     var22 = new HashMap((Map)var22);
                     var50.setVariables((Map)var22);

                     try {
                        if (var29.evaluate(var50) == null) {
                           var34 = 1;
                        }
                     } catch (Exception var43) {
                        var34 = 2;
                     }
                  }
               }

               if (var34 > 0) {
                  var20 = Math.min(var20, 10);
                  if (var34 == 2 && !CollectionUtils.intersection(var56, var30).isEmpty()) {
                     var22 = new HashMap((Map)var22);
                     var50.setVariables((Map)var22);

                     for (int var60 : var30) {
                        var22.remove(var60);
                     }
                  }
               }

               var25.add(new HashMap((Map)var22));
               var50.incrementEvaluationCount();
               var50.removeValue(var4);
               var26 += var29.getSize();
            }

            var48.setData("preCompRegs", var24);
            var48.setData("postCompRegs", var25);
            var23.wS = new EState(var50, false);
            var23.wS.setVariables((Map)var22);

            for (BasicBlock var54 : var48.getOutputs()) {
               amm var55 = (amm)var5.get(var54);
               if (var55.kS == null) {
                  var55.kS = new EState(var23.wS, false);
                  if (this.sY) {
                     var55.kS.setMemory(var1.duplicate());
                  }

                  var18.add(var54);
               }
            }
         }

         return 1;
      }
   }

   private int pC(long var1, IEStatement var3, EState var4, Collection var5, MultiMap var6) {
      AtomicInteger var7 = new AtomicInteger();
      Long var8 = var3.getPrimaryLowerLevelAddress();
      alv var9 = new alv(this, var4, var5, var8, var6, var7);
      return var3.visitDepthPost(var9) ? var7.get() : -1;
   }

   private int pC(BasicBlock var1, IEAssign var2, long var3, EState var5) {
      if (!this.vP.pC()) {
         return 0;
      } else {
         Long var6 = this.vP.pC(var5);
         if (var6 == null) {
            return 0;
         } else {
            var6 = this.A.getConverter().sanitizeNativeAddress(var6);
            Long var7 = var2.getPrimaryLowerLevelAddress();
            if (var7 == null) {
               return 0;
            } else if (!(this.kS.getNativeItemAt(var7) instanceof INativeInstructionItem var9)) {
               return 0;
            } else {
               IFlowInformation var10 = var9.getInstruction().getBreakingFlow(var7);
               if (!var10.isBroken()) {
                  return 0;
               } else {
                  InstructionHints var11 = var9.getHints(false);
                  if (var11 == null || var11.getSwitchDispatcher() == 0 && !var11.isFakeCall()) {
                     long var12 = var7 + var9.getInstruction().getSize();
                     if (var6 == var12) {
                        if (var11 != null && var11.isActualCall()) {
                           return 0;
                        } else {
                           var9.getHints(true).setActualCall(true);
                           var9.getHints(true).setTailCall(false);
                           var9.getHints(true).setCondTailCall(false);
                           this.kS.requestRoutineReanalysis(this.A.getRoutine());
                           if (var1.outsize() == 1) {
                              long var18 = var1.getOutputBlock(0).getBase();
                              if (var18 == var3 + var2.getSize()) {
                                 return 1;
                              }
                           }

                           return 2;
                        }
                     } else if (var6 != this.NS) {
                        return 0;
                     } else if (this.vP.A()) {
                        return 0;
                     } else {
                        try {
                           long var14 = var2.getSrcOperand().evaluateAddress(var5);
                           if (var14 == this.NS) {
                              return 0;
                           }
                        } catch (Exception var16) {
                           return 0;
                        }

                        if (var11 != null && var11.isTailCall()) {
                           return 0;
                        } else {
                           var9.getHints(true).setActualCall(false);
                           var9.getHints(true).setTailCall(true);
                           var9.getHints(true).setCondTailCall(false);
                           this.kS.requestRoutineReanalysis(this.A.getRoutine());
                           return var1.outsize() == 0 ? 1 : 2;
                        }
                     }
                  } else {
                     return 0;
                  }
               }
            }
         }
      }
   }

   public int pC(boolean[] var1) {
      if (!(this.A.getNativeContext() instanceof INativeCodeUnit var2)) {
         return -1;
      } else {
         IEConverter var39 = this.A.getConverter();
         INativeCodeAnalyzer var4 = var2.getCodeAnalyzer();
         NativeCommentManager var5 = var2.getCommentManager();
         INativeCodeModel var6 = var2.getCodeModel();
         IReferenceManager var7 = var6.getReferenceManager();
         ICallGraph var8 = var6.getCallGraphManager().getGlobalCallGraph();
         int var9 = 0;
         int var10 = 0;

         for (Entry var12 : this.ld.entrySet()) {
            Long var13 = this.A.convertIntermediateOffset(((Long)var12.getKey()).intValue());
            if (var13 != null) {
               String var14 = (String)var12.getValue();
               var5.addMetaComment(var5.memoryToAddress(var13), new MetaComment(var14), false);
            }
         }

         for (Entry var42 : this.gp.entrySet()) {
            Long var44 = this.A.convertIntermediateOffset(((Long)var42.getKey()).intValue());
            if (var44 != null) {
               INativeMethodItem var46 = (INativeMethodItem)var42.getValue();
               if (var2.recordDynamicBranchTarget(var44, true, new BranchTarget(var46))) {
                  ((ey)var7).recordExternalReference(var44, var46, ReferenceType.ROUTINE_CALL);
                  var8.recordExternalCall(var44, var46, false);
                  var9++;
                  var10++;
               }
            }
         }

         int var41 = var39.getProgramCounter().getId();
         MultiMap var47 = new MultiMap();

         for (BasicBlock var16 : this.wS.getBlocks()) {
            List var17 = (List)var16.getData("preCompRegs");
            List var18 = (List)var16.getData("postCompRegs");
            int var19 = 0;

            for (AddressableInstruction var21 : var16.addressableInstructions()) {
               IEStatement var22 = (IEStatement)var21.getInstruction();
               Long var23 = var22.getPrimaryLowerLevelAddress();
               if (var23 == null) {
                  var19++;
               } else {
                  boolean var24 = false;
                  boolean var25 = false;
                  INativeContinuousItem var26 = var2.getNativeItemAt(var23);
                  if (var26 instanceof INativeInstructionItem) {
                     var24 = ((INativeInstructionItem)var26).getInstruction().getRoutineCall(var23).isBroken();
                     var25 = ((INativeInstructionItem)var26).getInstruction().getBreakingFlow(var23).isBroken();
                  }

                  if (var22 instanceof IEAssign && ((IEAssign)var22).getLeftOperand() == var39.getProgramCounter()) {
                     IEAssign var53 = var22.asAssign();
                     IEGeneric var54 = var53.getRightOperand();
                     if (!(var54 instanceof IEImm) && !this.pC(var54)) {
                        Map var56 = (Map)var18.get(var19);
                        if (var56.containsKey(var41)) {
                           IEImm var58 = (IEImm)var56.get(var41);
                           if (var58.canReadAsAddress()) {
                              long var60 = var58.getValueAsAddress();
                              if (var60 != 0L && var60 != var23) {
                                 EState var63 = new EState(var2.getProcessor().getEndianness());
                                 var63.setVariables(var56);
                                 CodePointer var34 = var2.getProcessor().createEntryPoint(var60, this.A.getConverter().getStateProcessorMode(var63));
                                 MemoryRanges var65 = ((a)var4).pC();
                                 if (var65 != null && var65.count() != 0 && !var65.contains(var60)) {
                                    continue;
                                 }

                                 if (var4.recordDynamicBranchTarget(var23, false, new BranchTarget(var34), false)) {
                                    if (var24) {
                                       var8.recordInternalCall(var23, var34, false);
                                       var4.enqueuePointerForAnalysis(var34, 0, 0);
                                    } else {
                                       auu var36 = ((Tw)var6).E(var60);
                                       if (var25) {
                                          var4.enqueuePointerForAnalysis(var34, 0, 1);
                                          if (var36 != null) {
                                             var8.recordInternalCall(var23, var34, false);
                                          }
                                       } else {
                                          var4.enqueuePointerForAnalysis(var34, 0, var36 == null ? 0 : 1);
                                       }
                                    }

                                    var9++;
                                 }
                              }
                           }
                        }
                     }
                  } else {
                     EDefUseInfo var27 = new EDefUseInfo();
                     var22.getDefUse(var27);
                     List var43 = var27.getDefinedVarIds();
                     List var45 = var27.getUsedVarIds();
                     List var28 = var47.get(var23);
                     if (var17 != null) {
                        Map var29 = (Map)var17.get(var19);

                        for (int var31 : var45) {
                           if (this.pC(var31) && var29.containsKey(var31) && (var28 == null || !var28.contains(var31))) {
                              IEImm var32 = (IEImm)var29.get(var31);
                              if (var32 != null && var32.canReadAsLong()) {
                                 long var33 = var32.getValueAsLong();

                                 try {
                                    long var35 = var39.getNativeRegisterIdFromRegisterVariable(this.A.getVariableById(var31));
                                    if (var33 != 0L && var4.recordDynamicRegisterValue(var23, false, var35, var33)) {
                                       var9++;
                                    }

                                    var47.put(var23, var31);
                                    if (var28 == null) {
                                       var28 = var47.get(var23);
                                    }
                                 } catch (Exception var38) {
                                 }
                              }
                           }
                        }
                     }

                     if (var18 != null) {
                        Map var55 = (Map)var18.get(var19);

                        for (int var59 : var43) {
                           if (var59 != var41 && this.pC(var59) && var55.containsKey(var59)) {
                              IEImm var61 = (IEImm)var55.get(var59);
                              if (var61 != null && var61.canReadAsLong()) {
                                 long var62 = var61.getValueAsLong();

                                 try {
                                    long var64 = var39.getNativeRegisterIdFromRegisterVariable(this.A.getVariableById(var59));
                                    if (var62 != 0L && var4.recordDynamicRegisterValue(var23, true, var64, var62)) {
                                       var9++;
                                    }
                                 } catch (Exception var37) {
                                 }
                              }
                           }
                        }
                     }
                  }

                  var19++;
               }
            }
         }

         for (Pointer var49 : this.oT) {
            if (var4.enqueuePointerForAnalysis(var49)) {
               List var50 = this.fI.get(var49);
               if (var50 != null) {
                  for (Long var52 : var50) {
                     var6.getReferenceManager().recordInternalReference(var52, var49.getAddress(), ReferenceType.GEN_DATA, 2);
                  }
               }
            }
         }

         if (var1 != null && var1.length >= 1) {
            var1[0] = var4.needsAnalysis() || var10 > 0;
         }

         return var9;
      }
   }

   private boolean pC(int var1) {
      return var1 >= 0 && var1 < 65536;
   }

   private boolean pC(IEGeneric var1) {
      return var1 instanceof IECond && ((IECond)var1).getExpressionTrue() instanceof IEImm && ((IECond)var1).getExpressionFalse() instanceof IEImm;
   }

   public int A() {
      IEVar var1 = this.A.getStackPointer();
      int var2 = 0;

      for (BasicBlock var4 : this.wS.getBlocks()) {
         List var5 = (List)var4.getData("preCompRegs");
         int var6 = 0;

         for (IEStatement var8 : var4) {
            Map var9 = (Map)var5.get(var6);
            if (var9 != null) {
               IEImm var10 = (IEImm)var9.get(var1.getId());
               if (var10 != null) {
                  int var11 = (int)(var10.getValueAsLong() - this.WR);
                  var8.setSPDelta(var11);
                  var2++;
               }
            }

            var6++;
         }
      }

      return var2;
   }

   static class Av {
      IERoutineContext pC;
      IEVar A;
      Integer kS;
      int wS;
      int UT;

      Av(IERoutineContext var1) {
         this.pC = var1;
         this.wS = var1.getStackPointer().getId();
         this.UT = var1.getStackManager().getNormalSlotSize();
         ICallingConvention var2 = var1.getNativeContext().getTypeManager().getCallingConventionManager().getDefaultConvention();
         if (var2 != null) {
            StorageEntry var3 = var2.getReturnAddressSlot();
            if (var3 != null) {
               if (var3.isRegisterBased() && var3.getRegisters().size() == 1) {
                  IEGeneric var4 = var1.getConverter().getRegisterVariableFromNativeRegisterId(var3.getValue());
                  if (var4 instanceof IEVar) {
                     this.A = (IEVar)var4;
                  }
               } else if (var3.isStackBased()) {
                  this.kS = var3.getValueAsStackIndex();
               }
            }
         }
      }

      boolean pC() {
         return this.A != null || this.kS != null;
      }

      boolean A() {
         return this.A != null;
      }

      Long pC(EState var1) {
         try {
            if (this.A != null) {
               return var1.getValue(this.A.getId()).getValueAsAddress();
            }

            if (this.kS != null && var1.hasValue(this.wS)) {
               long var2 = var1.getValue(this.wS).getValueAsAddress();
               return this.pC.createMem(EUtil.imm(var2 + this.UT * this.kS, var1.getMemory().getSpaceBits()), this.UT * 8).evaluate(var1).getValueAsAddress();
            }
         } catch (Exception var4) {
         }

         return null;
      }

      boolean pC(EState var1, long var2) {
         try {
            if (this.A != null) {
               var1.setValue(this.A.getId(), var2);
               return true;
            }

            if (this.kS != null && var1.hasValue(this.wS)) {
               long var4 = var1.getValue(this.wS).getValueAsAddress();
               return var1.writeMemoryPointer(var4, var2);
            }
         } catch (Exception var6) {
         }

         return false;
      }
   }
}
