package com.pnfsoftware.jeb.core.units.codeobject;

public interface IPECOFFUnit extends ICodeObjectUnit {
   boolean isPE64();

   ICOFFHeader getCOFFHeader();

   IPEOptionalHeader getPEOptionalHeader();

   ICOFFSectionHeader[] getSectionHeaders();
}
