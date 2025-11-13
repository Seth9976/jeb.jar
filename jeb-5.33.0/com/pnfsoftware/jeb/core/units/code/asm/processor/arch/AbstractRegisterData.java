package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.CannotReadRegisterException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractRegisterData implements IRegisterData {
   private AbstractRegisterBank layout;
   private Endianness endianness;
   protected Map values = new HashMap();
   protected Set dirtyRegisters = new TreeSet();

   public AbstractRegisterData(AbstractRegisterBank var1, Endianness var2) {
      if (var1 == null) {
         throw new NullPointerException("Need a bank register layout");
      } else if (var2 == null) {
         throw new NullPointerException("Need byte ordering");
      } else {
         this.layout = var1;
         this.endianness = var2;
      }
   }

   public AbstractRegisterBank getBank() {
      return this.layout;
   }

   @Override
   public Endianness getEndianness() {
      return this.endianness;
   }

   Collection getDescriptionEntries() {
      return this.layout.getDescriptionEntries();
   }

   RegisterDescriptionEntry getDescriptionEntry(int var1) {
      return this.layout.getDescriptionEntry(var1);
   }

   int getCountOfDescriptionEntries() {
      return this.layout.getCountOfDescriptionEntries();
   }

   @Override
   public int size() {
      return this.getCountOfDescriptionEntries();
   }

   @Override
   public String getName(int var1) {
      return this.getDescriptionEntry(var1).getName();
   }

   @Override
   public int getBitsize(int var1) {
      return this.getDescriptionEntry(var1).getBitsize();
   }

   public int getSize(int var1) {
      return this.getDescriptionEntry(var1).getSize();
   }

   @Override
   public byte[] getValue(int var1) {
      return (byte[])this.values.get(var1);
   }

   @Override
   public Long getValueAsLong(int var1) {
      byte[] var2 = (byte[])this.values.get(var1);
      if (var2 == null) {
         return null;
      } else {
         RegisterDescriptionEntry var3 = this.getDescriptionEntry(var1);
         if (var2.length != var3.getSize()) {
            return null;
         } else {
            switch (var3.getBitsize()) {
               case 8:
                  return var2[0] & 255L;
               case 16:
                  return EndianUtil.bytesToShort(var2, 0, this.endianness.toByteOrder()) & 65535L;
               case 32:
                  return EndianUtil.bytesToInt(var2, 0, this.endianness.toByteOrder()) & 4294967295L;
               case 64:
                  return EndianUtil.bytesToLong(var2, 0, this.endianness.toByteOrder());
               default:
                  return null;
            }
         }
      }
   }

   @Override
   public boolean setValue(int var1, byte[] var2) {
      if (var2 != null && var2.length != this.getSize(var1)) {
         return false;
      } else {
         byte[] var3 = (byte[])this.values.get(var1);
         if (Arrays.equals(var3, var2)) {
            return true;
         } else {
            this.values.put(var1, var2);
            this.dirtyRegisters.add(var1);
            return true;
         }
      }
   }

   @Override
   public boolean setValueAsLong(int var1, long var2) {
      int var4 = this.getSize(var1);
      byte[] var5 = new byte[var4];
      if (this.endianness.isBig()) {
         switch (var4) {
            case 1:
               var5[0] = (byte)var2;
               break;
            case 2:
               EndianUtil.shortToBEBytes((short)var2, var5);
               break;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
               return false;
            case 4:
               EndianUtil.intToBEBytes((int)var2, var5);
               break;
            case 8:
               EndianUtil.longToBEBytes(var2, var5);
         }
      } else {
         switch (var4) {
            case 1:
               var5[0] = (byte)var2;
               break;
            case 2:
               EndianUtil.shortToLEBytes((short)var2, var5);
               break;
            case 3:
            case 5:
            case 6:
            case 7:
            default:
               return false;
            case 4:
               EndianUtil.intToLEBytes((int)var2, var5);
               break;
            case 8:
               EndianUtil.longToLEBytes(var2, var5);
         }
      }

      this.setValue(var1, var5);
      return true;
   }

   public Set getDirtyRegisters() {
      return this.dirtyRegisters;
   }

   public boolean isDirty(int var1) {
      return this.dirtyRegisters.contains(var1);
   }

   public void setDirty(int var1) {
      this.dirtyRegisters.add(var1);
   }

   public void setAllDirty() {
      this.dirtyRegisters.addAll(this.values.keySet());
   }

   public void clearDirty(int var1) {
      this.dirtyRegisters.remove(var1);
   }

   public void clearAllDirty() {
      this.dirtyRegisters.clear();
   }

   @Override
   public long getProgramCounter() throws CannotReadRegisterException {
      RegisterDescriptionEntry var1 = this.layout.getDescriptionEntryByType(RegisterType.ProgramCounter);
      if (var1 == null) {
         throw new CannotReadRegisterException(RegisterType.ProgramCounter);
      } else {
         Long var2 = this.getValueAsLong(var1.getNumber());
         if (var2 == null) {
            throw new CannotReadRegisterException(RegisterType.ProgramCounter);
         } else {
            return var2;
         }
      }
   }

   @Override
   public long getFlags() throws CannotReadRegisterException {
      RegisterDescriptionEntry var1 = this.layout.getDescriptionEntryByType(RegisterType.Flags);
      if (var1 == null) {
         throw new CannotReadRegisterException(RegisterType.Flags);
      } else {
         Long var2 = this.getValueAsLong(var1.getNumber());
         if (var2 == null) {
            throw new CannotReadRegisterException(RegisterType.Flags);
         } else {
            return var2;
         }
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (RegisterDescriptionEntry var4 : this.getDescriptionEntries()) {
         int var5 = var4.getNumber();
         byte[] var6 = this.getValue(var5);
         if (var6 != null) {
            if (var2 > 0 && var2 % 4 == 0) {
               var1.append("\n");
            }

            Strings.ff(var1, "%s:%s ", this.getName(var5), RegisterUtil.byteArrayToHex(this.endianness, var6));
            var2++;
         }
      }

      return var1.toString();
   }
}
