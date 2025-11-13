package com.pnfsoftware.jeb.core.units.code;

import java.util.List;

public interface IBasicBlock extends Iterable {
   long getFirstAddress();

   long getBase();

   long getLastAddress();

   long getEndAddress();

   boolean isEmpty();

   int size();

   IInstruction get(int var1);

   IInstruction getLast();

   List getInstructions();

   int getSizeOfInstructions();

   long getAddressOfInstruction(int var1);

   int getIndexOfInstruction(long var1);

   IInstruction getInstruction(long var1);

   boolean canThrow();

   List getOutputBlocks();

   IBasicBlock getOutputBlock(int var1);

   List getIrregularOutputBlocks();

   IBasicBlock getIrregularOutputBlock(int var1);

   List getAllOutputBlocks();

   List getOutputs();

   List getIrregularOutputs();

   Iterable getAllOutputs();

   List getInputBlocks();

   IBasicBlock getInputBlock(int var1);

   List getIrregularInputBlocks();

   IBasicBlock getIrregularInputBlock(int var1);

   List getAllInputBlocks();

   List getInputs();

   List getIrregularInputs();

   Iterable getAllInputs();

   int outsize();

   int irroutsize();

   int alloutsize();

   int insize();

   int irrinsize();

   int allinsize();
}
