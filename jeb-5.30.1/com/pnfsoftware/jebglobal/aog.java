package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.DecompilationOptions;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.PreRoutineInvocationDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrototypeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class aog {
   private static final StructuredLogger lm = aeg.q(aog.class);
   IEConverter q;
   INativeDecompilerExtensionsManager RF;
   INativeDecompilerContext xK;
   IWildcardTypeManager Dw;
   IEGlobalContext Uv;
   IERoutineContext oW;
   CFG gO;
   boolean nf = true;
   IWildcardPrototype gP;
   public int[] za;

   public aog(IERoutineContext var1, INativeDecompilerExtensionsManager var2, INativeDecompilerContext var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var2;
         this.xK = var3;
         this.oW = var1;
         this.Uv = var1.getGlobalContext();
         this.q = var1.getConverter();
         this.Dw = var1.getWildcardTypeManager();
      }
   }

   public void q(boolean var1) {
      this.nf = var1;
   }

   public boolean q() {
      return this.nf;
   }

   public IWildcardPrototype RF() {
      return this.gP;
   }

   public IWildcardPrototype q(anl var1) {
      IWildcardPrototype var2 = this.RF(var1);
      this.gP = var2;
      return var2;
   }

   private IWildcardPrototype RF(anl var1) {
      if (this.gO != null) {
         throw new IllegalStateException();
      } else {
         this.gO = this.oW.getCfg();
         INativeMethodItem var3 = var1.xK();
         if (var3 != null) {
            IWildcardPrototype var2 = this.Uv.getCandidatePrototype(var3);
            if (var2 != null) {
               return var2;
            }
         }

         int var4 = 0;

         for (INativeMethodItem var6 : var1.Dw()) {
            IWildcardPrototype var37 = this.Uv.getCandidatePrototype(var6);
            if (var37 != null) {
               return var37;
            }

            if (var6.getData() != null) {
               int var7 = var6.getData().getCFG().getEffectiveSize();
               if (var3 == null || var7 < var4) {
                  var3 = var6;
                  var4 = var7;
               }
            }
         }

         BasicBlock var39 = this.gO.getBlockContaining(var1.RF());
         AddressableInstruction var40 = var39.getLast2();
         if (!EUtil.isPCAssign((IEStatement)var40.getInstruction())) {
            return null;
         } else {
            IEAssign var41 = (IEAssign)var40.getInstruction();
            long var8 = var40.getOffset();
            IEGeneric var10 = var41.getSrcOperand();
            IEBranchDetails var11 = var41.getBranchDetails();
            if (var11 == null) {
               var11 = this.oW.getConverter().getDefaultBranchToRoutineSideEffects(null);
            }

            Object[] var10000 = new Object[]{var11};
            if (var11.getNativePrototypeHint() != null) {
               IWildcardPrototype var38 = this.Dw.createPrototype(var11.getNativePrototypeHint());
               if (var38 != null) {
                  return var38;
               }
            }

            PreRoutineInvocationDetails var12 = var11.getPreInvocationDetails();
            IWildcardPrototype var13 = null;
            boolean var14 = false;
            if (this.nf && var3 != null && var3.getData() != null && var3.getData().getTrampolineTarget() == null) {
               boolean[] var15 = new boolean[1];
               var13 = this.q(var3, var15);
               var14 = var15[0];
            }

            ICallingConvention var42 = null;
            Object var16 = null;
            Object var17 = null;
            if (var13 == null || var14) {
               IDFA var18 = this.gO.doDataFlowAnalysis();
               this.oW.getCfg();
               var10000 = new Object[]{var1};
               Map var19 = var18.getReachChains(var39, var39.size() - 1);
               Set var20 = var19.keySet();
               Map var21 = var18.getLiveChains(var39, var39.size());
               Set var22 = var21.keySet();
               Collection var23 = var18.getUseDiscrepancies(var8);
               var16 = new ArrayList();
               var17 = new ArrayList();
               var42 = this.Dw.getNativeTypeManager().getCallingConventionManager().getDefaultConvention();
               if (var42 == null) {
                  return null;
               }

               var10000 = new Object[]{var42};
               List var24 = var12 == null ? null : var12.getStackSlotAges();
               List var25 = var12 == null ? null : var12.getStackSlotValues();
               byte var26 = 8;
               byte var27 = 8;
               byte var28 = 3;
               boolean var29 = true;
               IStorageEntryGenerator var30 = var42.getInputsGenerator();

               for (int var31 = 0; var31 < var26; var31++) {
                  StorageEntry var32 = var30.next(TypeLayoutInfo.i1);
                  if (var32 == null) {
                     break;
                  }

                  boolean var33 = false;
                  if (var32.getType() == StorageEntry.Type.REGISTER) {
                     Integer var34 = EUtil.getVarSliceId(this.oW.getConverter().getRegisterVariableFromNativeRegisterId(var32.getValue()));
                     if (var34 != null
                        && var20.contains(var34)
                        && !((Collection)var19.get(var34)).isEmpty()
                        && var23 != null
                        && !var23.contains(var34)
                        && !EUtil.getUsedVarIds(var10).contains(var34)) {
                        boolean var35 = false;
                        Collection var36 = (Collection)var19.get(var34);
                        if (var36.size() == 1 && (Long)var36.iterator().next() == -1L) {
                           var36 = var18.getInputMap(var34);
                           if (var36.size() == 1 && (Long)var36.iterator().next() == var8) {
                              var35 = true;
                           }
                        }

                        if (!var35) {
                           var17.add(this.Dw.createWithSlotcount(1));
                           var33 = true;
                        }
                     }
                  } else if (var32.getType() == StorageEntry.Type.STACK && var24 != null && var25 != null) {
                     int var48 = var32.getValueAsStackIndex();
                     if (var48 < var24.size() && var48 < var25.size()) {
                        int var50 = (Integer)var24.get(var48);
                        if (var50 <= var27) {
                           IEImm var53 = (IEImm)var25.get(var48);
                           if (var29 || var53 != null && var53._signum() != 0) {
                              var17.add(this.Dw.createWithSlotcount(1));
                              var27 += var28;
                              var33 = true;
                           }
                        }
                     }
                  }

                  if (!var33) {
                     break;
                  }
               }

               StorageEntry var46 = var42.getOutput(TypeLayoutInfo.i1, 0);
               if (var46 != null && var46.getType() == StorageEntry.Type.REGISTER) {
                  Integer var47 = EUtil.getVarSliceId(this.oW.getConverter().getRegisterVariableFromNativeRegisterId(var46.getValue()));
                  if (var47 != null) {
                     if (var22.contains(var47)) {
                        var16.add(this.Dw.createWithSlotcount(1));
                     } else {
                        IWildcardPrototype var49 = this.oW.getPrototype();
                        if (var49 != null) {
                           IWildcardType var51 = var49.getReturnType();
                           if (!var51.isVoid()) {
                              var16.add(this.Dw.createWithSlotcount(1));
                           }
                        } else if (this.q(var47, var8, var18)) {
                           var16.add(this.Dw.createWithSlotcount(1));
                        }
                     }
                  }
               }
            }

            if (var42 != null && var13 != null) {
               this.za = new int[]{var13.getParameterTypes().size(), var17.size()};
            }

            if (var42 == null || var13 != null && this.za[0] <= this.za[1]) {
               var17 = var13.getParameterTypes();
               var16 = var13.getReturnTypes();
               var42 = var13.getCallingConvention();
            }

            ArrayList var43 = new ArrayList();
            if (var12 != null) {
               aob var44 = new aob(this.oW, var41, var12, var42, (List)var17);
               if (var44.q(true)) {
                  for (int var45 = 0; var45 < var44.RF(); var45++) {
                     var17.remove(var17.size() - 1);
                  }

                  var43.add(PrototypeAttribute.VarArg);
               }
            }

            return this.Dw.createPrototype(var42, (List)var16, (List)var17, var43);
         }
      }
   }

   private boolean q(int var1, long var2, IDFA var4) {
      boolean var5 = false;

      for (BasicBlock var7 : this.gO.getExitBlocks()) {
         Map var8 = var4.getOutputMap(var7);
         if (!var8.containsKey(var1)) {
            return false;
         }

         if (((Collection)var8.get(var1)).contains(var2)) {
            var5 = true;
         }
      }

      return var5;
   }

   private IWildcardPrototype q(INativeMethodItem var1, boolean[] var2) {
      if (this.xK != null && var1 != null && var1.getData() != null) {
         aea var3 = (aea)this.xK.getEngine();
         IDecompiledMethod var4 = null;

         Object var48;
         try {
            DecompilationContext var5 = var3.xK();
            var5 = DecompilationContext.safe(var5);
            DecompilationOptions var6 = var5.addFlags(0);

            try {
               lm.beginSection("[RECURSE] Partial decompilation of invoked routine");
               var4 = var3.q(var1, var5, avy.xK, 1, false, true);
               if (var4 == null || var4.getErrorDescription() != null) {
                  return null;
               }
            } catch (Exception var43) {
               lm.catching(var43, "Partial decompilation of the sub failed!");
               return null;
            } finally {
               var5.setOptions(var6);
               lm.closeSection();
            }

            IERoutineContext var7 = var4.getIRContext();
            if (var7 != null) {
               apk var49 = new apk(var7, true);

               try {
                  lm.beginSection("Stack analyzer...");
                  var49.nf();
               } finally {
                  lm.closeSection();
               }

               aoy var10 = new aoy(var7);
               SPDC var11 = SPDC.getBest(var49.q(), ((aml)var7).oW());
               Object[] var10000 = new Object[]{var11};
               SPDC var12 = SPDC.getBest(var49.RF(), ((aml)var7).gO());
               var10000 = new Object[]{var12};
               var10.q(var11, var12);
               IWildcardPrototype var13 = var10.q();
               boolean var14 = false;
               if (var13 != null) {
                  ICallingConvention var15 = var13.getCallingConvention();
                  IStorageEntryGenerator var16 = var15.getInputsGenerator();
                  StorageEntry var17 = null;
                  int var18 = 0;

                  for (IWildcardType var20 : var13.getParameterTypes()) {
                     var17 = var16.next(var20.getLayoutInfo());
                     if (var17 == null) {
                        break;
                     }

                     var18 = var17.nextSlotIndex(var18, var20.getSlotCount());
                  }

                  if (var17 != null && var17.isRegisterBased()) {
                     long var51 = (Long)var17.getRegisters().iterator().next();
                     IEGeneric var21 = this.q.getRegisterVariableFromNativeRegisterId(var51);
                     if (var21 instanceof IEVar) {
                        int var22 = var21.asVar().getId();
                        CFG var23 = var7.getCfg();
                        IDFA var24 = var23.doDataFlowAnalysis();
                        Collection var25 = var24.getInputMap(var22);
                        var14 = true;
                        if (var25 != null) {
                           for (Long var27 : var25) {
                              IEStatement var28 = (IEStatement)var23.getInstruction(var27);
                              if (var28.examine(var1x -> var1x.isVar() && var1x.asVar().getId() == var22)) {
                                 var14 = false;
                                 break;
                              }
                           }
                        }
                     }
                  }
               }

               var2[0] = var14;
               return var13;
            }

            var48 = null;
         } finally {
            if (var4 != null) {
               var3.q(var4);
            }
         }

         return (IWildcardPrototype)var48;
      } else {
         return null;
      }
   }
}
