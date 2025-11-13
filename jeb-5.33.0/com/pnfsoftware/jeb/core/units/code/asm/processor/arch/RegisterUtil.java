package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterBankService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;

public class RegisterUtil {
   public static IRegisterBank getBank(ProcessorType var0) {
      if (var0 == ProcessorType.X86) {
         return RegisterBankX86.getInstance();
      } else if (var0 == ProcessorType.X86_64) {
         return RegisterBankX64.getInstance();
      } else if (var0 == ProcessorType.ARM) {
         return RegisterBankArm.getInstance();
      } else if (var0 == ProcessorType.ARM64) {
         return RegisterBankArm64.getInstance();
      } else if (var0 == ProcessorType.MIPS) {
         return RegisterBankMips.getInstance();
      } else {
         return (IRegisterBank)(var0 == ProcessorType.MIPS64 ? RegisterBankMips64.getInstance() : RegisterBankService.getInstance().get(var0));
      }
   }

   public static long createRegisterId(int var0, int var1, int var2) {
      return createRegisterId(var0, var1, var2, 0);
   }

   public static long createRegisterId(int var0, int var1, int var2, int var3) {
      if (var0 >= 0 && var0 <= 1048575 && var1 >= 0 && var1 <= 4095 && var2 >= 0 && var2 <= 4095 && var3 >= 0 && var3 <= 4095) {
         return (long)var3 << 44 | (long)var2 << 32 | (long)var1 << 20 | var0;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static long createPureRegisterId(int var0, int var1) {
      return createRegisterId(var0, var1, 0, 0);
   }

   public static long createRegisterIdFromPureId(long var0, int var2, int var3) {
      if ((var0 & -4294967296L) == 0L && var2 >= 0 && var2 <= 4095 && var3 >= 0 && var3 <= 4095) {
         return (long)var3 << 44 | (long)var2 << 32 | var0;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static boolean isPureId(long var0) {
      return (var0 & -4294967296L) == 0L;
   }

   public static long getPureId(long var0) {
      return var0 & 4294967295L;
   }

   public static int getRegisterIndex(long var0) {
      return (int)var0 & 1048575;
   }

   public static int getRegisterGroup(long var0) {
      return (int)(var0 >> 20) & 4095;
   }

   public static int getRegisterBitsize(long var0) {
      return (int)(var0 >> 32) & 4095;
   }

   public static int getRegisterBitstart(long var0) {
      return (int)(var0 >> 44) & 4095;
   }

   public static RegisterDescriptionEntry getEntryByType(IRegisterBank var0, RegisterType var1) {
      for (RegisterDescriptionEntry var3 : var0.getDescriptionEntries()) {
         if (var3.getType() == var1) {
            return var3;
         }
      }

      return null;
   }

   public static RegisterDescriptionEntry getEntryByType(IRegisterData var0, RegisterType var1) {
      return getEntryByType(var0.getBank(), var1);
   }

   public static RegisterDescriptionEntry getEntryByName(IRegisterBank var0, String var1) {
      for (RegisterDescriptionEntry var3 : var0.getDescriptionEntries()) {
         if (var3.hasName(var1)) {
            return var3;
         }
      }

      return null;
   }

   public static RegisterDescriptionEntry getEntryByName(IRegisterData var0, String var1) {
      return getEntryByName(var0.getBank(), var1);
   }

   public static byte[] getValueByName(IRegisterData var0, String var1) {
      RegisterDescriptionEntry var2 = getEntryByName(var0, var1);
      return var2 == null ? null : var0.getValue(var2.getNumber());
   }

   public static Long getValueByNameAsLong(IRegisterData var0, String var1) {
      RegisterDescriptionEntry var2 = getEntryByName(var0, var1);
      return var2 == null ? null : var0.getValueAsLong(var2.getNumber());
   }

   public static boolean setValueByName(IRegisterData var0, String var1, byte[] var2) {
      RegisterDescriptionEntry var3 = getEntryByName(var0, var1);
      return var3 == null ? false : var0.setValue(var3.getNumber(), var2);
   }

   public static boolean setValueByNameAsLong(IRegisterData var0, String var1, long var2) {
      RegisterDescriptionEntry var4 = getEntryByName(var0, var1);
      return var4 == null ? false : var0.setValueAsLong(var4.getNumber(), var2);
   }

   public static String byteArrayToHex(Endianness var0, byte[] var1) {
      return byteArrayToHex(var0, var1, 0, var1.length);
   }

   public static String byteArrayToHex(Endianness var0, byte[] var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var3 <= var1.length && var2 <= var3) {
         int var4 = var3 - var2;
         switch (var4) {
            case 1:
               return Strings.ff("0x%02X", var1[var2]);
            case 2:
               return Strings.ff("0x%04X", EndianUtil.bytesToShort(var1, var2, var0.toByteOrder()));
            case 3:
            case 5:
            case 6:
            case 7:
            default:
               StringBuilder var5 = new StringBuilder();

               for (int var6 = var2; var6 < var3; var6++) {
                  Strings.ff(var5, "%02X", var1[var6]);
               }

               return var5.toString();
            case 4:
               return Strings.ff("0x%08X", EndianUtil.bytesToInt(var1, var2, var0.toByteOrder()));
            case 8:
               return Strings.ff("0x%016X", EndianUtil.bytesToLong(var1, var2, var0.toByteOrder()));
         }
      } else {
         throw new IllegalArgumentException();
      }
   }
}
