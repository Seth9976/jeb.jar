package com.pnfsoftware.jeb.core.units.code.asm.memory;

import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import java.nio.ByteOrder;

public class VMReader {
   IVirtualMemory vm;

   public VMReader(IVirtualMemory var1) {
      this.vm = var1;
   }

   public VMReader.Buf read(long var1, INativeType var3) {
      int var4 = var3.getSize();
      byte[] var5 = new byte[var4];

      try {
         return this.vm.read(var1, var4, var5, 0, true) != var4 ? null : new VMReader.Buf(var5, var3, this.vm);
      } catch (MemoryException var6) {
         return null;
      }
   }

   public static class Buf {
      byte[] bytes;
      INativeType t;
      IStructureType ut;
      IVirtualMemory vm;
      ByteOrder bo;

      Buf(byte[] var1, INativeType var2, IVirtualMemory var3) {
         this.bytes = var1;
         this.t = var2;
         INativeType var4 = TypeUtil.getNonAlias(var2);
         if (var4 instanceof IStructureType) {
            this.ut = (IStructureType)var4;
         }

         this.vm = var3;
         this.bo = var3.getStandardEndianess().toByteOrder();
      }

      public int getByte(String var1) {
         IStructureTypeField var2 = this.ut.getFieldByName(var1);
         return this.getIntAt(var2.getOffset());
      }

      public int getByteAt(int var1) {
         return this.bytes[var1];
      }

      public int getShort(String var1) {
         IStructureTypeField var2 = this.ut.getFieldByName(var1);
         return this.getShortAt(var2.getOffset());
      }

      public int getShortAt(int var1) {
         return EndianUtil.bytesToShort(this.bytes, var1, this.bo);
      }

      public int getInt(String var1) {
         IStructureTypeField var2 = this.ut.getFieldByName(var1);
         return this.getIntAt(var2.getOffset());
      }

      public int getIntAt(int var1) {
         return EndianUtil.bytesToInt(this.bytes, var1, this.bo);
      }

      public long getLong(String var1) {
         IStructureTypeField var2 = this.ut.getFieldByName(var1);
         return this.getLongAt(var2.getOffset());
      }

      public long getLongAt(int var1) {
         return EndianUtil.bytesToLong(this.bytes, var1, this.bo);
      }

      public long getPtr(String var1) {
         IStructureTypeField var2 = this.ut.getFieldByName(var1);
         return this.getPtrAt(var2.getOffset());
      }

      public long getPtrAt(int var1) {
         int var2 = this.t.getTypeManager().getPointerSize();
         if (var2 == 4) {
            return this.getIntAt(var1);
         } else if (var2 == 8) {
            return this.getLongAt(var1);
         } else {
            throw new RuntimeException();
         }
      }

      public String readPointedAsciiString(String var1) {
         long var2 = this.getPtr(var1);
         return VirtualMemoryUtil.readNullTerminatedStringSafe(this.vm, var2, 4096);
      }
   }
}
