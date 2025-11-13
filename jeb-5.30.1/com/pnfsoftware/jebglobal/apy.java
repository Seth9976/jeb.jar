package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.PreRoutineInvocationDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.VariableArgumentInformation;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class apy {
   private static final StructuredLogger Uv = aeg.q(apy.class);
   IEConverter q;
   IERoutineContext RF;
   INativeDecompilerExtensionsManager xK;
   IWildcardTypeManager Dw;

   public apy(IERoutineContext var1, INativeDecompilerExtensionsManager var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
         this.q = var1.getConverter();
         this.xK = var2;
         this.Dw = var1.getWildcardTypeManager();
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public List q(CFG var1, int var2, IWildcardPrototype var3, INativeMethodItem var4) {
      if (var2 + 1 >= var1.size()) {
         return null;
      } else if (var3 != null && var3.isVariableArgument()) {
         ICallingConvention var5 = var3.getCallingConvention();
         if (var5 != null && var5.isStackCleanedByCaller()) {
            if (this.xK != null) {
               VariableArgumentInformation var6 = (VariableArgumentInformation)this.xK
                  .resolveVariableArgumentInformation(this.RF, var1, var2, var3)
                  .getResult();
               if (var6 != null) {
                  return var6.getVarArgTypes();
               }
            }

            BasicBlock var15 = var1.get(var2);
            if (EUtil.isPCAssign((IEStatement)var15.getLast())) {
               IEAssign var7 = (IEAssign)var15.getLast();
               PreRoutineInvocationDetails var8 = var7.getBranchDetails(true).getPreInvocationDetails();
               if (var8 != null) {
                  List var9 = this.q(var7, var8, var5, var3.getParameterTypes(), var4);
                  if (var9 != null) {
                     return var9;
                  }
               }
            }

            BasicBlock var16 = var1.get(var2 + 1);
            Object[] var10000 = new Object[]{var16};
            Integer var17 = this.q(var1, var2, var3);
            if (var17 == null) {
               var17 = this.RF(var1, var2, var3);
               if (var17 == null) {
                  return null;
               }
            }

            int var18 = 0;
            IStorageEntryGenerator var10 = var5.getInputsGenerator();

            for (IWildcardType var12 : var3.getParameterTypes()) {
               int var13 = var12.getSlotCount();
               StorageEntry var14 = var10.next(var12.getLayoutInfo());
               if (var14 == null) {
                  throw new RuntimeException();
               }

               switch (var14.getType()) {
                  case REGISTER:
                  case REGISTER_PAIR:
                     break;
                  case STACK:
                     var18 += var13;
                     break;
                  default:
                     throw new RuntimeException();
               }
            }

            if (var17 < var18) {
               return null;
            } else {
               int var19 = var17 - var18;
               ArrayList var20 = new ArrayList(var19);

               while (var19-- > 0) {
                  var20.add(this.Dw.createWithSlotcount(1));
               }

               return var20;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private List q(IEAssign var1, PreRoutineInvocationDetails var2, ICallingConvention var3, List var4, INativeMethodItem var5) {
      if (var5 != null) {
         String var6 = var5.getFinalName();
         if (var6 != null && this.RF.getNativeContext().getCodeContainerType() == 1) {
            switch (var6) {
               case "open":
               case "open64":
               case "openat":
               case "openat64":
                  IStorageEntryGenerator var19 = var3.getInputsGenerator();
                  int var22 = 0;
                  StorageEntry var26 = var19.next(((IWildcardType)var4.get(var22++)).getLayoutInfo());
                  if (var26 == null) {
                     return null;
                  }

                  if (var6.startsWith("openat")) {
                     var26 = var19.next(((IWildcardType)var4.get(var22++)).getLayoutInfo());
                     if (var26 == null) {
                        return null;
                     }
                  }

                  var26 = var19.next(((IWildcardType)var4.get(var22)).getLayoutInfo());
                  if (var26 == null) {
                     return null;
                  }

                  IEImm var30 = var2.readArg(this.RF.getConverter(), var26);
                  if (var30 != null && var30.canReadAsLong()) {
                     long var32 = var30.getValueAsLong();
                     ArrayList var34 = new ArrayList();
                     if ((var32 & 64L) != 0L) {
                        var34.add(this.Dw.create("mode_t"));
                     }

                     return var34;
                  }

                  return null;
               case "ioctl":
                  ArrayList var18 = new ArrayList();
                  var18.add(this.Dw.create("char*"));
                  return var18;
               case "prctl":
                  IStorageEntryGenerator var17 = var3.getInputsGenerator();
                  byte var21 = 0;
                  StorageEntry var25 = var17.next(((IWildcardType)var4.get(var21)).getLayoutInfo());
                  if (var25 == null) {
                     return null;
                  }

                  IEImm var29 = var2.readArg(this.RF.getConverter(), var25);
                  if (var29 != null && var29.canReadAsLong()) {
                     long var31 = var29.getValueAsLong();
                     if (var31 < 2147483647L && var31 > -2147483648L) {
                        ArrayList var33 = new ArrayList();
                        switch ((int)var31) {
                           case 1:
                           case 4:
                           case 6:
                           case 8:
                           case 10:
                           case 12:
                           case 14:
                           case 20:
                           case 22:
                           case 23:
                           case 24:
                           case 26:
                           case 28:
                           case 29:
                           case 36:
                           case 38:
                           case 1499557217:
                              var33.add(this.Dw.create("unsigned long"));
                              break;
                           case 2:
                           case 5:
                           case 9:
                           case 11:
                           case 19:
                           case 25:
                           case 37:
                              var33.add(this.Dw.create("int*"));
                           case 3:
                           case 7:
                           case 13:
                           case 21:
                           case 27:
                           case 30:
                           case 31:
                           case 32:
                           case 34:
                           case 39:
                              break;
                           case 15:
                           case 16:
                              var33.add(this.Dw.create("char*"));
                              break;
                           case 33:
                           case 35:
                              var33.add(this.Dw.create("unsigned long"));
                              var33.add(this.Dw.create("unsigned long"));
                              break;
                           case 40:
                              var33.add(this.Dw.create("int*"));
                              break;
                           default:
                              return null;
                        }

                        return var33;
                     }

                     return null;
                  }

                  return null;
               case "fcntl":
               case "fcntl64":
                  IStorageEntryGenerator var9 = var3.getInputsGenerator();
                  int var10 = 0;
                  StorageEntry var11 = var9.next(((IWildcardType)var4.get(var10++)).getLayoutInfo());
                  if (var11 == null) {
                     return null;
                  }

                  var11 = var9.next(((IWildcardType)var4.get(var10)).getLayoutInfo());
                  if (var11 == null) {
                     return null;
                  }

                  IEImm var12 = var2.readArg(this.RF.getConverter(), var11);
                  if (var12 != null && var12.canReadAsLong()) {
                     long var13 = var12.getValueAsLong();
                     if (var13 < 2147483647L && var13 > -2147483648L) {
                        ArrayList var15 = new ArrayList();
                        switch ((int)var13) {
                           case 0:
                           case 2:
                           case 4:
                           case 8:
                              var15.add(this.Dw.create("int"));
                           case 1:
                           case 3:
                           case 9:
                              break;
                           case 5:
                           case 6:
                           case 7:
                              var15.add(this.Dw.create("struct flock *"));
                              break;
                           default:
                              return null;
                        }

                        return var15;
                     }

                     return null;
                  }

                  return null;
               case "clone":
                  return null;
               case "ptrace":
                  return null;
               case "mq_open":
                  return null;
               case "sem_open":
                  return null;
               case "semctl":
                  return null;
            }
         }
      }

      aob var16 = new aob(this.RF, var1, var2, var3, var4);
      return !var16.q(false) ? null : var16.xK();
   }

   private Integer q(CFG var1, int var2, IWildcardPrototype var3) {
      IEVar var4 = this.q.getStackPointer();
      BasicBlock var5 = var1.get(var2 + 1);
      int var6 = Math.min(3, var5.size());
      IEGeneric var7 = null;

      for (int var8 = 0; var8 < var6; var8++) {
         IEStatement var9 = (IEStatement)var5.get(var8);
         if (var9 instanceof IEAssign && ((IEAssign)var9).getDstOperand() == var4) {
            var7 = ((IEAssign)var9).getSrcOperand();
            break;
         }
      }

      if (var7 == null) {
         return null;
      } else {
         OperationType var14 = EUtil.getOperation(var7, OperationType.ADD, OperationType.SUB);
         if (var14 == null) {
            return null;
         } else {
            IEGeneric var15 = ((IEOperation)var7).getOperand1();
            IEGeneric var10 = ((IEOperation)var7).getOperand2();
            long var11;
            if (var15 == var4 && var10 instanceof IEImm) {
               var11 = ((IEImm)var10).getValueAsLong();
               if (var14 == OperationType.SUB) {
                  var11 = -var11;
               }
            } else {
               if (!(var15 instanceof IEImm) || var10 != var4) {
                  return null;
               }

               if (var14 == OperationType.SUB) {
                  return null;
               }

               var11 = ((IEImm)var15).getValueAsLong();
            }

            if (var11 < 0L) {
               return null;
            } else {
               int var13 = (int)var11 / this.q.getStackSlotSize();
               return var13;
            }
         }
      }
   }

   private Integer RF(CFG var1, int var2, IWildcardPrototype var3) {
      apy.eo var4 = new apy.eo(this.RF, (int)var1.get(var2).getFirstAddress());
      Integer var5 = var4.q();
      if (var5 == null) {
         return null;
      } else {
         boolean var6 = var3.getCallingConvention().getReturnAddressSlot().getType() == StorageEntry.Type.STACK;
         if (var6) {
            var5 = var5 - 1;
         }

         return var5;
      }
   }

   private static class eo {
      IERoutineContext q;
      int RF;

      public eo(IERoutineContext var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }

      public Integer q() {
         int var1 = this.q.getStackPointerId();
         int var2 = this.q.getConverter().getStackSlotSize();
         EState var3 = this.q.getGlobalContext().buildState(false, false, false);
         long var4 = this.q.convertIntermediateOffset(this.RF);
         this.q.getConverter().initializeStateRegisters(var3, var4);
         IVirtualMemory var6 = var3.getMemory();
         var3.setValue(var1, 2113929216L);
         VirtualMemoryUtil.allocateFillGaps(var6, 2113863680L, 131072, 3);
         TreeSet var7 = new TreeSet();
         BasicBlock var8 = this.q.getCfg().getBlockAt((long)this.RF);

         for (int var9 = 0; var9 < var8.size() - 1; var9++) {
            IEStatement var10 = (IEStatement)var8.get(var9);
            Object[] var10000 = new Object[]{var10};
            IEMem var11 = EUtil.getAssigningToMemory(var10);
            if (var11 != null) {
               IEGeneric var12 = var11.getReference();

               try {
                  long var13 = var12.evaluateAddress(var3);
                  var7.add(var13);
               } catch (Exception var16) {
                  continue;
               }
            }

            try {
               var10.evaluate(var3);
            } catch (Exception var15) {
            }
         }

         int var17 = 0;

         for (long var18 = var3.getValueAsLong(var1); var7.contains(var18); var18 += var2) {
            var17++;
         }

         return var17;
      }
   }
}
