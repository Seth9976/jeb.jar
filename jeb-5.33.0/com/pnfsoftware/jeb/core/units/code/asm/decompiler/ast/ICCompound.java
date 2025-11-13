package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICCompound extends ICStatement {
   List getBlocks();

   boolean insertAt(long var1, ICStatement var3);

   List generateFlatList();

   void reset();

   ICCompound duplicate();
}
