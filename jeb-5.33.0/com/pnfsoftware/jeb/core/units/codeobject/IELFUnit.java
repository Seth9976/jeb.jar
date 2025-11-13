package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.IUnit;
import java.util.List;

public interface IELFUnit extends ICodeObjectUnit {
   boolean isELF64();

   IELFHeader getHeader();

   List getProgramEntries();

   IELFProgramEntry getProgramEntry(int var1);

   List getSectionEntries();

   IELFSectionEntry getSectionEntry(int var1);

   String getSectionName(int var1);

   List getSymbolTables();

   List getRelocationTables();

   IELFDynamicTable getDynamicTable();

   IUnit getImageUnit();

   List getDwarfDIEs();
}
