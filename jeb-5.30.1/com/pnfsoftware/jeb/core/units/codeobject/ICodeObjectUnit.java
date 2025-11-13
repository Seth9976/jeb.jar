package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.IBinaryUnit;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface ICodeObjectUnit extends IBinaryUnit {
   ILoaderInformation getLoaderInformation();

   Collection getSegments();

   Collection getValidSegments();

   Collection getSegments(int var1, int var2);

   ISegmentInformation getSegment(int var1);

   int getSegmentCount();

   Collection getSections();

   Collection getValidSections();

   Collection getSections(int var1, int var2);

   ISegmentInformation getSection(int var1);

   int getSectionCount();

   Collection getSymbols();

   Collection getSymbols(int var1, int var2);

   Collection getImportedSymbols();

   Collection getExportedSymbols();

   long convertFileOffsetToRelativeAddress(long var1);

   long convertRelativeAddressToFileOffset(long var1);

   IVirtualMemory getRawMemoryMappedImage();

   boolean map(IVirtualMemory var1, long var2, boolean var4, ILinkInfoProvider var5);
}
