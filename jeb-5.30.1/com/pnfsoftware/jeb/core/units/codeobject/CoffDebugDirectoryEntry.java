package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class CoffDebugDirectoryEntry {
   public static final int SIZE_ON_DISK = 28;
   @SerId(1)
   int Characteristics;
   @SerId(2)
   int TimeDateStamp;
   @SerId(3)
   int MajorVersion;
   @SerId(4)
   int MinorVersion;
   @SerId(5)
   int Type;
   @SerId(6)
   int SizeOfData;
   @SerId(7)
   int AddressOfRawData;
   @SerId(8)
   int PointerToRawData;
   @SerId(9)
   long startAddress;

   public static CoffDebugDirectoryEntry parse(long var0, ByteBuffer var2) {
      CoffDebugDirectoryEntry var3 = new CoffDebugDirectoryEntry();
      var3.startAddress = var0;
      var3.Characteristics = var2.getInt();
      var3.TimeDateStamp = var2.getInt();
      var3.MajorVersion = Winunp.getUINT16(var2);
      var3.MinorVersion = Winunp.getUINT16(var2);
      var3.Type = var2.getInt();
      var3.SizeOfData = var2.getInt();
      var3.AddressOfRawData = var2.getInt();
      var3.PointerToRawData = var2.getInt();
      return var3;
   }

   private CoffDebugDirectoryEntry() {
   }

   public int getCharacteristics() {
      return this.Characteristics;
   }

   public int getTimeDateStamp() {
      return this.TimeDateStamp;
   }

   public int getMajorVersion() {
      return this.MajorVersion;
   }

   public int getMinorVersion() {
      return this.MinorVersion;
   }

   public int getType() {
      return this.Type;
   }

   public int getSizeOfData() {
      return this.SizeOfData;
   }

   public int getAddressOfRawData() {
      return this.AddressOfRawData;
   }

   public int getPointerToRawData() {
      return this.PointerToRawData;
   }

   public long getStartAddress() {
      return this.startAddress;
   }
}
