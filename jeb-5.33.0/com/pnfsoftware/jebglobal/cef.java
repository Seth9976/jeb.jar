package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.RoutineIOHandler;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipEntry;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipFailSafeReader;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayOutputStream;
import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class cef implements IEEmulatorHooks {
   private static final ILogger E = GlobalLog.getLogger(cef.class);
   cdz pC;
   EEmulator A;
   IEGlobalContext kS;
   ITypeManager wS;
   IVirtualMemory UT;
   private RoutineIOHandler sY;
   private Map ys = new HashMap();
   private long ld;
   private long gp = 305250317824L;
   private Map oT = new HashMap();

   public cef(cdz var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = var1.fI();
         this.kS = this.A.getGlobalContext();
         this.wS = this.kS.getNativeContext().getTypeManager();
         this.UT = this.A.getVirtualMemory();
         this.sY = new RoutineIOHandler(this.A);
      }
   }

   @Override
   public Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
      Assert.a(var1 == this.A);
      ICallingConvention var4;
      if (var3 != null && var3.getPrototype() != null) {
         var4 = var3.getPrototype().getCallingConvention();
      } else {
         var4 = var1.getGlobalContext().getNativeContext().getTypeManager().getCallingConventionManager().getDefaultConvention();
      }

      this.sY.reset(var4);
      boolean var5;
      switch (ckx.kS(var2)) {
         case -2139993079:
            var5 = this.E();
            break;
         case -1920169007:
            var5 = this.sY();
            break;
         case -1343862143:
            var5 = this.NS();
            break;
         case -1239289342:
            var5 = this.pC();
            break;
         case -174524777:
            var5 = this.wS();
            break;
         case 25146812:
            var5 = this.kS();
            break;
         case 61722684:
            var5 = this.fI();
            break;
         case 261584447:
            var5 = this.gp();
            break;
         case 500988114:
            var5 = this.A();
            break;
         case 708913953:
            var5 = this.ys();
            break;
         case 1783694772:
            var5 = this.WR();
            break;
         case 1804574538:
            var5 = this.UT();
            break;
         case 1915068186:
            var5 = this.ld();
            break;
         case 2020892849:
            var5 = this.oT();
            break;
         default:
            if (!var2.startsWith("__android_log_")) {
               return null;
            }

            this.sY.retInt(1);
            var5 = true;
      }

      if (!var5) {
         Object[] var6 = new Object[]{var2};
         return null;
      } else {
         Object[] var10000 = new Object[]{var2};
         var1.processStoredReturnAddress(var4.getReturnAddressSlot());
         return true;
      }
   }

   private boolean pC() {
      long var1 = this.sY.nextAsPtr();
      long var3 = this.sY.nextAsPtr();
      String var5 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var1, 256);
      Object[] var10000 = new Object[]{var5};
      if (this.pC.kS() != null) {
         String var6 = (String)this.pC.kS().getSystemProperties().getOrDefault(var5, "");
         byte[] var7 = Strings.encodeUTF8(var6);
         int var8 = var7.length;
         var7 = Arrays.copyOf(var7, var8 + 1);
         if (VirtualMemoryUtil.writeSafe(this.UT, var3, var7)) {
            this.sY.retInt(var8);
            return true;
         }
      }

      if (ckx.pC(20, 7038322, -500197444, var5)) {
         String var11 = Integer.toString(33);
         byte[] var12 = Strings.encodeUTF8(var11);
         int var9 = var12.length;
         var12 = Arrays.copyOf(var12, var9 + 1);
         if (VirtualMemoryUtil.writeSafe(this.UT, var3, var12)) {
            this.sY.retInt(var9);
            return true;
         }
      } else if (ckx.pC(7, 6840690, -1448552125, var5)) {
         this.sY.retInt(0);
         return true;
      }

      return false;
   }

   private boolean A() {
      long var1 = this.sY.nextAsPtr();

      try {
         this.UT.writeInt(var1, 1);
      } catch (MemoryException var3) {
         return false;
      }

      this.ys.put(var1, new cef.Sv());
      this.sY.retInt(1);
      return true;
   }

   private boolean kS() {
      long var1 = this.sY.nextAsPtr();
      if (this.ys.remove(var1) == null) {
         return false;
      } else {
         this.sY.retInt(1);
         return true;
      }
   }

   private boolean wS() {
      this.sY.retInt(1);
      return true;
   }

   private boolean UT() {
      long var1 = this.sY.nextAsPtr();
      long var3 = this.sY.nextAsPtr();
      this.sY.nextAsPtr();
      long var5 = this.sY.nextAsPtr();
      long var7 = this.sY.nextAsPtr();
      cef.Sv var9 = (cef.Sv)this.ys.get(var1);
      if (var9 == null) {
         return false;
      } else {
         String var10 = null;
         if (var3 == 1L) {
            var10 = ckx.pC(new byte[]{75, 4, 22, 124, 108, 1, 1, 108, 127, 27, 8, 16, 102, 101, 49, 5, 0, 13, 7, 9}, 1, 10);
         }

         if (var10 == null) {
            return false;
         } else {
            byte[] var11 = new byte[0];
            if (var5 != 0L) {
               String var12 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var5, 4096);
               var11 = Strings.encodeASCII(var12);
            }

            byte[] var17 = new byte[0];
            if (var7 != 0L) {
               String var13 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var7, 4096);
               var17 = Strings.encodeASCII(var13);
            }

            var9.pC = true;
            var9.A = var3;
            var9.kS = var11;
            var9.wS = var17;

            Cipher var18;
            try {
               var18 = Cipher.getInstance(var10);
               SecretKeySpec var14 = new SecretKeySpec(var11, ckx.pC(new byte[]{69, 4, 22}, 1, 4));
               IvParameterSpec var15 = new IvParameterSpec(var17);
               var18.init(2, var14, var15);
            } catch (Exception var16) {
               return false;
            }

            var9.UT = var18;
            var9.E = new ByteArrayOutputStream();
            this.sY.retInt(1);
            return true;
         }
      }
   }

   private boolean E() {
      long var1 = this.sY.nextAsPtr();
      long var3 = this.sY.nextAsPtr();
      long var5 = this.sY.nextAsPtr();
      long var7 = this.sY.nextAsPtr();
      long var9 = this.sY.nextAsInt();
      cef.Sv var11 = (cef.Sv)this.ys.get(var1);
      if (var11 == null) {
         return false;
      } else {
         try {
            byte[] var12 = new byte[(int)var9];
            this.UT.read(var7, (int)var9, var12, 0, true);
            int var13 = (int)var9 + 65536;
            byte[] var14 = new byte[var13];
            int var15 = var11.UT.update(var12, 0, (int)var9, var14, 0);
            var11.E.write(var14, 0, var15);
            this.UT.writeInt(var5, var15);
            this.UT.write(var3, var15, var14, 0, true);
         } catch (Exception var16) {
            return false;
         }

         this.sY.retInt(1);
         return true;
      }
   }

   private boolean sY() {
      long var1 = this.sY.nextAsPtr();
      long var3 = this.sY.nextAsPtr();
      long var5 = this.sY.nextAsPtr();
      cef.Sv var7 = (cef.Sv)this.ys.get(var1);
      if (var7 == null) {
         return false;
      } else {
         try {
            int var8 = 65536;
            byte[] var9 = new byte[var8];
            int var10 = var7.UT.doFinal(var9, 0);
            var7.E.write(var9, 0, var10);
            this.UT.writeInt(var5, var10);
            this.UT.write(var3, var10, var9, 0, true);
         } catch (Exception var11) {
            return false;
         }

         var7.E.toByteArray();
         this.sY.retInt(1);
         return true;
      }
   }

   private boolean ys() {
      this.sY.nextAsPtr();
      long var1 = this.sY.nextAsPtr();
      this.ld = 4672924418048L | (var1 & 4294967295L) << 4;
      this.sY.retPtr(this.ld);
      return true;
   }

   private boolean ld() {
      long var1 = this.sY.nextAsPtr();
      long var3 = this.sY.nextAsPtr();
      int var5 = this.sY.nextAsInt();
      if (var1 != 0L && var1 == this.ld) {
         String var6 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.UT, var3, 4096);
         Object[] var10000 = new Object[]{var6, var5};
         long var7 = this.gp;
         this.gp += 16L;
         this.oT.put(var7, new cef.Av(var6));
         this.sY.retPtr(var7);
         return true;
      } else {
         return false;
      }
   }

   private boolean gp() {
      long var1 = this.sY.nextAsPtr();
      cef.Av var3 = (cef.Av)this.oT.remove(var1);
      if (var3 != null) {
         this.A.heapFree(var3.wS);
         var3.wS = 0L;
      }

      return true;
   }

   private boolean oT() {
      long var1 = this.sY.nextAsPtr();
      long var3 = this.sY.nextAsPtr();
      long var5 = this.sY.nextAsPtr();
      cef.Av var7 = (cef.Av)this.oT.get(var1);
      if (var7 == null) {
         return false;
      } else {
         try {
            label77: {
               int var19;
               try (
                  SeekableByteChannel var8 = this.pC.ld().getChannel();
                  ZipFailSafeReader var9 = new ZipFailSafeReader(var8);
               ) {
                  String var10 = ckx.pC(new byte[]{-81, 18, 0, 22, 17, 7}, 1, 206) + "/" + var7.pC;
                  ZipEntry var11 = var9.getEntry(var10);
                  if (var11 != null && var11.getCompressionMethod() == 0) {
                     var19 = var11.getOffsetToData();
                     int var13 = var11.getCompressedSize();
                     this.UT.writeLong(var3, var19);
                     this.UT.writeLong(var5, var13);
                     break label77;
                  }

                  this.sY.retInt(-1);
                  var19 = 1;
               }

               return (boolean)var19;
            }
         } catch (Exception var18) {
            this.sY.retInt(-1);
            return true;
         }

         this.sY.retInt(this.pC.mv);
         return true;
      }
   }

   private boolean fI() {
      return this.pC(true);
   }

   private boolean WR() {
      return this.pC(false);
   }

   private boolean pC(boolean var1) {
      long var2 = this.sY.nextAsPtr();
      cef.Av var4 = (cef.Av)this.oT.get(var2);
      if (var4 == null) {
         return false;
      } else {
         long var5 = var4.A;
         if (var5 < 0L) {
            try (
               SeekableByteChannel var7 = this.pC.ld().getChannel();
               ZipFailSafeReader var8 = new ZipFailSafeReader(var7);
            ) {
               String var9 = ckx.pC(new byte[]{92, 18, 0, 22, 17, 7}, 1, 61) + "/" + var4.pC;
               ZipEntry var10 = var8.getEntry(var9);
               var4.A = var10.getFilesize();
            } catch (Exception var15) {
            }

            var5 = var4.A;
         }

         if (var5 < 0L) {
            return false;
         } else {
            if (var1) {
               this.sY.retLong(var5);
            } else {
               this.sY.retSizet(var5);
            }

            return true;
         }
      }
   }

   private boolean NS() {
      long var1 = this.sY.nextAsPtr();
      cef.Av var3 = (cef.Av)this.oT.get(var1);
      if (var3 == null) {
         return false;
      } else {
         long var4 = var3.wS;
         if (var4 == 0L) {
            byte[] var6 = null;

            try (
               SeekableByteChannel var7 = this.pC.ld().getChannel();
               ZipFailSafeReader var8 = new ZipFailSafeReader(var7);
            ) {
               String var9 = ckx.pC(new byte[]{34, 28, 3, 28, 6, 26}, 2, 64) + "/" + var3.pC;
               ZipEntry var10 = var8.getEntry(var9);
               var6 = var8.readData(var10).getUncompressedData();
            } catch (Exception var15) {
            }

            if (var6 == null) {
               return false;
            }

            var4 = this.A.heapAlloc(var6.length);
            if (var4 == 0L) {
               return false;
            }

            if (!VirtualMemoryUtil.writeBytes(this.UT, var4, var6, 0, var6.length)) {
               this.A.heapFree(var4);
               return false;
            }

            var3.wS = var4;
            var3.kS = var6;
         }

         if (var4 == 0L) {
            return false;
         } else {
            this.sY.retPtr(var4);
            return true;
         }
      }
   }

   static class Av {
      String pC;
      int A = -1;
      byte[] kS;
      long wS;

      Av(String var1) {
         this.pC = var1;
      }
   }

   static class Sv {
      boolean pC;
      long A;
      byte[] kS;
      byte[] wS;
      Cipher UT;
      ByteArrayOutputStream E;
   }
}
