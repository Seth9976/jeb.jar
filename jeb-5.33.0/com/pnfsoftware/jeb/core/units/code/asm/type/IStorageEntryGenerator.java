package com.pnfsoftware.jeb.core.units.code.asm.type;

import java.util.List;

public interface IStorageEntryGenerator {
   StorageEntry next(TypeLayoutInfo var1);

   List getCurrentEntries();

   void reset();
}
