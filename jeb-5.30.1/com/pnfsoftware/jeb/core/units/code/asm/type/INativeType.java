package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.ICodeType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeType extends ICodeType, INativeItem {
   ITypeManager getTypeManager();

   int getSize();

   default int getBitsize() {
      return this.getSize() * 8;
   }

   IReferenceType getReference();
}
