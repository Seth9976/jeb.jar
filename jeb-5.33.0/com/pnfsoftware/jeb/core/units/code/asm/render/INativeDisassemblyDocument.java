package com.pnfsoftware.jeb.core.units.code.asm.render;

import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;

public interface INativeDisassemblyDocument extends ITextDocument {
   NativeDisassemblyProperties getPropertyOverrides();

   void setPropertyOverrides(NativeDisassemblyProperties var1);

   ITextDocumentPart getDisassemblyPart(long var1, long var3);

   INativeCodeUnit getUnit();
}
