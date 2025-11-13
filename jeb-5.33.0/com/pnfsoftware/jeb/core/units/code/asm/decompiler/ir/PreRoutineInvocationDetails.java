package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.aco;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class PreRoutineInvocationDetails {
   private static final StructuredLogger logger = aco.pC(PreRoutineInvocationDetails.class);
   @SerId(1)
   IVirtualMemory mem;
   @SerId(2)
   Map varmap;
   @SerId(3)
   List stackSlotValues;
   @SerId(4)
   List stackSlotAges;

   public PreRoutineInvocationDetails(IERoutineContext var1, EState var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.mem = var2.getMemory().duplicate();
         this.varmap = new HashMap(var2.getVariables());
         if (var1 != null && var2.getMemoryWriteHitmap() != null) {
            int var3 = var1.getStackManager().getNormalSlotSize();
            int var4 = var1.getStackPointerId();
            if (var2.hasValue(var4)) {
               IEImm var5 = var2.getValue(var4);
               long var6 = var5.getValueAsAddress();
               byte[] var8 = new byte[8 * var3];
               int var9 = VirtualMemoryUtil.readBytesSafe(this.mem, var6, var8.length, var8, 0, 1);
               int var10 = var9 / var3;
               if (var10 > 0) {
                  ArrayList var11 = new ArrayList(var10);
                  ArrayList var12 = new ArrayList(var10);

                  label48:
                  for (long var13 = 0L; var13 < var10; var13++) {
                     long var15 = var6 + var13 * var3;
                     IEImm var17 = null;

                     try {
                        var17 = var1.createMem(EUtil.imm(var15, this.mem.getSpaceBits()), var3 * 8).evaluate(var2);
                     } catch (Exception var22) {
                     }

                     var12.add(var17);
                     int var18 = -1;

                     for (int var19 = 0; var19 < var3; var19++) {
                        Integer var20 = (Integer)var2.getMemoryWriteHitmap().get(var15 + var19);
                        if (var20 == null) {
                           break label48;
                        }

                        int var21 = var2.getEvaluationCount() - var20;
                        if (var19 == 0) {
                           var18 = var21;
                        } else if (var21 != var18) {
                           break label48;
                        }
                     }

                     var11.add(var18);
                  }

                  this.stackSlotValues = var12;
                  this.stackSlotAges = var11;
               }
            }
         }
      }
   }

   public IVirtualMemory getVirtualMemory() {
      return this.mem;
   }

   public Map getVariableMap() {
      return this.varmap;
   }

   public List getStackSlotAges() {
      return this.stackSlotAges;
   }

   public List getStackSlotValues() {
      return this.stackSlotValues;
   }

   public IEImm readArg(IEConverter var1, StorageEntry var2) {
      if (var2.getType() == StorageEntry.Type.REGISTER) {
         IEGeneric var3 = var1.getRegisterVariableFromNativeRegisterId(var2.getValue());
         if (var3 instanceof IEVar) {
            int var4 = ((IEVar)var3).getId();
            return (IEImm)this.getVariableMap().get(var4);
         }
      } else if (var2.getType() == StorageEntry.Type.STACK) {
         int var5 = var2.getValueAsStackIndex();
         List var6 = this.getStackSlotValues();
         if (var6 != null && var5 < var6.size()) {
            return (IEImm)var6.get(var5);
         }
      }

      return null;
   }
}
