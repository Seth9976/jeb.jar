package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.ICodeData;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface INativeDataItem extends ICodeData, INativeContinuousItem {
   String ATTR_GENERATED_FROM = "GeneratedFrom";

   INativeFieldItem getField();

   INativeType getType();

   DataHints getHints(boolean var1);

   List getChildren();
}
