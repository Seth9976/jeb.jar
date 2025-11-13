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
public class tl implements IELFHeader {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   int Uv;
   @SerId(6)
   int oW;
   @SerId(7)
   int gO;
   @SerId(8)
   int nf;
   @SerId(9)
   long gP;
   @SerId(10)
   long za;
   @SerId(11)
   long lm;
   @SerId(12)
   int zz;
   @SerId(13)
   int JY;
   @SerId(14)
   int HF;
   @SerId(15)
   int LK;
   @SerId(16)
   int io;
   @SerId(17)
   int qa;
   @SerId(18)
   int Hk;
   @SerId(19)
   boolean Me;
   @SerId(20)
   boolean PV;

   @Override
   public int getBitsize() {
      return this.PV ? 64 : 32;
   }

   @Override
   public Endianness getEndianness() {
      return this.Me ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
   }

   @Override
   public int getOsabi() {
      return this.Dw;
   }

   @Override
   public int getAbiVersion() {
      return this.Uv;
   }

   @Override
   public int getType() {
      return this.oW;
   }

   @Override
   public int getMachine() {
      return this.gO;
   }

   @Override
   public long getEntry() {
      return this.gP;
   }

   @Override
   public long getProgramHeaderTableOffset() {
      return this.za;
   }

   @Override
   public long getSectionHeaderTableOffset() {
      return this.lm;
   }

   @Override
   public int getFlags() {
      return this.zz;
   }

   @Override
   public int getHeaderSize() {
      return this.JY;
   }

   @Override
   public int getProgramHeaderTableEntrySize() {
      return this.HF;
   }

   @Override
   public int getProgramHeaderTableEntryNumber() {
      return this.LK;
   }

   @Override
   public int getSectionHeaderTableEntrySize() {
      return this.io;
   }

   @Override
   public int getSectionHeaderTableEntryNumber() {
      return this.qa;
   }

   public static tl q(ByteBuffer var0) {
      tl var1 = new tl();
      var0.order(ByteOrder.LITTLE_ENDIAN);
      var1.Me = false;
      var1.PV = false;
      var0.position(4);
      var1.q = var0.get() & 255;
      var1.RF = var0.get() & 255;
      var1.xK = var0.get() & 255;
      var1.Dw = var0.get() & 255;
      var1.Uv = var0.get() & 255;
      if (var1.q == 2) {
         var1.PV = true;
      } else if (var1.q != 1) {
         throw new RuntimeException("Illegal ELF file: eiClass=" + var1.q);
      }

      if (var1.RF == 2) {
         var0.order(ByteOrder.BIG_ENDIAN);
         var1.Me = true;
      } else if (var1.RF != 1) {
         throw new RuntimeException("Illegal ELF file: eiData=" + var1.RF);
      }

      if (var1.xK != 1) {
         throw new RuntimeException("Illegal ELF file: eiVersion=" + var1.xK);
      } else {
         var0.position(var0.position() + 7);
         var1.oW = var0.getShort() & '\uffff';
         var1.gO = var0.getShort() & '\uffff';
         var1.nf = var0.getInt();
         var1.gP = var1.PV ? var0.getLong() : var0.getInt() & 4294967295L;
         var1.za = var1.PV ? var0.getLong() : var0.getInt() & 4294967295L;
         var1.lm = var1.PV ? var0.getLong() : var0.getInt() & 4294967295L;
         var1.zz = var0.getInt();
         var1.JY = var0.getShort() & '\uffff';
         var1.HF = var0.getShort() & '\uffff';
         var1.LK = var0.getShort() & '\uffff';
         var1.io = var0.getShort() & '\uffff';
         var1.qa = var0.getShort() & '\uffff';
         var1.Hk = var0.getShort() & '\uffff';
         return var1;
      }
   }

   public ProcessorType q() {
      switch (this.gO) {
         case 3:
            return ProcessorType.X86;
         case 8:
            return this.PV ? ProcessorType.MIPS64 : ProcessorType.MIPS;
         case 40:
            if (this.PV) {
               throw new RuntimeException();
            }

            return ProcessorType.ARM;
         case 62:
            return ProcessorType.X86_64;
         case 83:
            return ProcessorType.AVR;
         case 183:
            if (!this.PV) {
               throw new RuntimeException();
            }

            return ProcessorType.ARM64;
         case 185:
            return ProcessorType.AVR32;
         default:
            return ProcessorType.UNKNOWN;
      }
   }

   public SubsystemType RF() {
      switch (this.Dw) {
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
      this.q(var1, var2);
      return var2.toString();
   }

   public void q(IELFUnit var1, StringBuilder var2) {
      Strings.ff(
         var2,
         "ELF %d-bit, %s-endian, type %s, machine %s\n",
         this.PV ? 64 : 32,
         this.Me ? "big" : "little",
         ELF.getETString(this.oW),
         ELF.getEMString(this.gO)
      );
   }
}
