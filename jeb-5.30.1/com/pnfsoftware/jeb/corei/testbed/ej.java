package com.pnfsoftware.jeb.corei.testbed;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ICOFFHeader;
import com.pnfsoftware.jeb.core.units.codeobject.ICOFFSectionHeader;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPEDataDirectory;
import com.pnfsoftware.jeb.core.units.codeobject.IPEOptionalHeader;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Bytes;
import com.pnfsoftware.jeb.util.primitives.Integers;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.primitives.Shorts;
import java.util.HashMap;
import java.util.Map;

public class ej implements nI {
   private static final ILogger Uv = GlobalLog.getLogger(ej.class);
   private String oW = "PE";

   @Override
   public Map q(IUnit var1) {
      if (!var1.getFormatType().equals("winpe")) {
         return null;
      } else {
         IPECOFFUnit var2 = (IPECOFFUnit)var1;
         HashMap var3 = new HashMap();
         ICOFFHeader var4 = var2.getCOFFHeader();
         if (var4 != null) {
            var3.put("ImageFileHeader.Characteristics", Integers.toUnsignedString(var4.getCharacteristics()));
            var3.put("ImageFileHeader.Machine", Integers.toUnsignedString(var4.getMachine()));
            var3.put("ImageFileHeader.NumberOfSections", Integers.toUnsignedString(var4.getNumberOfSections()));
            var3.put("ImageFileHeader.NumberOfSymbols", Integers.toUnsignedString(var4.getNumberOfSymbols()));
            var3.put("ImageFileHeader.PointerToSymbolTable", Integers.toUnsignedString(var4.getPointerToSymbolTable()));
            var3.put("ImageFileHeader.SizeOfOptionalHeader", Integers.toUnsignedString(var4.getSizeOfOptionalHeader()));
            var3.put("ImageFileHeader.TimeDateStamp", Integers.toUnsignedString(var4.getTimeDateStamp()));
         }

         IPEOptionalHeader var5 = var2.getPEOptionalHeader();
         if (var5 != null) {
            var3.put("ImageOptionalHeader.AddressOfEntryPoint", Longs.toUnsignedString(var5.getAddressOfEntryPoint()));
            var3.put("ImageOptionalHeader.BaseOfCode", Longs.toUnsignedString(var5.getBaseOfCode()));
            if (var5.getMagic() == 267) {
               var3.put("ImageOptionalHeader.BaseOfData", Longs.toUnsignedString(var5.getBaseOfData()));
            }

            var3.put("ImageOptionalHeader.CheckSum", Integers.toUnsignedString(var5.getCheckSum()));
            var3.put("ImageOptionalHeader.DllCharacteristics", Shorts.toUnsignedString(var5.getDllCharacteristics()));
            var3.put("ImageOptionalHeader.FileAlignment", Integers.toUnsignedString(var5.getFileAlignment()));
            var3.put("ImageOptionalHeader.ImageBase", Longs.toUnsignedString(var5.getImageBase()));
            var3.put("ImageOptionalHeader.LoaderFlags", Integers.toUnsignedString(var5.getLoaderFlags()));
            var3.put("ImageOptionalHeader.Magic", Shorts.toUnsignedString(var5.getMagic()));
            var3.put("ImageOptionalHeader.MajorImageVersion", Shorts.toUnsignedString(var5.getMajorImageVersion()));
            var3.put("ImageOptionalHeader.MajorLinkerVersion", Bytes.toUnsignedString(var5.getMajorLinkerVersion()));
            var3.put("ImageOptionalHeader.MajorOperatingSystemVersion", Shorts.toUnsignedString(var5.getMajorOperatingSystemVersion()));
            var3.put("ImageOptionalHeader.MajorSubsystemVersion", Shorts.toUnsignedString(var5.getMajorSubsystemVersion()));
            var3.put("ImageOptionalHeader.MinorImageVersion", Shorts.toUnsignedString(var5.getMinorImageVersion()));
            var3.put("ImageOptionalHeader.MinorLinkerVersion", Bytes.toUnsignedString(var5.getMinorLinkerVersion()));
            var3.put("ImageOptionalHeader.MinorOperatingSystemVersion", Shorts.toUnsignedString(var5.getMinorOperatingSystemVersion()));
            var3.put("ImageOptionalHeader.MinorSubsystemVersion", Shorts.toUnsignedString(var5.getMinorSubsystemVersion()));
            var3.put("ImageOptionalHeader.NumberOfRvaAndSizes", Longs.toUnsignedString(var5.getNumberOfRvaAndSizes()));
            var3.put("ImageOptionalHeader.SectionAlignment", Integers.toUnsignedString(var5.getSectionAlignment()));
            var3.put("ImageOptionalHeader.SizeOfCode", Longs.toUnsignedString(var5.getSizeOfCode()));
            var3.put("ImageOptionalHeader.SizeOfHeaders", Longs.toUnsignedString(var5.getSizeOfHeaders()));
            var3.put("ImageOptionalHeader.SizeOfHeapCommit", Longs.toUnsignedString(var5.getSizeOfHeapCommit()));
            var3.put("ImageOptionalHeader.SizeOfHeapReserve", Longs.toUnsignedString(var5.getSizeOfHeapReserve()));
            var3.put("ImageOptionalHeader.SizeOfImage", Longs.toUnsignedString(var5.getSizeOfImage()));
            var3.put("ImageOptionalHeader.SizeOfInitializedData", Longs.toUnsignedString(var5.getSizeOfInitializedData()));
            var3.put("ImageOptionalHeader.SizeOfStackCommit", Longs.toUnsignedString(var5.getSizeOfStackCommit()));
            var3.put("ImageOptionalHeader.SizeOfStackReserve", Longs.toUnsignedString(var5.getSizeOfStackReserve()));
            var3.put("ImageOptionalHeader.SizeOfUninitializedData", Longs.toUnsignedString(var5.getSizeOfUninitializedData()));
            var3.put("ImageOptionalHeader.Subsystem", Shorts.toUnsignedString(var5.getSubsystem()));
            var3.put("ImageOptionalHeader.Win32VersionValue", Integers.toUnsignedString(var5.getWin32VersionValue()));
            IPEDataDirectory[] var6 = var5.getDataDirectory();
            if (var6 != null) {
               var3.put("ImageOptionalHeader.DataDirectory.size_custom", Integers.toUnsignedString(var6.length));
               int var7 = 0;

               for (IPEDataDirectory var11 : var6) {
                  var3.put(Strings.ff("ImageOptionalHeader.DataDirectory%d.VirtualAddress", var7), Longs.toUnsignedString(var11.getPosition()));
                  var3.put(Strings.ff("ImageOptionalHeader.DataDirectory%d.Size", var7), Longs.toUnsignedString(var11.getSize()));
                  var7++;
               }
            }
         }

         ICOFFSectionHeader[] var13 = var2.getSectionHeaders();
         if (var13 != null) {
            var3.put("ImageSectionHeader.size_custom", Integers.toUnsignedString(var13.length));
            int var14 = 0;

            for (ICOFFSectionHeader var18 : var13) {
               if (var18 != null) {
                  var3.put(Strings.ff("Section%d.Characteristics", var14), Integers.toUnsignedString(var18.getCharacteristics()));
                  String var12 = "";
                  if (!ArrayUtil.isSled(var18.getName(), (byte)0)) {
                     var12 = Strings.decodeASCII(var18.getName());
                  }

                  var3.put(Strings.ff("Section%d.Name", var14), var12);
                  var3.put(Strings.ff("Section%d.NumberOfLinenumbers", var14), Integers.toUnsignedString(var18.getNumberOfLinenumbers()));
                  var3.put(Strings.ff("Section%d.NumberOfRelocations", var14), Integers.toUnsignedString(var18.getNumberOfRelocations()));
                  var3.put(Strings.ff("Section%d.PointerToLinenumbers", var14), Longs.toUnsignedString(var18.getPointerToLinenumbers()));
                  var3.put(Strings.ff("Section%d.PointerToRawData", var14), Longs.toUnsignedString(var18.getPointerToRawData()));
                  var3.put(Strings.ff("Section%d.PointerToRelocations", var14), Longs.toUnsignedString(var18.getPointerToRelocations()));
                  var3.put(Strings.ff("Section%d.SizeOfRawData", var14), Longs.toUnsignedString(var18.getSizeOfRawData()));
                  var3.put(Strings.ff("Section%d.VirtualAddress", var14), Longs.toUnsignedString(var18.getVirtualAddress()));
                  var3.put(Strings.ff("Section%d.VirtualSize", var14), Longs.toUnsignedString(var18.getVirtualSize()));
                  var14++;
               }
            }
         }

         return var3;
      }
   }

   @Override
   public String q() {
      return this.oW;
   }

   @Override
   public int RF() {
      return 0;
   }

   @Override
   public Map q(String var1) {
      return null;
   }
}
