package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.base.Couple;
import java.util.List;

public interface IControlFlowGraph extends Iterable {
   int size();

   IBasicBlock get(int var1);

   int indexOf(IBasicBlock var1);

   IBasicBlock getLast();

   long getEntryAddress();

   long getFirstAddress();

   long getLastAddress();

   long getEndAddress();

   int getEffectiveSize();

   IBasicBlock getBlock(int var1);

   List getBlocks();

   List getBlocksView();

   IBasicBlock getBlockAt(long var1);

   IBasicBlock getBlockEndingAt(long var1);

   IBasicBlock getBlockContaining(long var1);

   IBasicBlock getBlockByLastAddress(long var1);

   IBasicBlock getEntryBlock();

   List getExitBlocks();

   int getInstructionCount();

   Iterable instructions();

   Iterable addressableInstructions();

   List getInstructions();

   IInstruction getInstruction(long var1);

   Couple getInstructionLocation(long var1);

   void getGraphRepresentation(List var1, List var2);
}
