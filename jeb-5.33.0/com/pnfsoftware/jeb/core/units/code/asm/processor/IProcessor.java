package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.ICodeResolver;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorVariant;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface IProcessor {
   int MODE_INVALID = -1;
   int MODE_DEFAULT = 0;
   int MODE_8 = 8;
   int MODE_16 = 16;
   int MODE_32 = 32;
   int MODE_64 = 64;
   int MODE_128 = 128;
   int MODE_256 = 256;

   IInstruction parseAt(byte[] var1, int var2, int var3) throws ProcessorException;

   IInstruction parseAt(IVirtualMemory var1, long var2) throws ProcessorException;

   IInstruction parseWithContext(IMachineContext var1) throws ProcessorException;

   ProcessorType getType();

   IRegisterBank getRegisterBank();

   boolean isRISC();

   Collection getSupportedVariants();

   ProcessorVariant getVariant();

   void setVariant(ProcessorVariant var1) throws ProcessorException;

   int getDefaultMode();

   int getPCRegisterBitsize();

   int getGPRegisterBitsize();

   Collection getSupportedModes();

   int getMode();

   int setMode(int var1) throws ProcessorException;

   Endianness getEndianness();

   void setEndianness(Endianness var1);

   int getInstructionAlignment();

   void setInstructionAlignment(int var1);

   String getRegisterName(long var1);

   ICodeResolver getResolver();

   CodePointer createEntryPoint(long var1);

   CodePointer createEntryPoint(long var1, int var3);
}
