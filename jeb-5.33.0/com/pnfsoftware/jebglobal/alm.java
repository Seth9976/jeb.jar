package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchResolution;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class alm {
   private static final StructuredLogger gp = aco.pC(alm.class);
   IERoutineContext pC;
   INativeDecompilerContext A;
   INativeDecompilerExtensionsManager kS;
   IEGlobalContext wS;
   INativeContext UT;
   IEConverter E;
   IWildcardTypeManager sY;
   int ys;
   boolean ld;
   private boolean oT;

   public alm(IERoutineContext var1, INativeDecompilerContext var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = var2;
         this.kS = var2 == null ? null : var2.getExtensionsManager();
         this.wS = var1.getGlobalContext();
         this.E = (AbstractConverter)var1.getConverter();
         this.UT = this.E.getNativeContext();
         this.ys = this.E.getAddressBitsize();
         this.sY = this.E.getGlobalContext().getWildcardTypeManager();
      }
   }

   public void pC(boolean var1) {
      this.ld = var1;
   }

   public boolean pC() {
      return this.oT;
   }

   public void A() {
      this.oT = false;
   }

   public ali kS() {
      return this.pC((alh)null);
   }

   public ali pC(alh var1) {
      CFG var2 = this.pC.getCfg();
      ali var3 = new ali();
      int var4 = 0;

      for (int var5 = 0; var5 < var2.size(); var5++) {
         BasicBlock var6 = var2.get(var5);
         AddressableInstruction var7 = var6.getLast2();
         if (var7.getInstruction() instanceof IEAssign) {
            IEAssign var8 = (IEAssign)var7.getInstruction();
            if (var8.getLeftOperand() == this.pC.getProgramCounter()) {
               long var9 = var7.getOffset();
               IEGeneric var11 = var8.getRightOperand();
               amj var12 = new amj(this.pC);
               if (var12.pC(var6, false)
                  ? var8.isRoutineCall()
                     || (var12.A == null ? var12.wS != null && !var12.wS.isEmpty() : var12.A != this.pC.getRoutine()) && var8.upgradeBreakToCall(var9)
                  : var8.isRoutineCall()) {
                  Long var13 = var12.pC;
                  IWildcardPrototype var14 = var12.kS;
                  INativeMethodItem var15 = var12.A;
                  List var16 = var12.wS;
                  List var17 = var12.UT;
                  if (!(var11 instanceof IEImm) && var13 != null) {
                     Long var18 = var8.getPrimaryLowerLevelAddress();
                     if (var18 != null) {
                        Long var19 = this.pC.convertNativeAddress(var18);
                        if (var19 == (int)var7.getOffset()) {
                           this.pC(var18, true, var13);
                        }
                     }
                  }

                  if (var15 != null) {
                     var14 = this.wS.getCandidatePrototype(var15);
                  }

                  boolean var26 = false;
                  if (var14 == null) {
                     var26 = true;
                     alg var27 = new alg(var7, var15, var16);
                     if (!this.ld) {
                        var3.A.add(var27);
                        continue;
                     }

                     try {
                        aly var20 = new aly(this.pC, this.kS, this.A);
                        Object[] var10000 = new Object[]{var27};
                        var14 = var20.pC(var27);
                     } catch (Exception var25) {
                        JebCoreService.notifySilentExceptionToClient(var25);
                     }

                     if (var14 == null) {
                        var14 = this.E.buildFailsafePrototype(this.pC, var8);
                        if (var14 == null) {
                           throw new RuntimeException("Converters must provide fail-safe prototypes!");
                        }
                     }
                  }

                  List var28 = null;
                  if (var14.isVariableArgument()) {
                     try {
                        ano var29 = new ano(this.pC, this.kS);
                        var28 = var29.pC(var2, var5, var14, var15);
                     } catch (Exception var24) {
                        JebCoreService.notifySilentExceptionToClient(var24);
                     }
                  }

                  Object var30 = var11;
                  if (var15 != null) {
                     if (this.wS.getCandidatePrototype(var15) == null) {
                        this.wS.setCandidatePrototype(var15, var14, var26 ? 0 : 1);
                     }

                     var30 = this.pC.createSymbolForRoutine(var15);
                     Assert.a(var30 != null);
                     var14 = null;
                  }

                  IECall var21 = this.pC.createCall((IEGeneric)var30, var17, var14, var28, var26);
                  if (var8.isTentativeCall()) {
                     var21.setTentativeCall(true);
                  }

                  IEBranchDetails var22 = var8.getBranchDetails();
                  if (var22 != null) {
                     var21.addSpoiledVariables(var22.getSpoiled());
                     SPDC var23 = var22.getStackPointerDelta();
                     if (var23.getGuarantee() >= 10 && var23.getOrigin() >= 10) {
                        var21.setStackPointerDeltaAfterExecution(var23.getValue());
                     }
                  }

                  var21.copyProperties(var8);
                  var6.set(var6.size() - 1, var21);
                  var3.pC++;
                  var4++;
               }
            }
         }
      }

      if (var4 > 0) {
         var2.invalidateDataFlowAnalysis();
      }

      return var3;
   }

   private boolean pC(long var1, boolean var3, long var4) {
      CodePointer var6 = this.UT.getProcessor().createEntryPoint(var4);
      if (var6.isUnknownAddress()) {
         return false;
      } else {
         if (this.UT.recordDynamicBranchTarget(var1, var3, new BranchTarget(var6))) {
            this.oT = true;
         }

         return true;
      }
   }

   public int wS() {
      int var1 = 0;
      CFG var2 = this.pC.getCfg();
      IDFA var3 = var2.doDataFlowAnalysis();

      for (BasicBlock var5 : var2) {
         AddressableInstruction var6 = var5.getLast2();
         IEStatement var7 = (IEStatement)var6.getInstruction();
         if (var7 instanceof IECall var8) {
            IEGeneric var9 = var8.getCallSite();
            IEGeneric var10 = this.E.normalizeBranchingExpression(var3, var5, var9, null);
            if (var10 != null && var10 != var9) {
               var8.setCallsite(var10);
               var1++;
            }
         } else if (var7 instanceof IEJumpFar var11) {
            IEGeneric var12 = var11.getJumpsite();
            IEGeneric var13 = this.E.normalizeBranchingExpression(var3, var5, var12, null);
            if (var13 != null && var13 != var12) {
               var11.setJumpsite(var13);
               var1++;
            }
         }
      }

      if (var1 > 0) {
         var2.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   public void UT() {
      CFG var1 = this.pC.getCfg();
      IDFA var2 = var1.doDataFlowAnalysis();

      for (BasicBlock var4 : var1) {
         AddressableInstruction var5 = var4.getLast2();
         IEStatement var6 = (IEStatement)var5.getInstruction();
         IEGeneric var7;
         Boolean var8;
         if (var6 instanceof IECall var9) {
            if (var9.isStaticCallsite()) {
               continue;
            }

            var7 = var9.getCallSite();
            var8 = var9.isFailsafePrototype();
         } else {
            if (!(var6 instanceof IEJumpFar var14)) {
               continue;
            }

            long var10 = var5.getOffset() + var14.getSize();
            if (var1.getBlockAt(var10) != null) {
               continue;
            }

            var7 = var14.getJumpsite();
            var8 = null;
         }

         Long var15 = var6.getPrimaryLowerLevelAddress();
         if (var15 != null) {
            IEGeneric var16 = this.E.normalizeBranchingExpression(var2, var4, var7, null);
            if (var16 != null) {
               if (var16 instanceof IEImm) {
                  long var17 = ((IEImm)var16).getValueAsAddress();
                  Long var13 = this.pC.convertNativeAddress(var15);
                  if (var13 == (int)var5.getOffset()) {
                     this.pC(var15, true, var17);
                  }
               } else {
                  long var11 = var5.getOffset();
                  if (!this.pC(var15, var16, var11, var1) && (var8 == null || var8) && var16.getType() != null && this.pC(var15, var6, var16)) {
                  }
               }
            }
         }
      }
   }

   public void E() {
      for (BasicBlock var3 : this.pC.getCfg()) {
         if (var3.outsize() == 0) {
            AddressableInstruction var4 = var3.getLast2();
            IEStatement var5 = (IEStatement)var4.getInstruction();
            if (var5 instanceof IECall var6) {
               IEGeneric var7 = var6.getReturnLocation();
               if (var7 instanceof IEImm) {
                  long var8 = var7.asImm().getValueAsAddress();
                  Long var10 = var6.getPrimaryLowerLevelAddress();
                  if (var10 != null) {
                     INativeContinuousItem var11 = this.UT.getNativeItemAt(var10);
                     if (var11 instanceof INativeInstructionItem) {
                        long var12 = var11.getMemoryAddressEnd();
                        if (var12 == var8) {
                           ((INativeInstructionItem)var11).getHints(true).setTailCall(true);
                           this.pC(var10, false, var8);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private boolean pC(long var1, IEStatement var3, IEGeneric var4) {
      IWildcardType var5 = var4.getType();
      if (var5 == null) {
         return false;
      } else {
         Object[] var10000 = new Object[]{var5, var1};
         IPrototypeItem var6 = null;
         IPrototypeItem[] var7 = new IPrototypeItem[1];
         if (TypeUtil.isFunctionPointer(var5.getNativeType(), var7)) {
            var6 = var7[0];
         }

         if (var6 == null) {
            return false;
         } else {
            INativeContinuousItem var8 = this.UT.getNativeItemAt(var1);
            if (!(var8 instanceof aus)) {
               return false;
            } else {
               if (var3 instanceof IECall var9) {
                  IWildcardPrototype var10 = this.sY.createPrototype(var6);
                  if (var10.getParameterTypes().isEmpty()) {
                     boolean var11 = false;
                     Object var12 = var10.getReturnTypes();
                     if (var9.getCountOfReturns() > var6.getReturnTypes().size()) {
                        var12 = new ArrayList();

                        for (IEGeneric var14 : var9.getReturnExpressions()) {
                           var12.add(var14.getType());
                        }

                        var11 = true;
                     }

                     Object var17 = var10.getParameterTypes();
                     if (var9.getCountOfArguments() > var6.getCountOfParameters()) {
                        var17 = new ArrayList();

                        for (IEGeneric var15 : var9.getArguments()) {
                           var17.add(var15.getType());
                        }

                        var11 = true;
                     }

                     if (var11) {
                        var6 = this.sY.createPrototype(var6.getCallingConvention(), (List)var12, (List)var17, var6.getPrototypeAttributes()).resolve();
                     }
                  }
               }

               InstructionHints var16 = ((aus)var8).getHints(true);
               if (var6 != null) {
                  var16.setCallsitePrototype(var6);
               }

               if (var3 instanceof IEJumpFar) {
                  var16.setTailCall(true);
               }

               this.oT = true;
               return true;
            }
         }
      }
   }

   private boolean pC(long var1, IEGeneric var3, long var4, CFG var6) {
      IBranchResolution var7 = this.UT.getDynamicBranchResolution(var1);
      if (var7.getCandidates() != null && !var7.getCandidates().isEmpty()) {
         return true;
      } else {
         IEGeneric[] var8 = new IEGeneric[]{var3};
         int[] var9 = new int[]{0};
         if (!this.pC(var8, var9)) {
            return false;
         } else {
            alm.Av var11 = new alm.Av(var4, var8[0], var9[0]);
            Object var10;
            if (var11.A instanceof IEImm) {
               var10 = new ArrayList();
               var10.add(var11);
            } else {
               if (!(var11.A instanceof IEVar)) {
                  return false;
               }

               ArrayList var12 = new ArrayList();
               var12.add(var11);
               var10 = this.pC(var6, var12);
            }

            Object[] var10000 = new Object[]{var10};
            int var16 = 0;

            for (alm.Av var14 : var10) {
               Long var15 = this.pC(var14);
               if (var15 != null && var15 != 0L && this.pC(var1, false, var15)) {
                  var16++;
               }
            }

            return var16 > 0;
         }
      }
   }

   private List pC(CFG var1, List var2) {
      IDFA var3 = var1.doDataFlowAnalysis();
      ArrayList var4 = new ArrayList();
      int var5 = 0;

      while (!var2.isEmpty()) {
         alm.Av var6 = (alm.Av)var2.remove(0);
         int var7 = ((IEVar)var6.A).getId();
         Collection var8 = var3.getUseDefs(var6.pC, var7);
         Iterator var9 = var8.iterator();

         while (true) {
            if (var9.hasNext()) {
               long var10 = (Long)var9.next();
               IEStatement var12 = (IEStatement)var1.getInstruction(var10);
               if (!(var12 instanceof IEAssign) || ((IEAssign)var12).getLeftOperand() != var6.A) {
                  continue;
               }

               IEGeneric var13 = ((IEAssign)var12).getRightOperand();
               IEGeneric[] var14 = new IEGeneric[]{var13};
               int[] var15 = new int[]{0};
               if (!this.pC(var14, var15)) {
                  continue;
               }

               alm.Av var16 = new alm.Av(var6);
               var16.A = var14[0];
               var16.kS = var16.kS + var15[0];
               var16.pC = var10;
               if (var16.A instanceof IEImm) {
                  var4.add(var16);
               } else if (var16.A instanceof IEVar) {
                  var2.add(var16);
               }

               if (var5++ < 50) {
                  continue;
               }
            }

            if (var5++ >= 50) {
               return var4;
            }
            break;
         }
      }

      return var4;
   }

   private boolean pC(IEGeneric[] var1, int[] var2) {
      IEGeneric var3 = var1[0];
      if (var3 instanceof IEImm) {
         var1[0] = var3;
         return true;
      } else if (var3 instanceof IEVar var4) {
         if (var4.isGlobalReference()) {
            var1[0] = this.pC.createImm(var4.getAddress(), this.ys);
         } else if (var4.isGlobalVariable()) {
            var1[0] = this.pC.createImm(var4.getAddress(), this.ys);
            var2[0]++;
         } else {
            var1[0] = var4;
         }

         return true;
      } else if (var3 instanceof IEMem) {
         var1[0] = ((IEMem)var3).getReference();
         var2[0]++;
         return this.pC(var1, var2);
      } else {
         return false;
      }
   }

   private Long pC(alm.Av var1) {
      IEImm var2 = (IEImm)var1.A;
      Long var3 = var2.getValueAsAddress();

      for (int var4 = var1.kS; var3 != null && var4-- > 0; var4--) {
         var3 = this.pC(var3, this.ys);
      }

      return var3;
   }

   public Long pC(long var1, int var3) {
      switch (var3) {
         case 8:
         case 16:
         case 32:
         case 64:
            return VirtualMemoryUtil.readAsUnsignedLongSafe(this.UT.getMemory(), this.UT.getProcessor().getEndianness(), var1, var3 / 8);
         default:
            return null;
      }
   }

   private static class Av {
      long pC;
      IEGeneric A;
      int kS;

      Av(long var1, IEGeneric var3, int var4) {
         this.pC = var1;
         this.A = var3;
         this.kS = var4;
      }

      Av(alm.Av var1) {
         this.pC = var1.pC;
         this.A = var1.A;
         this.kS = var1.kS;
      }

      @Override
      public String toString() {
         return Strings.ff("%dx[%s]", this.kS, this.A);
      }
   }
}
