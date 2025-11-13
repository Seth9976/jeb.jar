package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaCompound extends IJavaStatement {
   List getBlocks();

   boolean insertAt(int var1, IJavaStatement var2);

   List generateFlatList();

   void reset();

   List getSubElements(boolean var1);

   IJavaCompound duplicate();
}
