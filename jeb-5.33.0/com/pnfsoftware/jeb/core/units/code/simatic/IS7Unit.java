package com.pnfsoftware.jeb.core.units.code.simatic;

import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;

public interface IS7Unit extends IS7BlocksManager, ICodeObjectUnit {
   IS7Block getBlockAt(long var1);

   IS7Block getBlockContaining(long var1);

   long getAddressOfRawBytes(IS7Block var1);

   long getAddressOfCode(S7.BlockType var1, int var2);

   long getAddressOfData(S7.BlockType var1, int var2);

   long getAddressOfGlobals();

   long getAddressOfDigitalInputs();

   long getAddressOfDigitalOutputs();

   long getAddressOfCounters();

   long getAddressOfTimers();
}
