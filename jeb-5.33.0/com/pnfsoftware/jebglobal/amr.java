package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVarCopyFinder;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.VarSrc;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConventionManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class amr {
   private static final StructuredLogger ys = aco.pC(amr.class);
   IERoutineContext pC;
   ITypeManager A;
   IWildcardTypeManager kS;
   SPDC wS;
   SPDC UT;
   Set E = new HashSet();
   Set sY = new HashSet();

   public amr(IERoutineContext var1) {
      this.pC = var1;
      this.kS = var1.getWildcardTypeManager();
      this.A = this.kS.getNativeTypeManager();
      this.pC(var1);
      this.A(var1);
   }

   private Collection pC(ICallingConvention var1) {
      Endianness var2 = this.pC.getGlobalContext().isBigEndian() ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
      Collection var3 = CallingConventionUtil.getOrderedSingleInputRegisters(var1, var2);
      LinkedHashSet var4 = new LinkedHashSet();

      for (long var6 : var3) {
         Integer var8 = EUtil.getVarSliceId(this.pC.getConverter().getRegisterVariableFromNativeRegisterId(var6));
         if (var8 != null) {
            var4.add(var8);
         }
      }

      return var4;
   }

   private Collection A(ICallingConvention var1) {
      Endianness var2 = this.pC.getGlobalContext().isBigEndian() ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
      Collection var3 = CallingConventionUtil.getOrderedSingleOutputRegisters(var1, var2);
      LinkedHashSet var4 = new LinkedHashSet();

      for (long var6 : var3) {
         Integer var8 = EUtil.getVarSliceId(this.pC.getConverter().getRegisterVariableFromNativeRegisterId(var6));
         if (var8 != null) {
            var4.add(var8);
         }
      }

      return var4;
   }

   private void pC(IERoutineContext var1) {
      aki var2 = (aki)var1;
      this.pC(var2.wS(), var2.UT());
   }

   public void pC(SPDC var1, SPDC var2) {
      this.wS = var1;
      this.UT = var2;
   }

   private boolean A(IERoutineContext var1) {
      CFG var2 = var1.getCfg();
      IDFA var3 = var2.doDataFlowAnalysis();
      Object[] var10000 = new Object[0];
      HashSet var4 = new HashSet();
      var4.add(var1.getProgramCounterId());
      var4.add(var1.getStackPointerId());
      List var5 = var2.getExitBlocks();
      boolean var6 = true;
      HashSet var7 = new HashSet();

      for (BasicBlock var9 : var5) {
         HashSet var10 = new HashSet();
         Map var11 = var3.getOutputMap(var9);

         for (int var13 : var11.keySet()) {
            if (!aku.pC(var13)) {
               VarSrc var14 = var1.getSourceForVariable(var13);
               if (var14 != null) {
                  var14.collectSourceIds(var10, var4);
               }
            } else if (!var4.contains(var13)) {
               var10.add(var13);
            }
         }

         if (var6) {
            var6 = false;
            var7 = var10;
         } else {
            var10.retainAll(var7);
            var7 = var10;
         }
      }

      this.sY.addAll(var7);
      var10000 = new Object[]{this.sY};
      Map var15 = var3.getInputMap();

      for (int var17 : var15.keySet()) {
         if (!aku.pC(var17)) {
            VarSrc var18 = var1.getSourceForVariable(var17);
            if (var18 != null) {
               var18.collectSourceIds(this.E, var4);
            }
         } else if (!var4.contains(var17)) {
            this.E.add(var17);
         }
      }

      var10000 = new Object[]{this.E};
      return true;
   }

   public IWildcardPrototype pC() {
      int var1 = this.A.getSlotSize();
      int var2 = Integer.MIN_VALUE;
      ICallingConvention var3 = null;
      ICallingConventionManager var4 = this.A.getCallingConventionManager();
      Iterator var5 = var4.getConventions().iterator();

      while (true) {
         ICallingConvention var6;
         byte var8;
         int var9;
         do {
            if (!var5.hasNext()) {
               ICallingConvention var24 = var3;
               if (var3 == null) {
                  var24 = var4.getDefaultConvention();
               }

               if (var24 != null && !var24.isUnknown()) {
                  int var25;
                  if (!var24.isStackCleanedByCaller()) {
                     var25 = (this.wS.getValue() + var1 - 1) / var1 - 1;
                  } else {
                     var25 = (this.UT.getValue() + var1 - 1) / var1 - 1;
                  }

                  IClassType var26 = null;
                  INativeMethodItem var27 = this.pC.getRoutine();
                  if (var27 != null) {
                     INativeClassItem var29 = var27.getClassItem();
                     if (var29 != null && var29.getInstanceMethods().contains(var27)) {
                        var26 = var29.getClassType();
                     }
                  }

                  ArrayList var30 = new ArrayList();
                  int var31 = -1;
                  IStorageEntryGenerator var33 = var24.getInputsGenerator();

                  for (int var35 = 0; var30.size() < 1000; var35++) {
                     StorageEntry var36 = var33.next(TypeLayoutInfo.i1);
                     if (var36 == null) {
                        break;
                     }

                     IWildcardType var14 = this.kS.createWithSlotcount(1);
                     if (var35 == 0 && var26 != null) {
                        var14 = this.kS.create(var26.getReference());
                     }

                     if (var36.getType() == StorageEntry.Type.STACK) {
                        if (var36.getValue() > var25) {
                           break;
                        }

                        IEVar var15 = this.pC.getStackManager().getVariableAtSlot(var36.getValueAsStackIndex());
                        if (var15 != null) {
                           IWildcardType var16 = var15.safelyType(this.kS);
                           if (var14.isLessSpecializedThan(var16) || var16.getBitsize() != var14.getBitsize()) {
                              var14 = var16;
                           }
                        }

                        var30.add(var14);
                        var31 = -1;
                     } else if (var36.getType() == StorageEntry.Type.REGISTER) {
                        var30.add(var14);
                        Integer var39 = EUtil.getVarSliceId(this.pC.getConverter().getRegisterVariableFromNativeRegisterId(var36.getValue()));
                        if (var39 != null) {
                           if (!this.E.contains(var39) && var31 < 0) {
                              var31 = var30.size() - 1;
                           } else if (var31 >= 0) {
                              CFG var41 = this.pC.getCfg();
                              IDFA var17 = var41.doDataFlowAnalysis();
                              IEGeneric var18 = this.pC.getInputVariableForRegister(var17, this.pC.getVariableById(var39));
                              if (var18 != null) {
                                 if (var18 instanceof IEVar) {
                                    Collection var19 = var17.getInputMap(((IEVar)var18).getId());
                                    if (var19 != null) {
                                       for (long var21 : var19) {
                                          if (!EUtil.isPCAssign((IEStatement)var41.getInstruction(var21))) {
                                             var31 = -1;
                                             break;
                                          }
                                       }
                                    } else {
                                       var31 = -1;
                                    }
                                 } else {
                                    var31 = -1;
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (var31 >= 0) {
                     while (var30.size() > var31) {
                        var30.remove(var31);
                     }
                  }

                  IWildcardType var37 = null;
                  StorageEntry var38 = var24.getOutput(TypeLayoutInfo.i1, 0);
                  if (var38 != null && var38.getType() == StorageEntry.Type.REGISTER) {
                     Integer var40 = EUtil.getVarSliceId(this.pC.getConverter().getRegisterVariableFromNativeRegisterId(var38.getValue()));
                     if (var40 != null && this.sY.contains(var40)) {
                        var37 = this.kS.createWithSlotcount(1);
                        CFG var42 = this.pC.getCfg();
                        IDFA var44 = var42.doDataFlowAnalysis();
                        IEGeneric var45 = null;

                        for (BasicBlock var47 : var42.getExitBlocks()) {
                           Collection var48 = var44.getOutputs();
                           EVarCopyFinder var22 = this.pC.copyFinder(var38, var48, var47.getLastAddress());
                           IEGeneric var23 = var22.getIRForSlicedReg(false);
                           if (var23 == null) {
                              var45 = null;
                              break;
                           }

                           if (var45 == null) {
                              var45 = var23;
                           } else if (!var45.equals(var23)) {
                              var45 = null;
                              break;
                           }
                        }

                        if (var45 != null && var45.getBitsize() < var37.getBitsize()) {
                           var37 = var37.updateEffectiveBitsize(var45.getBitsize());
                        }
                     }
                  }

                  int var43 = var30.size();
                  if (var43 > 40) {
                     ys.error("Prototype determination likely failed; too many parameters (%d)", var43);
                     var30 = new ArrayList(var30.subList(0, 40));
                  }

                  return this.kS.createPrototype(var24, var37, var30, null);
               }

               throw new RuntimeException("Cannot determine prototype for convention: " + var24);
            }

            var6 = (ICallingConvention)var5.next();
            int var7 = 0;
            if (var6.getReturnAddressSlot() != null && var6.getReturnAddressSlot().getType() == StorageEntry.Type.STACK) {
               var7 = var1;
            }

            if (this.wS.getValue() > var7) {
               var8 = 2;
            } else if (this.wS.getValue() == var7) {
               if (this.UT.getValue() > this.wS.getValue()) {
                  var8 = 1;
               } else {
                  var8 = 0;
               }
            } else {
               var8 = 0;
            }

            if (var8 == 0) {
               break;
            }

            var9 = var6.isStackCleanedByCaller() ? 1 : 2;
         } while (var9 != var8);

         var9 = 0;
         Collection var10 = this.pC(var6);

         for (int var12 : this.E) {
            if (var10.contains(var12)) {
               var9++;
            } else {
               var9--;
            }
         }

         Collection var32 = this.A(var6);

         for (int var13 : this.sY) {
            if (var32.contains(var13)) {
               var9++;
            } else {
               var9--;
            }
         }

         if (var6.getProcessorTypes().contains(this.pC.getNativeContext().getProcessor().getType())) {
            var9++;
         }

         if (var9 > var2) {
            var3 = var6;
            var2 = var9;
         }
      }
   }
}
