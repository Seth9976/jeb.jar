package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerType;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;

public class ave extends AbstractEOptimizer {
   public ave() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED, OptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      this.getPluginInformation().setDescription("Inline decryptor for protected strings");
   }

   @Override
   protected int perform() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      //
      // Bytecode:
      // 000: bipush 0
      // 001: istore 1
      // 002: aload 0
      // 003: getfield com/pnfsoftware/jebglobal/ave.cfg Lcom/pnfsoftware/jeb/core/units/code/asm/cfg/CFG;
      // 006: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/cfg/CFG.iterator ()Ljava/util/Iterator;
      // 009: astore 2
      // 00a: aload 2
      // 00b: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 010: ifeq 1eb
      // 013: aload 2
      // 014: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 019: checkcast com/pnfsoftware/jeb/core/units/code/asm/cfg/BasicBlock
      // 01c: astore 3
      // 01d: aload 3
      // 01e: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/cfg/BasicBlock.getLast ()Lcom/pnfsoftware/jeb/core/units/code/IInstruction;
      // 021: checkcast com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEStatement
      // 024: astore 4
      // 026: aload 4
      // 028: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEStatement.isConditionalJump ()Z 1
      // 02d: ifne 033
      // 030: goto 00a
      // 033: aload 4
      // 035: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEStatement.asJump ()Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEJump; 1
      // 03a: astore 5
      // 03c: aload 5
      // 03e: ldc com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEMem
      // 040: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEJump.findByType (Ljava/lang/Class;)Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric; 2
      // 045: checkcast com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEMem
      // 048: astore 6
      // 04a: aload 6
      // 04c: ifnonnull 052
      // 04f: goto 00a
      // 052: aload 3
      // 053: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/cfg/BasicBlock.getEndAddress ()J
      // 056: lstore 7
      // 058: aload 5
      // 05a: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEJump.getBranchAddress ()I 1
      // 05f: i2l
      // 060: lstore 9
      // 062: aload 0
      // 063: aload 3
      // 064: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/cfg/BasicBlock.getBase ()J
      // 067: lload 7
      // 069: lload 9
      // 06b: invokevirtual com/pnfsoftware/jebglobal/ave.q (JJJ)Z
      // 06e: ifne 074
      // 071: goto 00a
      // 074: aload 3
      // 075: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/cfg/BasicBlock.getLastAddress ()J
      // 078: lstore 11
      // 07a: aload 0
      // 07b: getfield com/pnfsoftware/jebglobal/ave.ectx Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext;
      // 07e: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext.getGlobalContext ()Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IEGlobalContext; 1
      // 083: sipush 10000
      // 086: invokestatic com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.createStandard (Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IEGlobalContext;I)Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator;
      // 089: astore 13
      // 08b: aload 13
      // 08d: aload 0
      // 08e: getfield com/pnfsoftware/jebglobal/ave.ectx Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext;
      // 091: lload 11
      // 093: l2i
      // 094: lload 9
      // 096: l2i
      // 097: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.setStubExecution (Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext;II)V
      // 09a: aload 13
      // 09c: bipush 1
      // 09d: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.setResetUnknownRegisters (Z)V
      // 0a0: aload 13
      // 0a2: bipush 1
      // 0a3: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.setRecordMemoryWrites (Z)V
      // 0a6: aload 13
      // 0a8: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.setup ()V
      // 0ab: aload 13
      // 0ad: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.run ()V
      // 0b0: aload 13
      // 0b2: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.getMemoryWrites ()Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/MemoryWrites;
      // 0b5: astore 14
      // 0b7: aload 14
      // 0b9: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/MemoryWrites.getCountOfRecords ()I
      // 0bc: ifgt 0c7
      // 0bf: aload 13
      // 0c1: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.teardown ()V
      // 0c4: goto 00a
      // 0c7: aload 13
      // 0c9: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.getState ()Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/EState;
      // 0cc: astore 15
      // 0ce: aload 15
      // 0d0: lload 11
      // 0d2: l2i
      // 0d3: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/EState.setVirtualPC (I)V
      // 0d6: aload 5
      // 0d8: aload 15
      // 0da: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEJump.evaluate (Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/EState;)Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEImm; 2
      // 0df: astore 16
      // 0e1: aload 16
      // 0e3: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEImm.getValueAsLong ()J 1
      // 0e8: lload 9
      // 0ea: lcmp
      // 0eb: ifeq 0f6
      // 0ee: aload 13
      // 0f0: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.teardown ()V
      // 0f3: goto 00a
      // 0f6: goto 102
      // 0f9: pop
      // 0fa: aload 13
      // 0fc: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.teardown ()V
      // 0ff: goto 00a
      // 102: aload 14
      // 104: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/MemoryWrites.getAggregatedWrittenBytes ()[B
      // 107: arraylength
      // 108: bipush 8
      // 10a: if_icmpge 115
      // 10d: aload 13
      // 10f: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.teardown ()V
      // 112: goto 00a
      // 115: getstatic com/pnfsoftware/jebglobal/ave.logger Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // 118: ldc "Aggregated mem.writes:\n%s"
      // 11a: bipush 1
      // 11b: anewarray 34
      // 11e: dup
      // 11f: bipush 0
      // 120: aload 14
      // 122: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/MemoryWrites.getAggregatedWrittenBytes ()[B
      // 125: invokestatic com/pnfsoftware/jeb/util/format/Formatter.formatBinaryBlock ([B)Ljava/lang/CharSequence;
      // 128: aastore
      // 129: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.trace (Ljava/lang/String;[Ljava/lang/Object;)V 3
      // 12e: aload 13
      // 130: bipush 1
      // 131: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.commitMemoryChanges (Z)Z
      // 134: pop
      // 135: aload 13
      // 137: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.teardown ()V
      // 13a: goto 150
      // 13d: pop
      // 13e: aload 13
      // 140: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.teardown ()V
      // 143: goto 00a
      // 146: astore 17
      // 148: aload 13
      // 14a: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/EEmulator.teardown ()V
      // 14d: aload 17
      // 14f: athrow
      // 150: aload 14
      // 152: bipush 0
      // 153: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/MemoryWrites.getRecord (I)Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/MemoryWrites$Record;
      // 156: astore 15
      // 158: aload 15
      // 15a: invokevirtual com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/emulator/MemoryWrites$Record.getAddress ()J
      // 15d: lstore 16
      // 15f: aconst_null
      // 160: aload 0
      // 161: getfield com/pnfsoftware/jebglobal/ave.ectx Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext;
      // 164: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext.getGlobalContext ()Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IEGlobalContext; 1
      // 169: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IEGlobalContext.getNativeMemory ()Lcom/pnfsoftware/jeb/core/units/code/asm/memory/IVirtualMemory; 1
      // 16e: lload 16
      // 170: bipush 4
      // 171: bipush -1
      // 172: invokestatic com/pnfsoftware/jeb/core/units/code/asm/items/DataStringUtil.isValidStringAt (Lcom/pnfsoftware/jeb/core/units/code/asm/analyzer/INativeCodeModel;Lcom/pnfsoftware/jeb/core/units/code/asm/memory/IVirtualMemory;JII)Z
      // 175: ifeq 1da
      // 178: aload 0
      // 179: getfield com/pnfsoftware/jebglobal/ave.ectx Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext;
      // 17c: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext.getNativeContext ()Lcom/pnfsoftware/jeb/core/units/code/asm/INativeContext; 1
      // 181: instanceof com/pnfsoftware/jeb/core/units/INativeCodeUnit
      // 184: ifeq 1da
      // 187: aload 0
      // 188: getfield com/pnfsoftware/jebglobal/ave.ectx Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext;
      // 18b: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/IERoutineContext.getNativeContext ()Lcom/pnfsoftware/jeb/core/units/code/asm/INativeContext; 1
      // 190: checkcast com/pnfsoftware/jeb/core/units/INativeCodeUnit
      // 193: astore 18
      // 195: aload 18
      // 197: lload 16
      // 199: invokeinterface com/pnfsoftware/jeb/core/units/INativeCodeUnit.getNativeItemAt (J)Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeContinuousItem; 3
      // 19e: astore 19
      // 1a0: aload 19
      // 1a2: ifnonnull 1c9
      // 1a5: aload 18
      // 1a7: lload 16
      // 1a9: aload 18
      // 1ab: invokeinterface com/pnfsoftware/jeb/core/units/INativeCodeUnit.getTypeManager ()Lcom/pnfsoftware/jeb/core/units/code/asm/type/ITypeManager; 1
      // 1b0: ldc "char"
      // 1b2: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/type/ITypeManager.getType (Ljava/lang/String;)Lcom/pnfsoftware/jeb/core/units/code/asm/type/INativeType; 2
      // 1b7: aconst_null
      // 1b8: invokeinterface com/pnfsoftware/jeb/core/units/INativeCodeUnit.setDataAt (JLcom/pnfsoftware/jeb/core/units/code/asm/type/INativeType;Ljava/lang/String;)Z 5
      // 1bd: pop
      // 1be: aload 18
      // 1c0: lload 16
      // 1c2: invokeinterface com/pnfsoftware/jeb/core/units/INativeCodeUnit.getNativeItemAt (J)Lcom/pnfsoftware/jeb/core/units/code/asm/items/INativeContinuousItem; 3
      // 1c7: astore 19
      // 1c9: aload 19
      // 1cb: ifnull 1da
      // 1ce: aload 19
      // 1d0: ldc "GeneratedFrom"
      // 1d2: ldc "Auto-decrypted string"
      // 1d4: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/items/INativeContinuousItem.setAttribute (Ljava/lang/String;Ljava/lang/Object;)Z 3
      // 1d9: pop
      // 1da: aload 5
      // 1dc: bipush 1
      // 1dd: invokestatic com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/EUtil.one (I)Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEImm;
      // 1e0: invokeinterface com/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEJump.setCondition (Lcom/pnfsoftware/jeb/core/units/code/asm/decompiler/ir/IEGeneric;)V 2
      // 1e5: iinc 1 1
      // 1e8: goto 00a
      // 1eb: aload 0
      // 1ec: iload 1
      // 1ed: invokevirtual com/pnfsoftware/jebglobal/ave.postPerform (I)I
      // 1f0: ireturn
   }

   boolean q(long var1, long var3, long var5) {
      HashSet var7 = new HashSet();
      ArrayDeque var8 = new ArrayDeque();
      var8.add(var3);

      while (!var8.isEmpty()) {
         long var9 = (Long)var8.remove();
         if (var7.add(var9)) {
            BasicBlock var11 = this.cfg.getBlockAt(var9);
            if (var11 == null) {
               return false;
            }

            for (BasicBlock var13 : var11.getAllOutputBlocks()) {
               long var14 = var13.getBase();
               if (var14 != var5) {
                  var8.add(var14);
               }
            }
         }
      }

      var7.add(var1);

      for (long var10 : var7) {
         BasicBlock var17 = this.cfg.getBlockAt(var10);
         List var18 = var17.getInputOffsets();
         if (!var7.containsAll(var18)) {
            return false;
         }
      }

      return true;
   }
}
