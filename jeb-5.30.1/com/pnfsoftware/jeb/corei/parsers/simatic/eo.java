package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.simatic.IS7Block;
import com.pnfsoftware.jeb.core.units.code.simatic.IS7Unit;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;

@Ser
public class eo extends AbstractAnalyzerExtension {
   private static final ILogger q = GlobalLog.getLogger(eo.class);

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      if (var1 == 0 && this.getUnit().getCodeObjectContainer() instanceof LR var3) {
         ITypeManager var4 = this.getUnit().getTypeManager();

         for (IS7Block var6 : var3.getBlocks()) {
            long var7 = var3.getAddressOfRawBytes(var6);
            if (var7 != -1L) {
               INativeType var9 = var6.generateNativeHeaderType(var4);
               if (var9 != null) {
                  this.getUnit().setDataTypeAt(var7, var9);
               }

               var9 = var6.generateNativeTrailerType(var4);
               if (var9 != null) {
                  this.getUnit().setDataTypeAt(var7 + var6.getTrailerOffset(), var9);
               }
            }
         }

         eM var14 = new eM(var4);

         for (IS7Block var16 : var3.getBlocks()) {
            kY var8;
            try {
               var8 = var3.q(var16);
            } catch (Exception var13) {
               q.catching(var13);
               continue;
            }

            if (!var16.getType().isLogicBlock() && var16.getType() == S7.BlockType.DB) {
               long var18 = var3.getAddressOfData(var16.getType(), var16.getNumber());

               try {
                  IStructureType var11 = var14.q(var8, var16);
                  this.getUnit().setDataTypeAt(var18, var11);
               } catch (Exception var12) {
                  q.catching(var12);
               }
            }
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult postprocessImage(int var1) {
      if (var1 == 0 && this.getUnit().getCodeObjectContainer() instanceof LR var3) {
         ITypeManager var4 = this.getUnit().getTypeManager();
         eM var5 = new eM(var4);

         for (IS7Block var7 : var3.getBlocks()) {
            try {
               if (var7.getType().isLogicBlock()) {
                  long var8 = var3.getAddressOfCode(var7.getType(), var7.getNumber());
                  INativeMethodItem var10 = this.getUnit().getRoutine(var8);
                  if (var10 != null) {
                     kY var11 = var3.q(var7);
                     ArrayList var12 = new ArrayList();
                     IPrototypeItem var13 = var5.q(var11, var7, var12);
                     var10.setPrototype(var13);
                     var10.setParameterNames(var12);
                  }
               }
            } catch (Exception var14) {
               q.catching(var14);
            }
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult customizeInstructionItem(INativeInstructionItem var1) {
      long var2 = var1.getMemoryAddress();
      PY var4 = (PY)var1.getInstruction();
      String var5 = var4.getMnemonic();
      if (var5.equals("UC") || var5.equals("CC")) {
         long var6 = var1.getMemoryAddressEnd();
         int[] var8 = new int[1];
         VirtualMemoryUtil.readLEIntSafe(this.getUnit().getMemory(), var6, var8);
         if ((var8[0] & 65535) == 2928) {
            int var9 = (var8[0] >>> 24) + (var8[0] >>> 8 & 0xFF00);
            int var10 = var9 * 2;
            INativeType var11 = this.getUnit().getTypeManager().getType("DWORD");

            for (long var12 = var6 + 4L; var12 < var6 + var10; var12 += 4L) {
               this.getUnit().setDataTypeAt(var12, var11);
            }
         }

         vb var18 = (vb)var4.getOperand(0);
         if (var18.q() == 0) {
            S7.BlockType var19 = null;
            switch (var18.RF()) {
               case 10:
                  var19 = S7.BlockType.FC;
                  break;
               case 11:
                  var19 = S7.BlockType.FB;
                  break;
               case 12:
                  var19 = S7.BlockType.SFC;
                  break;
               case 13:
                  var19 = S7.BlockType.SFB;
            }

            if (var19 != null) {
               int var20 = var18.xK();
               if (this.getUnit().getParent() instanceof IS7Unit) {
                  IS7Unit var13 = (IS7Unit)this.getUnit().getParent();
                  IS7Block var14 = var13.findBlock(var19, var20);
                  if (var14 != null) {
                     long var15 = var13.getAddressOfCode(var14.getType(), var14.getNumber());
                     if (var15 != -1L) {
                        IReferenceManager var17 = this.gca.getModel().getReferenceManager();
                        var17.recordInternalReference(var2, var15, ReferenceType.ROUTINE_CALL);
                     }
                  }
               }
            }
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   private long q(long var1) {
      short[] var3 = new short[1];
      VirtualMemoryUtil.readBEShortSafe(this.getUnit().getMemory(), var1 + 2L, var3);
      short var4 = var3[0];
      return var1 + 2 * var4;
   }
}
