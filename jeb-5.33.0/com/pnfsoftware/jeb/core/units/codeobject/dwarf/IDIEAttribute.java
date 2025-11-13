package com.pnfsoftware.jeb.core.units.codeobject.dwarf;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IDIEAttribute {
   String getName();

   Long getRawForm();

   Object getForm();

   IDIE getReference();

   Dwarf.DwarfFormType getType();
}
