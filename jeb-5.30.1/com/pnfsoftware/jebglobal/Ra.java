package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationApplicator;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.math.MathUtil;
import java.util.Arrays;
import java.util.List;

public class Ra implements IELFArchRelocatorFacility {
   public static Ra.CU q = new wa(0);
   public static Ra.CU RF = new UW(23);
   public static Ra.CU xK = new Yp(2);
   public static Ra.CU Dw = new NM(3);
   public static Ra.CU Uv = new qQ(42);
   public static ELFRelocationApplicator oW = new Lt(27);
   public static ELFRelocationApplicator gO = new Ir(28);
   public static ELFRelocationApplicator nf = new PT(29);
   public static ELFRelocationApplicator gP = new tr(30);
   public static ELFRelocationApplicator za = new SE(10);
   public static ELFRelocationApplicator lm = new iK(96);
   public static ELFRelocationApplicator zz = new Ai(20);
   public static ELFRelocationApplicator JY = new pw(21);
   public static ELFRelocationApplicator HF = new OS(22);
   public static ELFRelocationApplicator LK = new dS(38);
   public static ELFRelocationApplicator io = new Wy(41);
   public static ELFRelocationContext qa = new aY(q, xK, Dw, Uv, oW, gO, nf, gP, za, lm, LK, io);
   public static ELFRelocationContext Hk = new nu(RF, zz);
   public static ELFRelocationContext Me = new TC(q, HF, JY);
   public static ELFRelocationContext PV = new Dr(xK);

   protected static void q(Ra.eo var0, short var1, short var2, short var3, short var4) throws MemoryException {
      Assert.a((var1 & ~var3) == 0 && (var2 & ~var4) == 0);
      short var5 = var0.mem.readShort(var0.P(), var0.endianness);
      var0.mem.writeShort(var0.P(), (short)(var5 & ~var3 | var1 & var3), var0.endianness);
      short var6 = var0.mem.readShort(var0.P() + 2L, var0.endianness);
      var0.mem.writeShort(var0.P() + 2L, (short)(var6 & ~var4 | var2 & var4));
   }

   protected static void q(Ra.eo var0, int var1, boolean var2) throws MemoryException {
      var1 = (var1 & 33554430) >> 1;
      short var3 = (short)(var1 >> 11 & 1023);
      short var4 = (short)(var1 >> 23 & 1);
      short var5 = (short)(var4 << 10 | var3);
      short var6 = (short)(var1 & 2047);
      short var7 = (short)(var2 ? var1 >> 21 & 1 : var4);
      short var8 = (short)(~(var7 ^ var4) & 1);
      short var9 = (short)(var2 ? var1 >> 22 & 1 : var4);
      short var10 = (short)(~(var9 ^ var4) & 1);
      short var11 = (short)(var10 << 13 | var8 << 11 | var6);
      q(var0, var5, var11, (short)2047, (short)12287);
   }

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(qa, Hk, Me, PV);
   }

   abstract static class CU extends ELFRelocationApplicator {
      public CU(int var1) {
         super(var1);
      }

      @Override
      public void apply(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) throws MemoryException {
         Ra.eo var3 = new Ra.eo(var1, var2);
         this.applyInternal(var3);
      }
   }

   static class eo extends ELFStandardRelocOperations {
      eo(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) {
         super(var1, var2);
      }

      public long q() {
         return this.se.getType() == 2 && (this.se.getValue() & 1L) != 0L ? 1L : 0L;
      }

      @Override
      public long A() throws MemoryException {
         if (this.relocEntry.isAddendSet()) {
            return this.relocEntry.getAddend();
         } else if (this.relocEntry.getType() == 27 || this.relocEntry.getType() == 28 || this.relocEntry.getType() == 29) {
            int var11 = this.mem.readInt(this.targetAddress);
            boolean var12 = (var11 & -268435456) == -268435456;
            int var13 = var12 ? (var11 & 16777216) >> 24 : 0;
            return (var11 & 16777215) << 2 | var13 << 1;
         } else if (this.relocEntry.getType() == 42) {
            return this.mem.readInt(this.targetAddress) & 2147483647L;
         } else if (this.relocEntry.getType() == 10 || this.relocEntry.getType() == 30) {
            short var1 = this.mem.readShort(this.targetAddress);
            short var2 = this.mem.readShort(this.targetAddress + 2L);
            short var3 = (short)(var1 & 1023);
            short var4 = (short)(var1 >> 10 & 1);
            short var5 = (short)(var2 & 2047);
            short var6 = (short)(var2 >> 11 & 1);
            short var7 = (short)(~(var6 ^ var4) & 1);
            short var8 = (short)(var2 >> 13 & 1);
            short var9 = (short)(~(var8 ^ var4) & 1);
            int var10 = var4 << 24 | var9 << 23 | var7 << 22 | var3 << 12 | var5 << 1;
            return MathUtil.signExtend32(var10, 25);
         } else {
            return this.relocEntry.getType() == 22 ? 0L : this.mem.readInt(this.targetAddress) & 4294967295L;
         }
      }
   }
}
