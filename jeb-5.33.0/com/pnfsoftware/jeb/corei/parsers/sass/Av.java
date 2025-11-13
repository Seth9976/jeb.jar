package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.cuda.ICudaCubinEntry;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.FlagsFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Av implements ICudaCubinEntry {
   public static final FlagsFormatter pC = FlagsFormatter.Builder.create()
      .add(1, "AddressSize64")
      .add(2, "HasDebugInfo")
      .add(4, "HasDebugInfo")
      .add(16, "HostLinux")
      .add(32, "HostMac")
      .add(64, "HostWindows")
      .add(8192, "CompressionLZ4")
      .add(32768, "CompressionZstd")
      .build();
   @SerId(1)
   private int A;
   @SerId(2)
   private int kS;
   @SerId(3)
   private int wS;
   @SerId(4)
   private int UT;
   @SerId(5)
   private int E;
   @SerId(6)
   private byte[] sY;

   public Av(int var1, int var2, int var3, int var4, int var5, byte[] var6) {
      Assert.a(var6 != null);
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = var5;
      this.sY = var6;
   }

   @Override
   public int getKind() {
      return this.A;
   }

   @Override
   public int getVersionMajor() {
      return this.kS;
   }

   @Override
   public int getVersionMinor() {
      return this.wS;
   }

   @Override
   public int getCudaArch() {
      return this.UT;
   }

   @Override
   public int getFlags() {
      return this.E;
   }

   @Override
   public byte[] getData() {
      return this.sY;
   }

   @Override
   public String toString() {
      return this.pC(false);
   }

   public String pC(boolean var1) {
      String var2 = "kind:%s, flags:%s, version:%d.%d, architecture:sm_%d, data:0x%X bytes";
      if (var1) {
         var2 = S.L("kind:%s, flags:%s, version:%d.%d, architecture:sm_%d, data:0x%X bytes");
      }

      return Strings.ff(var2, pC(this.A), A(this.E), this.kS, this.wS, this.UT, this.sY.length);
   }

   public static String pC(int var0) {
      return switch (var0) {
         case 1 -> "PTX source";
         case 2 -> "SASS code";
         default -> "unknown (" + var0 + ")";
      };
   }

   public static String A(int var0) {
      return pC.format(var0);
   }
}
