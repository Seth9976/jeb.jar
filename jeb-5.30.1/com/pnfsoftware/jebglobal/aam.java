package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModelListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface aam extends IMemoryModelListener, INativeItemListener {
   void q(axp var1);

   void RF(axp var1);
}
