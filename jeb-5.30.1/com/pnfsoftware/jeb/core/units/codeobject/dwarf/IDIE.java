package com.pnfsoftware.jeb.core.units.codeobject.dwarf;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IDIE {
   long getOffset();

   String getTagName();

   String getAttributeName();

   String getNamespace();

   String getFullName();

   IDIE getParent();

   List getChildren();

   List getAttributes();

   IDIEAttribute getAttribute(String var1);

   IDIEAttribute getAttribute(Dwarf.DwarfAttribute var1);

   IDIE getChildAtOffset(long var1);

   IDwCompileUnit getCompileUnit();

   IDIE getReference(IDIEAttribute var1);

   IDIE getSpecification();
}
