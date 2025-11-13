package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFHeader;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Ser
public class p implements IELFHeader {
   @SerId(1)
   public int pC;
   @SerId(2)
   public int A;
   @SerId(3)
   public int kS;
   @SerId(4)
   public int wS;
   @SerId(5)
   public int UT;
   @SerId(6)
   public int E;
   @SerId(7)
   public int sY;
   @SerId(8)
   public int ys;
   @SerId(9)
   public long ld;
   @SerId(10)
   public long gp;
   @SerId(11)
   public long oT;
   @SerId(12)
   public int fI;
   @SerId(13)
   public int WR;
   @SerId(14)
   public int NS;
   @SerId(15)
   public int vP;
   @SerId(16)
   public int xC;
   @SerId(17)
   public int ED;
   @SerId(18)
   public int Sc;
   @SerId(19)
   boolean ah;
   @SerId(20)
   boolean eP;

   @Override
   public int getBitsize() {
      return this.eP ? 64 : 32;
   }

   @Override
   public Endianness getEndianness() {
      return this.ah ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
   }

   @Override
   public int getOsabi() {
      return this.wS;
   }

   @Override
   public int getAbiVersion() {
      return this.UT;
   }

   @Override
   public int getType() {
      return this.E;
   }

   @Override
   public int getMachine() {
      return this.sY;
   }

   @Override
   public long getEntry() {
      return this.ld;
   }

   @Override
   public long getProgramHeaderTableOffset() {
      return this.gp;
   }

   @Override
   public long getSectionHeaderTableOffset() {
      return this.oT;
   }

   @Override
   public int getFlags() {
      return this.fI;
   }

   @Override
   public int getHeaderSize() {
      return this.WR;
   }

   @Override
   public int getProgramHeaderTableEntrySize() {
      return this.NS;
   }

   @Override
   public int getProgramHeaderTableEntryNumber() {
      return this.vP;
   }

   @Override
   public int getSectionHeaderTableEntrySize() {
      return this.xC;
   }

   @Override
   public int getSectionHeaderTableEntryNumber() {
      return this.ED;
   }

   public static p pC(ByteBuffer var0) {
      p var1 = new p();
      var0.order(ByteOrder.LITTLE_ENDIAN);
      var1.ah = false;
      var1.eP = false;
      var0.position(4);
      var1.pC = var0.get() & 255;
      var1.A = var0.get() & 255;
      var1.kS = var0.get() & 255;
      var1.wS = var0.get() & 255;
      var1.UT = var0.get() & 255;
      if (var1.pC == 2) {
         var1.eP = true;
      } else if (var1.pC != 1) {
         throw new RuntimeException("Illegal ELF file: eiClass=" + var1.pC);
      }

      if (var1.A == 2) {
         var0.order(ByteOrder.BIG_ENDIAN);
         var1.ah = true;
      } else if (var1.A != 1) {
         throw new RuntimeException("Illegal ELF file: eiData=" + var1.A);
      }

      if (var1.kS != 1) {
         throw new RuntimeException("Illegal ELF file: eiVersion=" + var1.kS);
      } else {
         var0.position(var0.position() + 7);
         var1.E = var0.getShort() & '\uffff';
         var1.sY = var0.getShort() & '\uffff';
         var1.ys = var0.getInt();
         var1.ld = var1.eP ? var0.getLong() : var0.getInt() & 4294967295L;
         var1.gp = var1.eP ? var0.getLong() : var0.getInt() & 4294967295L;
         var1.oT = var1.eP ? var0.getLong() : var0.getInt() & 4294967295L;
         var1.fI = var0.getInt();
         var1.WR = var0.getShort() & '\uffff';
         var1.NS = var0.getShort() & '\uffff';
         var1.vP = var0.getShort() & '\uffff';
         var1.xC = var0.getShort() & '\uffff';
         var1.ED = var0.getShort() & '\uffff';
         var1.Sc = var0.getShort() & '\uffff';
         return var1;
      }
   }

   public ProcessorType pC() {
      switch (this.sY) {
         case 3:
            return ProcessorType.X86;
         case 8:
            return this.eP ? ProcessorType.MIPS64 : ProcessorType.MIPS;
         case 40:
            if (this.eP) {
               throw new RuntimeException();
            }

            return ProcessorType.ARM;
         case 62:
            return ProcessorType.X86_64;
         case 83:
            return ProcessorType.AVR;
         case 183:
            if (!this.eP) {
               throw new RuntimeException();
            }

            return ProcessorType.ARM64;
         case 185:
            return ProcessorType.AVR32;
         default:
            return ProcessorType.UNKNOWN;
      }
   }

   public SubsystemType A() {
      switch (this.wS) {
         case 0:
         case 3:
            return SubsystemType.LINUX;
         default:
            return SubsystemType.LINUX;
      }
   }

   @Override
   public String format(IELFUnit var1) {
      StringBuilder var2 = new StringBuilder();
      this.pC(var1, var2);
      return var2.toString();
   }

   public void pC(IELFUnit var1, StringBuilder var2) {
      Strings.ff(
         var2, "ELF %d-bit, %s-endian, type %s, machine %s\n", this.eP ? 64 : 32, this.ah ? "big" : "little", ELF.getETString(this.E), ELF.getEMString(this.sY)
      );
   }
}
