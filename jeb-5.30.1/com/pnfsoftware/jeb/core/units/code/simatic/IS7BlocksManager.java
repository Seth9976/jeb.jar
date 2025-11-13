package com.pnfsoftware.jeb.core.units.code.simatic;

import java.util.List;

public interface IS7BlocksManager {
   List getBlocks();

   IS7Block findBlock(S7.BlockType var1, int var2);

   String getBlockEntryName(IS7Block var1);
}
