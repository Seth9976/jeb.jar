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
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.collections4.CollectionUtils;

public class anz implements IESimulationResults {
   private static final StructuredLogger q = aeg.q(anz.class);
   private static final String RF = "preCompRegs";
   private static final String xK = "postCompRegs";
   private static final long Dw = 2113929216L;
   private static final long Uv = -3819410105738241352L;
   private IERoutineContext oW;
   private INativeContext gO;
   private CFG nf;
   private INativeDecompilerContext gP;
   private int za = 1;
   private boolean lm = true;
   private boolean zz = true;
   private int JY;
   private Map HF = new HashMap();
   private Map LK = new HashMap();
   private Set io = new LinkedHashSet();
   private MultiMap qa = new MultiMap();
   private final long Hk;
   private long Me;
   private anz.eo PV;

   public anz(IERoutineContext var1, INativeDecompilerContext var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW = var1;
         this.nf = var1.getCfg();
         this.gP = var2;
         this.gO = var1.getNativeContext();
         this.Hk = 2113929216L;
      }
   }

   public void q(int var1) {
      if (var1 != 0 && var1 != 1) {
         throw new IllegalArgumentException(Strings.ff("Illegal mode: %d! Valid modes accepted by the fast-simulator are 0 and 1", var1));
      } else {
         this.za = var1;
      }
   }

   public void q(boolean var1) {
      this.lm = var1;
   }

   public IERoutineContext q() {
      return this.oW;
   }

   public IESimulationResults RF() {
      return this;
   }

   @Override
   public boolean recordComment(long var1, String var3) {
      if (Strings.isBlank(var3)) {
         return false;
      } else {
         this.HF.put(var1, var3);
         return true;
      }
   }

   @Override
   public boolean recordSupportRoutineCall(long var1, INativeMethodItem var3) {
      this.LK.put(var1, var3);
      return true;
   }

   public IEImm q(int var1, int var2) {
      return this.q(var1, var2, false);
   }

   public IEImm RF(int var1, int var2) {
      return this.q(var1, var2, true);
   }

   public IEImm q(int var1, int var2, boolean var3) {
      Couple var4 = this.nf.getInstructionLocation(var1);
      if (var4 == null) {
         return null;
      } else {
         List var5 = (List)((BasicBlock)var4.getFirst()).getData(var3 ? "postCompRegs" : "preCompRegs");
         if (var5 == null) {
            return null;
         } else {
            Map var6 = (Map)var5.get((Integer)var4.getSecond());
            return var6 == null ? null : (IEImm)var6.get(var2);
         }
      }
   }

   public int xK() {
      IVirtualMemory var1 = this.oW.getGlobalContext().getNativeMemory();
      IVirtualMemoryShim var2 = VirtualMemoryUtil.getCopyOnWriteShim(var1);

      try {
         return this.q(var2);
      } catch (Exception var4) {
         Assert.debugFail(var4);
         return 0;
      }
   }

   private int q(IVirtualMemoryShim var1) {
      if (this.oW.usesCopyVars()) {
         throw new IllegalStateException();
      } else {
         IEVar var2 = this.oW.getStackPointer();
         int var3 = var2.getId();
         int var4 = this.oW.getProgramCounterId();
         IdentityHashMap var5 = new IdentityHashMap(this.nf.size());

         for (BasicBlock var7 : this.nf.getBlocks()) {
            aot var8 = new aot(this.nf, var7);
            var5.put(var7, var8);
         }

         BasicBlock var48 = this.nf.getEntryBlock();
         Assert.a(var48.getFirstAddress() == 0L);
         EState var50 = this.oW.buildEmptyState();
         var50.setExecuteSubRoutines(true);
         var50.setMemory(var1);
         long var52 = this.oW.getRoutine().getMemoryAddress();
         var50.setValue(var4, var52);
         if (this.za == 0) {
            this.oW.getConverter().initializeStateRegisters(var50, var52);
         } else {
            this.oW.getConverter().customInitStateRegisters(var50, var52);
         }

         long var10 = this.Hk;
         var50.setValue(var2, var10);
         long var12 = (var10 & -4096L) - 65536L;
         int var14 = 131072;
         long var15 = var12 + var14;
         VirtualMemoryUtil.allocateFillGaps(var1, var12, var14, 3);
         var50.setNativeStackStart(var12);
         var50.setNativeStackEnd(var15);
         if (this.za == 1) {
            var50.setSoftFailMode(true);
            var50.writeMemoryBad(var12, var14 * 8, 2);
         }

         int var17 = var1.getSpaceBits() / 8;
         this.Me = -3819410105738241352L / var17 * var17;
         this.Me = this.oW.getConverter().sanitizeNativeAddress(-3819410105738241352L);
         this.PV = new anz.eo(this.oW);
         if (this.PV.q()) {
            this.PV.q(var50, this.Me);
         }

         var50.enableMemoryWriteHitmap(this.zz, true);
         ((aot)var5.get(var48)).xK = var50;
         ArrayDeque var18 = new ArrayDeque();
         var18.add(var48);
         IdentityHashMap var19 = new IdentityHashMap();
         int var20 = 30;
         SimulationPointInformation var21 = new SimulationPointInformation();
         var21.cfg = this.nf;

         while (!var18.isEmpty()) {
            var48 = (BasicBlock)var18.remove();
            var21.bb = var48;
            aot var23 = (aot)var5.get(var48);
            if (var23.xK == null) {
               throw new RuntimeException("No input tracker for block: " + var48);
            }

            var50 = new EState(var23.xK, false);
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
               if (this.gP != null) {
                  this.gP.getExtensionsManager().augmentSimulationContext(this.gP, this.oW, this, var26, var29, var50);
               }

               int var35 = this.q(var26, var29, var50, this.io, this.qa);
               if (var35 == -1) {
                  Object[] var69 = new Object[0];
                  var34 = 2;
               }

               if (var34 == 0) {
                  if (EUtil.isPCAssign(var29)) {
                     int var36 = this.q(var48, (IEAssign)var29, var26, var50);
                     if (var36 == 2) {
                        return 2;
                     }
                  }

                  if (var29 instanceof IECall || var29 instanceof IEAssign && ((IEAssign)var29).isRoutineCall()) {
                     var22 = new HashMap((Map)var22);
                     var50.setVariables((Map)var22);
                     boolean var57 = false;
                     if (!(var29 instanceof IECall)) {
                        if (this.zz) {
                           IEBranchDetails var37 = ((IEAssign)var29).getBranchDetails(true);
                           var37.setPreInvocationDetails(new PreRoutineInvocationDetails(this.oW, var50));
                        }

                        try {
                           if (var29.evaluate(var50) == null) {
                              var57 = true;
                           }
                        } catch (Exception var46) {
                           var57 = true;
                        }

                        var50.setRoutineContext(this.oW);
                     }

                     Integer var59;
                     if (var29 instanceof IECall) {
                        var59 = ((IECall)var29).getStackPointerDeltaAfterExecution();
                     } else {
                        Integer var38 = null;

                        try {
                           var21.state = var50;
                           var21.instructionEmulationFailed = var57;
                           var38 = this.oW.getConverter().determineStackPointerDeltaFromSimulation(var21);
                        } catch (Exception var45) {
                        }

                        if (var38 != null) {
                           Object[] var70 = new Object[]{var38};
                           var59 = var38;
                           IEBranchDetails var39 = ((IEAssign)var29).getBranchDetails(true);
                           var39.getStackPointerDeltaDeterminer().add(var59, var20, 10);
                        } else {
                           Integer var63 = null;
                           aoq var40 = new aoq(this.oW);
                           if (var40.q(var48, true)) {
                              IWildcardPrototype var41 = var40.q();
                              if (var41 != null) {
                                 var63 = this.oW.getConverter().determineStackPointerDeltaAfterIRCall(var41, null);
                              }
                           }

                           if (var63 != null) {
                              var59 = var63;
                              var20 = 30;
                           } else {
                              IEBranchDetails var67 = ((IEAssign)var29).getBranchDetails();
                              if (var67 == null) {
                                 var59 = this.oW.getConverter().getDefaultBranchToRoutineSideEffects(null).getStackPointerDeltaValue();
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
                           IEVar var66 = this.oW.getGlobalContext().getVar(var64);

                           try {
                              long var68 = this.oW.getConverter().getNativeRegisterIdFromRegisterVariable(var66);
                              if (((AbstractConverter)this.oW.getConverter()).isPossibleSpoiledRegistersForProcessorCallingConventions(var68)) {
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
            var23.Dw = new EState(var50, false);
            var23.Dw.setVariables((Map)var22);

            for (BasicBlock var54 : var48.getOutputBlocks()) {
               aot var55 = (aot)var5.get(var54);
               if (var55.xK == null) {
                  var55.xK = new EState(var23.Dw, false);
                  if (this.lm) {
                     var55.xK.setMemory(var1.duplicate());
                  }

                  var18.add(var54);
               }
            }
         }

         return 1;
      }
   }

   private int q(long var1, IEStatement var3, EState var4, Collection var5, MultiMap var6) {
      AtomicInteger var7 = new AtomicInteger();
      Long var8 = var3.getPrimaryLowerLevelAddress();
      aoa var9 = new aoa(this, var4, var5, var8, var6, var7);
      return var3.visitDepthPost(var9) ? var7.get() : -1;
   }

   private int q(BasicBlock var1, IEAssign var2, long var3, EState var5) {
      if (!this.PV.q()) {
         return 0;
      } else {
         Long var6 = this.PV.q(var5);
         if (var6 == null) {
            return 0;
         } else {
            var6 = this.oW.getConverter().sanitizeNativeAddress(var6);
            Long var7 = var2.getPrimaryLowerLevelAddress();
            if (var7 == null) {
               return 0;
            } else if (!(this.gO.getNativeItemAt(var7) instanceof INativeInstructionItem var9)) {
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
                           this.gO.requestRoutineReanalysis(this.oW.getRoutine());
                           if (var1.outsize() == 1) {
                              long var18 = var1.getOutputBlock(0).getBase();
                              if (var18 == var3 + var2.getSize()) {
                                 return 1;
                              }
                           }

                           return 2;
                        }
                     } else if (var6 != this.Me) {
                        return 0;
                     } else if (this.PV.RF()) {
                        return 0;
                     } else {
                        try {
                           long var14 = var2.getSrcOperand().evaluateAddress(var5);
                           if (var14 == this.Me) {
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
                           this.gO.requestRoutineReanalysis(this.oW.getRoutine());
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

   private String gO() {
      EDefUseInfo var1 = new EDefUseInfo();
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = new StringBuilder();

      for (BasicBlock var5 : this.nf.getBlocks()) {
         List var6 = (List)var5.getData("preCompRegs");
         List var7 = (List)var5.getData("postCompRegs");
         int var8 = 0;

         for (AddressableInstruction var10 : var5.addressableInstructions()) {
            long var11 = var10.getOffset();
            IEStatement var13 = (IEStatement)var10.getInstruction();
            Long var14 = var13.getPrimaryLowerLevelAddress();
            String var15 = var14 == null ? "?" : Strings.ff("%08Xh", var14);
            Strings.ff(var3, "%04Xh/%s: ", var11, var15);
            Strings.ff(var3, "%-100s  ", var13.format(var11));
            var1.clear();
            var13.getDefUse(var1);
            var2.clear();
            var1.getDef().collectVarIds(var2);
            ArrayList var16 = new ArrayList();
            var1.clear();
            var13.getExplicitlyUsed(var1);
            var1.getUse().collectVarIds(var16);
            if (var6 != null) {
               var3.append("    PRE={");
               TreeMap var17 = new TreeMap((Map)var6.get(var8));
               int var18 = 0;
               boolean var19 = true;
               boolean var20 = true;
               if (var19) {
                  for (int var22 : var17.keySet()) {
                     if (var20 || ((IEImm)var17.get(var22))._signum() != 0) {
                        if (var18 >= 1) {
                           var3.append(", ");
                        }

                        this.q(var3, var22, (IEImm)var17.get(var22));
                        var18++;
                     }
                  }
               } else {
                  for (int var30 : var16) {
                     if (var17.containsKey(var30)) {
                        if (var18 >= 1) {
                           var3.append(", ");
                        }

                        this.q(var3, var30, (IEImm)var17.get(var30));
                        var18++;
                     }
                  }
               }

               var3.append("}");
            }

            if (var7 != null) {
               var3.append("    POST={");
               TreeMap var23 = new TreeMap((Map)var7.get(var8));
               int var24 = 0;
               boolean var25 = true;
               boolean var26 = true;
               if (var25) {
                  for (int var31 : var23.keySet()) {
                     if (var26 || ((IEImm)var23.get(var31))._signum() != 0) {
                        if (var24 >= 1) {
                           var3.append(", ");
                        }

                        this.q(var3, var31, (IEImm)var23.get(var31));
                        var24++;
                     }
                  }
               } else {
                  for (int var32 : var2) {
                     if (var23.containsKey(var32)) {
                        if (var24 >= 1) {
                           var3.append(", ");
                        }

                        this.q(var3, var32, (IEImm)var23.get(var32));
                        var24++;
                     }
                  }
               }

               var3.append("}");
            }

            var3.append('\n');
            var8++;
         }
      }

      return var3.toString();
   }

   private void q(StringBuilder var1, int var2, IEImm var3) {
      String var4 = this.oW.getVariableById(var2).getName();
      var1.append(var4).append('=');
      if (var3 == null) {
         var1.append("BAD!");
      } else {
         var1.append(var3.toHexString().toUpperCase()).append('h');
      }
   }

   public int q(boolean[] var1) {
      if (!(this.oW.getNativeContext() instanceof INativeCodeUnit)) {
         return -1;
      } else {
         INativeCodeUnit var2 = (INativeCodeUnit)this.oW.getNativeContext();
         IEConverter var3 = this.oW.getConverter();
         INativeCodeAnalyzer var4 = var2.getCodeAnalyzer();
         NativeCommentManager var5 = var2.getCommentManager();
         IReferenceManager var6 = var2.getCodeModel().getReferenceManager();
         ICallGraph var7 = var2.getCodeModel().getCallGraphManager().getGlobalCallGraph();
         INativeCodeModel var8 = var2.getCodeModel();
         int var9 = 0;
         int var10 = 0;

         for (Entry var12 : this.HF.entrySet()) {
            Long var13 = this.oW.convertIntermediateOffset(((Long)var12.getKey()).intValue());
            if (var13 != null) {
               String var14 = (String)var12.getValue();
               var5.addMetaComment(var5.memoryToAddress(var13), new MetaComment(var14), false);
            }
         }

         for (Entry var41 : this.LK.entrySet()) {
            Long var43 = this.oW.convertIntermediateOffset(((Long)var41.getKey()).intValue());
            if (var43 != null) {
               INativeMethodItem var45 = (INativeMethodItem)var41.getValue();
               if (var2.recordDynamicBranchTarget(var43, true, new BranchTarget(var45))) {
                  ((abl)var6).recordExternalReference(var43, var45, ReferenceType.ROUTINE_CALL);
                  var7.recordExternalCall(var43, var45, false);
                  var9++;
                  var10++;
               }
            }
         }

         int var40 = var3.getProgramCounter().getId();
         MultiMap var46 = new MultiMap();

         for (BasicBlock var16 : this.nf.getBlocks()) {
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

                  if (var22 instanceof IEAssign && ((IEAssign)var22).getLeftOperand() == var3.getProgramCounter()) {
                     IEAssign var52 = var22.asAssign();
                     IEGeneric var53 = var52.getRightOperand();
                     if (!(var53 instanceof IEImm) && !this.q(var53)) {
                        Map var55 = (Map)var18.get(var19);
                        if (var55.containsKey(var40)) {
                           IEImm var57 = (IEImm)var55.get(var40);
                           if (var57.canReadAsAddress()) {
                              long var59 = var57.getValueAsAddress();
                              if (var59 != 0L && var59 != var23) {
                                 EState var62 = new EState(var2.getProcessor().getEndianness());
                                 var62.setVariables(var55);
                                 CodePointer var34 = var2.getProcessor().createEntryPoint(var59, this.oW.getConverter().getStateProcessorMode(var62));
                                 MemoryRanges var64 = ((aae)var4).q();
                                 if (var64 != null && var64.count() != 0 && !var64.contains(var59)) {
                                    continue;
                                 }

                                 if (var4.recordDynamicBranchTarget(var23, false, new BranchTarget(var34), false)) {
                                    if (var24) {
                                       var7.recordInternalCall(var23, var34, false);
                                       var4.enqueuePointerForAnalysis(var34, 0, 0);
                                    } else {
                                       axp var36 = ((aaf)var8).oW(var59);
                                       if (var25) {
                                          var4.enqueuePointerForAnalysis(var34, 0, 1);
                                          if (var36 != null) {
                                             var7.recordInternalCall(var23, var34, false);
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
                     List var42 = var27.getDefinedVarIds();
                     List var44 = var27.getUsedVarIds();
                     List var28 = var46.get(var23);
                     if (var17 != null) {
                        Map var29 = (Map)var17.get(var19);

                        for (int var31 : var44) {
                           if (this.RF(var31) && var29.containsKey(var31) && (var28 == null || !var28.contains(var31))) {
                              IEImm var32 = (IEImm)var29.get(var31);
                              if (var32 != null && var32.canReadAsLong()) {
                                 long var33 = var32.getValueAsLong();

                                 try {
                                    long var35 = var3.getNativeRegisterIdFromRegisterVariable(this.oW.getVariableById(var31));
                                    if (var33 != 0L && var4.recordDynamicRegisterValue(var23, false, var35, var33)) {
                                       var9++;
                                    }

                                    var46.put(var23, var31);
                                    if (var28 == null) {
                                       var28 = var46.get(var23);
                                    }
                                 } catch (Exception var38) {
                                 }
                              }
                           }
                        }
                     }

                     if (var18 != null) {
                        Map var54 = (Map)var18.get(var19);

                        for (int var58 : var42) {
                           if (var58 != var40 && this.RF(var58) && var54.containsKey(var58)) {
                              IEImm var60 = (IEImm)var54.get(var58);
                              if (var60 != null && var60.canReadAsLong()) {
                                 long var61 = var60.getValueAsLong();

                                 try {
                                    long var63 = var3.getNativeRegisterIdFromRegisterVariable(this.oW.getVariableById(var58));
                                    if (var61 != 0L && var4.recordDynamicRegisterValue(var23, true, var63, var61)) {
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

         for (Pointer var48 : this.io) {
            if (var4.enqueuePointerForAnalysis(var48)) {
               List var49 = this.qa.get(var48);
               if (var49 != null) {
                  for (Long var51 : var49) {
                     var8.getReferenceManager().recordInternalReference(var51, var48.getAddress(), ReferenceType.GEN_DATA, 2);
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

   private boolean RF(int var1) {
      return var1 >= 0 && var1 < 65536;
   }

   private boolean q(IEGeneric var1) {
      return var1 instanceof IECond && ((IECond)var1).getExpressionTrue() instanceof IEImm && ((IECond)var1).getExpressionFalse() instanceof IEImm;
   }

   public int Dw() {
      return this.JY;
   }

   public Set Uv() {
      return this.io;
   }

   public int oW() {
      IEVar var1 = this.oW.getStackPointer();
      int var2 = 0;

      for (BasicBlock var4 : this.nf.getBlocks()) {
         List var5 = (List)var4.getData("preCompRegs");
         int var6 = 0;

         for (IEStatement var8 : var4) {
            Map var9 = (Map)var5.get(var6);
            if (var9 != null) {
               IEImm var10 = (IEImm)var9.get(var1.getId());
               if (var10 != null) {
                  int var11 = (int)(var10.getValueAsLong() - this.Hk);
                  var8.setSPDelta(var11);
                  var2++;
               }
            }

            var6++;
         }
      }

      return var2;
   }

   static class eo {
      IERoutineContext q;
      IEVar RF;
      Integer xK;
      int Dw;
      int Uv;

      eo(IERoutineContext var1) {
         this.q = var1;
         this.Dw = var1.getStackPointer().getId();
         this.Uv = var1.getStackManager().getNormalSlotSize();
         ICallingConvention var2 = var1.getNativeContext().getTypeManager().getCallingConventionManager().getDefaultConvention();
         StorageEntry var3 = var2.getReturnAddressSlot();
         if (var3 != null) {
            if (var3.isRegisterBased() && var3.getRegisters().size() == 1) {
               IEGeneric var4 = var1.getConverter().getRegisterVariableFromNativeRegisterId(var3.getValue());
               if (var4 instanceof IEVar) {
                  this.RF = (IEVar)var4;
               }
            } else if (var3.isStackBased()) {
               this.xK = var3.getValueAsStackIndex();
            }
         }
      }

      boolean q() {
         return this.RF != null || this.xK != null;
      }

      boolean RF() {
         return this.RF != null;
      }

      boolean xK() {
         return this.xK != null;
      }

      Long q(EState var1) {
         try {
            if (this.RF != null) {
               return var1.getValue(this.RF.getId()).getValueAsAddress();
            }

            if (this.xK != null && var1.hasValue(this.Dw)) {
               long var2 = var1.getValue(this.Dw).getValueAsAddress();
               return this.q.createMem(EUtil.imm(var2 + this.Uv * this.xK, var1.getMemory().getSpaceBits()), this.Uv * 8).evaluate(var1).getValueAsAddress();
            }
         } catch (Exception var4) {
         }

         return null;
      }

      boolean q(EState var1, long var2) {
         try {
            if (this.RF != null) {
               var1.setValue(this.RF.getId(), var2);
               return true;
            }

            if (this.xK != null && var1.hasValue(this.Dw)) {
               long var4 = var1.getValue(this.Dw).getValueAsAddress();
               return var1.writeMemoryPointer(var4, var2);
            }
         } catch (Exception var6) {
         }

         return false;
      }
   }
}
