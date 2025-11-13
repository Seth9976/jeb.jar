package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.DecompilerException;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aeg;
import com.pnfsoftware.jebglobal.amy;
import com.pnfsoftware.jebglobal.anw;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EPrototypeHandler implements IEPrototypeHandler {
   private static final StructuredLogger logger = aeg.q(EPrototypeHandler.class);
   protected IERoutineContext ctx;

   public EPrototypeHandler(IERoutineContext var1) {
      this.ctx = var1;
   }

   @Override
   public boolean applyKnownPrototype(boolean var1) {
      IWildcardPrototype var2 = this.ctx.getPrototype();
      if (var2 == null) {
         return false;
      } else {
         ICallingConvention var3 = var2.getCallingConvention();
         CFG var4 = this.ctx.getCfg();
         IEStackManager var5 = this.ctx.getStackManager();
         int var6 = var5.getNormalSlotSize();
         int var7 = 0;
         Collection var8 = null;
         IStorageEntryGenerator var9 = var3.getInputsGenerator();
         int var10 = 0;

         for (IWildcardType var12 : var2.getParameterTypes()) {
            IDFA var13 = var4.getDataFlowAnalysis();
            if (var13 == null) {
               var13 = var4.doDataFlowAnalysis();
               var8 = null;
            }

            if (var8 == null) {
               var8 = var13.getInputs();
            }

            IEVar var14 = this.ctx.getConverter().getInputVariableByIndex(this.ctx, var10);
            if (var14 != null) {
               var10++;
            } else {
               StorageEntry var15 = var9.next(var12.getLayoutInfo());
               switch (var15.getType()) {
                  case REGISTER:
                     EVarCopyFinder var37 = this.ctx.copyFinder(var15, var8, 0L);
                     IEGeneric var33 = var37.getIRForSlicedReg(var1);
                     if (var33 != null) {
                        if (var37.wasCreated()) {
                           Object[] var50 = new Object[]{var33};
                        }

                        this.ctx.setTypeForSame(var33, var12);
                     }
                     break;
                  case REGISTER_PAIR:
                     EVarCopyFinder var36 = this.ctx.copyFinder(var15, var8, 0L);
                     var14 = var36.getVarForRegPair(var1);
                     if (var14 != null) {
                        if (var36.wasCreated()) {
                           Object[] var10000 = new Object[]{var14};
                        }

                        var14.setType(var12);
                     }

                     if (var1) {
                        IEVar var41 = var36.getVarToSubstitute(0);
                        IEVar var18 = var36.getVarToSubstitute(1);
                        anw var19 = new anw(this.ctx, var4);
                        var19.q(-1L, var41.getId(), true);
                        var19.q(var41, var14.part(var41.getBitsize()));
                        var19.q(-1L, var18.getId(), true);
                        var19.q(var18, var14.slice(var41.getBitsize()));
                        var4.invalidateDataFlowAnalysis();
                     }
                     break;
                  case STACK:
                     if (var1) {
                        int var16 = var15.getValueAsStackIndex();
                        int var17 = var16 * var6;
                        var14 = var5.createStackItem(var17, var12.getBitsize());
                        if (var14 == null) {
                           throw new RuntimeException("Unexpected: stack parameter item cannot be created");
                        }

                        var14.setType(var12);
                        var7++;
                     }
                  case MIXED:
                     break;
                  default:
                     throw new DecompilerException("Input storage type not supported: " + var15.getType());
               }

               int var38 = var12.getSlotCount();
               var10 = var15.nextSlotIndex(var10, var38);
            }
         }

         var8 = null;
         if (var7 > 0) {
            var4.invalidateDataFlowAnalysis();
         }

         int var27 = EUtil.determineArgumentStackSlotCount(var2, null);
         var9 = var3.getOutputsGenerator(var27);
         HashMap var28 = new HashMap();
         var10 = 0;

         for (IWildcardType var34 : var2.getReturnTypes()) {
            StorageEntry var35 = var9.next(var34.getLayoutInfo());
            if (var35 == null) {
               var10++;
            } else {
               for (BasicBlock var42 : var4.getExitBlocks()) {
                  IDFA var43 = var4.getDataFlowAnalysis();
                  if (var43 == null) {
                     var43 = var4.doDataFlowAnalysis();
                     var8 = null;
                  }

                  if (var8 == null) {
                     var8 = var43.getOutputs(var42);
                  }

                  switch (var35.getType()) {
                     case REGISTER:
                        EVarCopyFinder var48 = this.ctx.copyFinder(var35, var8, var42.getLastAddress());
                        IEGeneric var46 = var48.getIRForSlicedReg(false);
                        if (var46 == null) {
                           logger.debug("Unexpected: return variable not found, we cannot apply the return type: " + var34);
                        } else {
                           this.ctx.setTypeForSame(var46, var34);
                        }
                        break;
                     case REGISTER_PAIR:
                        EVarCopyFinder var47 = this.ctx.copyFinder(var35, var8, var42.getLastAddress());
                        IEVar var45 = var47.getVarForRegPair(var1);
                        if (var1) {
                           IEVar var49 = var47.getVarToSubstitute(0);
                           IEVar var22 = var47.getVarToSubstitute(1);
                           IEAssign var23 = this.ctx.createAssign(var45, this.ctx.createCompose(var49, var22));
                           var23.setFlags(2);
                           var23.copyLowerLevelAddresses((IEStatement)var42.getLast());
                           var28.put((int)var42.getLastAddress(), var23);
                        }

                        if (var45 != null) {
                           var45.setType(var34);
                        }
                        break;
                     case STACK:
                        if (var1) {
                           int var20 = var35.getValueAsStackIndex();
                           int var21 = var20 * var6;
                           IEVar var44 = var5.createStackItem(var21, var34.getBitsize());
                           if (var44 == null) {
                              throw new RuntimeException("Unexpected: stack parameter item cannot be created");
                           }

                           var44.setType(var34);
                           var7++;
                        }
                        break;
                     default:
                        throw new DecompilerException("Output storage type not supported: " + var35.getType());
                  }
               }

               int var40 = var34.getSlotCount();
               var10 = var35.nextSlotIndex(var10, var40);
            }
         }

         if (!var28.isEmpty()) {
            EPrototypeHandler$1 var30 = new EPrototypeHandler$1(this, this.ctx, var28);
            var30.process(true, true, true);
            var4 = this.ctx.getCfg();
            var4.invalidateDataFlowAnalysis();
         }

         if (var7 > 0) {
            var4.invalidateDataFlowAnalysis();
         }

         return true;
      }
   }

   @Override
   public boolean retrieveFromPrototype(List var1, List var2) {
      IWildcardPrototype var3 = this.ctx.getPrototype();
      if (var3 == null) {
         return false;
      } else {
         ICallingConvention var4 = var3.getCallingConvention();
         CFG var5 = this.ctx.getCfg();
         IEStackManager var6 = this.ctx.getStackManager();
         int var7 = var6.getNormalSlotSize();
         Collection var8 = null;
         if (var1 != null) {
            IStorageEntryGenerator var9 = var4.getInputsGenerator();
            int var10 = 0;

            for (IWildcardType var12 : var3.getParameterTypes()) {
               IDFA var13 = var5.getDataFlowAnalysis();
               if (var13 == null) {
                  var13 = var5.doDataFlowAnalysis();
                  var8 = null;
               }

               if (var8 == null) {
                  var8 = var13.getInputs();
               }

               Object var14 = this.ctx.getConverter().getInputVariableByIndex(this.ctx, var10);
               if (var14 != null) {
                  var10++;
               } else {
                  StorageEntry var15 = var9.next(var12.getLayoutInfo());
                  switch (var15.getType()) {
                     case REGISTER:
                        EVarCopyFinder var19 = this.ctx.copyFinder(var15, var8, 0L);
                        var14 = var19.getIRForSlicedReg(true);
                        if (var19.wasCreated()) {
                           this.ctx.setTypeForSame((IEGeneric)var14, var12);
                        }
                        break;
                     case REGISTER_PAIR:
                        EVarCopyFinder var18 = this.ctx.copyFinder(var15, var8, 0L);
                        var14 = var18.getVarForRegPair(true);
                        if (var18.wasCreated()) {
                           ((IEGeneric)var14).setType(var12);
                        }
                        break;
                     case STACK:
                        int var16 = var15.getValueAsStackIndex();
                        int var17 = var16 * var7;
                        var14 = var6.getVariable(var17);
                        if (var14 == null) {
                           throw new RuntimeException("EVar for stack parameter should exist at this point");
                        }
                        break;
                     case MIXED:
                        var14 = amy.q(var12.getBitsize(), var12, null);
                        break;
                     default:
                        throw new DecompilerException("Input storage type not supported: " + var15.getType());
                  }

                  int var20 = var12.getSlotCount();
                  var10 = var15.nextSlotIndex(var10, var20);
               }

               var1.add(var14);
            }
         }

         if (var2 != null) {
            var2.addAll(var3.getReturnTypes());
         }

         return true;
      }
   }

   @Override
   public boolean refinePrototype() {
      INativeMethodItem var1 = this.ctx.getRoutine();
      if (var1.getPrototype() != null && !var1.isAutoGeneratedPrototype()) {
         return false;
      } else {
         IWildcardPrototype var2 = this.ctx.getPrototype();
         if (var2 == null) {
            throw new IllegalStateException("Prototype discovery must be performed first");
         } else {
            ICallingConvention var3 = var2.getCallingConvention();
            CFG var4 = this.ctx.getCfg();
            IEStackManager var5 = this.ctx.getStackManager();
            int var6 = var5.getNormalSlotSize();
            Collection var7 = null;
            int var8 = 0;
            Object[] var10000 = new Object[]{var3};
            ArrayList var9 = new ArrayList();
            IStorageEntryGenerator var10 = var3.getInputsGenerator();
            int var11 = 0;

            for (IWildcardType var13 : var2.getParameterTypes()) {
               IDFA var14 = var4.getDataFlowAnalysis();
               if (var14 == null) {
                  var14 = var4.doDataFlowAnalysis();
                  var7 = null;
               }

               if (var7 == null) {
                  var7 = var14.getInputs();
               }

               Object var15 = this.ctx.getConverter().getInputVariableByIndex(this.ctx, var11);
               if (var15 != null) {
                  var11++;
               } else {
                  StorageEntry var16 = var10.next(var13.getLayoutInfo());
                  switch (var16.getType()) {
                     case REGISTER:
                        EVarCopyFinder var50 = this.ctx.copyFinder(var16, var7, 0L);
                        var15 = var50.getIRForSlicedReg(false);
                        break;
                     case REGISTER_PAIR:
                        EVarCopyFinder var49 = this.ctx.copyFinder(var16, var7, 0L);
                        var15 = var49.getVarForRegPair(false);
                        break;
                     case STACK:
                        int var17 = var16.getValueAsStackIndex();
                        int var18 = var17 * var6;
                        var15 = var5.getVariable(var18);
                        if (var15 == null) {
                           var15 = var5.createStackItem(var18, var6 * 8);
                           if (var15 != null) {
                              ((IEGeneric)var15).setType(this.ctx.getWildcardTypeManager().createWithSlotcount(1));
                           }
                        }
                        break;
                     default:
                        throw new RuntimeException();
                  }

                  int var51 = var13.getSlotCount();
                  var11 = var16.nextSlotIndex(var11, var51);
               }

               if (var15 != null) {
                  IWildcardType var43 = ((IEGeneric)var15).getType();
                  if (var43 != null && var13.isLessSpecializedThan(var43)) {
                     var9.add(var43);
                     var8++;
                  } else {
                     var9.add(var13);
                  }
               } else {
                  var9.add(var13);
               }
            }

            if (var2.getReturnTypes().size() == 1) {
               boolean var30 = true;
               int var32 = -1;
               ArrayList var34 = new ArrayList();

               label197:
               for (BasicBlock var44 : var4.getBlocks()) {
                  AddressableInstruction var52 = var44.getLast2();
                  if (var52.getInstruction() instanceof IEReturn) {
                     IEReturn var56 = (IEReturn)var52.getInstruction();
                     if (!(var56.getValue() instanceof IEImm)) {
                        if (!(var56.getValue() instanceof IEVar)) {
                           var30 = false;
                           break;
                        }

                        IEVar var19 = (IEVar)var56.getValue();
                        if (var19.getType() == null || var19.getType().getEffectiveBitsize() != 0) {
                           var30 = false;
                           break;
                        }

                        int var20 = var19.getId();
                        IDFA var21 = var4.getDataFlowAnalysis();
                        if (var21 == null) {
                           var21 = var4.doDataFlowAnalysis();
                        }

                        for (long var24 : var21.getUseDefs(var52.getOffset(), var20)) {
                           IEStatement var26 = (IEStatement)var4.getInstruction(var24);
                           if (var26 == null || !var26.isAssignTo(var19)) {
                              var30 = false;
                              break label197;
                           }

                           IEGeneric var27 = var26.asAssign().getSrcOperand();
                           if (var27 instanceof IEImm) {
                              var34.add(var27.asImm());
                           } else if (EUtil.isZeroExtend(var27)) {
                              int var28 = ((IECompose)var27).getLowPart().getBitsize();
                              if (var32 == -1) {
                                 var32 = var28;
                              } else if (var32 != var28) {
                                 var30 = false;
                                 break label197;
                              }
                           }
                        }
                     } else {
                        var34.add(var56.getValue().asImm());
                     }
                  }
               }

               if (var30 && var32 > 0 && !var34.isEmpty()) {
                  for (IEImm var45 : var34) {
                     if (var45.getBitsize() < var32 || !EUtil.isZero(var45.slice(var32))) {
                        var30 = false;
                        break;
                     }
                  }

                  if (var30) {
                     for (BasicBlock var46 : var4.getBlocks()) {
                        AddressableInstruction var53 = var46.getLast2();
                        if (var53.getInstruction() instanceof IEReturn) {
                           IEReturn var57 = (IEReturn)var53.getInstruction();
                           if (var57.getValue() instanceof IEVar) {
                              IEVar var59 = var57.getValue().asVar();
                              var59.setType(var59.getType().updateEffectiveBitsize(var32));
                           }
                        }
                     }
                  }
               }
            }

            ArrayList var31 = new ArrayList();
            HashSet var33 = new HashSet();

            for (BasicBlock var40 : var4.getBlocks()) {
               AddressableInstruction var47 = var40.getLast2();
               if (var47.getInstruction() instanceof IEReturn) {
                  IEReturn var54 = (IEReturn)var47.getInstruction();
                  int var58 = 0;

                  for (IEGeneric var61 : var54.getValues()) {
                     IWildcardType var62 = var61.getType();
                     if (var62 != null) {
                        IWildcardType var63 = (IWildcardType)Lists.get(var31, var58);
                        if (var63 == null) {
                           if (!var33.contains(var58)) {
                              Lists.set(var31, var58, var62);
                           }
                        } else if (var63 != var62) {
                           Lists.set(var31, var58, null);
                           var33.add(var58);
                        }
                     }

                     var58++;
                  }
               }
            }

            ArrayList var36 = new ArrayList();
            byte var29 = 0;

            for (IWildcardType var48 : var2.getReturnTypes()) {
               IWildcardType var55 = (IWildcardType)Lists.get(var31, var29);
               if (var55 != null && var48.isLessSpecializedThan(var55)) {
                  var36.add(var55);
                  var8++;
               } else {
                  var36.add(var48);
               }
            }

            if (var8 <= 0) {
               return false;
            } else {
               IWildcardPrototype var42 = this.ctx
                  .getWildcardTypeManager()
                  .createPrototype(var2.getCallingConvention(), var36, var9, var2.getPrototypeAttributes());
               this.ctx.setPrototype(var42);
               return true;
            }
         }
      }
   }
}
