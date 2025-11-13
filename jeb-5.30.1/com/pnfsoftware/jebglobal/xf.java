package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationApplicator;
import java.util.Arrays;
import java.util.List;

public class xf implements IELFArchRelocatorFacility {
   public static xf.CU q = new vG(256);
   public static xf.CU RF = new bl(0);
   public static xf.CU xK = new in(1027);
   public static xf.CU Dw = new wl(1032);
   public static xf.CU Uv = new tJ(257);
   public static xf.CU oW = new dZ(258);
   public static xf.CU gO = new Uj(261);
   public static xf.CU nf = new aW(260);
   public static xf.CU gP = new sa(283);
   public static xf.CU za = new CV(282);
   public static xf.CU lm = new oW(275);
   public static xf.CU zz = new Gj(277);
   public static xf.CU JY = new rF(1025);
   public static xf.CU HF = new ZZ(1026);
   public static xf.CU LK = new vE(1024);
   public static ELFRelocationContext io = new GI(q, RF, Uv, oW, gO, nf, gP, za, lm, zz);
   public static ELFRelocationContext qa = new xl(q, RF, Dw, JY, HF, Uv);
   public static ELFRelocationContext Hk = new aH(xK, LK);
   public static ELFRelocationContext Me = new Ss(Uv, xK);

   protected static void q(xf.eo var0, int var1) throws MemoryException {
      int var2 = var0.mem.readInt(var0.P());
      var0.mem.writeInt(var0.P(), var2 | var1);
   }

   protected static void q(xf.eo var0, long var1) throws MemoryException {
      long var3 = var0.mem.readLong(var0.P());
      var0.mem.writeLong(var0.P(), var3 | var1);
   }

   protected static void RF(xf.eo var0, int var1) throws MemoryException {
      int var2 = var0.mem.readInt(var0.P());
      var0.mem.writeInt(var0.P(), var2 + var1);
   }

   protected static void RF(xf.eo var0, long var1) throws MemoryException {
      long var3 = var0.mem.readLong(var0.P());
      var0.mem.writeLong(var0.P(), var3 + var1);
   }

   protected static void q(xf.eo var0) throws MemoryException {
      long var1 = var0.S() + var0.A() - var0.P();
      var1 = (var1 & 268435452L) >> 2;
      q(var0, (int)var1);
   }

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(io, qa, Hk, Me);
   }

   abstract static class CU extends ELFRelocationApplicator {
      public CU(int var1) {
         super(var1);
      }

      @Override
      public void apply(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) throws MemoryException {
         xf.eo var3 = new xf.eo(var1, var2);
         this.applyInternal(var3);
      }
   }

   static class eo extends ELFStandardRelocOperations {
      eo(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) {
         super(var1, var2);
      }

      @Override
      public long A() throws MemoryException {
         return this.relocEntry.isAddendSet() ? this.relocEntry.getAddend() : this.mem.readLong(this.targetAddress);
      }
   }
}
