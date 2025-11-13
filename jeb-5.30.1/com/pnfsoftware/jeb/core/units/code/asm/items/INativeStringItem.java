package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.ICodeString;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeStringItem extends ICodeString, INativeDataItem {
   StringEncoding getStringType();
}
