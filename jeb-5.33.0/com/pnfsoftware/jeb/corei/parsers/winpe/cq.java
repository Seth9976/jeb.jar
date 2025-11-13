package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.codeobject.IPEDataDirectory;
import com.pnfsoftware.jeb.core.units.codeobject.IPEOptionalHeader;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class cq implements IPEOptionalHeader {
   @SerId(1)
   public short pC;
   @SerId(2)
   public byte A;
   @SerId(3)
   public byte kS;
   @SerId(4)
   public long wS;
   @SerId(5)
   public long UT;
   @SerId(6)
   public long E;
   @SerId(7)
   public long sY;
   @SerId(8)
   public long ys;
   @SerId(9)
   public long ld;
   @SerId(10)
   public long gp;
   @SerId(11)
   public int oT;
   @SerId(12)
   public int fI;
   @SerId(13)
   public short WR;
   @SerId(14)
   public short NS;
   @SerId(15)
   public short vP;
   @SerId(16)
   public short xC;
   @SerId(17)
   public short ED;
   @SerId(18)
   public short Sc;
   @SerId(19)
   public int ah;
   @SerId(20)
   public long eP;
   @SerId(21)
   public long UO;
   @SerId(22)
   public int Ab;
   @SerId(23)
   public short rl;
   @SerId(24)
   public short z;
   @SerId(25)
   public long Ek;
   @SerId(26)
   public long hK;
   @SerId(27)
   public long Er;
   @SerId(28)
   public long FE;
   @SerId(29)
   public int Aj;
   @SerId(30)
   public long EX;
   @SerId(31)
   public Av[] LM = new Av[0];

   public static cq pC(ByteBuffer var0, boolean var1) {
      cq var2 = new cq();
      var2.pC = var0.getShort();
      var2.A = var0.get();
      var2.kS = var0.get();
      var2.wS = var0.getInt() & 4294967295L;
      var2.UT = var0.getInt() & 4294967295L;
      var2.E = var0.getInt() & 4294967295L;
      var2.sY = var0.getInt() & 4294967295L;
      var2.ys = var0.getInt() & 4294967295L;
      var2.ld = var1 ? 0L : var0.getInt() & 4294967295L;
      var2.gp = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.oT = var0.getInt();
      var2.fI = var0.getInt();
      var2.WR = var0.getShort();
      var2.NS = var0.getShort();
      var2.vP = var0.getShort();
      var2.xC = var0.getShort();
      var2.ED = var0.getShort();
      var2.Sc = var0.getShort();
      var2.ah = var0.getInt();
      var2.eP = var0.getInt() & 4294967295L;
      var2.UO = var0.getInt() & 4294967295L;
      var2.Ab = var0.getInt();
      var2.rl = var0.remaining() == 1 ? var0.get() : var0.getShort();

      try {
         var2.z = var0.getShort();
         var2.Ek = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
         var2.hK = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
         var2.Er = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
         var2.FE = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
         var2.Aj = var0.getInt();
         var2.EX = var0.getInt() & 4294967295L;
         int var3 = var2.EX <= 16L ? (int)var2.EX : 16;
         var2.LM = new Av[var3];

         for (int var4 = 0; var4 < var3; var4++) {
            Av var5 = new Av();
            var5.kS = var0.getInt() & 4294967295L;
            var5.wS = var0.getInt() & 4294967295L;
            var2.LM[var4] = var5;
         }
      } catch (BufferUnderflowException var6) {
      }

      return var2;
   }

   @Override
   public short getMagic() {
      return this.pC;
   }

   @Override
   public byte getMajorLinkerVersion() {
      return this.A;
   }

   @Override
   public byte getMinorLinkerVersion() {
      return this.kS;
   }

   @Override
   public long getSizeOfCode() {
      return this.wS;
   }

   @Override
   public long getSizeOfInitializedData() {
      return this.UT;
   }

   @Override
   public long getSizeOfUninitializedData() {
      return this.E;
   }

   @Override
   public long getAddressOfEntryPoint() {
      return this.sY;
   }

   @Override
   public long getBaseOfCode() {
      return this.ys;
   }

   @Override
   public long getBaseOfData() {
      return this.ld;
   }

   @Override
   public long getImageBase() {
      return this.gp;
   }

   @Override
   public int getSectionAlignment() {
      return this.oT;
   }

   @Override
   public int getFileAlignment() {
      return this.fI;
   }

   @Override
   public short getMajorOperatingSystemVersion() {
      return this.WR;
   }

   @Override
   public short getMinorOperatingSystemVersion() {
      return this.NS;
   }

   @Override
   public short getMajorImageVersion() {
      return this.vP;
   }

   @Override
   public short getMinorImageVersion() {
      return this.xC;
   }

   @Override
   public short getMajorSubsystemVersion() {
      return this.ED;
   }

   @Override
   public short getMinorSubsystemVersion() {
      return this.Sc;
   }

   @Override
   public int getWin32VersionValue() {
      return this.ah;
   }

   @Override
   public long getSizeOfImage() {
      return this.eP;
   }

   @Override
   public long getSizeOfHeaders() {
      return this.UO;
   }

   @Override
   public int getCheckSum() {
      return this.Ab;
   }

   @Override
   public short getSubsystem() {
      return this.rl;
   }

   @Override
   public short getDllCharacteristics() {
      return this.z;
   }

   @Override
   public long getSizeOfStackReserve() {
      return this.Ek;
   }

   @Override
   public long getSizeOfStackCommit() {
      return this.hK;
   }

   @Override
   public long getSizeOfHeapReserve() {
      return this.Er;
   }

   @Override
   public long getSizeOfHeapCommit() {
      return this.FE;
   }

   @Override
   public int getLoaderFlags() {
      return this.Aj;
   }

   @Override
   public long getNumberOfRvaAndSizes() {
      return this.EX;
   }

   @Override
   public IPEDataDirectory[] getDataDirectory() {
      return this.LM;
   }
}
