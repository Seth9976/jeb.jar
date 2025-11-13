package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.codeobject.IPEDataDirectory;
import com.pnfsoftware.jeb.core.units.codeobject.IPEOptionalHeader;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class Nt implements IPEOptionalHeader {
   @SerId(1)
   public short q;
   @SerId(2)
   public byte RF;
   @SerId(3)
   public byte xK;
   @SerId(4)
   public long Dw;
   @SerId(5)
   public long Uv;
   @SerId(6)
   public long oW;
   @SerId(7)
   public long gO;
   @SerId(8)
   public long nf;
   @SerId(9)
   public long gP;
   @SerId(10)
   public long za;
   @SerId(11)
   public int lm;
   @SerId(12)
   public int zz;
   @SerId(13)
   public short JY;
   @SerId(14)
   public short HF;
   @SerId(15)
   public short LK;
   @SerId(16)
   public short io;
   @SerId(17)
   public short qa;
   @SerId(18)
   public short Hk;
   @SerId(19)
   public int Me;
   @SerId(20)
   public long PV;
   @SerId(21)
   public long oQ;
   @SerId(22)
   public int xW;
   @SerId(23)
   public short KT;
   @SerId(24)
   public short Gf;
   @SerId(25)
   public long Ef;
   @SerId(26)
   public long cC;
   @SerId(27)
   public long sH;
   @SerId(28)
   public long CE;
   @SerId(29)
   public int wF;
   @SerId(30)
   public long If;
   @SerId(31)
   public eo[] Dz = new eo[0];

   public static Nt q(ByteBuffer var0, boolean var1) {
      Nt var2 = new Nt();
      var2.q = var0.getShort();
      var2.RF = var0.get();
      var2.xK = var0.get();
      var2.Dw = var0.getInt() & 4294967295L;
      var2.Uv = var0.getInt() & 4294967295L;
      var2.oW = var0.getInt() & 4294967295L;
      var2.gO = var0.getInt() & 4294967295L;
      var2.nf = var0.getInt() & 4294967295L;
      var2.gP = var1 ? 0L : var0.getInt() & 4294967295L;
      var2.za = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.lm = var0.getInt();
      var2.zz = var0.getInt();
      var2.JY = var0.getShort();
      var2.HF = var0.getShort();
      var2.LK = var0.getShort();
      var2.io = var0.getShort();
      var2.qa = var0.getShort();
      var2.Hk = var0.getShort();
      var2.Me = var0.getInt();
      var2.PV = var0.getInt() & 4294967295L;
      var2.oQ = var0.getInt() & 4294967295L;
      var2.xW = var0.getInt();
      var2.KT = var0.remaining() == 1 ? var0.get() : var0.getShort();

      try {
         var2.Gf = var0.getShort();
         var2.Ef = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
         var2.cC = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
         var2.sH = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
         var2.CE = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
         var2.wF = var0.getInt();
         var2.If = var0.getInt() & 4294967295L;
         int var3 = var2.If <= 16L ? (int)var2.If : 16;
         var2.Dz = new eo[var3];

         for (int var4 = 0; var4 < var3; var4++) {
            eo var5 = new eo();
            var5.xK = var0.getInt() & 4294967295L;
            var5.Dw = var0.getInt() & 4294967295L;
            var2.Dz[var4] = var5;
         }
      } catch (BufferUnderflowException var6) {
      }

      return var2;
   }

   @Override
   public short getMagic() {
      return this.q;
   }

   @Override
   public byte getMajorLinkerVersion() {
      return this.RF;
   }

   @Override
   public byte getMinorLinkerVersion() {
      return this.xK;
   }

   @Override
   public long getSizeOfCode() {
      return this.Dw;
   }

   @Override
   public long getSizeOfInitializedData() {
      return this.Uv;
   }

   @Override
   public long getSizeOfUninitializedData() {
      return this.oW;
   }

   @Override
   public long getAddressOfEntryPoint() {
      return this.gO;
   }

   @Override
   public long getBaseOfCode() {
      return this.nf;
   }

   @Override
   public long getBaseOfData() {
      return this.gP;
   }

   @Override
   public long getImageBase() {
      return this.za;
   }

   @Override
   public int getSectionAlignment() {
      return this.lm;
   }

   @Override
   public int getFileAlignment() {
      return this.zz;
   }

   @Override
   public short getMajorOperatingSystemVersion() {
      return this.JY;
   }

   @Override
   public short getMinorOperatingSystemVersion() {
      return this.HF;
   }

   @Override
   public short getMajorImageVersion() {
      return this.LK;
   }

   @Override
   public short getMinorImageVersion() {
      return this.io;
   }

   @Override
   public short getMajorSubsystemVersion() {
      return this.qa;
   }

   @Override
   public short getMinorSubsystemVersion() {
      return this.Hk;
   }

   @Override
   public int getWin32VersionValue() {
      return this.Me;
   }

   @Override
   public long getSizeOfImage() {
      return this.PV;
   }

   @Override
   public long getSizeOfHeaders() {
      return this.oQ;
   }

   @Override
   public int getCheckSum() {
      return this.xW;
   }

   @Override
   public short getSubsystem() {
      return this.KT;
   }

   @Override
   public short getDllCharacteristics() {
      return this.Gf;
   }

   @Override
   public long getSizeOfStackReserve() {
      return this.Ef;
   }

   @Override
   public long getSizeOfStackCommit() {
      return this.cC;
   }

   @Override
   public long getSizeOfHeapReserve() {
      return this.sH;
   }

   @Override
   public long getSizeOfHeapCommit() {
      return this.CE;
   }

   @Override
   public int getLoaderFlags() {
      return this.wF;
   }

   @Override
   public long getNumberOfRvaAndSizes() {
      return this.If;
   }

   @Override
   public IPEDataDirectory[] getDataDirectory() {
      return this.Dz;
   }
}
