package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class clf {
   @SerId(1)
   final clg q;
   @SerId(2)
   private final byte RF;
   @SerId(3)
   private final short xK;
   @SerId(4)
   private int Dw;

   public clf(clg var1) {
      this(var1, (byte)0, (short)0, 0);
   }

   public clf(clg var1, byte var2, short var3, int var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   public clf(clf var1, clg var2) {
      this(var2, var1.RF, var1.xK, var1.Dw);
   }

   public clg q() {
      return this.q;
   }

   public static clf q(IUnitCreator var0) {
      if (var0 instanceof IELFUnit) {
         return q((IELFUnit)var0);
      } else if (var0 instanceof IPECOFFUnit) {
         clg var1 = q((IPECOFFUnit)var0);
         return var1 == null ? null : new clf(var1);
      } else {
         return null;
      }
   }

   private static clf q(IELFUnit var0) {
      int var1 = var0.getHeader().getFlags();
      int var2 = var0.getHeader().getType();
      clg var3 = clg.q((var1 & -268435456) >>> 24);
      byte var4 = (byte)((var1 & 251658240) >> 24);
      short var5 = (short)((var1 & 0xFF0000) >> 16);
      return new clf(var3, var4, var5, var2);
   }

   private static clg q(IPECOFFUnit var0) {
      switch (var0.getCOFFHeader().getMachine()) {
         case 352:
         case 354:
            return clg.q;
         case 353:
         case 355:
         case 356:
         case 357:
         case 359:
         default:
            return null;
         case 358:
            return clg.xK;
         case 360:
            return clg.Dw;
         case 361:
            return clg.RF;
      }
   }

   public boolean RF() {
      return this.RF != 0;
   }

   public boolean xK() {
      return (this.RF & 2) != 0;
   }

   public boolean Dw() {
      return (this.RF & 4) != 0;
   }

   public boolean Uv() {
      return (this.RF & 8) != 0;
   }

   public short oW() {
      return this.xK;
   }

   public String gO() {
      switch (this.xK) {
         case 129:
            return "MACH_3900 (Toshiba R3900)";
         case 130:
            return "MACH_4010 (LSI R4010)";
         case 131:
            return "MACH_4100 (NEC VR4100)";
         case 132:
         case 134:
         case 137:
         case 143:
         case 144:
         case 147:
         case 148:
         case 149:
         case 150:
         case 151:
         case 154:
         case 155:
         case 156:
         case 157:
         case 158:
         case 159:
         default:
            return "0x" + Integer.toHexString(this.xK);
         case 133:
            return "MACH_4650 (MIPS R4650)";
         case 135:
            return "MACH_4120 (NEC VR4120)";
         case 136:
            return "MACH_4111 (NEC VR4111/VR4181)";
         case 138:
            return "MACH_SB1 (Broadcom SB-1)";
         case 139:
            return "MACH_OCTEON (Cavium Networks Octeon)";
         case 140:
            return "MACH_XLR (RMI Xlr)";
         case 141:
            return "MACH_OCTEON2 (Cavium Networks Octeon2)";
         case 142:
            return "MACH_OCTEON3 (Cavium Networks Octeon3)";
         case 145:
            return "MACH_5400 (NEC VR5400)";
         case 146:
            return "MACH_5900 (MIPS R5900)";
         case 152:
            return "MACH_5500 (NEC VR5500)";
         case 153:
            return "MACH_9000";
         case 160:
            return "MACH_LS2E (ST Microelectronics Loongson 2E)";
         case 161:
            return "MACH_LS2F (ST Microelectronics Loongson 2F)";
         case 162:
            return this.Dw == 65440 ? "Allegrex (Sony PSP)" : "MACH_LS3A (Loongson 3A)";
      }
   }

   public static clf q(int var0) {
      return new clf(var0 == 64 ? clg.gP : clg.nf);
   }
}
