package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.ICodeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeFieldItem extends ICodeField, INativeItem {
   IClassType getClassType();

   INativeType getFieldType();

   Couple getStructureFieldDetails();

   INativeDataItem getData();

   default Long getMemoryAddress() {
      return this.getData() == null ? null : this.getData().getMemoryAddress();
   }
}
