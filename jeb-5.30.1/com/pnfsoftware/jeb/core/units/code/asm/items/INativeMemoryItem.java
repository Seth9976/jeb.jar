package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeMemoryItem extends INativeItem {
   long getMemoryAddress();

   String getLabel();
}
