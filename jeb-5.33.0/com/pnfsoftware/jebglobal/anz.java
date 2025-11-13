package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX86;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionName;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;

public class anz extends anx {
   private static final int gp = RegisterBankX86.getInstance().getDescriptionEntryByName("ecx").getNumber();
   private boolean oT;
   private String fI;
   private ICallingConvention WR;

   public anz(INativeCodeUnit var1, INativeDecompilerUnit var2) {
      this(var1, var2, false);
   }

   public anz(INativeCodeUnit var1, INativeDecompilerUnit var2, boolean var3) {
      super(var1, var2);
      this.oT = var3;
      this.fI = CallingConventionName.THISCALL.toString();
      this.WR = this.E.getCallingConventionManager().getConvention(this.fI);
   }

   @Override
   protected boolean pC() {
      if (this.A.getProcessor().getType() != ProcessorType.X86) {
         return false;
      } else {
         if (this.oT) {
            ICompiler var1 = this.A.getCodeAnalyzer().getDetectedCompiler();
            if (var1 == null || !var1.isVisualStudio()) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   protected boolean pC(long param1, INativeMethodItem param3) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      //
      // Bytecode:
      // 000: aload 0
      // 001: aload 3
      // 002: getstatic com/pnfsoftware/jeb/core/units/code/asm/decompiler/NativeDecompilationStage.RAW_CONVERSION Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/NativeDecompilationStage;
      // 005: invokevirtual com/pnfsoftware/jebglobal/anz.pC (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeMethodItem;Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/NativeDecompilationStage;)Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IDecompiledMethod;
      // 008: astore 4
      // 00a: aload 4
      // 00c: ifnull 019
      // 00f: aload 4
      // 011: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IDecompiledMethod.isFailure ()Z 1
      // 016: ifeq 029
      // 019: bipush 0
      // 01a: istore 5
      // 01c: aload 0
      // 01d: getfield com/pnfsoftware/jebglobal/anz.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 020: aload 3
      // 021: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 026: iload 5
      // 028: ireturn
      // 029: aload 4
      // 02b: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IDecompiledMethod.getIRContext ()Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext; 1
      // 030: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext.getCfg ()Lcom/pnfsoftware/jeb/core/units/code/asm/cfg/CFG; 1
      // 035: astore 5
      // 037: aload 5
      // 039: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/cfg/CFG.doDataFlowAnalysis ()Lcom/pnfsoftware/jeb/core/units/code/IDFA;
      // 03c: astore 6
      // 03e: aload 4
      // 040: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IDecompiledMethod.getIRContext ()Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext; 1
      // 045: aload 6
      // 047: getstatic com/pnfsoftware/jebglobal/anz.gp I
      // 04a: i2l
      // 04b: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext.getInputVariableForRegister (Lcom/pnfsoftware/jeb/core/units/code/IDFA;J)Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric; 4
      // 050: ifnull 0d6
      // 053: bipush 0
      // 054: istore 7
      // 056: aload 5
      // 058: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/cfg/CFG.instructions ()Ljava/lang/Iterable;
      // 05b: invokeinterface java/lang/Iterable.iterator ()Ljava/util/Iterator; 1
      // 060: astore 8
      // 062: aload 8
      // 064: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 069: ifeq 0d6
      // 06c: aload 8
      // 06e: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 073: checkcast com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEStatement
      // 076: astore 9
      // 078: iload 7
      // 07a: iinc 7 1
      // 07d: bipush 100
      // 07f: if_icmplt 085
      // 082: goto 0d6
      // 085: aload 9
      // 087: instanceof com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEAssign
      // 08a: ifeq 0d3
      // 08d: aload 9
      // 08f: checkcast com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEAssign
      // 092: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEAssign.getSrcOperand ()Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric; 1
      // 097: instanceof com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEImm
      // 09a: ifeq 0d3
      // 09d: aload 9
      // 09f: checkcast com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEAssign
      // 0a2: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEAssign.getSrcOperand ()Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric; 1
      // 0a7: checkcast com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEImm
      // 0aa: astore 10
      // 0ac: aload 10
      // 0ae: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEImm.getValueAsAddress ()J 1
      // 0b3: lstore 11
      // 0b5: aload 0
      // 0b6: lload 11
      // 0b8: bipush 1
      // 0b9: invokevirtual com/pnfsoftware/jebglobal/anz.pC (JZ)Ljava/lang/Long;
      // 0bc: ifnull 0cf
      // 0bf: bipush 1
      // 0c0: istore 13
      // 0c2: aload 0
      // 0c3: getfield com/pnfsoftware/jebglobal/anz.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 0c6: aload 3
      // 0c7: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 0cc: iload 13
      // 0ce: ireturn
      // 0cf: goto 0d3
      // 0d2: pop
      // 0d3: goto 062
      // 0d6: bipush 0
      // 0d7: istore 7
      // 0d9: aload 0
      // 0da: getfield com/pnfsoftware/jebglobal/anz.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 0dd: aload 3
      // 0de: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 0e3: iload 7
      // 0e5: ireturn
      // 0e6: pop
      // 0e7: bipush 0
      // 0e8: istore 5
      // 0ea: aload 0
      // 0eb: getfield com/pnfsoftware/jebglobal/anz.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 0ee: aload 3
      // 0ef: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 0f4: iload 5
      // 0f6: ireturn
      // 0f7: astore 14
      // 0f9: aload 0
      // 0fa: getfield com/pnfsoftware/jebglobal/anz.kS Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit;
      // 0fd: aload 3
      // 0fe: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/INativeDecompilerUnit.removeDecompilation (Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeItem;)V 2
      // 103: aload 14
      // 105: athrow
   }

   @Override
   protected boolean pC(IClassType var1, INativeMethodItem var2) {
      IPrototypeItem var3 = var2.getPrototype();
      if (var3 == null) {
         return false;
      } else {
         IReferenceType var4 = this.E.createReference(var1);
         ArrayList var5 = new ArrayList(var3.getParameterTypes());
         ICallingConvention var6 = var3.getCallingConvention();
         if (var6 != null && var6 == this.WR) {
            if (var5.isEmpty()) {
               Assert.debugFail("__thiscall routine without a single parameter!");
               var5.add(null);
            }

            var5.set(0, var4);
         } else {
            var5.add(0, var4);
         }

         INativeType var7 = var3.getReturnType();
         if ((var2.getGenericFlags() & 65536) != 0) {
            var7 = null;
         }

         IPrototypeItem var8 = this.E.createPrototype(this.WR, var7, var5, null);
         var2.setPrototype(var8);
         return true;
      }
   }
}
