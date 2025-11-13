package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICConditionalStatement extends ICCompound {
   ICBlock getDefaultBlock();

   void setDefaultBlock(ICBlock var1);

   boolean hasDefaultBlock();

   List getConditionalBlocks();

   @Override
   List getBlocks();

   int size();

   int sizeWithoutDefault();

   ICConditionalStatement duplicate();
}
