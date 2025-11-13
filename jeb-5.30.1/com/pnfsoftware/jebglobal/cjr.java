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
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class cjr implements IEEmulatorHooks {
   private static final ILogger oW = GlobalLog.getLogger(cjr.class);
   cjo q;
   EEmulator RF;
   IEGlobalContext xK;
   ITypeManager Dw;
   IVirtualMemory Uv;
   private RoutineIOHandler gO;
   private static final int nf = 1;
   private Map gP = new HashMap();

   public cjr(cjo var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.RF = var1.za();
         this.xK = this.RF.getGlobalContext();
         this.Dw = this.xK.getNativeContext().getTypeManager();
         this.Uv = this.RF.getVirtualMemory();
         this.gO = new RoutineIOHandler(this.RF);
      }
   }

   @Override
   public Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
      Assert.a(var1 == this.RF);
      ICallingConvention var4;
      if (var3 != null && var3.getPrototype() != null) {
         var4 = var3.getPrototype().getCallingConvention();
      } else {
         var4 = var1.getGlobalContext().getNativeContext().getTypeManager().getCallingConventionManager().getDefaultConvention();
      }

      this.gO.reset(var4);
      boolean var5;
      switch (cvm.xK(var2)) {
         case -2139993079:
            var5 = this.gO();
            break;
         case -1920169007:
            var5 = this.nf();
            break;
         case -1239289342:
            var5 = this.RF();
            break;
         case -174524777:
            var5 = this.Uv();
            break;
         case 25146812:
            var5 = this.Dw();
            break;
         case 500988114:
            var5 = this.xK();
            break;
         case 1702617509:
            var5 = this.q();
            break;
         case 1804574538:
            var5 = this.oW();
            break;
         default:
            return null;
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

   private boolean q() {
      this.gO.retInt(0);
      return true;
   }

   private boolean RF() {
      long var1 = this.gO.nextAsPtr();
      long var3 = this.gO.nextAsPtr();
      String var5 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var1, 256);
      Object[] var10000 = new Object[]{var5};
      if (this.q.xK() != null) {
         String var6 = (String)this.q.xK().getSystemProperties().getOrDefault(var5, "");
         byte[] var7 = Strings.encodeUTF8(var6);
         int var8 = var7.length;
         var7 = Arrays.copyOf(var7, var8 + 1);
         if (VirtualMemoryUtil.writeSafe(this.Uv, var3, var7)) {
            this.gO.retInt(var8);
            return true;
         }
      }

      if (cvm.q(20, 7038322, -500197444, var5)) {
         String var11 = Integer.toString(33);
         byte[] var12 = Strings.encodeUTF8(var11);
         int var9 = var12.length;
         var12 = Arrays.copyOf(var12, var9 + 1);
         if (VirtualMemoryUtil.writeSafe(this.Uv, var3, var12)) {
            this.gO.retInt(var9);
            return true;
         }
      } else if (cvm.q(7, 6840690, -1448552125, var5)) {
         this.gO.retInt(0);
         return true;
      }

      return false;
   }

   private boolean xK() {
      long var1 = this.gO.nextAsPtr();

      try {
         this.Uv.writeInt(var1, 1);
      } catch (MemoryException var3) {
         return false;
      }

      this.gP.put(var1, new cjr.eo());
      this.gO.retInt(1);
      return true;
   }

   private boolean Dw() {
      long var1 = this.gO.nextAsPtr();
      if (this.gP.remove(var1) == null) {
         return false;
      } else {
         this.gO.retInt(1);
         return true;
      }
   }

   private boolean Uv() {
      this.gO.retInt(1);
      return true;
   }

   private boolean oW() {
      long var1 = this.gO.nextAsPtr();
      long var3 = this.gO.nextAsPtr();
      this.gO.nextAsPtr();
      long var5 = this.gO.nextAsPtr();
      long var7 = this.gO.nextAsPtr();
      cjr.eo var9 = (cjr.eo)this.gP.get(var1);
      if (var9 == null) {
         return false;
      } else {
         String var10 = null;
         if (var3 == 1L) {
            var10 = cvm.q(new byte[]{2, 42, 35, 86, 49, 43, 36, 71, 36, 107, 107, 48, 28, 112, 80, 93, 93, 90, 66, 71}, 2, 151);
         }

         if (var10 == null) {
            return false;
         } else {
            byte[] var11 = new byte[0];
            if (var5 != 0L) {
               String var12 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var5, 4096);
               var11 = Strings.encodeASCII(var12);
            }

            byte[] var17 = new byte[0];
            if (var7 != 0L) {
               String var13 = VirtualMemoryUtil.readNullTerminatedStringSafe(this.Uv, var7, 4096);
               var17 = Strings.encodeASCII(var13);
            }

            var9.q = true;
            var9.RF = var3;
            var9.xK = var11;
            var9.Dw = var17;

            Cipher var18;
            try {
               var18 = Cipher.getInstance(var10);
               SecretKeySpec var14 = new SecretKeySpec(var11, cvm.q(new byte[]{2, 42, 35}, 2, 35));
               IvParameterSpec var15 = new IvParameterSpec(var17);
               var18.init(2, var14, var15);
            } catch (Exception var16) {
               return false;
            }

            var9.Uv = var18;
            var9.oW = new ByteArrayOutputStream();
            this.gO.retInt(1);
            return true;
         }
      }
   }

   private boolean gO() {
      long var1 = this.gO.nextAsPtr();
      long var3 = this.gO.nextAsPtr();
      long var5 = this.gO.nextAsPtr();
      long var7 = this.gO.nextAsPtr();
      long var9 = this.gO.nextAsInt();
      cjr.eo var11 = (cjr.eo)this.gP.get(var1);
      if (var11 == null) {
         return false;
      } else {
         try {
            byte[] var12 = new byte[(int)var9];
            this.Uv.read(var7, (int)var9, var12, 0, true);
            int var13 = (int)var9 + 65536;
            byte[] var14 = new byte[var13];
            int var15 = var11.Uv.update(var12, 0, (int)var9, var14, 0);
            var11.oW.write(var14, 0, var15);
            this.Uv.writeInt(var5, var15);
            this.Uv.write(var3, var15, var14, 0, true);
         } catch (Exception var16) {
            return false;
         }

         this.gO.retInt(1);
         return true;
      }
   }

   private boolean nf() {
      long var1 = this.gO.nextAsPtr();
      long var3 = this.gO.nextAsPtr();
      long var5 = this.gO.nextAsPtr();
      cjr.eo var7 = (cjr.eo)this.gP.get(var1);
      if (var7 == null) {
         return false;
      } else {
         try {
            int var8 = 65536;
            byte[] var9 = new byte[var8];
            int var10 = var7.Uv.doFinal(var9, 0);
            var7.oW.write(var9, 0, var10);
            this.Uv.writeInt(var5, var10);
            this.Uv.write(var3, var10, var9, 0, true);
         } catch (Exception var11) {
            return false;
         }

         var7.oW.toByteArray();
         this.gO.retInt(1);
         return true;
      }
   }

   static class eo {
      boolean q;
      long RF;
      byte[] xK;
      byte[] Dw;
      Cipher Uv;
      ByteArrayOutputStream oW;
   }
}
